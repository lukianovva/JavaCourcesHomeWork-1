package ru.liga.csv.contracts.readers;

import ru.liga.csv.entities.Row;

import java.io.IOException;
import java.util.List;

/**
 * Чтение и разбор csv файла
 */
public interface Reader {
    /**
     * Прочитать строки из файла и вернуть значения полей
     *
     * @param filename Файл для чтения
     * @param count    Количество строк, которые необходимо причитать
     * @return Массив значений csv-файла
     * @throws IOException Ошибка чтения файла
     */
    List<Row> readLines(String filename, int count) throws IOException;
}
