package ru.mrpo_lr.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table
public class EntryMedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer mediaRate;
    private String mediaReview;

    @ManyToOne
    @JoinColumn(name = "entries_id", nullable = false)
    private Media media;

    @ManyToOne
    @JoinColumn(name = "medias_id", nullable = false)
    private MyListTable table;

}
