package ru.liga.currencyForecast.forecast.domain.entities;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class ExchangeRateTest {

    @Test
    void nominalRate() {
        ExchangeRate rate = new ExchangeRate(10, new GregorianCalendar(), 100F);

        assertThat(rate.nominalRate()).isEqualTo(10);
    }

    @Test
    void testToString() {
        ExchangeRate rate = new ExchangeRate(
                10,
                new GregorianCalendar(2023, Calendar.NOVEMBER, 15),
                100F
        );

        assertThat(rate.toString()).isEqualTo("Ср 15.11.2023 - 100,00\n");
    }
}