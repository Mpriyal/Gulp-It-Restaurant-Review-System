package com.jpa.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jpa.models.Customer;
import com.jpa.models.RestaurantOwner;
import com.jpa.models.User;

public class AdminDao {

	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	final String DB_URL = "jdbc:mysql://cs5200-spring2018-mittal.c9fddtskt253.us-east-2.rds.amazonaws.com/GulpIt";
	final String USER = "Mpriyal";
	final String PASS = "Priyaldbms94!";
	static Connection conn = null;
	static PreparedStatement statement = null;
	static ResultSet resultset = null;

	public static AdminDao instance = null;

	public static AdminDao getInstance() {
		if (instance == null) {
			instance = new AdminDao();
		}
		return instance;
	}
	private AdminDao() {}

	public List <User> UserfindAllUsers() {
		
		List <User> users = new ArrayList<User>();
		CustomerDao custumer = CustomerDao.getInstance();
		RestaurantOwnerDao restaurantOwner = RestaurantOwnerDao.getInstance();
		users.addAll(custumer.findAllCustomers());
		users.addAll(restaurantOwner.findAllRestaurantOwners());
		return users;
	
	}
	
	public Customer findCustomerById(int customerId){
		Customer customer = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			statement = connection.prepareStatement("Select * from Person where id=?");
			statement.setInt(1, customerId);
			result = statement.executeQuery();
			if(result.next()) {
				String firstName = result.getString("firstName");
				String lastName = result.getString("lastName");
				String username = result.getString("username");
				String password = result.getString("password");
				String email = result.getString("email");
				Date dob = result.getDate("dob");
				String customer_key = result.getString("customer_key");
				int Person = result.getInt("Person");
				customer = new Customer(Person, firstName, lastName, username, password,email, dob, customer_key);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return customer;
		}
	/**
	 * @author amanrayat
	 * @param UserId
	 * @return
	 */
	public int deleteUserById(int UserId) {
		
			Connection connection = null;
			PreparedStatement statement = null;
			int result = 0;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(DB_URL, USER, PASS);
				statement = connection.prepareStatement("DELETE FROM Person WHERE id =?");
				statement.setInt(1, UserId);
				result = statement.executeUpdate();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return result;
			}
	
	public void createRestaurantOwner(RestaurantOwner restaurantOwner) {
		RestaurantOwnerDao dao = RestaurantOwnerDao.getInstance();
		dao.createRestaurantOwner(restaurantOwner);
	}
	public void CreateCustomer (Customer customer) {
		CustomerDao dao = CustomerDao.getInstance();
		dao.createCustomer(customer);
	}
//	public void updateUser(User user) {
//		if(user.)
//	}
}

