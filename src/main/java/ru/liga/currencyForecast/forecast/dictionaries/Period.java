package ru.liga.currencyForecast.forecast.dictionaries;

/**
 * Доступные периоды расчета
 */
public enum Period {
    TOMORROW ("tomorrow"),
    WEEK ("week"),
    MONTH ("month");

    private final String title;

    Period(String name) {
        this.title = name;
    }

    @Override
    public String toString() {
        return title;
    }
}
