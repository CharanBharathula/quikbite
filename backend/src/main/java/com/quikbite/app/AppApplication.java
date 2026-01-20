package com.quikbite.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import com.quikbite.app.email_notification.dtos.NotificationDto;
import com.quikbite.app.email_notification.service.NotificationService;
import com.quikbite.app.enums.NotificationType;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@EnableAsync
@RequiredArgsConstructor
public class AppApplication {

	private final NotificationService notificationService;
	
	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	// It will get called when the application starts
	/*@Bean
	CommandLineRunner runner(){
		return args -> {
			NotificationDto notificationDto = NotificationDto.builder()
			.recepient("bcharan197@gmail.com")
			.subject("Test Mail subject - QuikBite App")
			.body("Its a sample test mail body from QuikBite Team")
			.notificationType(NotificationType.EMAIL)
			.build();

			notificationService.sendEmail(notificationDto);
			
		};
	}*/

}
