package ru.mrpo_lr.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.mrpo_lr.entity.MediaCategory;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class MediaResponse {
    private String name;
    private String type;
    private String description;
    private Long categoryId;
}
