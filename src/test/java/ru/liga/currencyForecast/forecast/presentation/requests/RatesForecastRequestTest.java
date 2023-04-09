package ru.liga.currencyForecast.forecast.presentation.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.liga.currencyForecast.forecast.dictionaries.*;
import ru.liga.currencyForecast.forecast.exceptions.ValidationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatException;

class RatesForecastRequestTest {
    @ParameterizedTest
    @CsvSource({
            "TRY,tomorrow,week",
            "USD,week,year",
            "EUR,month,mist",
            "EUR,month,regression",
    })
    public void successValidation(
            String currency,
            String date,
            String algorithm
    ) {
        String[] args = {"rate", currency, "-date", date, "-alg", algorithm};
        RatesForecastRequest request = new RatesForecastRequest(args);

        assertThat(request.getCommand()).isEqualTo(Command.RATE);
        assertThat(request.getCurrency()).isEqualTo(Currency.valueOf(currency.toUpperCase()));
        assertThat(request.getPeriod()).isEqualTo(Period.valueOf(date.toUpperCase()));
        assertThat(request.getAlgorithm()).isEqualTo(Algorithm.valueOf(algorithm.toUpperCase()));
        assertThat(request.getOutput()).isEqualTo(Output.LIST);
    }

    @Test
    public void exceptionWhenWrongCommand() {
        String[] args = {"command", "currency", "-date", "date", "-alg", "algorithm"};

        assertThatException().isThrownBy(() -> {
            new RatesForecastRequest(args);
        }).isExactlyInstanceOf(ValidationException.class).withMessageContaining("Команда \"command\" не доступна");
    }

    @Test
    public void exceptionWhenWrongCurrency() {
        String[] args = {"rate", "RUB", "-date", "week", "-alg", "week"};

        assertThatException().isThrownBy(() -> {
            new RatesForecastRequest(args);
        }).isExactlyInstanceOf(ValidationException.class).withMessageContaining("Валюта \"RUB\" не доступна");
    }

    @Test
    public void exceptionWhenWrongPeriod() {
        String[] args = {"rate", "USD", "-date", "weekly", "-alg", "week"};

        assertThatException().isThrownBy(() -> {
            new RatesForecastRequest(args);
        }).isExactlyInstanceOf(ValidationException.class).withMessageContaining("Период \"weekly\" не доступен");
    }

    @Test
    public void exceptionWhenWrongAlgorithm() {
        String[] args = {"rate", "USD", "-date", "week", "-alg", "true"};

        assertThatException().isThrownBy(() -> {
            new RatesForecastRequest(args);
        }).isExactlyInstanceOf(ValidationException.class).withMessageContaining("Алгоритм \"true\" не доступен");
    }

    @Test
    public void exceptionWhenWrongOutput() {
        String[] args = {"rate", "USD", "-date", "week", "-alg", "week", "-output", "graphic"};

        assertThatException().isThrownBy(() -> {
            new RatesForecastRequest(args);
        }).isExactlyInstanceOf(ValidationException.class).withMessageContaining("Метод вывода \"graphic\" не доступен");
    }
}