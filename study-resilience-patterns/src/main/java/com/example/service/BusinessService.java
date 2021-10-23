package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.bulkhead.annotation.Bulkhead.Type;

@Service
public class BusinessService {
	@Autowired
	private FailingService failingService;

//	haveFun() throws IllegalStateException.
//	haveFun() returns 'failure'
//	haveFun() throws IllegalStateException.
//	haveFun() returns 'failure'
//	haveFun() throws IllegalStateException.
//	@Retry(name = "retry1", fallbackMethod = "retryFallbackMethod")
//	@RateLimiter(name="ratelimiter1",fallbackMethod = "ratelimiterFallbackMethod" )
//	@CircuitBreaker(name="circuitBreaker1", fallbackMethod = "circuitBreakerFallbackMethod")
	@Bulkhead(name="bulkhead1", type = Type.THREADPOOL)
	public String haveGun() {
		return failingService.haveFun();
	}

//	haveFun() throws IllegalStateException.
//	haveFun() throws IllegalStateException.
//	haveFun() throws IllegalStateException.
//	haveFun() returns 'failure'
//	haveFun() throws IllegalStateException.
//	retryFallbackMethod() runs!
//	Error: Illegal state.
//	haveGun returns: failure
	public String retryFallbackMethod(Exception e) {
		System.err.println("retryFallbackMethod() runs!");
		System.err.println("Error: " + e.getMessage());
		return "failure";
	}
	
	public String ratelimiterFallbackMethod(Exception e) {
		System.err.println("ratelimiterFallbackMethod() runs!");
		System.err.println("Error: " + e.getMessage());
		return "failure";
	}
	
	public String circuitBreakerFallbackMethod(Exception e) {
		System.err.println("circuitBreakerFallbackMethod() runs!");
		System.err.println("Error: " + e.getMessage());
		return "failure";
	}
}
