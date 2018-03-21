package com.jpa.models;

public class Menu {
	private int id;
	private String name;
	private int price;
	private String description;
	private int restaurant;
	
	public Menu() {
		super();
	}
	public Menu(String name, int price, String description) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Menu(int id, String name, int price, String description, int restaurant) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.restaurant = restaurant;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(int restaurant) {
		this.restaurant = restaurant;
	}
	
}
