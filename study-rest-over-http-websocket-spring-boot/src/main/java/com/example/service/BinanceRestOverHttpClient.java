package com.example.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

@Service
@SuppressWarnings("deprecation")
public class BinanceRestOverHttpClient {
	@Value("${binance.rest.over.http}")
	private String binanceRestOverHttpUrl;
	
	// @Scheduled(fixedRate = 3_000)
	public void syncCallBinanceRestOverHttp() {
		RestTemplate restTemplate = new RestTemplate();
		var response = restTemplate.getForEntity(binanceRestOverHttpUrl, String.class).getBody();
		System.err.println(response);
	}
	
	@Scheduled(fixedRate = 3_000)
	public void asyncCallBinanceRestOverHttp() {
		AsyncRestTemplate restTemplate = new AsyncRestTemplate();
		restTemplate.getForEntity(binanceRestOverHttpUrl, String.class)
		            .addCallback((response) ->{
		            	System.err.println(response.getBody());
		            }, (error) ->{
		            	System.err.println(error.getMessage());
		            });		                    
	}
}
