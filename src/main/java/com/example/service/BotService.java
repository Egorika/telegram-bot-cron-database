package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import com.example.model.Messages;
import com.example.model.User;
import com.example.service.UserService;
import com.example.service.MessagesService;

@Component
public class BotService extends TelegramLongPollingBot {

	@Autowired
	private UserService userService;

	@Autowired
	private MessagesService messagesService;

	@Override
	public String getBotUsername() {
		return "SimpleSpringExampleBot";
	}

	@Override
	public void onUpdateReceived(Update update) {
		Message message = update.getMessage();
		String text = message.getText();
		User user;
		if (this.userService.findByTelegramId(message.getChatId()) == null) {
			user = new User();
			user.setTelegramId(message.getChatId());
			user.setFirstName(message.getFrom().getFirstName());
			user.setLastName(message.getFrom().getLastName());			
		} else {
			user = this.userService.findByTelegramId(message.getChatId());
			if (text.equals("/getMessages")) {
				List<Messages> msg = this.messagesService.findAllById(user.getId());
				String getMsg = "Ваши сообщения (" + user.getFirstName() + "):\n";
				for (Messages all : msg)
					getMsg += all.getText() + "\n";
				sendMsg(message, getMsg);
			} else {
				Messages messages = new Messages();
				messages.setText(text);
				user.addMessage(messages);
			}
		}
		this.userService.save(user);
	}

	@Override
	public String getBotToken() {
		return "[ADD BOT TOKET]";
	}

	public void sendMsg(Message msg, String text) {
		SendMessage s = new SendMessage();
		s.setChatId(msg.getChatId());
		s.setText(text);
		try {
			execute(s);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	@Scheduled(fixedDelay = 60000)
	//@Scheduled(cron = "* /5 * * * * *")
	public void schedule() {
		List<User> users = this.userService.findAll();
		for(User all : users) {
			SendMessage s = new SendMessage();
			s.setChatId(all.getTelegramId());
			s.setText("Тест автосообщений...");
			try {
				execute(s);
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		}
	}
}
