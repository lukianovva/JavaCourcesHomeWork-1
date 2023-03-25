package ru.liga.forecast.domain.services;

import ru.liga.forecast.domain.entities.ExchangeRatesList;

/**
 * Сервис прогнозирования курса валют по предыдущим курсам
 */
public interface RateForecastService {
    /**
     * Рассчитать прогноз на указанное количество дней в будущем от сегодняшнего числа
     *
     * @param rates Список предыдущих курсов валют
     * @param days Количество дней, на которые нужно рачситать курс
     * @return Список спрогнозированных курсов валют
     */
    ExchangeRatesList calculateForFeatureDays(ExchangeRatesList rates, int days);
}