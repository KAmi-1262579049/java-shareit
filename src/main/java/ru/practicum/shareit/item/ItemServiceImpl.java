
package ru.practicum.shareit.item;

import java.util.List;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.UserStorage;
import ru.practicum.shareit.exception.NotFoundException;

// Сервис для управления вещами
@Service
public class ItemServiceImpl implements ItemService {

    // Хранилище вещей
    private final ItemStorage items;

    // Хранилище пользователей
    private final UserStorage users;

    // Инициализирует сервис с хранилищами вещей и пользователей
    public ItemServiceImpl(ItemStorage items, UserStorage users) {
        this.items = items;
        this.users = users;
    }

    // Создаёт новую вещь и назначает её владельца
    public ItemDto create(long userId, ItemDto dto) {
        if (dto.getName() == null || dto.getName().isBlank()
                || dto.getDescription() == null || dto.getDescription().isBlank()
                || dto.getAvailable() == null) {
            throw new IllegalArgumentException("Invalid item");
        }
        
        Item i = ItemMapper.toItem(dto);
        
        i.setOwner(users.get(userId));
        
        return ItemMapper.toDto(items.save(i));
    }

    // Обновляет данные вещи, если пользователь является её владельцем
    public ItemDto update(long userId, long itemId, ItemDto dto) {
        
        Item i = items.get(itemId);
        
        if (i.getOwner().getId() != userId) {
            throw new NotFoundException("Item is not owned by user");
        }
        
        if (dto.getName() != null) {
            
            if (dto.getName().isBlank()) {
                throw new IllegalArgumentException("Empty name");
            }
            i.setName(dto.getName());
        }
        
        if (dto.getDescription() != null) {
            if (dto.getDescription().isBlank()) {
                throw new IllegalArgumentException("Empty description");
            }
            i.setDescription(dto.getDescription());
        }
        
        if (dto.getAvailable() != null) {
            i.setAvailable(dto.getAvailable());
        }
        
        return ItemMapper.toDto(i);
    }

    // Возвращает информацию о вещи по её идентификатору
    public ItemDto get(long itemId) {
        return ItemMapper.toDto(items.get(itemId));
    }

    // Возвращает список всех вещей указанного владельца
    public List<ItemDto> getByOwner(long userId) {
        users.get(userId);
        
        return items.all().stream()
                .filter(i -> i.getOwner().getId() == userId)
                .map(ItemMapper::toDto)
                .toList();
    }

    // Ищет доступные вещи по названию или описанию
    public List<ItemDto> search(String text) {
        
        if (text == null || text.isBlank()) {
            return List.of();
        }
        
        String q = text.toLowerCase(java.util.Locale.ROOT);
        
        return items.all().stream()
                .filter(i -> Boolean.TRUE.equals(i.getAvailable()))
                .filter(i -> i.getName().toLowerCase(java.util.Locale.ROOT).contains(q)
                        || i.getDescription().toLowerCase(java.util.Locale.ROOT).contains(q))
                .map(ItemMapper::toDto)
                .toList();
    }
}
