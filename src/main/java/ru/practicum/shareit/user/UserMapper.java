package ru.practicum.shareit.user;

// Класс для преобразования пользователей между моделью и DTO
public final class UserMapper {
    
    // Приватный конструктор запрещает создание экземпляров класса
    private UserMapper() {
    }
    
    // Преобразует модель пользователя в DTO
    public static UserDto toDto(User u) {
        UserDto d = new UserDto();
        
        d.setId(u.getId());
        
        d.setName(u.getName());
        
        d.setEmail(u.getEmail());
        
        return d;
    }
    
    // Преобразует DTO пользователя в модель
    public static User toUser(UserDto d) {
        User u = new User();
        
        u.setId(d.getId());
        
        u.setName(d.getName());
        
        u.setEmail(d.getEmail());
        
        return u;
    }
}
