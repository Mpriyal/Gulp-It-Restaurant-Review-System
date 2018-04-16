package com.jpa.models;

public class Address {
	
	private int id;
	private String street1;
	private String street2;
	private String city;
	private String state;
	private String country;
	private int pin_code;
	private Boolean billing;
	private int Person;
	private int Restaurant;
	
	public String toString() {
		return id +" "+ street1 +" "+street2+" "+city+" "+state;
	}
	
	public Address(int id, String street1, String street2, String city, String state, String country, int pin_code, Boolean billing, int Person) {
		this.id = id;
		this.street1 = street1;
		this.street2 = street2;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pin_code = pin_code;
		this.billing = billing;
		this.Person = Person;
	}
	
	public Address(int id, String street1, String street2, String city, String state, String country, int pin_code, int Restaurant, Boolean billing) {
		this.id = id;
		this.street1 = street1;
		this.street2 = street2;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pin_code = pin_code;
		this.billing = billing;
		this.Restaurant = Restaurant;
	}

	public int getPin_code() {
		return pin_code;
	}

	public void setPin_code(int pin_code) {
		this.pin_code = pin_code;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Boolean getBilling() {
		return billing;
	}

	public void setBilling(Boolean billing) {
		this.billing = billing;
	}
	
	public int getPerson() {
		return Person;
	}

	public void setPerson(int person) {
		Person = person;
	}

	public int getRestaurant() {
		return Restaurant;
	}

	public void setRestaurant(int restaurant) {
		Restaurant = restaurant;
	}

}
