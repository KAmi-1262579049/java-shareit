package ru.practicum.shareit.user;

import java.util.List;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.ConflictException;

// Сервис для управления пользователями
@Service
public class UserService {

    // Хранилище пользователей
    private final UserStorage storage;

    // Конструктор для внедрения хранилища
    public UserService(UserStorage storage) {
        this.storage = storage;
    }

    // Проверяет корректность и уникальность электронной почты
    private void validateEmail(String email, Long excludeId) {
        if (email == null || email.isBlank()
                || !email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            throw new IllegalArgumentException("Invalid email");
        }

        for (User u : storage.all()) {
            if (u.getEmail().equalsIgnoreCase(email)
                    && !u.getId().equals(excludeId)) {
                throw new ConflictException("Email already exists");
            }
        }
    }

    // Создаёт нового пользователя
    public UserDto create(UserDto dto) {
        validateEmail(dto.getEmail(), null);
        return UserMapper.toDto(storage.save(UserMapper.toUser(dto)));
    }

    // Обновляет данные существующего пользователя
    public UserDto update(long id, UserDto dto) {
        User u = storage.get(id);

        if (dto.getEmail() != null) {
            validateEmail(dto.getEmail(), id);
            u.setEmail(dto.getEmail());
        }

        if (dto.getName() != null) {
            u.setName(dto.getName());
        }

        return UserMapper.toDto(u);
    }

    // Возвращает пользователя по идентификатору
    public UserDto get(long id) {
        return UserMapper.toDto(storage.get(id));
    }

    // Возвращает список всех пользователей
    public List<UserDto> all() {
        return storage.all().stream()
                .map(UserMapper::toDto)
                .toList();
    }

    // Удаляет пользователя по идентификатору
    public void delete(long id) {
        storage.delete(id);
    }
}
