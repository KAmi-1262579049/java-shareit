package ru.practicum.shareit.item;

import java.util.List;
import ru.practicum.shareit.item.dto.ItemDto;

// Интерфейс для управления вещами
public interface ItemService {

    // Создаёт новую вещь для указанного пользователя
    ItemDto create(long userId, ItemDto dto);

    // Обновляет информацию о вещи
    ItemDto update(long userId, long itemId, ItemDto dto);

    // Возвращает вещь по идентификатору
    ItemDto get(long itemId);

    // Возвращает список вещей указанного владельца
    List<ItemDto> getByOwner(long userId);

    // Ищет доступные вещи по текстовому запросу
    List<ItemDto> search(String text);
}
