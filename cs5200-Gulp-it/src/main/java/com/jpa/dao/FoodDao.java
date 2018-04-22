//package com.jpa.dao;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.jpa.models.Feedback;
//import com.jpa.models.Food;
//import com.jpa.models.Menu;
//
//public class FoodDao {
//	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//	final String DB_URL = "jdbc:mysql://cs5200-spring2018-mittal.c9fddtskt253.us-east-2.rds.amazonaws.com/GulpIt";
//	final String USER = "Mpriyal";
//	final String PASS = "Priyaldbms94!";
//	static Connection conn = null;
//	static PreparedStatement statement = null;
//	static ResultSet resultset = null;
//
//	public static FoodDao instance = null;
//
//	public static FoodDao getInstance() {
//		if (instance == null) {
//			instance = new FoodDao();
//		}
//		return instance;
//	}
//	private FoodDao() {}
//
//	public int addFoodForRestaurant(Food food,int restId) {
//		int result = -1;
//		try {
//			Class.forName(JDBC_DRIVER);
//			conn = DriverManager.getConnection(DB_URL,USER,PASS);
//			String CreateMenu = "INSERT INTO Menu (name,price,description,Restaurant) VALUES (?,?,?,?)";
//			statement=conn.prepareStatement(CreateMenu);
//			statement.setString(1, food.getName());
//			statement.setFloat(2, food.getPrice());
//			statement.setString(3, food.getDescription());
//			statement.setInt(4,restId);
//			result=statement.executeUpdate();
//
//			String createFood = "INSERT INTO Food (Vegetarian,Menu) VALUES (?,LAST_INSERT_ID())";
//			statement=conn.prepareStatement(createFood);
//			statement.setBoolean(1, food.isVegetarian());
//			result=statement.executeUpdate();
//
//			conn.close();
//
//		} catch (SQLException | ClassNotFoundException e) {
//
//			e.printStackTrace();
//		}finally {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return result;
//	}
//
//	public List <Food> findFoodByNameForRestaurant(String Name,int RestaurantId) {
//		List <Food> foods =new ArrayList<>();
//		try {
//			Class.forName(JDBC_DRIVER);
//			conn = DriverManager.getConnection(DB_URL,USER,PASS);
//			String FoodById = "SELECT * FROM \n" + 
//					"Food,Menu WHERE Food.Menu =Menu.id and Menu.name= ? and Menu.restaurant = ?";
//			statement= conn.prepareStatement(FoodById);
//
//			statement.setString(1,Name);
//			statement.setInt(2,RestaurantId);
//
//			resultset = statement.executeQuery();
//			while(resultset.next()){
//				String name = resultset.getString("name");
//				Boolean Vegetarian = resultset.getBoolean("Vegetarian");
//				int price = resultset.getInt("price");
//				String description = resultset.getString("description");
//
//				Food food = new Food(Vegetarian,name,price,description);
//				foods.add(food);
//			}
//			statement.close();
//		} catch (SQLException | ClassNotFoundException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return foods;
//	}
//
//	public List <Food> findFoodByTypeForRestaurant(Boolean Type,int RestaurantId) {
//		List <Food> foods =new ArrayList<>();
//		try {
//			Class.forName(JDBC_DRIVER);
//			conn = DriverManager.getConnection(DB_URL,USER,PASS);
//			String FoodById = "SELECT * FROM Food,Menu WHERE Food.Menu =Menu.id and Food.Vegetarian= ? and Menu.restaurant = ?";
//
//			statement= conn.prepareStatement(FoodById);
//			statement.setBoolean(1,Type);
//			statement.setInt(2,RestaurantId);
//
//			resultset = statement.executeQuery();
//			while(resultset.next()){
//				String name = resultset.getString("name");
//				Boolean Vegetarian = resultset.getBoolean("Vegetarian");
//				int price = resultset.getInt("price");
//				String description = resultset.getString("description");
//				Food food = new Food(Vegetarian,name,price,description);
//				foods.add(food);
//			}
//			statement.close();
//		} catch (SQLException | ClassNotFoundException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return foods;
//	}
//
//	public List <Food> findAllFoodByRestaurant(int RestaurantId) {
//		List <Food> foods =new ArrayList<>();
//		try {
//			Class.forName(JDBC_DRIVER);
//			conn = DriverManager.getConnection(DB_URL,USER,PASS);
//			String FoodById = "SELECT * FROM \n" + 
//					"Food,Menu WHERE Food.Menu =Menu.id and Menu.restaurant = ?";
//			statement= conn.prepareStatement(FoodById);
//			statement.setInt(1,RestaurantId);
//
//			resultset = statement.executeQuery();
//			while(resultset.next()){
//				String name = resultset.getString("name");
//				Boolean Vegetarian = resultset.getBoolean("Vegetarian");
//				int price = resultset.getInt("price");
//				String description = resultset.getString("description");
//
//				Food food = new Food(Vegetarian,name,price,description);
//				foods.add(food);
//			}
//			statement.close();
//		} catch (SQLException | ClassNotFoundException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return foods;
//	}
//	
//	public int deleteFoodForRestaurant(int id) {
//		int result = -1;
//		try {
//			Class.forName(JDBC_DRIVER);
//			conn = DriverManager.getConnection(DB_URL,USER,PASS);
//			String deleteMenu = "DELETE FROM Menu where id = ?";
//			statement=conn.prepareStatement(deleteMenu);
//			statement.setInt(1,id);
//			result=statement.executeUpdate();
//			conn.close();
//
//		} catch (SQLException | ClassNotFoundException e) {
//
//			e.printStackTrace();
//		}finally {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return result;
//	}
//	
//	public int updateFood(int RestaurantId, Food food){
//		int result = 0;
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection(DB_URL,USER,PASS);
//			String FoodUpdate = "UPDATE Menu,Food SET Menu.name =?, Menu.price =?, Menu.description =? ,\n" + 
//					"Food.Vegetarian =?  WHERE \n" + 
//					"Menu.id=Food.Menu\n" + 
//					"and Menu.Restaurant=?";
//			statement = conn.prepareStatement(FoodUpdate);
//			statement.setString(1, food.getName());
//			statement.setInt(2, food.getPrice());
//			statement.setString(3, food.getDescription());
//			statement.setBoolean(4, food.isVegetarian());
//			statement.setInt(5, RestaurantId);
//			result = statement.executeUpdate();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return result;
//		}
//}
