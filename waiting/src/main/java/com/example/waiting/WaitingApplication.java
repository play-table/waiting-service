package com.example.waiting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WaitingApplication {

	public static void main(String[] args) {
		SpringApplication.run(WaitingApplication.class, args);
	}

}
