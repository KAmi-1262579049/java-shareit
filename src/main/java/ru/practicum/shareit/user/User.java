package ru.practicum.shareit.user;

import lombok.Data;

// Модель пользователя сервиса ShareIt
@Data
public class User {

    // Уникальный идентификатор пользователя
    private Long id;

    // Имя пользователя
    private String name;

    // Электронная почта пользователя
    private String email;
}
