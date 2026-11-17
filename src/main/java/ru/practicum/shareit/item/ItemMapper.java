package ru.practicum.shareit.item;

import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

// Класс для преобразования объектов Item и ItemDto
public final class ItemMapper {

    // Приватный конструктор запрещает создание экземпляров класса
    private ItemMapper() {
    }

    // Преобразует объект Item в ItemDto
    public static ItemDto toDto(Item i) {
        ItemDto d = new ItemDto();
        d.setId(i.getId());
        d.setName(i.getName());
        d.setDescription(i.getDescription());
        d.setAvailable(i.getAvailable());
        d.setRequestId(i.getRequest() == null ? null : i.getRequest().getId());
        return d;
    }

    // Преобразует объект ItemDto в Item
    public static Item toItem(ItemDto d) {
        Item i = new Item();
        i.setId(d.getId());
        i.setName(d.getName());
        i.setDescription(d.getDescription());
        i.setAvailable(d.getAvailable());
        return i;
    }
}
