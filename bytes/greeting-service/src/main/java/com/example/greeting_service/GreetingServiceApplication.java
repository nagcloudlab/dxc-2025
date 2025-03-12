package com.example.greeting_service;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// import jakarta.persistence.Entity;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@NoArgsConstructor
@Data
// @Entity
// @Table(name = "accounts")
class Account {
	// @Id
	@Id
	private String number;
	private double balance;
}

@Repository
interface AccountRepository extends ReactiveCrudRepository<Account, String> {
	@Query("SELECT * FROM accounts ")
	Flux<Account> findAll();
}

@SpringBootApplication
@Controller
@EnableR2dbcRepositories
public class GreetingServiceApplication {

	@Autowired
	private AccountRepository accountRepository;

	@RequestMapping("/greeting")
	@ResponseBody
	public Mono<String> greeting() {
		System.out.println("handling request by thread: " + Thread.currentThread().getName());

		// io-bound operation : e,g reading from file, network call

		// try {
		// 	Thread.sleep(1000);
		// } catch (InterruptedException e) {
		// 	e.printStackTrace();
		// }

		// reactive-stream ( expect data in future) -
		// reactive stream implementation in spring
		// - Mono (0 or 1)
		// - Flux (0 or more)

		// resturn mono, publish 'Hello World' in future ( after 3s)

		return Mono.just("Hello World").delayElement(Duration.ofSeconds(3));

	}


	@RequestMapping("/accounts")
	@ResponseBody
	public Flux<Account> getAccounts() {
		return accountRepository.findAll(); // blocking call (sync)
	}

	public static void main(String[] args) {
		SpringApplication.run(GreetingServiceApplication.class, args);
	}

}
