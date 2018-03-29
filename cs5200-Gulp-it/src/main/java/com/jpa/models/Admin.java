package com.jpa.models;

import java.sql.Date;

public class Admin extends User {

	private int id;
	private String adminKey;
	private int Person;
	
	public Admin(int id, String firstName, String lastName, String username, String password, String email, Date dob, String adminKey) {
		super(id,firstName, lastName, username, password, email, dob);
		this.adminKey = adminKey;
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdminKey() {
		return adminKey;
	}

	public void setAdminKey(String adminKey) {
		this.adminKey = adminKey;
	}

	public int getPerson() {
		return Person;
	}

	public void setPerson(int person) {
		Person = person;
	}
}
