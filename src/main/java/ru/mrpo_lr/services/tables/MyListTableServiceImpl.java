package ru.mrpo_lr.services.tables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.mrpo_lr.entity.MyListTable;
import ru.mrpo_lr.entity.MyUser;
import ru.mrpo_lr.models.MyListTableResponse;
import ru.mrpo_lr.repositories.MyListTableRepository;
import ru.mrpo_lr.repositories.MyUserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyListTableServiceImpl implements MyListTableService {
    private final MyListTableRepository myListTableRepository;
    private final MyUserRepository myUserRepository;


    private MyListTableResponse buildResponse(MyListTable myListTable){
        return new MyListTableResponse()
                .setId(myListTable.getId())
                .setName(myListTable.getName())
                .setInfo(myListTable.getInfo());
    }


    @Autowired
    public MyListTableServiceImpl(MyListTableRepository myListTableRepository,
                                  MyUserRepository myUserRepository){
        this.myListTableRepository = myListTableRepository;
        this.myUserRepository = myUserRepository;
    }


    @Override
    public MyListTableResponse createTable(Long userId, String name, String info){
        if(!myUserRepository.existsById(userId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        if(name == null || name.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name cannot be empty");
        }

        MyListTable myListTable = new MyListTable();
        myListTable.setName(name);
        myListTable.setInfo(info);



        MyUser user = myUserRepository.getReferenceById(userId);
        user.addTable(myListTable);
        myListTable.setUser(user);

        myListTableRepository.save(myListTable);
        myUserRepository.save(user);

        System.out.println(user.getMyListTables().size());

        return buildResponse(myListTable);
    }


    @Override
    public MyListTableResponse editTable(Long id, String name, String info){
        if(name == null || name.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name cannot be empty");
        }

        if(!myListTableRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Table with id %s not found", id));
        }

        MyListTable myListTable = myListTableRepository.getReferenceById(id);

        myListTable.setName(name);
        myListTable.setInfo(info);

        return buildResponse(myListTableRepository.save(myListTable));
    }


    @Override
    public MyListTableResponse getTable(Long id){
        if(!myListTableRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Table with id %s not found", id));
        }

        return buildResponse(myListTableRepository.getReferenceById(id));
    }


    @Override
    public void deleteTable(Long id){
        myListTableRepository.deleteById(id);
    }


    @Override
    public List<MyListTableResponse> getAllTables(){
        return myListTableRepository.findAll().stream().map(this::buildResponse).collect(Collectors.toList());
    }

    @Override
    public List<MyListTableResponse> getTablesByUserId(Long userId) {
        if(!myUserRepository.existsById(userId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User with id %s not found", userId));
        }
        MyUser user = myUserRepository.getReferenceById(userId);

        return user.getMyListTables().stream().map(this::buildResponse).collect(Collectors.toList());
    }
}
