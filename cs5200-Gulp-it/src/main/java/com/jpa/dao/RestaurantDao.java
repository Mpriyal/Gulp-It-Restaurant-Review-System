package com.jpa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jpa.models.Restaurant;

public class RestaurantDao {
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://cs5200-spring2018-mittal.c9fddtskt253.us-east-2.rds.amazonaws.com/GulpIt";
		final String USER = "Mpriyal";
		final String PASS = "Priyaldbms94!";
		static Connection conn = null;
		static PreparedStatement statement = null;
		static ResultSet resultset = null;
		
		public static RestaurantDao instance = null;
		
		public static RestaurantDao getInstance() {
			if (instance == null) {
				instance = new RestaurantDao();
			}
			return instance;
		}
		private RestaurantDao() {}
		

		public int addRestaurantForOwner(Restaurant restaurant,int OwnerId) {
			int result = -1;
			try {
				
				Class.forName(JDBC_DRIVER);
				conn = DriverManager.getConnection(DB_URL,USER,PASS);
				String AllRestaurant = "INSERT INTO Restaurant "
						+ "(`name`,`description`,`image_link`,`cost_for_two`,`restaurant_owner`,`restaurant_type`) "
						+ "VALUES (?,?,?,?,?,?)";
				statement= conn.prepareStatement(AllRestaurant);
				
				statement.setString(1, restaurant.getName());
				statement.setString(2, restaurant.getDescription());
				statement.setString(3, restaurant.getImage_link());
				statement.setFloat(4,restaurant.getCost_for_two());
				statement.setInt(5,OwnerId);
				statement.setInt(6, restaurant.getRestaurant_type());
				
				result = statement.executeUpdate();
				conn.close();
				statement.close();
				} catch (SQLException | ClassNotFoundException e) {
					e.printStackTrace();
					} finally {
						try {
							conn.close();
							} catch (SQLException e) {
									e.printStackTrace();
								}
							}
			
						return result;
				}

		public List<Restaurant> findAllRestaurant() {
			List <Restaurant> restaurants = new ArrayList<>();
			try {
				Class.forName(JDBC_DRIVER);
				conn = DriverManager.getConnection(DB_URL,USER,PASS);
				String AllRestaurant = "SELECT * FROM restaurant";
				statement= conn.prepareStatement(AllRestaurant);
				resultset = statement.executeQuery();
					while(resultset.next()) {
							int id= resultset.getInt("id");
							String name = resultset.getString("name");
							String description = resultset.getString("description");
							String image_link = resultset.getString("image_link");
							float cost_for_two = resultset.getFloat("cost_for_two");
							int restaurant_owner = resultset.getInt("restaurant_owner");
							int restaurant_type = resultset.getInt("restaurant_type");
						
						Restaurant restaurant = new Restaurant(id,name,description,image_link,cost_for_two,restaurant_owner,restaurant_type);
						restaurants.add(restaurant);
					}
				statement.close();
				} catch (SQLException | ClassNotFoundException e) {
					e.printStackTrace();
					} finally {
						try {
							conn.close();
							} catch (SQLException e) {
									e.printStackTrace();
								}
							}
			
						return restaurants;
					}
					
			}

