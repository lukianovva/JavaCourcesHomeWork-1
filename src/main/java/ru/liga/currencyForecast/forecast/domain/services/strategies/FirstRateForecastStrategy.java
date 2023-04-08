package ru.liga.currencyForecast.forecast.domain.services.strategies;

import ru.liga.currencyForecast.forecast.domain.entities.ExchangeRatesList;

/**
 * Прогноз как первый попавшийся курс в списке
 */
public class FirstRateForecastStrategy implements ForecastStrategy {
    public Float calculate(ExchangeRatesList rates, int afterLastRateDayNumber) {
        return rates.rates().get(0).nominalRate();
    }
}
