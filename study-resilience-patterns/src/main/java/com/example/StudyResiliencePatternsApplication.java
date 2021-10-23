package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/*
    Resilience Patterns:
    1. Retry ✔
    2. Rate Limiter/Time Limiter ✔
    3. Bulk Head       (Service)
    4. Circuit-breaker (Client) -> "Stateful version of retry"   
 */
@SpringBootApplication
@EnableScheduling
public class StudyResiliencePatternsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyResiliencePatternsApplication.class, args);
	}

}
