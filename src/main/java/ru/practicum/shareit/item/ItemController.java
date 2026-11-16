package ru.practicum.shareit.item;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;

// Контроллер для обработки HTTP-запросов, связанных с вещами
@RestController
@RequestMapping("/items")
public class ItemController {

    // Сервис для выполнения операций с вещами
    private final ItemService service;

    // Конструктор для внедрения сервиса вещей
    public ItemController(ItemService service) {
        this.service = service;
    }

    // Создаёт новую вещь от имени указанного пользователя
    @PostMapping
    public ItemDto create(
            @RequestHeader("X-Sharer-User-Id") long userId,
            @Valid @RequestBody ItemDto dto) {
        return service.create(userId, dto);
    }

    // Обновляет информацию о вещи по её идентификатору
    @PatchMapping("/{itemId}")
    public ItemDto patch(
            @RequestHeader("X-Sharer-User-Id") long userId,
            @PathVariable long itemId,
            @RequestBody ItemDto dto) {
        return service.update(userId, itemId, dto);
    }

    // Возвращает информацию о вещи по её идентификатору
    @GetMapping("/{itemId}")
    public ItemDto get(@PathVariable long itemId) {
        return service.get(itemId);
    }

    // Возвращает список всех вещей указанного владельца
    @GetMapping
    public List<ItemDto> all(
            @RequestHeader("X-Sharer-User-Id") long userId) {
        return service.getByOwner(userId);
    }

    // Ищет доступные вещи по тексту в названии или описании
    @GetMapping("/search")
    public List<ItemDto> search(@RequestParam String text) {
        return service.search(text);
    }
}
