package ru.liga.currencyForecast.csv.readers;

import ru.liga.currencyForecast.csv.entities.Row;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Function;

/**
 * Чтение и разбор csv файла
 */
public interface Reader {
    /**
     * Прочитать указанное количество строк из файла и вернуть значения полей
     *
     * @param filename Файл для чтения
     * @param count Количество строк, которые необходимо причитать
     * @return Набор значений csv-файла
     * @throws IOException Ошибка чтения файла
     */
    List<Row> readLines(String filename, int count) throws IOException;

    /**
     * Прочитать файл целиком и вернуть значения полей
     *
     * @param filename Файл для чтения
     * @return Набор значений csv-файла
     * @throws IOException Ошибка чтения файла
     */
    List<Row> readAll(String filename) throws IOException;

    /**
     * Прочитать строки из файла и вернуть значения полей соответсвующих фильтру,
     * но не больше указанного количества
     *
     * @param filename Файл для чтения
     * @param count Максимальное строк, которые необходимо добавить в результат
     * @param callback Метод, при котором данные будут попадать в результирующий набор
     * @return Набор значений csv-файла
     * @throws IOException Ошибка чтения файла
     */
    List<Row> readFiltered(String filename, int count, Function<Row, Boolean> callback) throws IOException;

    /**
     * Прочитать строки из файла и вернуть значения полей соответсвующих фильтру
     *
     * @param filename Файл для чтения
     * @param callback Метод, при котором данные будут попадать в результирующий набор
     * @return Набор значений csv-файла
     * @throws IOException Ошибка чтения файла
     */
    List<Row> readFilteredAll(String filename, Function<Row, Boolean> callback) throws IOException;
}
