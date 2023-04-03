package ru.liga.currencyForecast.forecast.presentation;

import ru.liga.currencyForecast.forecast.domain.entities.ExchangeRatesList;
import ru.liga.currencyForecast.forecast.application.services.ApplicationForecastService;
import ru.liga.currencyForecast.forecast.exceptions.CurrencyNotFoundException;
import ru.liga.currencyForecast.forecast.presentation.requests.RatesForecastRequest;
import ru.liga.currencyForecast.forecast.presentation.views.ConsoleView;

import java.util.*;

/**
 * Контроллер прогнозов курсов валют
 */
public class ForecastController {
    private final ApplicationForecastService applicationForecastService;
    private final ConsoleView forecastView;

    public ForecastController(ApplicationForecastService applicationForecastService, ConsoleView forecastView) {
        this.applicationForecastService = applicationForecastService;
        this.forecastView = forecastView;
    }

    /**
     * Спрогнозировать курсы валют и вывести результат в консоль
     */
    public void rate(RatesForecastRequest request) {
        Map<String, Object> data = new HashMap<>();
        data.put("forecasts", this.applicationForecastService.calculateForFeatureDays(
                request.getCurrency().name(),
                1)
        );

        this.forecastView.render(data);
    }
}
