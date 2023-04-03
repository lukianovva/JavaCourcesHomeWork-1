package ru.liga.currencyForecast.forecast.repositories;

import ru.liga.currencyForecast.forecast.domain.entities.ExchangeRatesList;
import ru.liga.currencyForecast.forecast.exceptions.CurrencyNotFoundException;

import java.util.Calendar;

/**
 * Репозиторий для работы с курсами обмена валюты
 */
public interface RatesRepository {
    /**
     * Последние курсы валют
     *
     * @param currency Валюта
     * @param count Количество получаемых записей
     * @return Список курсов валют
     * @throws CurrencyNotFoundException Курсы не найдены
     */
    ExchangeRatesList getLastRates(String currency, int count);

    /**
     * Курс валют за сегодняшнюю дату в прошлом году
     *
     * @param currency Валюта
     * @return Список курсов валют
     * @throws CurrencyNotFoundException Курсы не найдены
     */
    ExchangeRatesList getPreviousYearDayRate(String currency, Calendar date);
}
