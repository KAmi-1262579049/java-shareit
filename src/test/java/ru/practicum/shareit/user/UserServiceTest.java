package ru.practicum.shareit.user;

import org.junit.jupiter.api.Test;
import ru.practicum.shareit.exception.ConflictException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// Тестирование сервиса управления пользователями
class UserServiceTest {

    // Проверяет уникальность email и частичное обновление пользователя
    @Test
    void rejectsDuplicateEmailAndSupportsPatch() {
        // Создание сервиса с хранилищем пользователей
        UserService service = new UserService(new UserStorage());

        // Создание DTO нового пользователя
        UserDto input = new UserDto();

        // Установка имени пользователя
        input.setName("First");

        // Установка электронной почты пользователя
        input.setEmail("test@example.com");

        // Сохранение пользователя
        UserDto created = service.create(input);

        // Проверка ошибки при повторном использовании email
        assertThrows(ConflictException.class, () -> service.create(input));

        // Создание DTO для частичного обновления
        UserDto patch = new UserDto();

        // Установка нового имени пользователя
        patch.setName("Updated");

        // Проверка успешного обновления имени
        assertEquals("Updated", service.update(created.getId(), patch).getName());

        // Проверка сохранения прежнего email
        assertEquals("test@example.com", service.get(created.getId()).getEmail());
    }
}
