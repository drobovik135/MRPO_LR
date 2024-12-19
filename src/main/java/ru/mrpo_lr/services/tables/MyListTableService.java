package ru.mrpo_lr.services.tables;

import ru.mrpo_lr.models.MyListTableResponse;

import java.util.List;

public interface MyListTableService {
    MyListTableResponse createTable(Long userId, String name, String info);
    MyListTableResponse editTable(Long id, String name, String info);
    MyListTableResponse getTable(Long id);
    void deleteTable(Long id);
    List<MyListTableResponse> getAllTables();
    List<MyListTableResponse> getTablesByUserId(Long userId);
}
