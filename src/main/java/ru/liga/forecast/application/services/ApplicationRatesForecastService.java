package ru.liga.forecast.application.services;

import ru.liga.forecast.application.contracts.services.ApplicationForecastService;
import ru.liga.forecast.domain.contracts.services.RateForecastService;
import ru.liga.forecast.domain.entities.ExchangeRate;
import ru.liga.forecast.domain.entities.ExchangeRatesList;
import ru.liga.forecast.exceptions.CurrencyNotFoundException;
import ru.liga.forecast.repositories.contracts.RatesRepository;

import java.util.List;

/**
 * Сервис прогнозирования курса валют
 */
public class ApplicationRatesForecastService implements ApplicationForecastService {
    private final RatesRepository repository;
    private final RateForecastService domainService;

    public ApplicationRatesForecastService(
            RatesRepository repository,
            RateForecastService domainService
    ) {
        this.repository = repository;
        this.domainService = domainService;
    }

    public ExchangeRatesList calculateForFeatureDays(String currency, int days) throws CurrencyNotFoundException {
        List<ExchangeRate> rates = this.repository.getLastRates(currency, days).rates();

        return this.domainService.calculateForFeatureDays(new ExchangeRatesList(rates), days);
    }
}
