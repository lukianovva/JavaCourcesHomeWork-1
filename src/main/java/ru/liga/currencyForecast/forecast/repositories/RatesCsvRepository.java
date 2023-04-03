package ru.liga.currencyForecast.forecast.repositories;

import ru.liga.currencyForecast.csv.readers.Reader;
import ru.liga.currencyForecast.csv.entities.Row;
import ru.liga.currencyForecast.forecast.domain.entities.ExchangeRate;
import ru.liga.currencyForecast.forecast.domain.entities.ExchangeRatesList;
import ru.liga.currencyForecast.forecast.exceptions.CurrencyNotFoundException;

import java.io.IOException;
import java.util.*;

/**
 * Получение данных о курсах фалют из csv файла
 */
public final class RatesCsvRepository implements RatesRepository {
    private final static int NOMINAL_INDEX = 0;
    private final static int DATE_INDEX = 1;
    private final static int RATE_INDEX = 2;

    /**
     * Инструмент для чтения файла
     */
    private final Reader fileReader;

    public RatesCsvRepository(Reader fileReader) {
        this.fileReader = fileReader;
    }

    public ExchangeRatesList getLastRates(String currency, int count) {
        String filename = getCsvFilename(currency);
        List<Row> rows = new ArrayList<>();

        try {
            rows = this.fileReader.readLines(filename, count);
        } catch (IOException exception) {
            throwNotFoundException(currency, exception);
        }

        return rowsToRates(rows);
    }

    public ExchangeRatesList getPreviousYearDayRate(String currency, Calendar date) {
        String filename = getCsvFilename(currency);
        List<Row> rows = new ArrayList<>();
        Calendar neededMaximumDate = (GregorianCalendar) date.clone();
        neededMaximumDate.add(Calendar.YEAR, -1);

        try {
            rows = this.fileReader.readFiltered(filename, 1, (row) -> {
                try {
                    Calendar rowDate = parseDate(row.fields().get(DATE_INDEX));

                    if (rowDate.equals(neededMaximumDate) || rowDate.before(neededMaximumDate)) {
                        return true;
                    }
                } catch (RuntimeException exception) {
                    return false;
                }

                return false;
            });
        } catch (IOException exception) {
            throwNotFoundException(currency, exception);
        }

        return rowsToRates(rows);
    }

    private void throwNotFoundException(String currency, Exception exception) {
        throw new CurrencyNotFoundException(
                "Курс валюты \"" + currency + "\" не найден: " + exception.getMessage()
        );
    }

    private String getCsvFilename(String currency) {
        return "/" + currency.toLowerCase() + ".csv";
    }

    private ExchangeRatesList rowsToRates(List<Row> rows) {
        ExchangeRatesList exchangeRates = new ExchangeRatesList (new ArrayList<>());
        for (Row row: rows) {
            exchangeRates.rates().add(buildRate(
                    row.fields().get(NOMINAL_INDEX),
                    row.fields().get(DATE_INDEX),
                    row.fields().get(RATE_INDEX).replace(',', '.')
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
        return new ExchangeRate(
                Integer.parseInt(nominal),
                parseDate(ruFormattedDate),
                Float.parseFloat(rate)
        );
    }

    private Calendar parseDate(String ruFormattedDate) {
        String[] dateParts = ruFormattedDate.split("\\.");

        return new GregorianCalendar(
                Integer.parseInt(dateParts[2]),
                Integer.parseInt(dateParts[1])-1,
                Integer.parseInt(dateParts[0])
        );
    }
}
