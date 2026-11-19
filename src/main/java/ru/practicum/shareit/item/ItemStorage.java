package ru.practicum.shareit.item;

import java.util.*;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.exception.NotFoundException;

// Хранилище вещей в памяти приложения
@Repository
public class ItemStorage {

    // Коллекция вещей с их идентификаторами
    private final Map<Long, Item> items = new LinkedHashMap<>();

    // Счётчик для генерации уникальных идентификаторов вещей
    private long nextId = 1;

    // Сохраняет новую вещь и присваивает ей уникальный идентификатор
    public synchronized Item save(Item i) {
        i.setId(nextId++);
        items.put(i.getId(), i);
        return i;
    }

    // Возвращает вещь по идентификатору или выбрасывает исключение, если она не найдена
    public Item get(long id) {
        Item i = items.get(id);
        if (i == null) {
            throw new NotFoundException("Item not found: " + id);
        }
        return i;
    }

    // Возвращает список всех сохранённых вещей
    public Collection<Item> all() {
        return new ArrayList<>(items.values());
    }
}
