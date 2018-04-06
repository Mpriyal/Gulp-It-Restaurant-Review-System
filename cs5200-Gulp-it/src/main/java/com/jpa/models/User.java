package com.jpa.models;

import java.sql.Date;
import java.util.List;

import com.jpa.models.Address;
import com.jpa.models.Phone;

public class User {
	
	private int Id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;
	private Date DOB;
	private List<Phone> phoneList;
	private List<Address> addressList;
	
	public String toString() {
		return Id +" "+ firstName +" "+lastName+" "+username+" "+password;
	}
	
	public User(int Id, String username, String password, String email, Date dob) {
		this(Id, "", "", username, password, email,dob);
	}

	public User(String firstName, String lastName, String username, String password, String email, Date dob) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.DOB = dob;
	}
	public User(int id, String firstName, String lastName, String username, String password, String email, Date DOB) {
		super();
		Id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.DOB = DOB;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		this.Id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDOB() {
		return DOB;
	}

	public void setDOB(Date dOB) {
		DOB = dOB;
	}
	
	public List<Phone> getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(List<Phone> phoneList) {
		this.phoneList = phoneList;
	}

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

}
