package ru.practicum.shareit.item;

import org.junit.jupiter.api.Test;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.UserStorage;
import ru.practicum.shareit.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

// Тестирование сервиса управления вещами
class ItemServiceImplTest {

    @Test
    void createsSearchesAndRestrictsUpdates() {

        // Создаёт хранилище пользователей
        UserStorage users = new UserStorage();

        // Создаёт владельца вещи
        User owner = new User();

        // Устанавливает имя владельца
        owner.setName("Owner");

        // Устанавливает электронную почту владельца
        owner.setEmail("owner@example.com");

        // Сохраняет владельца в хранилище
        users.save(owner);

        // Создаёт второго пользователя
        User other = new User();

        // Устанавливает имя второго пользователя
        other.setName("Other");

        // Устанавливает электронную почту второго пользователя
        other.setEmail("other@example.com");

        // Сохраняет второго пользователя
        users.save(other);

        // Создаёт сервис вещей с хранилищами вещей и пользователей
        ItemService service = new ItemServiceImpl(new ItemStorage(), users);

        // Создаёт DTO новой вещи
        ItemDto input = new ItemDto();

        // Устанавливает название вещи
        input.setName("Drill");

        // Устанавливает описание вещи
        input.setDescription("Electric drill");

        // Устанавливает доступность вещи для аренды
        input.setAvailable(true);

        // Создаёт вещь от имени владельца
        ItemDto created = service.create(owner.getId(), input);

        // Проверяет поиск вещи без учёта регистра
        assertEquals(1, service.search("DRILL").size());

        // Проверяет наличие вещи в списке владельца
        assertEquals(1, service.getByOwner(owner.getId()).size());

        // Проверяет запрет редактирования вещи другим пользователем
        assertThrows(NotFoundException.class,
                () -> service.update(other.getId(), created.getId(), input));

        // Создаёт DTO для частичного обновления вещи
        ItemDto patch = new ItemDto();

        // Устанавливает статус недоступности вещи
        patch.setAvailable(false);

        // Обновляет вещь от имени владельца
        service.update(owner.getId(), created.getId(), patch);

        // Проверяет отсутствие недоступной вещи в результатах поиска
        assertTrue(service.search("drill").isEmpty());
    }
}
