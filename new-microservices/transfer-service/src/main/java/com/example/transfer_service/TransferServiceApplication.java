package com.example.transfer_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

	private RestTemplate restTemplate = new RestTemplate();

	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	private static final String TOPIC = "transfer_topic";

	@PostMapping("/transfer")
	public ResponseEntity<TransferResponse> transfer(@RequestBody TransferRequest request) {
		// Logic to process the transfer
		// verfy if the accounts exist
		String accountsServiceApi = "http://localhost:8081/accounts/" + request.getFromAccount(); // bad, service tight coupling by address ( soln: service discovery)
		Account fromAccount = restTemplate.getForObject(accountsServiceApi, Account.class); // bad, sync call (soln: async call)
		if (fromAccount == null) {
			return ResponseEntity.badRequest().body(new TransferResponse("Failed", "From account does not exist."));
		}
		// verify if the accounts exist
		if (fromAccount.getBalance() < request.getAmount()) {
			// send message to kafka topic
			kafkaTemplate.send(TOPIC, "Insufficient balance for account: " + request.getFromAccount());
			return ResponseEntity.badRequest().body(new TransferResponse("Failed", "Insufficient balance."));
		}

		// Logic to deduct amount from the sender's account
		// Logic to add amount to the receiver's account

		// Logic to send a message to the Kafka topic
		kafkaTemplate.send(TOPIC, "Transfer of " + request.getAmount() + " from " + request.getFromAccount() + " to " + request.getToAccount());

		TransferResponse response = new TransferResponse();
		response.setStatus("Success");
		response.setMessage("Transfer completed successfully.");
		return ResponseEntity.ok(response);
	}
}


@SpringBootApplication
public class TransferServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransferServiceApplication.class, args);
	}

}
