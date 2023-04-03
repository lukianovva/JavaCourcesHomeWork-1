package ru.liga.currencyForecast.forecast.dictionaries;

/**
 * Доступные периоды расчета
 */
public enum Algorithm {
    WEEK ("week"),
    LASTYEAR("year"),
    MIST ("mist"),
    REGRESSION ("regression");

    private final String title;

    Algorithm(String name) {
        this.title = name;
    }

    @Override
    public String toString() {
        return title;
    }
}
