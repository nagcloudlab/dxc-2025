package com.example.transfer_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

@Data
class TransferRequest {
	private String fromAccount;
	private String toAccount;
	private double amount;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class TransferResponse {
	private String status;
	private String message;
}

@Data
class Account {
	private String accountNumber;
	private double balance;
	private String accountHolderName;
	private String accountType;

}

@RestController
class TransferController {

	// private RestTemplate restTemplate = new RestTemplate(); // sync-calls

	@Autowired
	private WebClient.Builder webClientBuilder; // async-calls

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	private static final String TOPIC = "transfer_topic";

	@PostMapping("/transfer")
	public Mono<TransferResponse> transfer(@RequestBody TransferRequest request) {
		String accountsServiceApi = "http://ACCOUNTS-SERVICE/accounts/" + request.getFromAccount();
		return webClientBuilder.build().get()
				.uri(accountsServiceApi)
				.retrieve()
				.bodyToMono(Account.class)
				.flatMap(account -> {
					if (account.getBalance() >= request.getAmount()) {
						account.setBalance(account.getBalance() - request.getAmount());
						kafkaTemplate.send(TOPIC,
								"Transfer of " + request.getAmount() + " from " + account.getAccountNumber());
						return Mono.just(new TransferResponse("SUCCESS", "Transfer successful"));

					} else {
						kafkaTemplate.send(TOPIC,
								"Transfer of " + request.getAmount() + " from " + account.getAccountNumber());
						return Mono.just(new TransferResponse("FAILURE", "Insufficient balance"));
					}
				});
	}
}

@SpringBootApplication
@EnableDiscoveryClient
public class TransferServiceApplication {

	@Bean
	@LoadBalanced
	public WebClient.Builder loadBalancedWebClientBuilder() {
		return WebClient.builder();
	}

	public static void main(String[] args) {
		SpringApplication.run(TransferServiceApplication.class, args);
	}

}
