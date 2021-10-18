package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class LotteryClientServiceUsingFeignClient {
	@Autowired
	private LotteryService lotteryService;

	@Scheduled(fixedRate = 1_000)
	//@Retry(name = "lottery", fallbackMethod = "fallbackDraw")
	public void callLotteryService() {
		lotteryService.draw(2).forEach(System.err::println);
		System.err.println("==================================");
	}
	
	public List<List<Integer>> fallbackDraw(int column,Throwable e){
		return List.of(List.of(1,2,3,4,5,6),List.of(4,8,15,16,23,42));
	}	
}
