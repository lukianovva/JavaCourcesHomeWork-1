package ru.liga.currencyForecast.forecast.dictionaries;

/**
 * Доступные периоды расчета
 */
public enum Output {
    LIST ("list"),
    GRAPH ("graph");

    private final String title;

    Output(String name) {
        this.title = name;
    }

    @Override
    public String toString() {
        return title;
    }
}
