package com.example.event;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Trade {
	@JsonProperty("s")
	private String symbol;
	@JsonProperty("b")
	private long bid;
	@JsonProperty("a")
	private long ask;
	@JsonProperty("p")
	private String price;
	@JsonProperty("q")
	private String quantity;
	@JsonProperty("t")
	private long sequence;
	@JsonProperty("T")
	private long timestamp;

	public Trade() {
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public long getBid() {
		return bid;
	}

	public void setBid(long bid) {
		this.bid = bid;
	}

	public long getAsk() {
		return ask;
	}

	public void setAsk(long ask) {
		this.ask = ask;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public long getSequence() {
		return sequence;
	}

	public void setSequence(long sequence) {
		this.sequence = sequence;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Trade [symbol=" + symbol + ", bid=" + bid + ", ask=" + ask + ", price=" + price + ", quantity="
				+ quantity + ", sequence=" + sequence + ", timestamp=" + timestamp + "]";
	}

}
