package ru.liga.forecast.exceptions;

/**
 * Ошибка валидации запроса
 */
public class ValidationException extends Exception {
    public ValidationException(String message) {
        super(message);
    }
}
