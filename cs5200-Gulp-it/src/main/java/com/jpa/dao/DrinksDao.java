package com.jpa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	public int addDrinkForRestaurant(Drinks drink,int restId) {
		int result = -1;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String CreateMenu = "INSERT INTO Menu (name,price,description,Restaurant) VALUES (?,?,?,?)";
			statement=conn.prepareStatement(CreateMenu);
			statement.setString(1, drink.getName());
			statement.setFloat(2, drink.getPrice());
			statement.setString(3, drink.getDescription());
			statement.setInt(4,restId);
			result=statement.executeUpdate();

			String createDrink = "INSERT INTO Drinks (Liquor,Menu) VALUES (?,LAST_INSERT_ID())";
			statement=conn.prepareStatement(createDrink);
			statement.setBoolean(1, drink.getLiquor());
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
