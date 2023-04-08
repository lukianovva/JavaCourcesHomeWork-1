package ru.liga.currencyForecast.forecast.domain.services;

import ru.liga.currencyForecast.forecast.domain.entities.ExchangeRatesList;
import java.util.Calendar;

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

    /**
     * Рассчитать прогноз на указанное количество дней в будущем от сегодняшнего числа
     *
     * @param startDate Дата начала прогноза
     * @param rates Список предыдущих курсов валют
     * @param days Количество дней, на которые нужно рачситать курс
     * @return Список спрогнозированных курсов валют
     */
    ExchangeRatesList calculateForFeatureDaysFromDate(Calendar startDate, ExchangeRatesList rates, int days);
}
