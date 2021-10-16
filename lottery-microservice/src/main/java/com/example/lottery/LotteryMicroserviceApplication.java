package com.example.lottery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// curl -X POST http://localhost:8001/lottery/api/v1/actuator/refresh -d {} -H "Content-Type: application/json"
@SpringBootApplication
public class LotteryMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LotteryMicroserviceApplication.class, args);
	}

}
