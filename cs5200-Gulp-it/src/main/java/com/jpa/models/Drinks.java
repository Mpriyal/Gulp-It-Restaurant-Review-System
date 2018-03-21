package com.jpa.models;

public class Drinks extends Menu{
	private int id; 
	private Boolean liquor; 
	private int Menu;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Boolean getLiquor() {
		return liquor;
	}
	public void setLiquor(Boolean liquor) {
		this.liquor = liquor;
	}
	public int getMenu() {
		return Menu;
	}
	public void setMenu(int menu) {
		Menu = menu;
	}
	public Drinks(Boolean liquor, String name ,int price, String description) {
		super(name, price, description);	
		this.liquor = liquor;
	}
	public Drinks() {
		super();
	}

}
