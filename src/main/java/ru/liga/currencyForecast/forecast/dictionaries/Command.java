package ru.liga.currencyForecast.forecast.dictionaries;

/**
 * Команды приложения
 */
public enum Command {
    RATE ("rate");

    private final String title;

    Command(String name) {
        this.title = name;
    }

    @Override
    public String toString() {
        return title;
    }
}
