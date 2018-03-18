package com.jpa.models;

public class Restaurant {
	
	/**
	 * @author amanrayat
	 */
	public Restaurant() {
		super();
	}
	
	/**
	 * This is the constructor of the Restaurant class
	 * @param id 
	 * @param name
	 * @param description
	 * @param image_link
	 * @param cost_for_two
	 * @param restaurant_type 
	 * @param restaurant_owner 
	 */
	
	public Restaurant(String name, String description, String image_link, float cost_for_two, int restaurant_type) {
		super();
		this.name = name;
		this.description = description;
		this.image_link = image_link;
		this.cost_for_two = cost_for_two;
		this.restaurant_type = restaurant_type;
	}
	
	
	public Restaurant(int id, String name, String description, String image_link, float cost_for_two, int restaurant_owner, int restaurant_type) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.image_link = image_link;
		this.cost_for_two = cost_for_two;
		this.restaurant_type = restaurant_type;
	}
	
	/**
	 * get the id of the restaurant 
	 * @return : Id of the restaurant;
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * set the id for the restaurant
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * get the name of the restaurant 
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * set the name of the restaurant
	 * @param name
	 */
	
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * get the description of the restaurant 
	 * @return description of the restaurant
	 */
	
	public String getDescription() {
		return description;
	}
	/**
	 * set description for the restaurant
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * gets the image link for the restaurant
	 * @return
	 */
	public String getImage_link() {
		return image_link;
	}
	/**
	 * sets image link for the restaurant
	 * @param image_link
	 */
	public void setImage_link(String image_link) {
		this.image_link = image_link;
	}
	
	/**
	 * 
	 * @return cost for two for the restaurant
	 */
	
	public float getCost_for_two() {
		return cost_for_two;
	}
	
	/**
	 * Sets cost for two for the restaurant
	 * @param cost_for_two
	 */
	
	public void setCost_for_two(float cost_for_two) {
		this.cost_for_two = cost_for_two;
	}
	
	/**
	 * Private variables of the restaurants 
	 */

	public int getRestaurant_type() {
		return restaurant_type;
	}

	public void setRestaurant_type(int restaurant_type) {
		this.restaurant_type = restaurant_type;
	}

	
	private int id;
	private String name;
	private String description;
	private String image_link;
	private float  cost_for_two;
	private int restaurant_type;


}
