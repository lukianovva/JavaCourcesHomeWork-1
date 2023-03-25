package ru.liga.forecast.presentation;

import ru.liga.csv.contracts.readers.Reader;
import ru.liga.forecast.application.services.ApplicationForecastService;
import ru.liga.forecast.application.services.ApplicationForecastServiceImpl;
import ru.liga.forecast.domain.services.RateForecastService;
import ru.liga.forecast.domain.services.RatesForecastServiceImpl;
import ru.liga.forecast.domain.services.strategies.AverageWeekRateForecastStrategy;
import ru.liga.forecast.domain.services.strategies.ForecastStrategy;
import ru.liga.forecast.presentation.views.ConsoleForecastView;
import ru.liga.forecast.presentation.views.ConsoleView;
import ru.liga.forecast.repositories.RatesCsvRepository;
import ru.liga.forecast.repositories.RatesRepository;

/**
 * Фабрика контроллеров
 */
public class ControllerFactory {

    public static RatesForecastController makeRatesForecastController() {
        ForecastStrategy forecastStrategy = new AverageWeekRateForecastStrategy();
        RateForecastService domainForecastService = new RatesForecastServiceImpl(forecastStrategy);
        Reader csvReader = new ru.liga.csv.readers.Reader();
        RatesRepository repository = new RatesCsvRepository(csvReader);

        ApplicationForecastService applicationForecastService = new ApplicationForecastServiceImpl(
                repository,
                domainForecastService
        );
        ConsoleView forecastView = new ConsoleForecastView();

        return new RatesForecastController(
                applicationForecastService,
                forecastView
        );
    }
}
