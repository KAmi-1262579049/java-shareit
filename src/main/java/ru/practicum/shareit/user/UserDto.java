package ru.practicum.shareit.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

// DTO для передачи данных пользователя
@Data
public class UserDto {

    // Уникальный идентификатор пользователя
    private Long id;

    // Имя пользователя
    private String name;

    // Электронная почта пользователя
    @NotBlank // Запрещает пустое значение
    @Email // Проверяет корректность формата email
    private String email;
}
