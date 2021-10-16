package com.example.algotrading.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.event.Trade;

@Service
public class AlgotradingConsumerService {

	@KafkaListener(topics = "trades")
	public void handleKafkaMessage(Trade trade) {
		System.err.println("Kafka: "+trade);
	}
	
	@RabbitListener(queues = "trades-queue")
	public void handleRabbitMessage(Trade trade) {
		System.err.println("Rabbit: "+trade);
	}
}
