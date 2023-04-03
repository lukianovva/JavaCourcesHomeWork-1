package ru.liga.currencyForecast.forecast.exceptions;

/**
 * Валюта не найдена в справочниках
 */
public class CurrencyNotFoundException extends RuntimeException {
    public CurrencyNotFoundException(String message) {
        super(message);
    }
}
