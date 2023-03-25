package ru.liga.forecast.presentation.views;

import ru.liga.forecast.domain.entities.ExchangeRate;
import ru.liga.forecast.domain.entities.ExchangeRatesList;

import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * Вывод в консоль информации о прогнозе валют
 */
public class ConsoleForecastView implements ConsoleView {
    public void render(Map data) {
        ExchangeRatesList forecasts = (ExchangeRatesList) data.get("forecasts");

        System.out.print(forecasts);
    }
}
