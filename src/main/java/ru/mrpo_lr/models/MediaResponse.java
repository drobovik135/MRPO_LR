package ru.mrpo_lr.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class MediaResponse {
    private Long id;
    private String name;
    private String info;
    private Long categoryId;
    private List<Long> entriesId = new ArrayList<>();
}
