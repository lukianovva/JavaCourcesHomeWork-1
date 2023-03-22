package ru.liga.forecast.domain.entities;

import java.util.Calendar;

/**
 * Курс обмена валюты
 *
 * @param nominal Количество денежных единиц, на покупку которых расчитан курс
 * @param date Дата расчета курса валют
 * @param rate Курс покупки номинала
 */
public record ExchangeRate(Integer nominal, Calendar date, Float rate) {
    /**
     * Расчитывает курс по номиналу
     *
     * @return Курс валюты на единицу номинала
     */
    public Float nominalRate() {
        return rate / nominal;
    }
}
