package ru.kvshe.homework.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kvshe.homework.models.User;
import ru.kvshe.homework.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    /**
     * Через объект интерфейса UserRepository вызывает метод findAll()
     *
     * @return список всех users
     */
    public List<User> findAll() {
        return repository.findAll();
    }

    /**
     * Через объект интерфейса UserRepository вызывает метод save()
     *
     * @param user объект, который сохраняют в db
     * @return user, которого сохранили в db
     */
    public User save(User user) {
        return repository.save(user);
    }

    /**
     * Через объект интерфейса UserRepository вызывает метод findById()
     *
     * @param id идентификатор, по которому ищет user в db
     * @return Optional с идентификатором id
     */
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    /**
     * Объекту user, присваивают id user из db.
     * Через объект интерфейса UserRepository вызывает метод findAll(), что приводит к перезаписи user с указанным id в db
     *
     * @param id   идентификатор user в db, у которого требуется обновить поля
     * @param user объект с обновлёнными полями, которые будут записаны в db по id
     * @return user, которого сохранили в db
     */
    public User update(Long id, User user) {
        user.setId(id);
        return repository.save(user);
    }

    /**
     * Через объект интерфейса UserRepository вызывает метод deleteById()
     *
     * @param id идентификатор user, по которому он будет удалён
     */
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
