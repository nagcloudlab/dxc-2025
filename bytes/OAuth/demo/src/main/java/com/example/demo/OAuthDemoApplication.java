package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class OAuthDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(OAuthDemoApplication.class, args);
    }
}

@RestController
@RequestMapping("/api")
class OAuthController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public endpoint!";
    }

    @GetMapping("/private")
    public String privateEndpoint(@AuthenticationPrincipal OidcUser user) {
        return "Hello, " + user.getFullName() + "! This is a secured endpoint.";
    }
}