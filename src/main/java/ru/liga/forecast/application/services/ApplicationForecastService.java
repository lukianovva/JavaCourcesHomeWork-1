package ru.liga.forecast.application.services;

import ru.liga.forecast.domain.entities.ExchangeRatesList;
import ru.liga.forecast.exceptions.CurrencyNotFoundException;

/**
 * Сервис прогнозирования курса валют
 */
public interface ApplicationForecastService {
    /**
     *
     * @param currency Международный код валюты
     * @param days Количество дней, на которые нужно сделать прогноз курса
     * @return
     * @throws CurrencyNotFoundException Если курс валют не найден в справочнике
     */
    public ExchangeRatesList calculateForFeatureDays(String currency, int days) throws CurrencyNotFoundException;
}
