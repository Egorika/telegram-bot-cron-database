package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SimpleGoogleSheetsApplication {

	public static void main(String[] args) {
		//ConfigurableApplicationContext context = 
		SpringApplication.run(SimpleGoogleSheetsApplication.class, args);
		//BotService service = context.getBean(BotService.class);
	}
}
