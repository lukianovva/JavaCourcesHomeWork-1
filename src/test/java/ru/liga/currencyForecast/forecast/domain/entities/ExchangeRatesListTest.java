package ru.liga.currencyForecast.forecast.domain.entities;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class ExchangeRatesListTest {

    @Test
    void averageRateTest() {
        ExchangeRatesList list = new ExchangeRatesList(
                new ArrayList<>(List.of(
                        new ExchangeRate(10, new GregorianCalendar(), 100F),
                        new ExchangeRate(10, new GregorianCalendar(), 1000F)
                ))
        );

        assertThat(list.averageRate()).isEqualTo(55F);
    }

    @Test
    void testToString() {
        ExchangeRatesList list = new ExchangeRatesList(
                new ArrayList<>(List.of(
                        new ExchangeRate(10, new GregorianCalendar(2023, Calendar.NOVEMBER, 15), 100F),
                        new ExchangeRate(10, new GregorianCalendar(2023, Calendar.NOVEMBER, 16), 1000F)
                ))
        );

        assertThat(list.toString()).isEqualTo("Ср 15.11.2023 - 100,00\nЧт 16.11.2023 - 1000,00\n");
    }
}