package com.jpa;

import java.sql.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jpa.dao.CustomerDao;
import com.jpa.dao.DrinksDao;
import com.jpa.dao.FoodDao;
//import com.jpa.dao.FoodDao;
import com.jpa.dao.MenuDao;
import com.jpa.dao.RestaurantDao;
import com.jpa.dao.RestaurantOwnerDao;
import com.jpa.models.Drinks;
import com.jpa.models.Food;
import com.jpa.models.Menu;
import com.jpa.models.Restaurant;
import com.jpa.models.RestaurantOwner;

@SpringBootApplication
public class Cs5200GulpItApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(Cs5200GulpItApplication.class, args);
		
		CustomerDao cust = CustomerDao.getInstance();
//		System.out.println(cust.findCustomerById(28));
//		Restaurant rest = new Restaurant("Chutneys","Indian food ","xyz",20,1);
//		Restaurant rest = new Restaurant("Qudoba","mexican food ","abc",20,1);
//		RestaurantDao restDao = RestaurantDao.getInstance();
//		restDao.addRestaurantForOwner(rest, 2);
//		System.out.println(restDao.findAllRestaurantByName("Chut"));
//		Menu menu = new Menu("Paneer Makhani",20,"cheese");
//		MenuDao menuDao = MenuDao.getInstance();
//		System.out.println(menuDao.addMenuForRestaurant(menu, 1));
//		Food food = new Food(true , "rajma" ,2, "tasty");
//		DrinksDao fooddao = DrinksDao.getInstance();
//		fooddao.addFoodForRestaurant(food, 1);
//				boolean vegetarian, int menu ,String name, Float price, String description)
//		fooddao.deleteFoodForRestaurant(1);
//		Boolean liquor, String name ,int price, String description
//		Drinks drinks = new Drinks(true,"Mojito",23,"Sasti sharab");
//	fooddao.addDrinksForRestaurant(drinks, 1);
//	System.out.println(fooddao.findDrinksByNameForRestaurant("Mojito", 1));
//		System.out.println(fooddao.findFoodByNameForRestaurant("rajma", 1));
		
//		String firstName, String lastName, String username, String password, String email, Date dob, String ownerKey
		
//		
//		RestaurantOwner rachna = new RestaurantOwner(455,"Rachna","Tondare","rachna","tondare","r@gml",null,"asd");
//		
//		RestaurantOwnerDao dao = RestaurantOwnerDao.getInstance();
////		dao.createRestaurantOwner(rachna);
//		System.out.println(rachna.getId());
//		System.out.println(dao.findRestaurantOwnerById(455));
//				Customer rahul = new Customer("Rahul", "Verma", "rahul", "rahul","rahul@neu.com",null,"123rahul");
//		Customer priyal = new Customer("Priyal", "Mittal","priyal","priyal","priyal@neu.com",null,"123priyal");
//		Customer extra = new Customer("Extra","Delete","extra","extra","extra@abc.com",null,"123extra");
//		Customer nitika = new Customer("Nitika","Malhotra","nonu","nonu","nonu@abc.com",null,"123nonu");
//		Customer aashna = new Customer("Aashna","Gupta","aashu","aashu","aashu@abc.com",null,"123aashu");
//		Customer aashna2 = new Customer(30,"Aashna","Gupta","aashuGu","aashuGu","aashu@abc.com",null,"1234aashu");
//		CustomerDao.getInstance().createCustomer(rahul);
//		CustomerDao.getInstance().createCustomer(priyal);
//		CustomerDao.getInstance().createCustomer(extra);
//		CustomerDao.getInstance().createCustomer(aashna);
//		System.out.println(CustomerDao.getInstance().findAllCustomers());
//		System.out.println(CustomerDao.getInstance().findCustomerById(30)); //check this (you will have to pass ids separately)
//		System.out.println(CustomerDao.getInstance().findCustomerByUsername(aashna.getUsername()));
//		System.out.println(CustomerDao.getInstance().findCustomerByCredentials(aashna.getUsername(),aashna.getPassword()));
//		System.out.println(CustomerDao.getInstance().updateCustomer(30, aashna2));
//		System.out.println(CustomerDao.getInstance().deleteCustomer(11));
		
//		RestaurantOwner aman = new RestaurantOwner("Aman","Rayat","aman","aman","aman@neu.com",null,"123aman");
//		RestaurantOwner ankur = new RestaurantOwner("Ankur","Bhambo","ankur","ankur","ankur@neu.com",null,"123ankur");
//		RestaurantOwner neha = new RestaurantOwner("Neha","Shevani","neha","neha","neha@neu.com",null,"123neha");
//		RestaurantOwner viha = new RestaurantOwner("Viha","Blah","viha","viha","viha@neu.com",null,"123viha");
		
//		RestaurantOwnerDao.getInstance().createRestaurantOwner(aman);
//		RestaurantOwnerDao.getInstance().createRestaurantOwner(ankur);
//		RestaurantOwnerDao.getInstance().createRestaurantOwner(neha);
//		RestaurantOwnerDao.getInstance().createRestaurantOwner(viha);
//		System.out.println(RestaurantOwnerDao.getInstance().findAllRestaurantOwners());
//		System.out.println(viha.getUsername());
//		System.out.println(RestaurantOwnerDao.getInstance().findRestaurantOwnerByUsername(viha.getUsername()));
//		System.out.println(viha.getId());
//		System.out.println(RestaurantOwnerDao.getInstance().findRestaurantOwnerById(viha.getId()));
//		System.out.println(RestaurantOwnerDao.getInstance().findRestaurantOwnerByCredentials(viha.getUsername(),viha.getPassword()));
//		System.out.println(viha.getId());
//		RestaurantOwnerDao dao = RestaurantOwnerDao.getInstance();
//		System.out.println(dao.findRestaurantOwnerById(6));
		//System.out.println(RestaurantOwnerDao.getInstance().deleteRestaurantOwner(aman.getId()));
//		Customer sumit = new Customer(39,"Sumit","Bhanwala","sumit","sumit","sumit@abc.com",null,"123sumit");
//		Customer sumit2 = new Customer(39,"Sumit","Bhanwala","sum","sum","sum@abc.com",null,"123sum");
//		RestaurantOwner disha = new RestaurantOwner(40,"Disha","Sule","dish","dish","disha@neu.com",null,"123disha");
//		RestaurantOwner disha2 = new RestaurantOwner(40,"Disha","Sule","disha","disha","disha@neu.com",null,"123disha2");
//		CustomerDao.getInstance().createCustomer(sumit);
//		RestaurantOwnerDao.getInstance().createRestaurantOwner(disha);
//		System.out.println(RestaurantOwnerDao.getInstance().findAllRestaurantOwners());
//		System.out.println(RestaurantOwnerDao.getInstance().findRestaurantOwnerById(40));
//		System.out.println(RestaurantOwnerDao.getInstance().findRestaurantOwnerByUsername(disha.getUsername()));
//		System.out.println(RestaurantOwnerDao.getInstance().findRestaurantOwnerByCredentials(disha.getUsername(),disha.getPassword()));
//		System.out.println(RestaurantOwnerDao.getInstance().updateRestaurantOwner(40, disha2));
//		System.out.println(RestaurantOwnerDao.getInstance().deleteRestaurantOwner(15));
		
//		Admin rajmohan = new Admin(41,"Rajmohan","Raj","raj","raj","raj@abc.com",null,"123raj");
//		Admin jose = new Admin(42,"Jose","Annunziato","jannunzi","jannunzi","jose@abc.com",null,"123jose");
//		Admin rajmohan2 = new Admin(43,"Rajmohan","Rajaram","raj2","raj2","raj@abc.com",null,"123raj2");
//		AdminDao.getInstance().createAdmin(rajmohan);
//		AdminDao.getInstance().createAdmin(jose);
//		AdminDao.getInstance().createAdmin(rajmohan2);
//		System.out.println(AdminDao.getInstance().findAllAdmins());
//		System.out.println(AdminDao.getInstance().findAdminById(41));
//		System.out.println(AdminDao.getInstance().findAdminByUsername(rajmohan.getUsername()));
//		System.out.println(AdminDao.getInstance().findAdminByCredentials(rajmohan.getUsername(),rajmohan.getPassword()));
//		System.out.println(AdminDao.getInstance().updateAdmin(41, rajmohan2));
//		System.out.println(AdminDao.getInstance().deleteAdmin(3));

	}
}
