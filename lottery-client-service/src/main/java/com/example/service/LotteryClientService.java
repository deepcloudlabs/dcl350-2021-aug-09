package com.example.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LotteryClientService {

	@Autowired
	private DiscoveryClient discoveryClient;
	private AtomicInteger counter = new AtomicInteger();
	private List<ServiceInstance> lotteryServiceInstances;
	
	@PostConstruct
	public void loadServiceInstances() {
		lotteryServiceInstances = discoveryClient.getInstances("lottery"); // spring.application.name=lottery
		lotteryServiceInstances.forEach(instance -> {
			System.err.println(String.format("%s:%d", instance.getHost(),instance.getPort()));
		});
	}
	
	//@Scheduled(fixedRate = 1_000)
	public void callLotteryService() {
		var restTemplate = new RestTemplate();
		var instance = getNextInstance();
		var lotteryServiceUrl = String.format("http://%s:%d/lottery/api/v1/numbers?column=2", 
				instance.getHost(),instance.getPort());
		System.err.println(String.format("Calling %s...",lotteryServiceUrl));
		var response = restTemplate.getForEntity(lotteryServiceUrl, String.class);
		System.err.println(response.getBody());
	}

	private ServiceInstance getNextInstance() { // client-side load balancing
		// round-robin
		return lotteryServiceInstances.get(counter.getAndIncrement()%lotteryServiceInstances.size());
	}
	
	
}
