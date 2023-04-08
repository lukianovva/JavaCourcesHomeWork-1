package ru.liga.currencyForecast.forecast.application.services;

import ru.liga.currencyForecast.forecast.dictionaries.Algorithm;
import ru.liga.currencyForecast.forecast.domain.entities.ExchangeRatesList;

/**
 * Сервис прогнозирования курса валют
 */
public interface ApplicationForecastService {
    /**
     * @param algorithm Алгоритм расчета
     * @param currency Международный код валюты
     * @param days Количество дней, на которые нужно сделать прогноз курса
     * @return Прогноз курсов валют на указанное количество дней
     */
    public ExchangeRatesList calculateForFeatureDays(
            Algorithm algorithm,
            String currency,
            int days
    );
}
