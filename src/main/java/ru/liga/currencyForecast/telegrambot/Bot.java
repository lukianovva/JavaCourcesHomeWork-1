package ru.liga.currencyForecast.telegrambot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.liga.currencyForecast.forecast.dictionaries.Command;
import ru.liga.currencyForecast.forecast.exceptions.CurrencyNotFoundException;
import ru.liga.currencyForecast.forecast.exceptions.ValidationException;
import ru.liga.currencyForecast.forecast.presentation.ControllerFactory;
import ru.liga.currencyForecast.forecast.presentation.ForecastController;
import ru.liga.currencyForecast.forecast.presentation.requests.RatesForecastRequest;

import java.util.List;

public class Bot extends TelegramLongPollingBot {
    final private String botToken;
    final private String botNane = "LukyanovvaCurrencyForecastBot";

    public Bot(String botToken) {
        this.botToken = botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return;
        }

        try {
            //Извлекаем из объекта сообщение пользователя
            Message inMess = update.getMessage();
            //Достаем из inMess id чата пользователя
            String chatId = inMess.getChatId().toString();
            //Получаем текст сообщения пользователя, отправляем в написанный нами обработчик
            String response = parseMessage(inMess.getText());
            //Создаем объект класса SendMessage - наш будущий ответ пользователю
            SendMessage outMess = new SendMessage();

            //Добавляем в наше сообщение id чата а также наш ответ
            outMess.setChatId(chatId);
            outMess.setText(response);

            //Отправка в чат
            execute(outMess);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }

    @Override
    public String getBotUsername() {
        return botNane;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }

    public String parseMessage(String textMsg) {
        String response = "Команда не распознана";
        try{
            RatesForecastRequest request = new RatesForecastRequest(textMsg.split("\\s+"));
            ForecastController controller = ControllerFactory.makeForecastController(request.getAlgorithm());

            if (request.getCommand() == Command.RATE) {
                response = controller.rate(request);
            }
        } catch (CurrencyNotFoundException | ValidationException exception) {
            response = "Ошибка: " + exception.getMessage();
        } catch (RuntimeException exception) {
            response = "Непредвиденная ошибка: " + exception.getMessage();
        }

        return response;
    }
}
