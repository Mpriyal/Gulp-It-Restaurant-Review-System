//package com.jpa.dao;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import com.jpa.models.Feedback;
//import com.jpa.models.Menu;
//
//public class FeedbackDao {
//	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//	final String DB_URL = "jdbc:mysql://cs5200-spring2018-mittal.c9fddtskt253.us-east-2.rds.amazonaws.com/GulpIt";
//	final String USER = "Mpriyal";
//	final String PASS = "Priyaldbms94!";
//	static Connection conn = null;
//	static PreparedStatement statement = null;
//	static ResultSet resultset = null;
//
//	public static FeedbackDao instance = null;
//
//	public static FeedbackDao getInstance() {
//		if (instance == null) {
//			instance = new FeedbackDao();
//		}
//		return instance;
//	}
//	private FeedbackDao() {}
//	
//	public int getAllFeedbackForRestaurantByaCustomer() {
//		Feedback feedback =null;
//		try {
//			Class.forName(JDBC_DRIVER);
//			conn = DriverManager.getConnection(DB_URL,USER,PASS);
//			String MenuById = "SELECT * FROM Menu WHERE id = ?";
//			statement= conn.prepareStatement(MenuById);
//			statement.setInt(1,MenuId);
//			resultset = statement.executeQuery();
//			if(resultset.next()){
//				int id= resultset.getInt("id");
//				String name = resultset.getString("name");
//				int price = resultset.getInt("price");
//				String description = resultset.getString("description");
//				int restaurant1 = resultset.getInt("Restaurant");
//				menu = new Menu(id,name,price,description,restaurant1);
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
//		return menu;
//	}
//}
