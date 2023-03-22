package ru.liga.forecast.domain.entities;

import java.util.List;

/**
 * Сущность списка курсов валют
 *
 * @param rates Список курсов валют
 */
public record ExchangeRatesList(List<ExchangeRate> rates) {
    /**
     * Рассчитывает средний на единицу номинала курс валюты в списке
     *
     * @return Средний курс валют
     */
    public Float averageRate() {
        Float sum = (float) 0;

        for (ExchangeRate rate : rates) {
            sum += rate.nominalRate();
        }

        return sum / rates.size();
    }
}

