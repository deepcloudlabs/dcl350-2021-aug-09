package com.example;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("deprecation")
public class StudyLegacyObserver {

	public static void main(String[] args) {
		Observer slowObserver = (o,event) -> {
			try {TimeUnit.SECONDS.sleep(3);}catch (Exception e) {}
			System.err.println("Slow Observer: "+event);
		};
		Observer fastObserver = (o,event) -> {
			System.err.println("Fast Observer: "+event);
		};
		var observable = new TradeEventObservable();
		observable.addObserver(slowObserver);
		observable.addObserver(fastObserver);
		var trades = List.of(
				new TradeEvent("orcl", 100.0, 1_000),
				new TradeEvent("msft", 120.0, 2_000),
				new TradeEvent("ibm", 70.0, 3_000),
				new TradeEvent("gogle", 130.0, 4_000)
		);
		trades.forEach(observable::notifyObservers);
		
	}

}

@SuppressWarnings("deprecation")
class TradeEventObservable extends Observable {

	@Override
	public void notifyObservers(Object event) {
		setChanged();
		super.notifyObservers(event);
	}
	
}
