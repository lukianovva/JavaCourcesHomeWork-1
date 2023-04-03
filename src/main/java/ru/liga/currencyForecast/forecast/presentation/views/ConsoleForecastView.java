package ru.liga.currencyForecast.forecast.presentation.views;

import ru.liga.currencyForecast.forecast.domain.entities.ExchangeRatesList;

import java.util.Map;
import java.util.Objects;

/**
 * Вывод в консоль информации о прогнозе валют
 */
public class ConsoleForecastView implements ConsoleView {
    public void render(Map<String, Object> data) {
        ExchangeRatesList forecasts = (ExchangeRatesList) data.get("forecasts");

        System.out.print(forecasts);
    }
}
