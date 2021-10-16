package com.example.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LotteryClientService {

	@Scheduled(fixedRate = 1_000)
	public void callLotteryService() {
		var restTemplate = new RestTemplate();
		var response = restTemplate.getForEntity("http://localhost:8001/lottery/api/v1/numbers?column=2", String.class);
		System.err.println(response.getBody());
	}
}
