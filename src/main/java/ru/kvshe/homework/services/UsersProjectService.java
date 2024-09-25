package ru.kvshe.homework.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import ru.kvshe.homework.exception.NotFoundException;
import ru.kvshe.homework.exception.ReAddingAUserToProjectException;
import ru.kvshe.homework.models.Project;
import ru.kvshe.homework.models.User;
import ru.kvshe.homework.models.UsersProject;
import ru.kvshe.homework.repositories.ProjectRepository;
import ru.kvshe.homework.repositories.UserRepository;
import ru.kvshe.homework.repositories.UsersProjectRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersProjectService {
    private final UsersProjectRepository usersProjectRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    /**
     * Метод, возвращающий список пользователей, связанных с определенным проектом
     *
     * @param projectId идентификатор проекта
     * @return список пользователей, связанных с проектом, чей идентификатор - projectId
     */
    public List<User> getUsersByProjectId(Long projectId) {
        return usersProjectRepository.findAllUsersProjectByProjectId(projectId)
                .stream()
                .map(u -> userRepository.findById(u.getUserId()).orElse(null))
                .toList();
    }

    /**
     * Метод, возвращающий список проектов, связанных с определенным пользователем
     *
     * @param userId идентификатор пользователя
     * @return список проектов, связанных с пользователем, чей идентификатор - userId
     */
    public List<Project> getProjectsByUserId(Long userId) {
        return usersProjectRepository.findAllUsersProjectByUserId(userId)
                .stream()
                .map(e -> projectRepository.findById(e.getProjectId()).orElse(null))
                .toList();
    }

    /**
     * Метод, добавляющий пользователя к проекту.
     * Если пользователь уже есть в проекте - бросает исключение ReAddingAUserToProjectException
     *
     * @param userId    идентификатор пользователя
     * @param projectId идентификатор проекта
     */
    public void addUserToProject(Long userId, Long projectId) {
//        usersProjectRepository.save(new UsersProject(userId, projectId));
        UsersProject usersProject = usersProjectRepository.findUsersProjectByProjectIdAndUserId(projectId, userId);
        if (usersProject != null) {
            throw new ReAddingAUserToProjectException("Этот пользователь уже задействован в проекте");
        }

        usersProject = new UsersProject();

        usersProject.setUserId(userId);
        usersProject.setProjectId(projectId);

        usersProjectRepository.save(usersProject);
    }

    /**
     * Метод, удаляющий пользователя из проекта
     *
     * @param userId    идентификатор пользователя
     * @param projectId идентификатор проекта
     */
    public void removeUserFromProject(Long userId, Long projectId) {
        UsersProject usersProject = usersProjectRepository.findUsersProjectByProjectIdAndUserId(projectId, userId);
        if (usersProject == null) {
            throw new NotFoundException("UsersProject: userId=" + userId + ", projectId=" + projectId + " not found");
        }

        usersProjectRepository.delete(usersProject);
    }
}
