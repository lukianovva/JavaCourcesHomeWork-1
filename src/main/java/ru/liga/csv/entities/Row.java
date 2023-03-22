package ru.liga.csv.entities;

import java.util.List;

/**
 * Строка csv-файла,
 *
 * @param fields Массив строковых полей строки
 */
public record Row(List<String> fields) {
}
