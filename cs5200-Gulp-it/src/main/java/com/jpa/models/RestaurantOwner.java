package com.jpa.models;

public class RestaurantOwner extends User {
	
	private int id;
	private String ownerKey;
	private int Person;
	
	public RestaurantOwner(int id, String firstName, String lastName, String username, String password, String ownerKey) {
		super(id, firstName, lastName, username, password);
		this.ownerKey = ownerKey;
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOwnerKey() {
		return ownerKey;
	}

	public void setOwnerKey(String ownerKey) {
		this.ownerKey = ownerKey;
	}

	public int getPerson() {
		return Person;
	}

	public void setPerson(int person) {
		Person = person;
	}

}
