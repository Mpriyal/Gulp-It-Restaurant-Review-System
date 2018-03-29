package com.jpa.models;

import java.sql.Date;

public class Customer extends User{

	private String customerKey;
	
	public Customer(int id, String firstName, String lastName, String username, String password, String email, Date dob, String customerKey) {
		super(id, firstName, lastName, username, password, email, dob);
		this.customerKey = customerKey;
		// TODO Auto-generated constructor stub
	}
	
	public Customer(String firstName, String lastName, String username, String password, String email, Date dob, String customerKey) {
		super(firstName, lastName, username, password, email, dob);
		this.customerKey = customerKey;
		// TODO Auto-generated constructor stub
	}

	public String getCustomerKey() {
		return customerKey;
	}

	public void setCustomerKey(String customerKey) {
		this.customerKey = customerKey;
	}


}
