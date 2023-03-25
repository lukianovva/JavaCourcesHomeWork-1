package ru.liga.forecast.repositories;

import ru.liga.csv.contracts.readers.Reader;
import ru.liga.csv.entities.Row;
import ru.liga.forecast.domain.entities.ExchangeRate;
import ru.liga.forecast.domain.entities.ExchangeRatesList;
import ru.liga.forecast.exceptions.CurrencyNotFoundException;

import java.io.IOException;
import java.util.*;

/**
 * Получение данных о курсах фалют из csv файла
 */
public final class RatesCsvRepository implements RatesRepository {
    /**
     * Инструмент для чтения файла
     */
    private final Reader fileReader;

    public RatesCsvRepository(Reader fileReader) {
        this.fileReader = fileReader;
    }

    public ExchangeRatesList getLastRates(String currency, int count) throws CurrencyNotFoundException {
        String filename = "/" + currency.toLowerCase() + ".csv";
        List<Row> rows;

        try {
            rows = this.fileReader.readLines(filename, count);
        } catch (IOException exception) {
            throw new CurrencyNotFoundException(
                    "Не удается прочитать файл курсов валют \"" + filename + "\": " + exception.getMessage()
            );
        }

        ExchangeRatesList exchangeRates = new ExchangeRatesList (new ArrayList<>());
        for (Row row: rows) {
            exchangeRates.rates().add(buildRate(
                    row.fields().get(0),
                    row.fields().get(1),
                    row.fields().get(2).replace(',', '.')
            ));
        }

        return exchangeRates;
    }

    /**
     * Построить объект курса валюты
     *
     * @param nominal Номинал
     * @param ruFormattedDate Дата в формате РФ
     * @param rate Курс
     * @return Курс валюты
     */
    private ExchangeRate buildRate(String nominal, String ruFormattedDate, String rate)
    {
        String[] dateParts = ruFormattedDate.split("\\.");

        Calendar date = new GregorianCalendar(
                Integer.parseInt(dateParts[2]),
                Integer.parseInt(dateParts[1])-1,
                Integer.parseInt(dateParts[0])
        );

        return new ExchangeRate(Integer.parseInt(nominal), date, Float.parseFloat(rate));
    }
}
