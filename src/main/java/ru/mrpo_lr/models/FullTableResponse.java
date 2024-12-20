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
public class FullTableResponse {
    private Long id;
    private String name;
    private String info;
    private List<FullEntryMediaResponse> medias = new ArrayList<FullEntryMediaResponse>();
}
