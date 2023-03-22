package ru.liga.csv.readers;

import ru.liga.csv.contracts.readers.Reader;
import ru.liga.csv.entities.Row;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Родитель для классов чтения csv файла
 */
public abstract class AbstractReader implements Reader {
    /**
     * Разделитель полей
     */
    private final static String SEPARATOR = ";";

    abstract public List<Row> readLines(String filename, int count) throws IOException;

    /**
     * Разбор строки на поля
     *
     * @param line Строка в формате csv
     * @return Набор полей
     */
    protected Row parseRow(String line) {
        Row row = new Row(new ArrayList<>());
        String[] fields = line.split(SEPARATOR);

        for (String field : fields) {
            row.fields().add(field.replaceAll("^\"+", "").replaceAll("\"+$", ""));
        }

        return row;
    }
}
