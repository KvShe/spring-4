package ru.kvshe.homework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kvshe.homework.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
