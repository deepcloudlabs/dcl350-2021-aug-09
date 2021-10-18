package com.example.lottery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.lottery.service.LotteryService;

// REST API: Resource -> Model
// over http -> 1) http method: GET @GetMapping, POST @PostMapping, PUT @PutMapping, PATCH @PatchMapping, DELETE @DeleteMapping
//              2) URI -> http(s)://localhost:7001/lottery/api/v1   /numbers
//                        physical segment -> application.properties
//              3) Represention: "application/json"

@RestController
@RequestScope
@RequestMapping("numbers")
@CrossOrigin
public class LotteryController {
	private LotteryService lotteryService;
	@Value("${server.address}")
	private String serverAddress;
	@Value("${server.port}")
	private int serverPort;
	
	// Constructor Injection	
	public LotteryController(LotteryService lotteryService) {
		this.lotteryService = lotteryService;
	}

	// http(s)://localhost:7001/lottery/api/v1/numbers?column=5
	@GetMapping(params= {"column"})
	public List<List<Integer>> getLotteryNumbers(@RequestParam int column){
		System.err.println(String.format("%s:%d", serverAddress, serverPort));
		return lotteryService.draw(column);
	}
}
