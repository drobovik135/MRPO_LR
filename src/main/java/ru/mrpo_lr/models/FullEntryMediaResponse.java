package ru.mrpo_lr.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class FullEntryMediaResponse {
    private Long id;
    private Integer mediaRate;
    private String mediaReview;
    private String name;
    private String info;
}
