package ru.liga.forecast.presentation.views;

import ru.liga.forecast.domain.entities.ExchangeRate;
import ru.liga.forecast.domain.entities.ExchangeRatesList;
import ru.liga.forecast.presentation.contracts.ConsoleView;

import java.util.Map;

/**
 * Вывод в консоль информации о прогнозе валют
 */
public class ConsoleForecastView implements ConsoleView {
    public void render(Map data) {
        ExchangeRatesList forecasts = (ExchangeRatesList) data.get("forecasts");

        for (ExchangeRate forecast : forecasts.rates()) {
            System.out.println(forecast.rate());
        }
    }
}
