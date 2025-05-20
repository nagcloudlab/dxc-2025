package com.example.foo_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FooServiceApplication {

	@GetMapping("/foo")
	public String getFoo() {
		return "Hello from Foo Service!";
	}

	public static void main(String[] args) {
		SpringApplication.run(FooServiceApplication.class, args);
	}

}
