package ru.liga.currencyForecast.forecast.presentation.views;

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
    void render(Map<String, Object> data);
}
