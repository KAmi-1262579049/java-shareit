package ru.practicum.shareit.exception;

// Исключение, возникающее, когда запрашиваемый объект не найден
public class NotFoundException extends RuntimeException {

    // Создаёт исключение с указанным сообщением об ошибке
    public NotFoundException(String message) {
        super(message); // Передаёт сообщение конструктору родительского класса
    }
}
