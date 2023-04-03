package ru.liga.currencyForecast.forecast.dictionaries;

/**
 * Доступные валюты
 */
public enum Currency {
    USD ("usd"),
    EUR ("eur"),
    TRY ("try");

    private final String title;

    Currency(String name) {
        this.title = name;
    }

    @Override
    public String toString() {
        return title;
    }
}
