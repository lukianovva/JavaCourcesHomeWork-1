package ru.liga.currencyForecast.csv.readers;

import ru.liga.currencyForecast.csv.entities.Row;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Чтение csv файла
 */
public final class ReaderImpl extends AbstractReader {
    public List<Row> readLines(String filename, int count) throws IOException {
        BufferedReader reader = getFileReader(filename);
        List<Row> rows = new ArrayList<>();

        String line;
        int i = 0;
        while ((line = reader.readLine()) != null && i < count) {
            rows.add(parseRow(line));
            i++;
        }

        return rows;
    }

    public List<Row> readAll(String filename) throws IOException {
        BufferedReader reader = getFileReader(filename);
        List<Row> rows = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            rows.add(parseRow(line));
        }

        return rows;
    }

    @Override
    public List<Row> readFiltered(String filename, int count, Function<Row, Boolean> filterCallback) throws IOException {
        BufferedReader reader = getFileReader(filename);
        List<Row> rows = new ArrayList<>();

        String line;
        int i = 0;

        while ((line = reader.readLine()) != null && i < count) {
            Row row = parseRow(line);
            if (filterCallback.apply(row)) {
                rows.add(row);
                i++;
            }
        }

        return rows;
    }

    @Override
    public List<Row> readFilteredAll(String filename, Function<Row, Boolean> filterCallback) throws IOException {
        BufferedReader reader = getFileReader(filename);
        List<Row> rows = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            Row row = parseRow(line);
            if (filterCallback.apply(row)) {
                rows.add(row);
            }
        }

        return rows;
    }
}
