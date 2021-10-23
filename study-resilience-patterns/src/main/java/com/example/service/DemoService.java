package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DemoService {
	@Autowired
	private BusinessService businessService;

	@Scheduled(fixedRate = 30_000)
	public void demo() {
		System.err.println(businessService.getClass());
		for (var i=0;i<20;i++) {
			System.err.println("haveGun returns: "+businessService.haveGun());
		}
	}
}

