package ru.liga.forecast.domain.services;

import ru.liga.forecast.domain.contracts.services.RateForecastService;
import ru.liga.forecast.domain.entities.ExchangeRate;
import ru.liga.forecast.domain.entities.ExchangeRatesList;
import ru.liga.forecast.domain.contracts.services.strategies.ForecastStrategy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Сервис прогнозирования курса валют по предыдущим курсам
 */
public class RatesForecastService implements RateForecastService {
    private final ForecastStrategy strategy;

    public RatesForecastService(ForecastStrategy strategy) {
        this.strategy = strategy;
    }

    public ExchangeRatesList calculateForFeatureDays(ExchangeRatesList rates, int days) {
        final Integer CALCULATED_NOMINAL = 1;

        ExchangeRatesList calculated = new ExchangeRatesList(new ArrayList<>());
        ExchangeRatesList useRates = new ExchangeRatesList(rates.rates());

        Calendar calculateDate = new GregorianCalendar();

        for (int i = 0; i < days; i++) {
            calculateDate.add(Calendar.DAY_OF_MONTH, 1);

            ExchangeRate rate = new ExchangeRate(
                    CALCULATED_NOMINAL,
                    (GregorianCalendar) calculateDate.clone(),
                    strategy.calculate(useRates)
            );

            calculated.rates().add(rate);
            useRates.rates().add(rate);
        }

        return calculated;
    }

}
