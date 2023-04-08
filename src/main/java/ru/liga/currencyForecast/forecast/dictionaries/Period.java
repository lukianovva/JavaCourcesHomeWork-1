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

    /**
     * @param period Период для получения количества дней в нём
     * @return Количество дней в периоде
     */
    public static int periodDays(Period period) {
        return switch (period) {
            case TOMORROW -> 1;
            case WEEK -> 7;
            case MONTH -> 30;
        };
    }
}
