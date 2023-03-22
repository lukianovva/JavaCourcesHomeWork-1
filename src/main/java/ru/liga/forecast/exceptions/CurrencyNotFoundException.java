package ru.liga.forecast.exceptions;

/**
 * Валюта не найдена в справочниках
 */
public class CurrencyNotFoundException extends Exception {
    public CurrencyNotFoundException(String message) {
        super(message);
    }
}
