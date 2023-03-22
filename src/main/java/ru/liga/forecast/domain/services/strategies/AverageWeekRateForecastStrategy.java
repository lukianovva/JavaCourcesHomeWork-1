package ru.liga.forecast.domain.services.strategies;

import ru.liga.forecast.domain.contracts.services.strategies.ForecastStrategy;
import ru.liga.forecast.domain.entities.ExchangeRate;
import ru.liga.forecast.domain.entities.ExchangeRatesList;

import java.util.Comparator;

/**
 * Прогноз по среднему курс валют за 7 дней торгов
 */
public class AverageWeekRateForecastStrategy implements ForecastStrategy {
    public Float calculate(ExchangeRatesList rates) {
        final Integer TRADE_DAYS = Integer.min(rates.rates().size(), 7);

        Comparator<ExchangeRate> dateComparator = Comparator.comparing(ExchangeRate::date);
        rates.rates().sort(dateComparator);

        ExchangeRatesList ratesForCalculate = new ExchangeRatesList(
                rates.rates().subList(rates.rates().size() - TRADE_DAYS, rates.rates().size())
        );

        return ratesForCalculate.averageRate();
    }
}
