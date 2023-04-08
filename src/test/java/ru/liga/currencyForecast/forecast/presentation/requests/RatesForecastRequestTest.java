package ru.liga.currencyForecast.forecast.presentation.requests;

import org.junit.jupiter.api.Test;
import ru.liga.currencyForecast.forecast.dictionaries.*;

import static org.assertj.core.api.Assertions.assertThat;

class RatesForecastRequestTest {
    @Test
    public void successValidationTest() {
        String[] args = {"rate", "TRY", "-date", "week", "-alg", "year"};
        RatesForecastRequest request = new RatesForecastRequest(args);

        assertThat(request.getCommand()).isEqualTo(Command.RATE);
        assertThat(request.getCurrency()).isEqualTo(Currency.TRY);
        assertThat(request.getPeriod()).isEqualTo(Period.WEEK);
        assertThat(request.getAlgorithm()).isEqualTo(Algorithm.YEAR);
        assertThat(request.getOutput()).isEqualTo(Output.LIST);
    }
}