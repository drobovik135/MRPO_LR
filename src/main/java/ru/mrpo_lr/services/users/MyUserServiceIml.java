package ru.mrpo_lr.services.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.mrpo_lr.entity.MyListTable;
import ru.mrpo_lr.entity.MyUser;
import ru.mrpo_lr.models.MyUserResponse;
import ru.mrpo_lr.repositories.MyUserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyUserServiceIml implements MyUserService {
    private final MyUserRepository myUserRepository;


    private MyUserResponse buildResponse(MyUser user){
        return new MyUserResponse()
                .setId(user.getId())
                .setName(user.getName())
                .setInfo(user.getInfo())
                .setTables(user.getMyListTables().stream().map(MyListTable::getId).collect(Collectors.toList()));
    }


    @Autowired
    public MyUserServiceIml(MyUserRepository myUserRepository) {
        this.myUserRepository = myUserRepository;
    }


    @Override
    public MyUserResponse createUser(String name, String info) {
        if(name == null || name.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name cannot be empty");
        }

        if(myUserRepository.existsByName(name)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Name already exists");
        }

        MyUser user = new MyUser();
        user.setName(name);
        user.setInfo(info);

        MyUser SavedUser = myUserRepository.save(user);

        return buildResponse(SavedUser);
    }


    @Override
    public void deleteUser(Long id) {
        if(!myUserRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User with id %s not found", id));
        }

        myUserRepository.deleteById(id);
    }


    @Override
    public MyUserResponse updateUser(Long id, String name, String info) {
        if(!myUserRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User with id %s not found", id));
        }

        MyUser user = myUserRepository.getReferenceById(id);

        if(myUserRepository.existsByName(name)){
            if(myUserRepository.findByName(name)!=user){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Name already exists");
            }
        }

        user.setName(name);
        user.setInfo(info);

        MyUser SavedUser = myUserRepository.save(user);

        return buildResponse(SavedUser);
    }


    @Override
    public MyUserResponse getMyUserById(Long id){
        if(!myUserRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User with id %s not found", id));
        }

        return buildResponse(myUserRepository.getReferenceById(id));
    }

    @Override
    public List<MyUserResponse> getMyUsers() {
        System.out.println("getMyUsers");
        return myUserRepository.findAll().stream().map(this::buildResponse).collect(Collectors.toList());
    }
}
