package com.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class StudyAsyncHttpClient {
	private static final String BINANCE_REST_OVER_HTTP_API = "https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT";
	private static final AtomicInteger counter = new AtomicInteger(0);
	public static void main(String[] args) throws IOException, InterruptedException {
		// Entity/Aggregate: Ticker {price, symbol} -> Rest over HTTP: Resource
		var client = HttpClient.newHttpClient();
		var request = HttpRequest.newBuilder()
				                 .uri(URI.create(BINANCE_REST_OVER_HTTP_API))
				                 .header("Accept", "application/json")
				                 .build();
		var start = System.currentTimeMillis();
		for (var i=0;i<50;i++) {
			client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
			      .thenAccept( response -> {
			    	  System.out.println(response.body());	
			    	  var numOfResponse = counter.incrementAndGet();
			    	  if (numOfResponse == 50) {
			    		  var stop = System.currentTimeMillis();
			    		  var avgResponseTime = (stop-start)/50. ;
			    		  // Average Response Time: 49.22 ms
			    		  System.err.println("Average Response Time: "+avgResponseTime);			    		  
			    	  }
			      });
		}
		TimeUnit.SECONDS.sleep(30);
	}

}
