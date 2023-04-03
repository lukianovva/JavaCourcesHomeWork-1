package ru.liga.currencyForecast.forecast.presentation;

import ru.liga.currencyForecast.csv.readers.Reader;
import ru.liga.currencyForecast.csv.readers.ReaderImpl;
import ru.liga.currencyForecast.forecast.dictionaries.Algorithm;
import ru.liga.currencyForecast.forecast.domain.services.RatesForecastServiceImpl;
import ru.liga.currencyForecast.forecast.domain.services.strategies.AverageWeekRateForecastStrategy;
import ru.liga.currencyForecast.forecast.application.services.ApplicationForecastService;
import ru.liga.currencyForecast.forecast.application.services.ApplicationForecastServiceImpl;
import ru.liga.currencyForecast.forecast.domain.services.RateForecastService;
import ru.liga.currencyForecast.forecast.domain.services.strategies.FirstRateForecastStrategy;
import ru.liga.currencyForecast.forecast.domain.services.strategies.ForecastStrategy;
import ru.liga.currencyForecast.forecast.presentation.views.ConsoleForecastView;
import ru.liga.currencyForecast.forecast.presentation.views.ConsoleView;
import ru.liga.currencyForecast.forecast.repositories.RatesCsvRepository;
import ru.liga.currencyForecast.forecast.repositories.RatesRepository;

/**
 * Фабрика контроллеров
 */
public class ControllerFactory {

    public static ForecastController makeForecastController(Algorithm forecastAlgorithm) {
        ForecastStrategy forecastStrategy = null;
        
        switch (forecastAlgorithm) {
            case WEEK -> {
                forecastStrategy = new AverageWeekRateForecastStrategy();
            }
            case LASTYEAR -> {
                forecastStrategy = new FirstRateForecastStrategy();
            }
        }

        RateForecastService domainForecastService = new RatesForecastServiceImpl(forecastStrategy);
        Reader csvReader = new ReaderImpl();
        RatesRepository repository = new RatesCsvRepository(csvReader);

        ApplicationForecastService applicationForecastService = new ApplicationForecastServiceImpl(
                repository,
                domainForecastService
        );
        ConsoleView forecastView = new ConsoleForecastView();

        return new ForecastController(
                applicationForecastService,
                forecastView
        );
    }
}
