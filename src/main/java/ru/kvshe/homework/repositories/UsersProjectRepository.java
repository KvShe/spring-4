package ru.kvshe.homework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kvshe.homework.models.UsersProject;

import java.util.List;

public interface UsersProjectRepository extends JpaRepository<UsersProject, Long> {
    List<UsersProject> findAllUsersProjectByProjectId(Long projectId);

    List<UsersProject> findAllUsersProjectByUserId(Long userId);

    UsersProject findUsersProjectByProjectIdAndUserId(Long projectId, Long userId);
}
