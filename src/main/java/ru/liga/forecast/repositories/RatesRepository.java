package ru.liga.forecast.repositories;

import ru.liga.forecast.domain.entities.ExchangeRatesList;
import ru.liga.forecast.exceptions.CurrencyNotFoundException;

import java.util.List;

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
    ExchangeRatesList getLastRates(String currency, int count) throws CurrencyNotFoundException;
}
