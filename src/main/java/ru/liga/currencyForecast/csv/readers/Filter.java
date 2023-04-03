package ru.liga.currencyForecast.csv.readers;

import ru.liga.currencyForecast.csv.entities.Row;

/**
 * Фильтр значений csv-файла
 */
public interface Filter {

    boolean idNeededRow(Row row);
}
