package ru.practicum.shareit.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.exception.NotFoundException;

// Хранилище пользователей в памяти приложения
@Repository
public class UserStorage {

    // Коллекция пользователей с их идентификаторами
    private final Map<Long, User> users = new LinkedHashMap<>();

    // Следующий уникальный идентификатор пользователя
    private long nextId = 1;

    // Сохраняет нового пользователя и присваивает ему идентификатор
    public synchronized User save(User u) {
        u.setId(nextId++);
        users.put(u.getId(), u);
        return u;
    }

    // Возвращает пользователя по идентификатору
    public User get(long id) {
        User u = users.get(id);

        if (u == null) {
            throw new NotFoundException("User not found: " + id);
        }

        return u;
    }

    // Возвращает список всех пользователей
    public Collection<User> all() {
        return new ArrayList<>(users.values());
    }

    // Удаляет пользователя по идентификатору
    public void delete(long id) {
        if (users.remove(id) == null) {
            throw new NotFoundException("User not found: " + id);
        }
    }
}
