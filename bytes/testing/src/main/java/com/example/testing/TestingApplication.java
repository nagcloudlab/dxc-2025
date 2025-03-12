package com.example.testing;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestingApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(
			AuthService authService
	) {
		return (args) -> {
			System.out.println();
			System.out.println(
					authService.authenticate("user", "password")
			);
			System.out.println(
					authService.authenticate("user", "wrong")
			);
		};
	}

}
