package ru.liga.currencyForecast.csv.entities;

import java.util.List;

/**
 * Строка csv-файла
 *
 * @param fields Набор полей строки
 */
public record Row(List<String> fields) {
}
