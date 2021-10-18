package com.example.lottery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

// curl -X POST http://localhost:8001/lottery/api/v1/actuator/refresh -d {} -H "Content-Type: application/json"
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class LotteryMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LotteryMicroserviceApplication.class, args);
	}

}
