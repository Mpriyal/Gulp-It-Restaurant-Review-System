package com.jpa.models;

import java.sql.Date;

public class EventProvider extends User{

	private String event_key;
	
	public EventProvider() {
		super();
	}
	
	public EventProvider(int Id, String username, String password, String email, Date dob, String type) {
		super(Id, username, password, email, dob,type);
	}
	
	public EventProvider(String firstName, String lastName, String username, String password, String email, Date dob, String event_key) {
		super(firstName, lastName, username, password, email, dob);
		this.event_key = event_key;
	}
	
	public EventProvider(String firstName, String lastName, String username, String password, String email, Date dob,String type, String event_key) {
		super(firstName, lastName, username, password, email, dob,type);
		this.event_key = event_key;
	}
	
	//mainly used constructor:
	public EventProvider(int id, String firstName, String lastName, String username, String password, String email, Date dob,String type, String event_key) {
		super(id, firstName, lastName, username, password, email, dob, type);
		this.event_key = event_key;
	}

	public String getEvent_key() {
		return event_key;
	}

	public void setEvent_key(String event_key) {
		this.event_key = event_key;
	}



}
