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
import ru.liga.currencyForecast.forecast.domain.services.strategies.LinearRegressionStrategy;
import ru.liga.currencyForecast.forecast.repositories.RatesCsvRepository;
import ru.liga.currencyForecast.forecast.repositories.RatesRepository;

/**
 * Фабрика контроллеров
 */
public class ControllerFactory {

    public static ForecastController makeForecastController(Algorithm forecastAlgorithm) {
        RateForecastService domainForecastService = new RatesForecastServiceImpl(
                makeForecastStrategy(forecastAlgorithm)
        );

        Reader csvReader = new ReaderImpl();
        RatesRepository repository = new RatesCsvRepository(csvReader);

        ApplicationForecastService applicationForecastService = new ApplicationForecastServiceImpl(
                repository,
                domainForecastService
        );

        return new ForecastController(applicationForecastService);
    }

    private static ForecastStrategy makeForecastStrategy(Algorithm forecastAlgorithm) {
        switch (forecastAlgorithm) {
            case WEEK -> {
                return new AverageWeekRateForecastStrategy();
            }
            case YEAR, MIST -> {
                return new FirstRateForecastStrategy();
            }
            case REGRESSION -> {
                return new LinearRegressionStrategy();
            }
        }

        throw new RuntimeException("Алгоритм \"" + forecastAlgorithm + "\" не реализован");
    }
}
