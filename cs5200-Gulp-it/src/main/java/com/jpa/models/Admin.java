package com.jpa.models;

import java.sql.Date;

public class Admin extends User {

	private String adminKey;
	
	public Admin(int id, String firstName, String lastName, String username, String password, String email, Date dob, String adminKey) {
		super(id, firstName, lastName, username, password, email, dob);
		this.adminKey = adminKey;
		// TODO Auto-generated constructor stub
	}
	
	public Admin(String firstName, String lastName, String username, String password, String email, Date dob, String adminKey) {
		super(firstName, lastName, username, password, email, dob);
		this.adminKey = adminKey;
		// TODO Auto-generated constructor stub
	}


	public String getAdminKey() {
		return adminKey;
	}

	public void setAdminKey(String adminKey) {
		this.adminKey = adminKey;
	}

}
