package ru.liga.currencyForecast.forecast.presentation;

import ru.liga.currencyForecast.forecast.application.services.ApplicationForecastService;
import ru.liga.currencyForecast.forecast.dictionaries.Period;
import ru.liga.currencyForecast.forecast.domain.entities.ExchangeRatesList;
import ru.liga.currencyForecast.forecast.presentation.requests.RatesForecastRequest;
import ru.liga.currencyForecast.forecast.presentation.views.ChartRatesView;

import java.io.IOException;

/**
 * Контроллер прогнозов курсов валют
 */
public class ForecastController {
    private final ApplicationForecastService applicationForecastService;

    public ForecastController(ApplicationForecastService applicationForecastService) {
        this.applicationForecastService = applicationForecastService;
    }

    /**
     * Спрогнозировать курсы валют
     */
    public String rate(RatesForecastRequest request) {
        ExchangeRatesList exchangeRatesList = this.applicationForecastService.calculateForFeatureDays(
                request.getAlgorithm(),
                request.getCurrency().name(),
                Period.periodDays(request.getPeriod())
        );

        ChartRatesView chart = new ChartRatesView(exchangeRatesList);

        try {
            chart.save("chart.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        switch (request.getOutput()) {
            case LIST -> {
                return exchangeRatesList.toString();
            }
        }

        throw new RuntimeException("Метод вывода " + request.getOutput() + " не реализован");
    }
}
