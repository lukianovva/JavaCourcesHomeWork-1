package ru.liga.currencyForecast.forecast.exceptions;

/**
 * Ошибка валидации запроса
 */
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
