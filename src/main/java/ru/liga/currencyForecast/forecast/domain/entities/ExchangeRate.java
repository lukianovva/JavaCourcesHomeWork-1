package ru.liga.currencyForecast.forecast.domain.entities;

import java.text.SimpleDateFormat;
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

    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("E dd.MM.y");

        return String.format(
                "%s - %.2f%n",
                toFirstCharUppercase(dateFormat.format(date().getTime())),
                rate()
        );
    }

    /**
     * Строка с заглавной буквы
     *
     * @param string Строка для приведения к формату
     * @return Строка с заглавной буквы
     */
    private String toFirstCharUppercase(String string) {
        String firstCharacter = string.substring(0, 1);
        firstCharacter = firstCharacter.toUpperCase();

        return firstCharacter + string.substring(1);
    }
}
