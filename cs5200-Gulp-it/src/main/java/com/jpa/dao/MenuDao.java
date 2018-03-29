package com.jpa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jpa.models.Menu;

/**
 * 
 * @author amanrayat
 *
 */
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

	public Menu findMenuById(int MenuId) {
		Menu menu =null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String MenuById = "SELECT * FROM Menu WHERE id = ?";
			statement= conn.prepareStatement(MenuById);
			statement.setInt(1,MenuId);
			resultset = statement.executeQuery();
			if(resultset.next()){
				int id= resultset.getInt("id");
				String name = resultset.getString("name");
				int price = resultset.getInt("price");
				String description = resultset.getString("description");
				int restaurant1 = resultset.getInt("Restaurant");
				menu = new Menu(id,name,price,description,restaurant1);
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
		return menu;
	}
	public List<Menu> findAllMenuByName (int menuName) {
		List <Menu> menus = new ArrayList<>();
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String AllMenus = "SELECT * FROM Menu WHERE name=?";
			statement= conn.prepareStatement(AllMenus);
			statement.setInt(1, menuName);
			resultset = statement.executeQuery();
			while(resultset.next()) {
				int id= resultset.getInt("id");
				String name = resultset.getString("name");
				int price = resultset.getInt("price");
				String description = resultset.getString("description");
				int restaurant1 = resultset.getInt("Restaurant");
				Menu menu = new Menu(id,name,price,description,restaurant1);
				menus.add(menu);
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
		return menus;
	}

	public List<Menu> findAllMenuByRestaurant (int RestaurantId) {
		List <Menu> menus = new ArrayList<>();
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String AllMenusByRestaurant = "SELECT * FROM Menu WHERE Restaurant=?";
			statement= conn.prepareStatement(AllMenusByRestaurant);
			statement.setInt(1, RestaurantId);
			resultset = statement.executeQuery();
			while(resultset.next()) {
				int id= resultset.getInt("id");
				String name = resultset.getString("name");
				int price = resultset.getInt("price");
				String description = resultset.getString("description");
				int restaurant1 = resultset.getInt("Restaurant");
				Menu menu = new Menu(id,name,price,description,restaurant1);
				menus.add(menu);
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
		return menus;
	}

	public int deleteMenuForRestaurant(int id) {
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
