package ru.liga.forecast.presentation.contracts;

import java.util.Map;

/**
 * Консольное представление
 */
public interface ConsoleView {
    /**
     * Вывод данных в консоль
     *
     * @param data Данные для вывода
     */
    void render(Map data);
}
