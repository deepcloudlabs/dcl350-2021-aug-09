package com.example.service;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

@Service
public class FailingService {
	public String haveFun() {
		var random = ThreadLocalRandom.current().nextDouble();
		if (random<= 0.3) {
			System.err.println("haveFun() returns 'success'");
			return "success";
		} else if (random<0.5) {
			System.err.println("haveFun() returns 'failure'");
			return "failure";
		}
		System.err.println("haveFun() throws IllegalStateException.");
		throw new IllegalStateException("Illegal state.");
	}
}
