package ru.mrpo_lr.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Data
@Accessors(chain = true)
public class MyListTableResponse {
    private Long id;
    private String name;
    private String info;
    private List<Long> groupIds = new ArrayList<>();

    public void addGroupId(Long id){
        groupIds.add(id);
    }
}
