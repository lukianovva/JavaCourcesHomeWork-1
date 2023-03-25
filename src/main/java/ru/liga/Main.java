package ru.liga;

import ru.liga.csv.contracts.readers.Reader;
import ru.liga.forecast.application.services.ApplicationForecastService;
import ru.liga.forecast.application.services.ApplicationForecastServiceImpl;
import ru.liga.forecast.domain.services.RateForecastService;
import ru.liga.forecast.domain.services.strategies.ForecastStrategy;
import ru.liga.forecast.domain.services.RatesForecastServiceImpl;
import ru.liga.forecast.domain.services.strategies.AverageWeekRateForecastStrategy;
import ru.liga.forecast.exceptions.CurrencyNotFoundException;
import ru.liga.forecast.exceptions.ValidationException;
import ru.liga.forecast.presentation.ControllerFactory;
import ru.liga.forecast.presentation.RatesForecastController;
import ru.liga.forecast.presentation.requests.RatesForecastConsoleRequest;
import ru.liga.forecast.presentation.views.ConsoleView;
import ru.liga.forecast.presentation.views.ConsoleForecastView;
import ru.liga.forecast.repositories.RatesCsvRepository;
import ru.liga.forecast.repositories.RatesRepository;

/**
 * Домашнее задание №1 - прогноз курса валют
 */
public class Main {
    public static void main(String[] args)  {
        if (args.length == 0) {
            System.out.println("Ошибка: Команда не передана");

            return;
        }

        if (args[0].equals("rate")) {
            try {
                RatesForecastConsoleRequest request = new RatesForecastConsoleRequest(args);
                request.validate();

                RatesForecastController controller = ControllerFactory.makeRatesForecastController();
                controller.rate(request);
            } catch (CurrencyNotFoundException | ValidationException exception) {
                System.out.println("Ошибка: " + exception.getMessage());
            }
        } else {
            System.out.println("Ошибка: Для команды " + args[0] + " нет обработчика");
        }
    }

    private static void rate(String[] args) {

    }
}