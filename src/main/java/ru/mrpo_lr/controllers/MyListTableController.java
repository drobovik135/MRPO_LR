package ru.mrpo_lr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mrpo_lr.models.MyListTableResponse;
import ru.mrpo_lr.services.tables.MyListTableService;
import ru.mrpo_lr.services.tables.MyListTableServiceImpl;

import java.util.List;

@RestController
public class MyListTableController {
    private final MyListTableService myListTableService;


    @Autowired
    public MyListTableController(MyListTableServiceImpl myListTableService){
        this.myListTableService = myListTableService;
    }


    @GetMapping("/tables")
    private ResponseEntity<List<MyListTableResponse>> get_tables(){
        return ResponseEntity.ok(myListTableService.getAllTables());
    }


    @GetMapping("/tables/{id}")
    private ResponseEntity<?> get_table(@PathVariable Long id){
        return ResponseEntity.ok(myListTableService.getTable(id));
    }


    @PutMapping("/tables/{id}")
    private ResponseEntity<?> edit_table(@PathVariable Long id, @RequestBody MyListTableResponse table){
        return ResponseEntity.ok(myListTableService.editTable(id, table.getName(), table.getInfo()));
    }

    @DeleteMapping("/tables/{id}")
    private ResponseEntity<?> delete_table(@PathVariable Long id){
        myListTableService.deleteTable(id);
        return ResponseEntity.ok("Deleted");
    }

    @PostMapping("/users/{id}/table")
    private ResponseEntity<?> create_table(@PathVariable Long id, @RequestBody MyListTableResponse table){
        return ResponseEntity.ok(myListTableService.createTable(id, table.getName(), table.getInfo()));
    }

    @GetMapping("/users/{id}/table")
    private ResponseEntity<?> get_user_table(@PathVariable Long id){
        return ResponseEntity.ok(myListTableService.getTablesByUserId(id));
    }
}
