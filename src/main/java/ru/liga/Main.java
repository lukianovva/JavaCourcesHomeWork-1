package ru.liga;

import ru.liga.csv.contracts.readers.Reader;
import ru.liga.forecast.application.contracts.services.ApplicationForecastService;
import ru.liga.forecast.application.services.ApplicationRatesForecastService;
import ru.liga.forecast.domain.contracts.services.RateForecastService;
import ru.liga.forecast.domain.contracts.services.strategies.ForecastStrategy;
import ru.liga.forecast.domain.services.RatesForecastService;
import ru.liga.forecast.domain.services.strategies.AverageWeekRateForecastStrategy;
import ru.liga.forecast.presentation.RatesForecastController;
import ru.liga.forecast.presentation.contracts.ConsoleView;
import ru.liga.forecast.presentation.views.ConsoleForecastView;
import ru.liga.forecast.repositories.RatesCsvRepository;
import ru.liga.forecast.repositories.contracts.RatesRepository;

/**
 * Домашнее задание №1 - прогноз курса валют
 */
public class Main {
    public static void main(String[] args)  {
        if (args.length == 0) {
            System.out.println("Ошибка: команда не передана");

            return;
        }

        if (args[0].equals("rate")) {
            Main.rate(args);
        } else {
            System.out.println("Ошибка: для команды нет обработчика");
        }
    }

    private static void rate(String[] args) {
        ForecastStrategy forecastStrategy = new AverageWeekRateForecastStrategy();
        RateForecastService domainForecastService = new RatesForecastService(forecastStrategy);
        Reader csvReader = new ru.liga.csv.readers.Reader();
        RatesRepository repository = new RatesCsvRepository(csvReader);

        ApplicationForecastService applicationForecastService = new ApplicationRatesForecastService(
                repository,
                domainForecastService
        );
        ConsoleView forecastView = new ConsoleForecastView();

        RatesForecastController controller = new RatesForecastController(
                applicationForecastService,
                forecastView
        );

        controller.rate(args);
    }
}