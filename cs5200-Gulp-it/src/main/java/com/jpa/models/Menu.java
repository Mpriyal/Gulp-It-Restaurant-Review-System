package com.jpa.models;

public class Menu {
	private int id;
	private String item_name;
	private int item_type;
	private int price;
	private String description;
	private int Restaurant;
	
	/**
	 * @author amanrayat	
	 */
	public Menu() {
		super();
	}
	
	public Menu(String item_name, int item_type, int price, String description) {
		super();
		this.item_name = item_name;
		this.item_type = item_type;
		this.price = price;
		this.description = description;
	}
	
	public Menu(int id, String item_name,int item_type, int price, String description, int Restaurant) {
		super();
		this.id = id;
		this.item_name = item_name;
		this.item_type = item_type;
		this.price = price;
		this.description = description;
		this.Restaurant = Restaurant;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public int getItem_type() {
		return item_type;
	}

	public void setItem_type(int item_type) {
		this.item_type = item_type;
	}

	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getRestaurant() {
		return Restaurant;
	}
	public void setRestaurant(int Restaurant) {
		this.Restaurant = Restaurant;
	}
	
}
