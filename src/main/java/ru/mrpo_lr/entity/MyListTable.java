package ru.mrpo_lr.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table
public class MyListTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private MyUser user;

    @Column(nullable=false)
    private String name;
    private String info;

    @OneToMany(mappedBy = "tableId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EntryMedia> media = new ArrayList<>();
}
