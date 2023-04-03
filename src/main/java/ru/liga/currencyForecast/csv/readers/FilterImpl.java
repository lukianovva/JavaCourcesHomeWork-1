package ru.liga.currencyForecast.csv.readers;

import ru.liga.currencyForecast.csv.entities.Row;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Фильтр значений csv-файла
 */
public class FilterImpl implements Filter {

    private final Function<Row, Boolean> callback;

    public FilterImpl(Function<Row, Boolean> callback) {
        this.callback = callback;
    }

    @Override
    public boolean idNeededRow(Row row) {
        return callback.apply(row);
    }
}
