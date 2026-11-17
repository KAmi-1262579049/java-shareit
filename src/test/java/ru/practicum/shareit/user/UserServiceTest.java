package ru.practicum.shareit.user;

import org.junit.jupiter.api.Test;
import ru.practicum.shareit.exception.ConflictException;

import static org.junit.jupiter.api.Assertions.*;

// Тестирование сервиса управления пользователями
class UserServiceTest {
    
    // Проверяет уникальность email и частичное обновление пользователя
    @Test
    void rejectsDuplicateEmailAndSupportsPatch() {
        UserService service = new UserService(new UserStorage());
        
        UserDto input = new UserDto();
        
        input.setName("First");
        
        input.setEmail("test@example.com");
        
        UserDto created = service.create(input);
        
        assertThrows(ConflictException.class, () -> service.create(input));
        
        UserDto patch = new UserDto();
        
        patch.setName("Updated");
        
        assertEquals("Updated", service.update(created.getId(), patch).getName());
        
        assertEquals("test@example.com", service.get(created.getId()).getEmail());
    }
}
