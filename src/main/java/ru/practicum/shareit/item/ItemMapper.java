package ru.practicum.shareit.item;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

// Класс для преобразования вещей между моделью и DTO
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ItemMapper {

    // Преобразует модель вещи в DTO
    public static ItemDto toDto(Item i) {
        ItemDto d = new ItemDto();
        d.setId(i.getId());
        d.setName(i.getName());
        d.setDescription(i.getDescription());
        d.setAvailable(i.getAvailable());
        d.setRequestId(i.getRequest() == null ? null : i.getRequest().getId());
        return d;
    }

    // Преобразует DTO вещи в модель
    public static Item toItem(ItemDto d) {
        Item i = new Item();
        i.setId(d.getId());
        i.setName(d.getName());
        i.setDescription(d.getDescription());
        i.setAvailable(d.getAvailable());
        return i;
    }
}
