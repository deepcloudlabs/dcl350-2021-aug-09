package com.example;

import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Flow.Subscription;

public class StudyStudyReactiveProgramming {
    // Java 9: Reactive Programming -> Flow API -> Concurrency API
	public static void main(String[] args) {
		var trades = List.of(
				new TradeEvent("orcl", 100.0, 1_000),
				new TradeEvent("msft", 120.0, 2_000),
				new TradeEvent("ibm", 70.0, 3_000),
				new TradeEvent("gogle", 130.0, 4_000)
		);		
		try(
			SubmissionPublisher<TradeEvent> publisher = new SubmissionPublisher<>();	
		){
			publisher.subscribe(new SlowSubscriber());
			publisher.subscribe(new FastSubscriber());
			trades.forEach(publisher::submit);
			try {TimeUnit.SECONDS.sleep(15);}catch (Exception e) {}	
			System.err.println("Application is done!");
		}
	}

}

class SlowSubscriber implements Flow.Subscriber<TradeEvent> {
	private Subscription subscription;
	
	@Override
	public void onSubscribe(Subscription subscription) {
		System.err.println("SlowSubscriber is subscribed!");
		this.subscription = subscription;
		this.subscription.request(1);
	}

	@Override
	public void onNext(TradeEvent event) {
		try {TimeUnit.SECONDS.sleep(3);}catch (Exception e) {}
		System.err.println("SlowSubscriber:"+event);
		this.subscription.request(1);		
	}

	@Override
	public void onError(Throwable throwable) {
		System.err.println("An error has occurred: "+throwable.getMessage());
	}

	@Override
	public void onComplete() {
		System.err.println("SlowSubscriber has processed all events!");
	}
	
}

class FastSubscriber implements Flow.Subscriber<TradeEvent> {
	private Subscription subscription;
	
	@Override
	public void onSubscribe(Subscription subscription) {
		System.err.println("FastSubscriber is subscribed!");
		this.subscription = subscription;
		this.subscription.request(1);
	}
	
	@Override
	public void onNext(TradeEvent event) {
		System.err.println("FastSubscriber:"+event);
		this.subscription.request(1);
	}
	
	@Override
	public void onError(Throwable throwable) {
		System.err.println("An error has occurred: "+throwable.getMessage());
	}
	
	@Override
	public void onComplete() {
		System.err.println("FastSubscriber has processed all events!");
	}
	
}
