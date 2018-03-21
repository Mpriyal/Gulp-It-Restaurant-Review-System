package com.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jpa.dao.MenuDao;
import com.jpa.dao.RestaurantDao;
import com.jpa.models.Menu;
import com.jpa.models.Restaurant;

@SpringBootApplication
public class Cs5200GulpItApplication {

	public static void main(String[] args) {
		SpringApplication.run(Cs5200GulpItApplication.class, args);
//		Restaurant rest = new Restaurant("Chutneys","Indian food ","xyz",20,1);
		Restaurant rest = new Restaurant("Qudoba","mexican food ","abc",20,1);
		RestaurantDao restDao = RestaurantDao.getInstance();
//		restDao.addRestaurantForOwner(rest, 2);
		System.out.println(restDao.findAllRestaurantByName("Chut"));
		Menu menu = new Menu("Paneer Makhani",20,"cheese");
		MenuDao menuDao = MenuDao.getInstance();
		System.out.println(menuDao.addMenuForRestaurant(menu, 1));
		
		
		
	}
}
