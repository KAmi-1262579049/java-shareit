package ru.practicum.shareit.item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

// DTO для передачи данных о вещи через API
@Data
public class ItemDto {

    // Уникальный идентификатор вещи
    private Long id;

    // Название вещи, не может быть пустым
    @NotBlank
    private String name;

    // Описание вещи, не может быть пустым
    @NotBlank
    private String description;

    // Статус доступности вещи для аренды, не может быть null
    @NotNull
    private Boolean available;

    // Идентификатор запроса, по которому была добавлена вещь
    private Long requestId;
}
