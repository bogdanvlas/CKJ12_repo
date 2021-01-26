package lesson39;

import java.io.IOException;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

	public void onUpdateReceived(Update update) {
		if (update.hasMessage() && update.getMessage().hasText()) {
			// получили сообщение от клиента
			Message msg = update.getMessage();
			String text = msg.getText();
			System.out.println(text);

			String chatId = msg.getChatId().toString();

			try {
				text = Weather.getWeather(text);
			} catch (IOException e1) {
				text = "Произошла ошибка!";
				e1.printStackTrace();
			}
			System.out.println(text);
			// подготовить ответ
			SendMessage answer = new SendMessage(chatId, text);
			try {
				// отправить ответ
				execute(answer);
				System.out.println("Answer sent!");
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}

		}
	}

	public String getBotUsername() {
		return "vlasenko_itstep_weather_bot";
	}

	@Override
	public String getBotToken() {
		return "1435994614:AAGcTY-glGHbFKbm4WpBoUVfbPlbn717g68";
	}
}
