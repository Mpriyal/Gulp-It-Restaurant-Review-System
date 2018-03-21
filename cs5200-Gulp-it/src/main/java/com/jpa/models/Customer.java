package com.jpa.models;

import java.sql.Date;

public class Customer extends User{

	private int id;
	private String customerKey;
	private int Person;
	
	public Customer(int id, String firstName, String lastName, String username, String password, String customerKey) {
		super(id, firstName, lastName, username, password);
		this.customerKey = customerKey;
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomerKey() {
		return customerKey;
	}

	public void setCustomerKey(String customerKey) {
		this.customerKey = customerKey;
	}

	public int getPerson() {
		return Person;
	}

	public void setPerson(int person) {
		Person = person;
	}


}
