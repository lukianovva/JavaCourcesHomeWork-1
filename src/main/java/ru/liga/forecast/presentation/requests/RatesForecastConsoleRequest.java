package ru.liga.forecast.presentation.requests;

import ru.liga.forecast.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Запрос на прогноз валют
 */
public class RatesForecastConsoleRequest {
    private final String[] args;

    public RatesForecastConsoleRequest(String[] args) {
        this.args = args;
    }

    /**
     * Валидация запроса
     *
     * @throws ValidationException При неверных параметрах
     */
    public void validate() throws ValidationException {
        if (args.length < 3) {
            throw new ValidationException("Количество аргументов не может быть менее трех");
        }

        String[] dates = {"tomorrow", "week"};
        List<String> availableDates = new ArrayList<>(Arrays.asList(dates));

        if (!availableDates.contains(args[2])) {
            throw new ValidationException("Период прогноза может быть только: " + Arrays.toString(dates));
        }
    }

    /**
     * Количество дней для расчета
     */
    public int getDays() {
        return switch (args[2]) {
            case "tomorrow" -> 1;
            case "week" -> 7;
            default -> 1;
        };
    }

    /**
     * Валюта для расчета
     */
    public String getCurrency() {
        return args[1];
    }
}
