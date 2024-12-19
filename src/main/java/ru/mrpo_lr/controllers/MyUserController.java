package ru.mrpo_lr.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mrpo_lr.models.MyUserResponse;
import ru.mrpo_lr.services.users.MyUserService;
import ru.mrpo_lr.services.users.MyUserServiceIml;

@RestController
@RequestMapping("/users")
public class MyUserController {
    private final MyUserService myUserService;


    @Autowired
    public MyUserController(MyUserServiceIml myUserService) {
        this.myUserService = myUserService;
    }


    @GetMapping
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(myUserService.getMyUsers());
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(myUserService.getMyUserById(id));
    }


    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody MyUserResponse user) {
        return ResponseEntity.ok(myUserService.createUser(user.getName(), user.getInfo()));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody MyUserResponse user) {
        return ResponseEntity.ok(myUserService.updateUser(id, user.getName(), user.getInfo()));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        myUserService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
