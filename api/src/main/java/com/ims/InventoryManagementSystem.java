package com.ims;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class InventoryManagementSystem {
	public static void main(String[] args) {
		SpringApplication.run(InventoryManagementSystem.class, args);
	}
}
