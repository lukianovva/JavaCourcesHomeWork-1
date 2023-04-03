package ru.liga;

import ru.liga.currencyForecast.forecast.dictionaries.Command;
import ru.liga.currencyForecast.forecast.exceptions.CurrencyNotFoundException;
import ru.liga.currencyForecast.forecast.exceptions.ValidationException;
import ru.liga.currencyForecast.forecast.presentation.ControllerFactory;
import ru.liga.currencyForecast.forecast.presentation.ForecastController;
import ru.liga.currencyForecast.forecast.presentation.requests.RatesForecastRequest;

/**
 * Домашнее задание №1 - прогноз курса валют
 */
public class Main {
    public static void main(String[] args)  {
        try {
            RatesForecastRequest request = new RatesForecastRequest(args);
            ForecastController controller = ControllerFactory.makeForecastController(request.getAlgorithm());

            if (request.getCommand() == Command.RATE) {
                controller.rate(request);
            }
        } catch (CurrencyNotFoundException | ValidationException exception) {
            System.out.println("Ошибка: " + exception.getMessage());
        }
    }
}