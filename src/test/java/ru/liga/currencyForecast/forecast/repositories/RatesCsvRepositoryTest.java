package ru.liga.currencyForecast.forecast.repositories;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.liga.currencyForecast.csv.readers.ReaderImpl;
import ru.liga.currencyForecast.forecast.domain.entities.ExchangeRatesList;
import ru.liga.currencyForecast.forecast.exceptions.CurrencyNotFoundException;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatException;

public class RatesCsvRepositoryTest {
    public RatesCsvRepository repository;

    @Before
    public void setupThis() {
        repository = new RatesCsvRepository(new ReaderImpl());
    }

    @BeforeEach
    public void setupThisEach() {
        repository = new RatesCsvRepository(new ReaderImpl());
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3 })
    public void successReadLastExchanges(int count) {
        assertThat(repository.getLastRates("usd", count).rates().size()).isLessThanOrEqualTo(count);
    }

    @ParameterizedTest
    @MethodSource("successReadProviderFactory")
    public void successReadWithFilter(Calendar date) {
        ExchangeRatesList list = repository.getPreviousYearDayRate("test_usd_last_year", date);

        assertThat(list.rates().size()).isLessThanOrEqualTo(1);

        Calendar exchangeDate = list.rates().get(0).date();
        Calendar neededMaximumDate = (GregorianCalendar) date.clone();
        neededMaximumDate.add(Calendar.YEAR, -1);

        assertThat(
                exchangeDate.equals(neededMaximumDate) || exchangeDate.before(neededMaximumDate)
        ).isEqualTo(true);
    }

    @Test
    public void exceptionWhenFileNotFound() {
        assertThatException().isThrownBy(() -> {
            repository.getLastRates("usd_not_exists", 1);
        }).isExactlyInstanceOf(CurrencyNotFoundException.class);

        assertThatException().isThrownBy(() -> {
            repository.getPreviousYearDayRate(
                    "usd_not_exists",
                    new GregorianCalendar(2022, Calendar.OCTOBER, 29)
            );
        }).isExactlyInstanceOf(CurrencyNotFoundException.class);
    }

    public static Stream<Calendar> successReadProviderFactory() {
        return Stream.of(
                new GregorianCalendar(2023, Calendar.DECEMBER, 18),
                new GregorianCalendar(2023, Calendar.DECEMBER, 12),
                new GregorianCalendar(2022, Calendar.NOVEMBER, 1),
                new GregorianCalendar(2022, Calendar.OCTOBER, 29)
        );
    }
}