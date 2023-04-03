package ru.liga.currencyForecast.csv.readers;

import ru.liga.currencyForecast.csv.entities.Row;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
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

    /**
     * @param filename Файл для чтения
     * @return BufferedReader для чтения файла
     */
    protected BufferedReader getFileReader(String filename) throws IOException {
        InputStream inputStream = getClass().getResourceAsStream(filename);
        if (null == inputStream) {
            throw new IOException("Файл " + filename + " не найден");
        }

        return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
    }
}
