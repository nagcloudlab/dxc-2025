package com.example.bar_service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BarServiceApplication {

	@Value("${trainer.name}")
	private String trainerName;

	@Value("${location}")
	String location;

	@GetMapping("/bar")
	public String bar() {
		return "Hello from Bar Service!" + trainerName + " " + location;
	}

	public static void main(String[] args) {
		SpringApplication.run(BarServiceApplication.class, args);
	}

}
