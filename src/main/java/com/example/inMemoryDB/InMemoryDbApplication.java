package com.example.inMemoryDB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class InMemoryDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(InMemoryDbApplication.class, args);
	}

}
