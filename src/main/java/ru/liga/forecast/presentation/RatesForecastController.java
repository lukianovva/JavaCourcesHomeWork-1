package ru.liga.forecast.presentation;

import ru.liga.forecast.application.services.ApplicationForecastService;
import ru.liga.forecast.domain.entities.ExchangeRatesList;
import ru.liga.forecast.exceptions.CurrencyNotFoundException;
import ru.liga.forecast.presentation.requests.RatesForecastConsoleRequest;
import ru.liga.forecast.presentation.views.ConsoleView;

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
    public void rate(RatesForecastConsoleRequest request) throws CurrencyNotFoundException {
        Map<String, ExchangeRatesList> data = new HashMap<>();
        data.put("forecasts", this.applicationForecastService.calculateForFeatureDays(
                request.getCurrency(),
                request.getDays())
        );

        this.forecastView.render(data);
    }
}
