package com.jpa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jpa.models.Menu;
import com.jpa.models.Restaurant;

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
	
	public int addMenuForRestaurant(Menu menu,int RestId, int OwnerId) {
		int result = -1;
		try {

			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String addRestaurant = "INSERT INTO Menu (item_name,item_type,price,description,Restaurant) "
					+ "VALUES(?,?,?,?,(SELECT id from Restaurant WHERE id = ? AND restaurant_owner=?))";
			statement= conn.prepareStatement(addRestaurant);
			statement.setString(1, menu.getItem_name());
			statement.setInt(2, menu.getItem_type());
			statement.setFloat(3, menu.getPrice());
			statement.setString(4,menu.getDescription());
			statement.setInt(5,RestId);
			statement.setInt(6,OwnerId);
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

	//this function find the details of a menu item, given its id
	public Menu findMenuItemByMenuId(int MenuId, int RestId, int ownerId) {
		Menu menuItem =null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String MenuItemById = "SELECT * FROM Menu,Restaurant WHERE Menu.Restaurant=Restaurant.id AND Menu.id = ? "
					+ "AND Restaurant=? AND Restaurant.restaurant_owner=?";
			statement= conn.prepareStatement(MenuItemById);
			statement.setInt(1,MenuId);
			statement.setInt(2,RestId);
			statement.setInt(3, ownerId);
			resultset = statement.executeQuery();
			if(resultset.next()){
				int id= resultset.getInt("id");
				String item_name = resultset.getString("item_name");
				int item_type = resultset.getInt("item_type");
				int price = resultset.getInt("price");
				String description = resultset.getString("description");
				int restaurant1 = resultset.getInt("Restaurant");
				menuItem = new Menu(id,item_name,item_type,price,description,restaurant1);
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
		return menuItem;
	}
	
	//this function finds all the items in the menu with the given name
	public List<Menu> findMenuItemsByName (String menuItemName, int RestId, int ownerId) {
		List <Menu> menus = new ArrayList<>();
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String AllMenus = "SELECT * FROM Menu,Restaurant WHERE Menu.Restaurant=Restaurant.id AND item_name LIKE ? AND "
					+ "Restaurant=? AND Restaurant.restaurant_owner=?";
			statement= conn.prepareStatement(AllMenus);
			statement.setString(1, "%" + menuItemName + "%");
			statement.setInt(2, RestId);
			statement.setInt(3, ownerId);
			resultset = statement.executeQuery();
			while(resultset.next()) {
				int id= resultset.getInt("id");
				String item_name = resultset.getString("item_name");
				int item_type = resultset.getInt("item_type");
				int price = resultset.getInt("price");
				String description = resultset.getString("description");
				int restaurant1 = resultset.getInt("Restaurant");
				Menu menu = new Menu(id,item_name,item_type,price,description,restaurant1);
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

	//this function lists all the items in the menu of the restaurant with the given id
	public List<Menu> findAllMenuItemsByRestaurantId (int RestaurantId, int ownerId) {
		List <Menu> menus = new ArrayList<>();
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String AllMenusByRestaurant = "SELECT * FROM Menu,Restaurant WHERE Menu.Restaurant=Restaurant.id AND Restaurant=? "
					+ "AND Restaurant.restaurant_owner=?";
			statement= conn.prepareStatement(AllMenusByRestaurant);
			statement.setInt(1, RestaurantId);
			statement.setInt(2, ownerId);
			resultset = statement.executeQuery();
			while(resultset.next()) {
				int id= resultset.getInt("id");
				String item_name = resultset.getString("item_name");
				int item_type = resultset.getInt("item_type");
				int price = resultset.getInt("price");
				String description = resultset.getString("description");
				int restaurant1 = resultset.getInt("Restaurant");
				Menu menu = new Menu(id,item_name,item_type,price,description,restaurant1);
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
	
	//this function updates the value of a menu item according to the given values
	// given the restaurant id and its owner id and the id of the item to be updated
	public int updateMenuItem (int id,int RestId, int ownerId, Menu menu){
		int result = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String RestaurantUpdate = "UPDATE Menu m INNER JOIN Restaurant r ON m.Restaurant = r.id "
					+ "SET item_name = ? ,item_type = ? ,price= ? ,m.description = ?  "
					+ "WHERE m.Restaurant=? AND m.id = ? AND r.restaurant_owner = ?";
			statement = conn.prepareStatement(RestaurantUpdate);
			statement.setString(1, menu.getItem_name());
			statement.setInt(2, menu.getItem_type());
			statement.setFloat(3, menu.getPrice());
			statement.setString(4, menu.getDescription());
			statement.setInt(5, RestId);
			statement.setInt(6, id);
			statement.setInt(7, ownerId);

			result = statement.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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
	
	public int deleteMenuForRestaurant(int id,int ownerId, int restId) {
		int result = -1;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String deleteMenu = "DELETE m FROM Menu m INNER JOIN Restaurant r ON m.Restaurant = r.id WHERE m.Restaurant=? AND m.id = ? AND r.restaurant_owner = ?";
			statement=conn.prepareStatement(deleteMenu);
			statement.setInt(1,restId);
			statement.setInt(2,id);
			statement.setInt(3,ownerId);
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
