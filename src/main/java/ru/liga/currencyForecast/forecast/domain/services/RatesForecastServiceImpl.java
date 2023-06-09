package ru.liga.currencyForecast.forecast.domain.services;

import ru.liga.currencyForecast.forecast.domain.entities.ExchangeRate;
import ru.liga.currencyForecast.forecast.domain.entities.ExchangeRatesList;
import ru.liga.currencyForecast.forecast.domain.services.strategies.ForecastStrategy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Сервис прогнозирования курса валют по предыдущим курсам
 */
public class RatesForecastServiceImpl implements RateForecastService {
    private final ForecastStrategy strategy;

    public RatesForecastServiceImpl(ForecastStrategy strategy) {
        this.strategy = strategy;
    }

    public ExchangeRatesList calculateForFeatureDays(ExchangeRatesList rates, int days) {
        GregorianCalendar startDate = new GregorianCalendar();
        startDate.add(Calendar.DAY_OF_MONTH, 1);
        return calculateForFeatureDaysFromDate(new GregorianCalendar(), rates, days);
    }

    public ExchangeRatesList calculateForFeatureDaysFromDate(Calendar startDate, ExchangeRatesList rates, int days) {
        final Integer CALCULATED_NOMINAL = 1;

        ExchangeRatesList calculated = new ExchangeRatesList(new ArrayList<>());
        ExchangeRatesList useRates = new ExchangeRatesList(rates.rates());

        Calendar calculateDate = (GregorianCalendar)  startDate.clone();

        for (int i = 0; i < days; i++) {
            ExchangeRate rate = new ExchangeRate(
                    CALCULATED_NOMINAL,
                    (GregorianCalendar) calculateDate.clone(),
                    strategy.calculate(useRates, i+1)
            );

            calculated.rates().add(rate);
            useRates.rates().add(rate);

            calculateDate.add(Calendar.DAY_OF_MONTH, 1);
        }

        return calculated;
    }
}
