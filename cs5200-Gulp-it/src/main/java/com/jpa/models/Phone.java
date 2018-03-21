package com.jpa.models;

public class Phone {

	private int id;
	private int country_code;
	private int area_code;
	private int phone_number;
	private Boolean primary;
	private int Person;
	private int Restaurant;
	
	public String toString() {
		return phone_number + " "+ primary;
	}

	public Phone(int id,int country_code, int area_code, int phone_number, Boolean primary, int Person, int Restaurant) {
		this.id = id;
		this.country_code = country_code;
		this.area_code = area_code;
		this.phone_number = phone_number;
		this.primary = primary;
		this.Person = Person;
		this.Restaurant = Restaurant;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCountry_code() {
		return country_code;
	}

	public void setCountry_code(int country_code) {
		this.country_code = country_code;
	}

	public int getArea_code() {
		return area_code;
	}

	public void setArea_code(int area_code) {
		this.area_code = area_code;
	}

	public int getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(int phone_number) {
		this.phone_number = phone_number;
	}

	public Boolean getPrimary() {
		return primary;
	}

	public void setPrimary(Boolean primary) {
		this.primary = primary;
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
