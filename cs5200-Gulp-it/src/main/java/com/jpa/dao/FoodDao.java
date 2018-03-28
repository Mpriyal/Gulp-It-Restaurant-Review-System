package com.jpa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.jpa.models.Food;

public class FoodDao {
	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	final String DB_URL = "jdbc:mysql://cs5200-spring2018-mittal.c9fddtskt253.us-east-2.rds.amazonaws.com/GulpIt";
	final String USER = "Mpriyal";
	final String PASS = "Priyaldbms94!";
	static Connection conn = null;
	static PreparedStatement statement = null;
	static ResultSet resultset = null;

	public static FoodDao instance = null;

	public static FoodDao getInstance() {
		if (instance == null) {
			instance = new FoodDao();
		}
		return instance;
	}
	private FoodDao() {}

	public int addFoodForRestaurant(Food food,int restId) {
		int result = -1;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String CreateMenu = "INSERT INTO Menu (name,price,description,Restaurant) VALUES (?,?,?,?)";
			statement=conn.prepareStatement(CreateMenu);
			statement.setString(1, food.getName());
			statement.setFloat(2, food.getPrice());
			statement.setString(3, food.getDescription());
			statement.setInt(4,restId);
			result=statement.executeUpdate();

			String createFood = "INSERT INTO Food (Vegetarian,Menu) VALUES (?,LAST_INSERT_ID())";
			statement=conn.prepareStatement(createFood);
			statement.setBoolean(1, food.isVegetarian());
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
	public Food findFoodByMenu(int MenuId) {
		Food food =null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String FoodName = "SELECT * FROM Food WHERE Menu =?";
			statement= conn.prepareStatement(FoodName);
			statement.setInt(1,MenuId);
			resultset = statement.executeQuery();
			if(resultset.next()) {
				int id= resultset.getInt("id");
				Boolean Vegetarian = resultset.getBoolean("Vegetarian");
				int menu = resultset.getInt("Menu");

				food = new Food(id,Vegetarian,menu);
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
		return food;
	}
	public int deleteFoodForRestaurant(int id,int restId) {
		int result = -1;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String CreateMenu = "DELETE FROM Menu (name,price,description,Restaurant) VALUES (?,?,?,?)";
			statement=conn.prepareStatement(deleteFood);
			statement.setString(1, food.getName());
			statement.setFloat(2, food.getPrice());
			statement.setString(3, food.getDescription());
			statement.setInt(4,restId);
			result=statement.executeUpdate();

			String createFood = "INSERT INTO Food (Vegetarian,Menu) VALUES (?,LAST_INSERT_ID())";
			statement=conn.prepareStatement(createFood);
			statement.setBoolean(1, food.isVegetarian());
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
