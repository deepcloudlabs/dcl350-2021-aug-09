package com.example.lottery.service.business;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.example.lottery.service.LotteryService;
import com.example.lottery.service.RandomNumberService;

@Service
public class StandardLotteryService implements LotteryService {

    private RandomNumberService randomNumberService;
	
	private List<RandomNumberService> randomNumberServices;
	private static final AtomicInteger counter = new AtomicInteger(0);
	
	/*
	public StandardLotteryService(
	    @ServiceQuality(QualityLevel.FAST) RandomNumberService randomNumberService) {
		this.randomNumberService = randomNumberService;
	}
    */
	
	public StandardLotteryService(List<RandomNumberService> randomNumberServices) {
		this.randomNumberServices = randomNumberServices;
	}

	@Override
	public List<List<Integer>> draw(int column) {
		randomNumberService = randomNumberServices.get(counter.getAndIncrement()%randomNumberServices.size());
		return IntStream.range(0, column)
		         .mapToObj( i -> this.draw())
		         .collect(Collectors.toList());
	}


	private List<Integer> draw(){
		return IntStream.generate(() -> randomNumberService.generate(1,60))
				        .distinct()
				        .limit(6)
				        .sorted()
				        .boxed()
				        .collect(Collectors.toList());
	}
}
