package ru.practicum.shareit.user;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

// Контроллер для обработки HTTP-запросов пользователей
@RestController
@RequestMapping("/users")
public class UserController {

    // Сервис для выполнения операций с пользователями
    private final UserService service;

    // Конструктор для внедрения сервиса пользователей
    public UserController(UserService service) {
        this.service = service;
    }

    // Создаёт нового пользователя на основе переданных данных
    @PostMapping
    public UserDto create(@Valid @RequestBody UserDto dto) {
        return service.create(dto);
    }

    // Обновляет данные пользователя по его идентификатору
    @PatchMapping("/{id}")
    public UserDto patch(@PathVariable long id, @RequestBody UserDto dto) {
        return service.update(id, dto);
    }

    // Возвращает пользователя по его идентификатору
    @GetMapping("/{id}")
    public UserDto get(@PathVariable long id) {
        return service.get(id);
    }

    // Возвращает список всех пользователей
    @GetMapping
    public List<UserDto> all() {
        return service.all();
    }

    // Удаляет пользователя по его идентификатору
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        service.delete(id);
    }
}
