package com.example.gateway_service;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

	// hardcoded service instances for demonstration purposes
	// In a real-world scenario, you would use a service discovery mechanism like
	// Eureka or Consul
	@Bean
	public ServiceInstanceListSupplier serviceInstanceListSupplier() {
		return new ServiceInstanceListSupplier() {
			@Override
			public String getServiceId() {
				return "accounts-service";
			}

			@Override
			public Flux<List<ServiceInstance>> get() {
				List<ServiceInstance> instance = List.of(
						new DefaultServiceInstance("instance1", "accounts-service", "localhost", 8181, false),
						new DefaultServiceInstance("instance2", "accounts-service", "localhost", 8182, false));
				return Flux.just(instance);
			}
		};
	}

}
