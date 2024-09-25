package ru.kvshe.homework.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kvshe.homework.models.User;
import ru.kvshe.homework.services.UserService;
import ru.kvshe.homework.utils.API;

@Tag(
        name = "Page controller",
        description = "Возвращает страницы")
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @Operation(
            summary = "Get all users",
            description = "Получить страницу со списком всех пользователей",
            responses = @ApiResponse(description = "Ok", responseCode = "200", content = @Content(schema = @Schema(implementation = User.class))))
    @API.InternalServerError
    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("users", service.findAll());
        return "users";
    }

    @Operation(
            summary = "Get user by id",
            description = "Возвращает страницу с данными пользователя по его идентификатору",
            responses = @ApiResponse(description = "Успешный ответ", responseCode = "200", content = @Content(schema = @Schema(implementation = User.class))))
    @API.NotFoundResponse
    @API.InternalServerError
    @GetMapping("/{id}")
    public String findById(@Parameter(description = "Идентификатор пользователя") @PathVariable Long id,
                           @Parameter(description = "Передаёт данные пользователя на страницу") Model model) {
        model.addAttribute("user", service.findById(id).orElseThrow());
        return "show";
    }

    @Operation(
            summary = "Get page with a form creating a new user",
            description = "Возвращает страницу с формой для создания нового пользователя")
    @API.InternalServerError
    @GetMapping("/new")
    public String newUserForm(@Parameter(description = "Передаёт данные пользователя на страницу") Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @Operation(
            summary = "Save a new user",
            description = "Сохраняет в базе данных нового пользователя с данными, полученные со страницы")
    @API.InternalServerError
    @PostMapping("/new")
    public String newUser(@Parameter(description = "Новый пользователь, полученный со страницы клиента") @ModelAttribute("user") User user) {
        service.save(user);
        return "redirect:/users";
    }

    @Operation(
            summary = "Get page with a form creating a new user",
            description = "Возвращает страницу с формой для редактирования данных пользователя")
    @API.NotFoundResponse
    @API.InternalServerError
    @GetMapping("/{id}/edit")
    public String editUserForm(@Parameter(description = "Идентификатор пользователя") @PathVariable Long id,
                               @Parameter(description = "Передаёт данные пользователя на страницу") Model model) {
        model.addAttribute("user", service.findById(id).orElseThrow());
        return "edit";
    }

    @Operation(
            summary = "Update user",
            description = "Получает со страницы клиента пользователя с обновлёнными данными и сохраняет изменения в базе данных")
    @API.InternalServerError
    @PatchMapping("/{id}")
    public String editUser(@Parameter(description = "Пользователь, переданный со страницы клиента") @ModelAttribute("user") User user,
                           @Parameter(description = "Идентификатор пользователя, которого передали со страницы клиента") @PathVariable Long id) {
        service.update(id, user);
        return "redirect:/users/{id}";
    }

    @Operation(
            summary = "Remove user by id",
            description = "Удаляет пользователя по указанному идентификатору")
    @API.NotFoundResponse
    @API.InternalServerError
    @DeleteMapping("/{id}")
    public String deleteUser(@Parameter(description = "Идентификатор пользователя") @PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/users";
    }
}
