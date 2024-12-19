package ru.mrpo_lr.models;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class MyUserResponse {
    private Long id;
    private String name;
    private String info;
    private List<Long> tables = new ArrayList<>();
}
