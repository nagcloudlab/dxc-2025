package com.example.bar_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BarServiceApplication {
	
	@GetMapping("/bar")
	public String bar() {
		return "Hello from Bar Service!";
	}

	public static void main(String[] args) {
		SpringApplication.run(BarServiceApplication.class, args);
	}

}
