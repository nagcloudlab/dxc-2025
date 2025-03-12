package com.example.auth_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AuthDemoApplication {


	// public endpoint
	@GetMapping("/public")
	public String publicEndpoint() {
		return "This is a public endpoint";
	}

	// private endpoint
	// Role : Member
	@GetMapping("/private")
	public String privateEndpoint() {
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println(user+" trying to access private endpoint");
		return "This is a private endpoint";
	}

	
	public static void main(String[] args) {
		SpringApplication.run(AuthDemoApplication.class, args);
	}

}
