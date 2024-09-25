package ru.kvshe.homework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kvshe.homework.models.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
