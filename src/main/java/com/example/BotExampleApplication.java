package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.starter.EnableTelegramBots;

import com.example.service.BotService;

@SpringBootApplication
@EnableTelegramBots
@EnableTransactionManagement
@EnableScheduling
public class BotExampleApplication {

	public static void main(String[] args) {
		ApiContextInitializer.init();

		TelegramBotsApi botsApi = new TelegramBotsApi();

		ConfigurableApplicationContext context = SpringApplication.run(BotExampleApplication.class, args);
		BotService service = context.getBean(BotService.class);
		try {
			botsApi.registerBot(service);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
}
