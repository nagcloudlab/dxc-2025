package com.example.notification_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@EnableKafka
public class NotificationServiceApplication {

	@KafkaListener(topics = "transfer_topic", groupId = "notification-group")
	public void listen(String message) {
		System.out.println("Received Message in group notification-group: " + message);
		// send to email
		// send to sms
		// send to push notification
	}

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

}
