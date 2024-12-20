package ru.mrpo_lr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mrpo_lr.entity.Media;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
    boolean existsByName(String name);
}
