package ru.kvshe.homework.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kvshe.homework.models.Project;
import ru.kvshe.homework.models.User;
import ru.kvshe.homework.services.UsersProjectService;
import ru.kvshe.homework.utils.API;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "UserProject", description = "API для работы с пользователями и проектами")
public class UserProjectController {
    private final UsersProjectService usersProjectService;

    @Operation(
            summary = "Get list all users associated with a project",
            description = "Получить список пользователей, связанных с проектом по его идентификатору",
            responses = @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = List.class))))
    @API.NotFoundResponse
    @API.InternalServerError
    @GetMapping("/projects/{id}/users")
    public ResponseEntity<List<User>> getUsersByProjectId(
            @Parameter(description = "Идентификатор проекта") @PathVariable("id") Long projectId) {
        return ResponseEntity.ok(usersProjectService.getUsersByProjectId(projectId));
    }

    @Operation(
            summary = "Get list all projects associated with a user",
            description = "Получить список проектов, связанных с пользователем по его идентификатору"
    )
    @API.InternalServerError
    @GetMapping("/users/{id}/projects")
    public ResponseEntity<List<Project>> getProjectsByUserId(
            @Parameter(description = "Идентификатор пользователя") @PathVariable("id") Long userId) {
        return ResponseEntity.ok(usersProjectService.getProjectsByUserId(userId));
    }

    @Operation(
            summary = "Add a new user to the project",
            description = "Добавление нового пользователя по его идентификатору в проект с идентификатором"
    )
    @API.InternalServerError
    @PostMapping("/projects/{project-id}/users/{user-id}")
    public ResponseEntity<Void> addUserToProject(
            @Parameter(description = "Идентификатор пользователя") @PathVariable("user-id") Long userId,
            @Parameter(description = "Идентификатор проекта") @PathVariable("project-id") Long projectId) {
        usersProjectService.addUserToProject(userId, projectId);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Remove a user from a project",
            description = "Удаление пользователя (user-id) из проекта (project-id)"
    )
    @API.InternalServerError
    @PostMapping("/projects/{project-id}/users/{user-id}/deleted")
    public ResponseEntity<Void> removeUserFromProject(
            @Parameter(description = "Идентификатор пользователя") @PathVariable("user-id") Long userId,
            @Parameter(description = "Идентификатор проекта") @PathVariable("project-id") Long projectId) {
        usersProjectService.removeUserFromProject(userId, projectId);
        return ResponseEntity.ok().build();
    }
}
