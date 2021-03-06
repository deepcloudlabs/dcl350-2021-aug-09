package com.example.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.resilience4j.retry.annotation.Retry;

@FeignClient(value = "lottery")
@RequestMapping("/lottery/api/v1")
public interface LotteryService {
	@GetMapping(value = "/numbers")
	@Retry(name = "lottery", fallbackMethod = "fallbackDraw")
	List<List<Integer>> draw(@RequestParam int column);
	
	default List<List<Integer>> fallbackDraw(int column,Throwable e){
		return List.of(List.of(1,2,3,4,5,6),List.of(4,8,15,16,23,42));
	}
}
