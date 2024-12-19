package ru.mrpo_lr.services.users;

import ru.mrpo_lr.models.MyListTableResponse;
import ru.mrpo_lr.models.MyUserResponse;

import java.util.List;

public interface MyUserService {
    MyUserResponse createUser(String name, String info);
    void deleteUser(Long id);
    MyUserResponse updateUser(Long id, String name, String info);
    MyUserResponse getMyUserById(Long id);
    List<MyUserResponse> getMyUsers();
}
