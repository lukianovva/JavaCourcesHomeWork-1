package ru.liga.currencyForecast.forecast.domain.services.strategies;

import ru.liga.currencyForecast.forecast.domain.entities.ExchangeRate;
import ru.liga.currencyForecast.forecast.domain.entities.ExchangeRatesList;

import java.util.Comparator;

/**
 * Прогноз по среднему курс валют за 7 дней торгов
 */
public class FirstRateForecastStrategy implements ForecastStrategy {
    public Float calculate(ExchangeRatesList rates) {
        rates.rates().sort(Comparator.comparing(ExchangeRate::date));

        return rates.rates().get(0).rate();
    }
}
