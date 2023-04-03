package ru.liga.currencyForecast.forecast.application.services;

import ru.liga.currencyForecast.forecast.domain.entities.ExchangeRate;
import ru.liga.currencyForecast.forecast.domain.entities.ExchangeRatesList;
import ru.liga.currencyForecast.forecast.domain.services.RateForecastService;
import ru.liga.currencyForecast.forecast.repositories.RatesRepository;

import java.util.List;

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

    public ExchangeRatesList calculateForFeatureDays(String currency, int days) {
        List<ExchangeRate> rates = this.repository.getLastRates(currency, days).rates();

        return this.domainService.calculateForFeatureDays(new ExchangeRatesList(rates), days);
    }
}
