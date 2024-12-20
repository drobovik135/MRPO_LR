package ru.mrpo_lr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mrpo_lr.entity.EntryMedia;

@Repository
public interface EntryMediaRepository extends JpaRepository<EntryMedia, Long> {

}
