package com.jpa.models;

public class Feedback {

	private String comment;
	private boolean favourite;
	private int Restaurant;
	private int Customer;
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public boolean isFavourite() {
		return favourite;
	}
	public void setFavourite(boolean favourite) {
		this.favourite = favourite;
	}
	public int getRestaurant() {
		return Restaurant;
	}
	public void setRestaurant(int restaurant) {
		Restaurant = restaurant;
	}
	public int getCustomer() {
		return Customer;
	}
	public void setCustomer(int customer) {
		Customer = customer;
	}
	public Feedback() {
		super();
	}
	public Feedback(String comment, boolean favourite) {
		super();
		this.comment = comment;
		this.favourite = favourite;
	}
	public Feedback(String comment, boolean favourite, int restaurant, int customer) {
		super();
		this.comment = comment;
		this.favourite = favourite;
		Restaurant = restaurant;
		Customer = customer;
	} 

}
