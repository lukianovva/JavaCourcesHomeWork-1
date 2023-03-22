package ru.liga.forecast.presentation;

import ru.liga.forecast.application.contracts.services.ApplicationForecastService;
import ru.liga.forecast.domain.entities.ExchangeRatesList;
import ru.liga.forecast.exceptions.CurrencyNotFoundException;
import ru.liga.forecast.exceptions.ValidationException;
import ru.liga.forecast.presentation.contracts.ConsoleView;

import java.util.*;

/**
 * Контроллер прогнозов курсов валют
 */
public class RatesForecastController {
    private final ApplicationForecastService applicationForecastService;
    private final ConsoleView forecastView;

    public RatesForecastController(ApplicationForecastService applicationForecastService, ConsoleView forecastView) {
        this.applicationForecastService = applicationForecastService;
        this.forecastView = forecastView;
    }

    /**
     * Спрогнозировать курсы валют и вывести результат в консоль
     *
     * @param args
     */
    public void rate(String[] args) {
        try {
            this.validateRateRequest(args);

            String currency = args[1];
            int days = 1;

            switch (args[2]) {
                case "tomorrow":
                    days = 1;
                    break;
                case "week":
                    days = 7;
                    break;
            }

            Map<String, ExchangeRatesList> data = new HashMap<>();
            data.put("forecasts", this.applicationForecastService.calculateForFeatureDays(currency, days));

            this.forecastView.render(data);
        } catch (CurrencyNotFoundException | ValidationException exception) {
            System.out.println("Ошибка: " + exception.getMessage());
        }
    }

    /**
     * Проверить на верность входные данные
     *
     * @param args Входные данные
     * @throws ValidationException Если проверка не увенчалась успехом
     */
    private void validateRateRequest(String[] args) throws ValidationException {
        if (args.length < 3) {
            throw new ValidationException("Количество аргументов не может быть менее трех");
        }

        String[] dates = {"tomorrow", "week"};
        List<String> availableDates = new ArrayList<>(Arrays.asList(dates));

        if (!availableDates.contains(args[2])) {
            throw new ValidationException("Период прогноза может быть только: " + Arrays.toString(dates));
        }
    }
}
