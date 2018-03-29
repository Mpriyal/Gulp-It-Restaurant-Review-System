package com.jpa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.jpa.models.Drinks;

public class DrinksDao {
	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	final String DB_URL = "jdbc:mysql://cs5200-spring2018-mittal.c9fddtskt253.us-east-2.rds.amazonaws.com/GulpIt";
	final String USER = "Mpriyal";
	final String PASS = "Priyaldbms94!";
	static Connection conn = null;
	static PreparedStatement statement = null;
	static ResultSet resultset = null;

	public static DrinksDao instance = null;

	public static DrinksDao getInstance() {
		if (instance == null) {
			instance = new DrinksDao();
		}
		return instance;
	}
	private DrinksDao() {}

	public int addDrinksForRestaurant(Drinks Drinks,int restId) {
		int result = -1;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String CreateMenu = "INSERT INTO Menu (name,price,description,Restaurant) VALUES (?,?,?,?)";
			statement=conn.prepareStatement(CreateMenu);
			statement.setString(1, Drinks.getName());
			statement.setFloat(2, Drinks.getPrice());
			statement.setString(3, Drinks.getDescription());
			statement.setInt(4,restId);
			result=statement.executeUpdate();

			String createDrinks = "INSERT INTO Drinks (Vegetarian,Menu) VALUES (?,LAST_INSERT_ID())";
			statement=conn.prepareStatement(createDrinks);
			statement.setBoolean(1, Drinks.getLiquor());
			result=statement.executeUpdate();

			conn.close();

		} catch (SQLException | ClassNotFoundException e) {

			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public List <Drinks> findDrinksByNameForRestaurant(String Name,int RestaurantId) {
		List <Drinks> Drinkss =new ArrayList<>();
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String DrinksById = "SELECT * FROM \n" + 
					"Drinks,Menu WHERE Drinks.Menu =Menu.id and Menu.name= ? and Menu.restaurant = ?";
			statement= conn.prepareStatement(DrinksById);

			statement.setString(1,Name);
			statement.setInt(2,RestaurantId);

			resultset = statement.executeQuery();
			while(resultset.next()){
				String name = resultset.getString("name");
				Boolean Vegetarian = resultset.getBoolean("Vegetarian");
				int price = resultset.getInt("price");
				String description = resultset.getString("description");

				Drinks Drinks = new Drinks(Vegetarian,name,price,description);
				Drinkss.add(Drinks);
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
		return Drinkss;
	}

	public List <Drinks> findDrinksByTypeForRestaurant(Boolean Type,int RestaurantId) {
		List <Drinks> Drinkss =new ArrayList<>();
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String DrinksById = "SELECT * FROM Drinks,Menu WHERE Drinks.Menu =Menu.id and Drinks.Vegetarian= ? and Menu.restaurant = ?";

			statement= conn.prepareStatement(DrinksById);
			statement.setBoolean(1,Type);
			statement.setInt(2,RestaurantId);

			resultset = statement.executeQuery();
			while(resultset.next()){
				String name = resultset.getString("name");
				Boolean Vegetarian = resultset.getBoolean("Vegetarian");
				int price = resultset.getInt("price");
				String description = resultset.getString("description");
				Drinks Drinks = new Drinks(Vegetarian,name,price,description);
				Drinkss.add(Drinks);
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
		return Drinkss;
	}

	public List <Drinks> findAllDrinksByRestaurant(int RestaurantId) {
		List <Drinks> Drinkss =new ArrayList<>();
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String DrinksById = "SELECT * FROM \n" + 
					"Drinks,Menu WHERE Drinks.Menu =Menu.id and Menu.restaurant = ?";
			statement= conn.prepareStatement(DrinksById);
			statement.setInt(1,RestaurantId);

			resultset = statement.executeQuery();
			while(resultset.next()){
				String name = resultset.getString("name");
				Boolean Vegetarian = resultset.getBoolean("Vegetarian");
				int price = resultset.getInt("price");
				String description = resultset.getString("description");

				Drinks Drinks = new Drinks(Vegetarian,name,price,description);
				Drinkss.add(Drinks);
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
		return Drinkss;
	}
	
	public int deleteDrinksForRestaurant(int id) {
		int result = -1;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String deleteMenu = "DELETE FROM Menu where id = ?";
			statement=conn.prepareStatement(deleteMenu);
			statement.setInt(1,id);
			result=statement.executeUpdate();
			conn.close();

		} catch (SQLException | ClassNotFoundException e) {

			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
