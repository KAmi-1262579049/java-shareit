package ru.practicum.shareit.request;

import java.time.LocalDateTime;
import lombok.Data;
import ru.practicum.shareit.user.User;

// Класс для хранения информации о запросе вещи
@Data
public class ItemRequest {

    // Уникальный идентификатор запроса
    private Long id;

    // Описание запрашиваемой вещи
    private String description;

    // Пользователь, создавший запрос
    private User requestor;

    // Дата и время создания запроса
    private LocalDateTime created;
}
