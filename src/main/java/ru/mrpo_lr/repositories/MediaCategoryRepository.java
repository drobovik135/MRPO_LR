package ru.mrpo_lr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mrpo_lr.entity.MediaCategory;

@Repository
public interface MediaCategoryRepository extends JpaRepository<MediaCategory, Long> {
    boolean existsByName(String name);
    MediaCategory findByName(String name);
}
