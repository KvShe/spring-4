package ru.kvshe.homework.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kvshe.homework.models.User;
import ru.kvshe.homework.services.UserService;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    /**
     * @param model transmits to view all users
     * @return page: users
     */
    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("users", service.findAll());
        return "users";
    }

    /**
     * Возвращает page на которой отображены данные user
     *
     * @param id    user's чьи данные будут отображены на page
     * @param model передаёт to view user's
     * @return page:show
     */
    @GetMapping("/{id}")
    public String findById(@PathVariable int id,
                           Model model) {
        model.addAttribute("user", service.findById(id));
        return "show";
    }

    /**
     * Возвращает page на которой заводят нового user's
     *
     * @param model передаёт to view data user's
     * @return page: new
     */
    @GetMapping("/new")
    public String newUserForm(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    /**
     * Принимает user из page на которой создавали нового user и сохраняет его в db
     *
     * @param user добавленный user
     * @return redirect page: users
     */
    @PostMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        service.save(user);
        return "redirect:/users";
    }

    /**
     * Возвращает page на которой редактируют данные user
     *
     * @param id    user's, который будет отредактирован
     * @param model передаёт to view данные user
     * @return page: edit
     */
    @GetMapping("/{id}/edit")
    public String editUserForm(@PathVariable int id,
                               Model model) {
        model.addAttribute("user", service.findById(id));
        return "edit";
    }

    /**
     * Метод получает из page user's с изменёнными данными. Обновляет данные user методом update()
     *
     * @param user возвращаемый из page
     * @param id   user's с изменёнными данными
     * @return page: users/id
     */
    @PatchMapping("/{id}")
    public String editUser(@ModelAttribute("user") User user,
                           @PathVariable int id) {
        service.update(id, user);
        return "redirect:/users/{id}";
    }

    /**
     * Удаляет user с id вызывая метод delete()
     *
     * @param id user's которого удаляют
     * @return page: users
     */
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        service.deleteById(id);
        return "redirect:/users";
    }
}
