package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@SpringBootApplication
public class SpringAopDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAopDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(TransferService transferService){
		return args -> {
			try {
				System.out.println();
				System.out.println(transferService.getClass());
				System.out.println();
				transferService.upiTransfer();
				System.out.println();
				transferService.neftTransfer();
				System.out.println();
			}catch (Exception e){
				System.out.println("Exception: "+e.getMessage());
			}
		};
	}

}
