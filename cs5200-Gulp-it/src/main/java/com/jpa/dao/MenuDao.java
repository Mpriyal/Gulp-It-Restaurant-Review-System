package com.jpa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jpa.models.Menu;

public class MenuDao {
	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	final String DB_URL = "jdbc:mysql://cs5200-spring2018-mittal.c9fddtskt253.us-east-2.rds.amazonaws.com/GulpIt";
	final String USER = "Mpriyal";
	final String PASS = "Priyaldbms94!";
	static Connection conn = null;
	static PreparedStatement statement = null;
	static ResultSet resultset = null;
	
	public static MenuDao instance = null;
	
	public static MenuDao getInstance() {
		if (instance == null) {
			instance = new MenuDao();
		}
		return instance;
	}
	private MenuDao() {}

	public int addMenuForRestaurant(Menu menu,int restId) {
int result = -1;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String CreatePerson = "INSERT INTO Menu (name,price,description,Restaurant) VALUES (?,?,?,?)";
			statement=conn.prepareStatement(CreatePerson);
			statement.setString(1, menu.getName());
			statement.setFloat(2, menu.getPrice());
			statement.setString(3, menu.getDescription());
			statement.setInt(4,restId);
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
