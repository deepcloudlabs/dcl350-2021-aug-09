package com.example.service;

import javax.annotation.PostConstruct;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;

import com.example.event.Trade;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BinanceWebSocketClient implements WebSocketHandler {
	@Value("${binance.rest.over.websocket}")
	private String binanceRestOverWebSocketUrl;
	private ObjectMapper objectMapper;
	private KafkaTemplate<String, Trade> kafkaTemplate;
	private RabbitTemplate rabbitTemplate;
	private WebSocketClient webSocketClient;
	
	public BinanceWebSocketClient(ObjectMapper objectMapper, KafkaTemplate<String, Trade> kafkaTemplate,
			RabbitTemplate rabbitTemplate, WebSocketClient webSocketClient) {
		this.objectMapper = objectMapper;
		this.kafkaTemplate = kafkaTemplate;
		this.rabbitTemplate = rabbitTemplate;
		this.webSocketClient = webSocketClient;
	}

	@PostConstruct
	public void connect() {
		webSocketClient.doHandshake(this, binanceRestOverWebSocketUrl);
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.err.println("Connected to the Binance WebSocket Server!");
		
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		String payload = message.getPayload().toString();
		Trade trade = objectMapper.readValue(payload, Trade.class);
		kafkaTemplate.send("trades", trade);
		rabbitTemplate.convertAndSend("tradeex", payload);
		System.err.println(trade);
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable e) throws Exception {
		System.err.println("An error has occured: " + e.getMessage());
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		System.err.println("Disconnected from the Binance WebSocket Server.");		
	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}
}
