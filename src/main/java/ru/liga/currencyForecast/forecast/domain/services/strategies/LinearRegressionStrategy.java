package ru.liga.currencyForecast.forecast.domain.services.strategies;

import edu.princeton.cs.algs4.LinearRegression;
import ru.liga.currencyForecast.forecast.domain.entities.ExchangeRate;
import ru.liga.currencyForecast.forecast.domain.entities.ExchangeRatesList;

import java.util.Comparator;

/**
 * Прогноз валюты по линейной регрессии
 */
public class LinearRegressionStrategy implements ForecastStrategy {
    public Float calculate(ExchangeRatesList rates, int afterLastRateDayNumber) {
        rates.rates().sort(Comparator.comparing(ExchangeRate::date));

        final int ratesCount = rates.rates().size();

        double[] dayNumbers = new double[ratesCount];
        double[] nominalRates = new double[ratesCount];

        double dayForCalculate = ratesCount + afterLastRateDayNumber;

        for (int i = 0; i < ratesCount; i++) {
            dayNumbers[i] = i;
            nominalRates[i] = rates.rates().get(i).nominalRate();
        }

        LinearRegression regression = new LinearRegression(dayNumbers, nominalRates);

        return (float) regression.predict(dayForCalculate);
    }
}
