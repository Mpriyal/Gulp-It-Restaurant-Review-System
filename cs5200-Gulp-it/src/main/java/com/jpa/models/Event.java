package com.jpa.models;

import java.sql.Date;

/**
 * 
 * @author priyalmittal
 *
 */

public class Event {
	
	private int id;
	private String event_name;
	private String description;
	private Date date;
	private int event_provider;
	
	public Event() {
		super();
	}

	public Event(int id, String event_name, String description, Date date, int event_provider) {
		super();
		this.id = id;
		this.event_name = event_name;
		this.description = description;
		this.date = date;
		this.event_provider = event_provider;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEvent_name() {
		return event_name;
	}

	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getEvent_provider() {
		return event_provider;
	}

	public void setEvent_provider(int event_provider) {
		this.event_provider = event_provider;
	}
	
}
