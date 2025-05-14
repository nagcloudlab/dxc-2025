package com.example.foo_service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RefreshScope
public class FooServiceApplication {

	@Value("${trainer.name}")
	String trainerName;

	@Value("${location}")
	String location;

	@GetMapping("/foo")
	public String foo() {
		return "Hello from Foo Service!" + trainerName + " in " + location;
	}

	public static void main(String[] args) {
		SpringApplication.run(FooServiceApplication.class, args);
	}

}
