package ru.liga.currencyForecast.forecast.domain.services.strategies;

import ru.liga.currencyForecast.forecast.domain.entities.ExchangeRate;
import ru.liga.currencyForecast.forecast.domain.entities.ExchangeRatesList;

import java.util.Comparator;

/**
 * Прогноз по среднему курс валют за 7 дней торгов
 */
public class AverageWeekRateForecastStrategy implements ForecastStrategy {
    public Float calculate(ExchangeRatesList rates, int afterLastRateDayNumber) {
        final int DAYS_IN_WEEK = 7;
        final int TRADE_DAYS = Integer.min(rates.rates().size(), DAYS_IN_WEEK);

        rates.rates().sort(Comparator.comparing(ExchangeRate::date));

        ExchangeRatesList ratesForCalculate = new ExchangeRatesList(
                rates.rates().subList(rates.rates().size() - TRADE_DAYS, rates.rates().size())
        );

        return ratesForCalculate.averageRate();
    }
}
