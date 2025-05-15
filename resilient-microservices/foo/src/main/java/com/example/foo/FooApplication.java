package com.example.foo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;



@Service
class BarService {
	private final RestTemplate restTemplate;

    public BarService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(
        name = "barService",
        fallbackMethod = "fallbackCallBarService"
    )
    public String callBarService() {
        return restTemplate.getForObject("http://localhost:8080/bar", String.class);
    }

    public String fallbackCallBarService(Exception e) {
        return "Fallback response from BarService: " + e.getMessage();
    }

}


@RestController
@RequestMapping("/foo")
class FooController {

    private final BarService barService;

    public FooController(BarService barService) {
        this.barService = barService;
    }

    @GetMapping
    public String getFoo() {
        return "Foo received: " + barService.callBarService();
    }
}


@SpringBootApplication
public class FooApplication {

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(FooApplication.class, args);
	}

}
