package ru.liga;

import io.github.cdimascio.dotenv.Dotenv;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.liga.currencyForecast.telegrambot.Bot;

/**
 * Домашнее задание - прогноз курса валют
 */
public class Main {
    public static void main(String[] args)  {
        try {
            Dotenv dotenv = Dotenv.load();

            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new Bot(
                    dotenv.get("BOT_TOKEN")
            ));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}