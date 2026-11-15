package ru.practicum.shareit.exception;

// Исключение, возникающее при конфликте данных
public class ConflictException extends RuntimeException {

    // Создаёт исключение с сообщением об ошибке
    public ConflictException(String message) {
        super(message); // Передаёт сообщение родительскому классу
    }
}
