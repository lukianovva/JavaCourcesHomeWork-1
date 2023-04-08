package ru.liga.currencyForecast.forecast.application.services;

import ru.liga.currencyForecast.forecast.dictionaries.Algorithm;
import ru.liga.currencyForecast.forecast.domain.entities.ExchangeRate;
import ru.liga.currencyForecast.forecast.domain.entities.ExchangeRatesList;
import ru.liga.currencyForecast.forecast.domain.services.RateForecastService;
import ru.liga.currencyForecast.forecast.repositories.RatesRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Сервис прогнозирования курса валют
 */
public class ApplicationForecastServiceImpl implements ApplicationForecastService {
    private final RatesRepository repository;
    private final RateForecastService domainService;

    public ApplicationForecastServiceImpl(
            RatesRepository repository,
            RateForecastService domainService
    ) {
        this.repository = repository;
        this.domainService = domainService;
    }

    public ExchangeRatesList calculateForFeatureDays(
            Algorithm forecastAlgorithm,
            String currency,
            int days
    ) {
        switch (forecastAlgorithm) {
            case WEEK -> {
                return calculateByWeekAlgorithm(currency, days);
            }
            case YEAR -> {
                return calculateByYearAlgorithm(currency, days);
            }
            case MIST -> {
                return calculateByMistAlgorithm(currency, days);
            }
            case REGRESSION -> {
                return calculateByRegressionAlgorithm(currency, days);
            }
        }

        throw new RuntimeException("Алгоритм \"" + forecastAlgorithm + "\" не реализован");
    }

    private ExchangeRatesList calculateByYearAlgorithm(String currency, int days) {
        ExchangeRatesList exchangeRatesList = new ExchangeRatesList(
                new ArrayList<>()
        );

        Calendar calculateDate = new GregorianCalendar();
        for (int i = 0; i < days; i++) {
            calculateDate.add(Calendar.DAY_OF_MONTH, 1);

            List<ExchangeRate> rates = repository.getPreviousYearDayRate(
                    currency,
                    (GregorianCalendar) calculateDate.clone()
            ).rates();

            exchangeRatesList.rates().addAll(
                    domainService.calculateForFeatureDaysFromDate(
                            (GregorianCalendar) calculateDate.clone(),
                            new ExchangeRatesList(rates),
                            1
                    ).rates()
            );
        }

        return exchangeRatesList;
    }

    private ExchangeRatesList calculateByWeekAlgorithm(String currency, int days) {
        final int DAYS_IN_WEEK = 7;
        List<ExchangeRate> rates = repository.getLastRates(currency, DAYS_IN_WEEK).rates();

        return domainService.calculateForFeatureDays(new ExchangeRatesList(rates), days);
    }

    private ExchangeRatesList calculateByMistAlgorithm(String currency, int days) {
        final int DAYS_IN_TWENTY_YEAR = 365 * 10 * 2;

        ExchangeRatesList exchangeRatesList = new ExchangeRatesList(
                new ArrayList<>()
        );

        Calendar calculateDate = new GregorianCalendar();

        for (int i = 0; i < days; i++) {
            calculateDate.add(Calendar.DAY_OF_MONTH, 1);

            int randomOffset = ThreadLocalRandom.current().nextInt(1, DAYS_IN_TWENTY_YEAR);

            Calendar randomDate = new GregorianCalendar();
            randomDate.add(Calendar.DAY_OF_MONTH, -randomOffset);

            List<ExchangeRate> rates;
            while ((rates = repository.getPreviousYearDayRate(currency, randomDate).rates()) == null) {
                // Выбираем значения пока не найдем случайное
            }

            exchangeRatesList.rates().addAll(
                    domainService.calculateForFeatureDaysFromDate(
                            (GregorianCalendar) calculateDate.clone(),
                            new ExchangeRatesList(rates),
                            1
                    ).rates()
            );
        }


        return exchangeRatesList;
    }

    private ExchangeRatesList calculateByRegressionAlgorithm(String currency, int days) {
        final int DAYS_IN_MONTH = 30;

        List<ExchangeRate> rates = repository.getLastRates(currency, DAYS_IN_MONTH).rates();

        return domainService.calculateForFeatureDays(new ExchangeRatesList(rates), days);
    }
}
