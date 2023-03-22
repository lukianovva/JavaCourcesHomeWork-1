package ru.liga.csv.readers;

import org.apache.commons.io.input.ReversedLinesFileReader;
import ru.liga.csv.entities.Row;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Чтение csv файла
 */
public final class Reader extends AbstractReader {
    public List<Row> readLines(String filename, int count) throws IOException {
        List<Row> rows = new ArrayList<>();



        try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream(filename)), StandardCharsets.UTF_8)
        )) {
            String line;
            int i = 0;

            while ((line = reader.readLine()) != null && i <= count) {
                if (i > 0) {
                    rows.add(parseRow(line));
                }
                i++;
            }
        }

        return rows;
    }
}
