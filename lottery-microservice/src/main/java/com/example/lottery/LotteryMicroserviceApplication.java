package com.example.lottery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

// curl -X POST http://localhost:8001/lottery/api/v1/actuator/refresh -d {} -H "Content-Type: application/json"
// curl -X POST http://localhost:7100/lottery/api/v1/login -d "{\"username\": \"jack\", \"password\": \"secret\"}" -H "Content-Type: application/json"
// curl http://localhost:7100/lottery/api/v1/numbers?column=2 -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqYWNrIiwiaWF0IjoxNjM0NjQ2MzY1LCJleHAiOjE2MzQ2NDcwODV9.fyQ42gsEKB9f4JQial9IHX28ZcE4w1Lvccgb69aw8tg"
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class LotteryMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LotteryMicroserviceApplication.class, args);
	}

}
