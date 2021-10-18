package com.example.crm.document;

public class Address {
	private String city;
	private String country;
	private String line1;
	private String line2;

	public Address() {
	}

	public Address(String city, String country, String line1, String line2) {
		this.city = city;
		this.country = country;
		this.line1 = line1;
		this.line2 = line2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	@Override
	public String toString() {
		return "Address [city=" + city + ", country=" + country + ", line1=" + line1 + ", line2=" + line2 + "]";
	}

}
