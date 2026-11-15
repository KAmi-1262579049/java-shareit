
package ru.practicum.shareit.booking;

import java.time.LocalDateTime;
import lombok.Data;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;

// Модель бронирования вещи
@Data
public class Booking {

    // Уникальный идентификатор бронирования
    private Long id;

    // Дата и время начала бронирования
    private LocalDateTime start;

    // Дата и время окончания бронирования
    private LocalDateTime end;

    // Вещь, которую бронирует пользователь
    private Item item;

    // Пользователь, который бронирует вещь
    private User booker;

    // Текущий статус бронирования
    private BookingStatus status;
}
