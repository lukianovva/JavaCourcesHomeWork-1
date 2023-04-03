package ru.liga.currencyForecast.forecast.presentation.requests;

import ru.liga.currencyForecast.forecast.dictionaries.*;
import ru.liga.currencyForecast.forecast.exceptions.*;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Запрос на прогноз валют
 */
public class RatesForecastRequest {
    private static final String COMMAND_ARGUMENT = "command";
    private static final String PERIOD_ARGUMENT = "-date";
    private static final String CURRENCY_ARGUMENT = "currency";
    private static final String ALGORITHM_ARGUMENT = "-alg";
    private static final String OUTPUT_ARGUMENT = "-output";


    private HashMap<String, String> arguments;

    private Command command;
    private Period period;
    private Currency currency;
    private Algorithm algorithm;
    private Output output;

    public RatesForecastRequest(String[] args) {
        parseArguments(args);
        prepareCommand();
        prepareCurrency();
        preparePeriod();
        prepareAlgorithm();
        prepareOutput();
    }

    private void parseArguments(String[] args) {
        arguments = new HashMap<>();

        try {
            arguments.put(COMMAND_ARGUMENT, args[0]);
        } catch (IndexOutOfBoundsException exception) {
            throw new ValidationException("Команда не задана");
        }

        try {
            arguments.put(CURRENCY_ARGUMENT, args[1]);
        } catch (IndexOutOfBoundsException exception) {
            throw new ValidationException("Валюта не задана");
        }

        for (int i = 2; i < args.length; i++) {
            parseNamedArgument(args, i);
        }
    }

    private void parseNamedArgument(String[] args, int argumentIndex) {
        switch (args[argumentIndex]) {
            case PERIOD_ARGUMENT, ALGORITHM_ARGUMENT, OUTPUT_ARGUMENT -> {
                String name = args[argumentIndex];
                String value = "";

                try {
                    value = args[argumentIndex + 1];
                } catch (IndexOutOfBoundsException ignored) {
                }

                arguments.put(name, value);
            }
        }
    }

    /**
     * Валидация запроса
     *
     * @throws ValidationException При неверных параметрах
     */
    private void prepareCommand() {
        try {
            command = Command.valueOf(arguments.get(COMMAND_ARGUMENT).toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new ValidationException(
                    "Команда \"" + arguments.get(COMMAND_ARGUMENT)
                            + "\" не доступна. Возможные значения:"
                            + Arrays.toString(Command.values())
            );
        }
    }

    private void prepareCurrency() {
        try {
            currency = Currency.valueOf(arguments.get(CURRENCY_ARGUMENT).toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new ValidationException(
                    "Валюта \"" + arguments.get(CURRENCY_ARGUMENT)
                            + "\" не доступна. Возможные значения:"
                            + Arrays.toString(Currency.values())
            );
        }
    }

    private void preparePeriod() {
        try {
            period = Period.valueOf(arguments.get(PERIOD_ARGUMENT).toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new ValidationException(
                    "Период \"" + arguments.get(PERIOD_ARGUMENT)
                            + "\" не доступен. Возможные значения:"
                            + Arrays.toString(Period.values())
            );
        } catch (NullPointerException exception) {
            throw new ValidationException("Период не задан");
        }
    }

    private void prepareAlgorithm() {
        try {
            algorithm = Algorithm.valueOf(arguments.get(ALGORITHM_ARGUMENT).toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new ValidationException(
                    "Алгоритм \"" + arguments.get(ALGORITHM_ARGUMENT)
                            + "\" не доступен. Возможные значения:"
                            + Arrays.toString(Algorithm.values())
            );
        } catch (NullPointerException exception) {
            throw new ValidationException("Алгоритм не задан");
        }
    }

    private void prepareOutput() {
        try {
            output = Output.valueOf(arguments.get(OUTPUT_ARGUMENT).toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new ValidationException(
                    "Метод вывода \"" + arguments.get(OUTPUT_ARGUMENT)
                            + "\" не доступен. Возможные значения:"
                            + Arrays.toString(Output.values())
            );
        } catch (NullPointerException exception) {
            output = Output.LIST;
        }
    }

    public Command getCommand() {
        return command;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Period getPeriod() {
        return period;
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public Output getOutput() {
        return output;
    }
}
