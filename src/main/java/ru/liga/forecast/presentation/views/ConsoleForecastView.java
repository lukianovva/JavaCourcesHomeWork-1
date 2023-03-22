package ru.liga.forecast.presentation.views;

import ru.liga.forecast.domain.entities.ExchangeRate;
import ru.liga.forecast.domain.entities.ExchangeRatesList;
import ru.liga.forecast.presentation.contracts.ConsoleView;

import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * Вывод в консоль информации о прогнозе валют
 */
public class ConsoleForecastView implements ConsoleView {
    public void render(Map data) {
        ExchangeRatesList forecasts = (ExchangeRatesList) data.get("forecasts");
        SimpleDateFormat dateFormat = new SimpleDateFormat("E d.MM.y");

        for (ExchangeRate forecast : forecasts.rates()) {
            System.out.printf(
                    "%s - %.2f%n",
                    this.toFirstCharUppercase(dateFormat.format(forecast.date().getTime())),
                    forecast.rate()
            );
        }
    }

    private String toFirstCharUppercase(String string) {
        String firstCharacter = string.substring(0, 1);
        firstCharacter = firstCharacter.toUpperCase();

        return firstCharacter + string.substring(1);
    }
}
