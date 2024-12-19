package ru.mrpo_lr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mrpo_lr.entity.MyUser;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Long> {
    boolean existsByName(String name);
    MyUser findByName(String name);
}
