package com.jpa.models;

import java.sql.Date;

public class RestaurantOwner extends User {
	
	private String ownerKey;
	
	public RestaurantOwner() {
		super();
	}
	
	public RestaurantOwner(String firstName, String lastName, String username, String password, String email, Date dob, String ownerKey) {
		super(firstName, lastName, username, password, email, dob);
		this.ownerKey = ownerKey;
		// TODO Auto-generated constructor stub
	}
	
	public RestaurantOwner(String firstName, String lastName, String username, String password, String email, Date dob,String type, String ownerKey) {
		super(firstName, lastName, username, password, email, dob,type);
		this.ownerKey = ownerKey;
		// TODO Auto-generated constructor stub
	}
	
	//mainly used constructor:
	public RestaurantOwner(int id,String firstName, String lastName, String username, String password, String email, Date dob,String type, String ownerKey) {
		super(id,firstName, lastName, username, password, email, dob,type);
		this.ownerKey = ownerKey;
		// TODO Auto-generated constructor stub
	}

	public String getOwnerKey() {
		return ownerKey;
	}

	public void setOwnerKey(String ownerKey) {
		this.ownerKey = ownerKey;
	}

}
