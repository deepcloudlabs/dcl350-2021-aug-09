package com.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class StudySyncHttpClient {
	private static final String BINANCE_REST_OVER_HTTP_API = "https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT";
	
	public static void main(String[] args) throws IOException, InterruptedException {
		var client = HttpClient.newHttpClient();
		var request = HttpRequest.newBuilder()
				                 .uri(URI.create(BINANCE_REST_OVER_HTTP_API))
				                 .header("Accept", "application/json")
				                 .build();
		var start = System.currentTimeMillis();
		for (var i=0;i<50;i++) {
			var response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
			System.out.println(response);			
		}
		var stop = System.currentTimeMillis();
		var avgResponseTime = (stop-start)/50. ;
		// Average Response Time: 356.62 ms
		System.err.println("Average Response Time: "+avgResponseTime);
	}

}
