package ru.practicum.shareit.user;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

// Класс для преобразования пользователей между моделью и DTO
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserMapper {

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
