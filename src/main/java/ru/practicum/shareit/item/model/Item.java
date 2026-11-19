package ru.practicum.shareit.item.model;

import lombok.Data;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.request.ItemRequest;

// Модель вещи, доступной для аренды
@Data
public class Item {

    // Уникальный идентификатор вещи
    private Long id;

    // Название вещи
    private String name;

    // Описание вещи
    private String description;

    // Статус доступности вещи для аренды
    private Boolean available;

    // Пользователь, которому принадлежит вещь
    private User owner;

    // Запрос, на основании которого добавлена вещь
    private ItemRequest request;
}
