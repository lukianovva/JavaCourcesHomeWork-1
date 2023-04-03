package ru.liga.currencyForecast.forecast.domain.services.strategies;

import ru.liga.currencyForecast.forecast.domain.entities.ExchangeRatesList;

/**
 * Стратегия расчета прогноза курса валют по предыдущим курсам
 */
public interface ForecastStrategy {
    /**
     * Рассчитать прогноз
     *
     * @param rates Список курсов за прошлые даты
     * @return Прогноз курса валюты
     */
    Float calculate(ExchangeRatesList rates);
}
