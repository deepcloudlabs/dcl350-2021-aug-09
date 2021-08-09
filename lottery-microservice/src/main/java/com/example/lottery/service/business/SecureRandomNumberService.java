package com.example.lottery.service.business;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.lottery.service.RandomNumberService;

// JVM Arguments: -Dspring.profiles.active=dev
@Service
//@ServiceQuality(QualityLevel.SECURE)
//@ConditionalOnProperty(name = "randomNumberService", havingValue = "secure")
@Profile({"prod", "test", "preprod"})
public class SecureRandomNumberService implements RandomNumberService {

	private Random random = new SecureRandom();

	@Override
	public int generate(int min, int max) {
		System.err.println("SecureRandomNumberService::generate");
		return random.nextInt(max - min + 1) + min;
	}

}
