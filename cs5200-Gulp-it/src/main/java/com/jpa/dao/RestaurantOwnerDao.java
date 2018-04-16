package com.jpa.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jpa.models.RestaurantOwner;
import com.jpa.models.User;

public class RestaurantOwnerDao {

	private static final String URL = "jdbc:mysql://cs5200-spring2018-mittal.c9fddtskt253.us-east-2.rds.amazonaws.com/GulpIt";
	private static final String USERNAME = "Mpriyal";
	private static final String PASSWORD = "Priyaldbms94!";
	private static final String CREATE_PERSON = "INSERT INTO Person (firstName, lastName, username, password,email,dob)"
			+ "VALUES (?,?,?,?,?,?)";
	private static final String CREATE_RESTAURANT_OWNER = "INSERT INTO restaurant_owner (owner_key, Person) VALUES (?,LAST_INSERT_ID())";
	private static final String FIND_ALL_RESTAURANT_OWNERS = "SELECT * FROM restaurant_owner, Person WHERE restaurant_owner.Person = Person.Id";
	private static final String FIND_RESTAURANT_OWNER_ID = "SELECT * FROM restaurant_owner, Person WHERE restaurant_owner.Person = Person.id AND "
			+ "Person.id =?";
	private static final String FIND_RESTAURANT_OWNER_USERNAME = "SELECT * FROM restaurant_owner, Person WHERE restaurant_owner.Person = Person.id AND "
			+ "Person.username =?";
	private static final String FIND_RESTAURANT_OWNER_CREDENTIALS = "SELECT * FROM restaurant_owner, Person WHERE restaurant_owner.Person = Person.id AND"
			+ " Person.username =? AND Person.password =?";
	private static final String UPDATE_RESTAURANT_OWNER = "UPDATE Person, restaurant_owner SET Person.id =?, firstName =?, lastName =?, username =?, password =?, email =?, dob =?, "
			+ "owner_key =? WHERE restaurant_owner.Person = Person.id AND Person.id =?";
	private static final String DELETE_RESTAURANT_OWNER = "DELETE FROM restaurant_owner WHERE restaurant_owner.id =?";

	public static RestaurantOwnerDao instance = null;
	public static RestaurantOwnerDao getInstance() {
		if(instance==null) {
			instance = new RestaurantOwnerDao();
		}
		return instance;
	}

	private RestaurantOwnerDao() {}

	// this function is to create a person;
	public int createRestaurantOwner(RestaurantOwner RestaurantOwner) {
		Connection connection = null;
		PreparedStatement statement = null;
		int result  = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			statement = connection.prepareStatement(CREATE_PERSON);
			statement.setString(1, RestaurantOwner.getFirstName());
			statement.setString(2, RestaurantOwner.getLastName());
			statement.setString(3, RestaurantOwner.getUsername());
			statement.setString(4, RestaurantOwner.getPassword());
			statement.setString(5, RestaurantOwner.getEmail());
			statement.setDate(6, RestaurantOwner.getDOB());
			result = statement.executeUpdate();
			statement = connection.prepareStatement(CREATE_RESTAURANT_OWNER);
			statement.setString(1, RestaurantOwner.getOwnerKey());
			result = statement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	//this function is used to find all the customers
	public List<RestaurantOwner> findAllRestaurantOwners(){
		List<RestaurantOwner> RestaurantOwners = new ArrayList<RestaurantOwner>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			statement = connection.createStatement();
			result = statement.executeQuery(FIND_ALL_RESTAURANT_OWNERS);
			while(result.next()) {
				String firstName = result.getString("firstName");
				String lastName = result.getString("lastName");
				String username = result.getString("username");
				String password = result.getString("password");
				String email = result.getString("email");
				Date dob = result.getDate("dob");
				String owner_key = result.getString("owner_key");
				int Person = result.getInt("Person");
				RestaurantOwner RestaurantOwner = new RestaurantOwner(Person, firstName, lastName, username, password, email, dob, owner_key);
				RestaurantOwners.add(RestaurantOwner);
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
		return RestaurantOwners;
	}
	
	//this function is to find a customer, given its id
	public RestaurantOwner findRestaurantOwnerById(int RestaurantOwnerId){
		RestaurantOwner RestaurantOwner = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			statement = connection.prepareStatement(FIND_RESTAURANT_OWNER_ID);
			statement.setInt(1, RestaurantOwnerId);
			result = statement.executeQuery();
			if(result.next()) {
				String firstName = result.getString("firstName");
				String lastName = result.getString("lastName");
				String username = result.getString("username");
				String password = result.getString("password");
				String email = result.getString("email");
				Date dob = result.getDate("dob");
				String owner_key = result.getString("owner_key");
				int Person = result.getInt("Person");
				RestaurantOwner = new RestaurantOwner(Person, firstName, lastName, username, password,email, dob, owner_key);
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
		return RestaurantOwner;
		}
	
		//this function is to to find a customer, given its username
		public RestaurantOwner findRestaurantOwnerByUsername(String username){
			RestaurantOwner RestaurantOwner = null;
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet result = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				statement = connection.prepareStatement(FIND_RESTAURANT_OWNER_USERNAME);
				statement.setString(1, username);
				result = statement.executeQuery();
				if(result.next()) {
					int Id = result.getInt("Id");
					String firstName = result.getString("firstName");
					String lastName = result.getString("lastName");
					String username1 = result.getString("username");
					String password = result.getString("password");
					String email = result.getString("email");
					Date dob = result.getDate("dob");
					String owner_key = result.getString("owner_key");
					RestaurantOwner = new RestaurantOwner(Id, firstName, lastName, username1, password,email, dob, owner_key);
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
			return RestaurantOwner;
			}
		
		//this function is to find a customer, given its credentials (username, password)
		public RestaurantOwner findRestaurantOwnerByCredentials(String username, String password){
			RestaurantOwner RestaurantOwner = null;
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet result = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				statement = connection.prepareStatement(FIND_RESTAURANT_OWNER_CREDENTIALS);
				statement.setString(1, username);
				statement.setString(2, password);
				result = statement.executeQuery();
				if(result.next()) {
					int Id = result.getInt("Id");
					String firstName = result.getString("firstName");
					String lastName = result.getString("lastName");
					String username1 = result.getString("username");
					String password1 = result.getString("password");
					String email = result.getString("email");
					Date dob = result.getDate("dob");
					String owner_key = result.getString("owner_key");
					RestaurantOwner = new RestaurantOwner(Id, firstName, lastName, username1, password1, email, dob, owner_key);
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
			return RestaurantOwner;
			}
	

		//this function is to update data of a customer
		public int updateRestaurantOwner(int RestaurantOwnerId, RestaurantOwner RestaurantOwner){
			Connection connection = null;
			PreparedStatement statement = null;
			int result = 0;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				statement = connection.prepareStatement(UPDATE_RESTAURANT_OWNER);
				statement.setInt(1, RestaurantOwner.getId());
				statement.setString(2, RestaurantOwner.getFirstName());
				statement.setString(3, RestaurantOwner.getLastName());
				statement.setString(4, RestaurantOwner.getUsername());
				statement.setString(5, RestaurantOwner.getPassword());
				statement.setString(6, RestaurantOwner.getEmail());
				statement.setDate(7, RestaurantOwner.getDOB());
				statement.setString(8, RestaurantOwner.getOwnerKey());
				statement.setInt(9, RestaurantOwnerId);
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
		
		//this function is to delete a customer
		public int deleteRestaurantOwner(int RestaurantOwnerId){
			Connection connection = null;
			PreparedStatement statement = null;
			int result = 0;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				statement = connection.prepareStatement(DELETE_RESTAURANT_OWNER);
				statement.setInt(1, RestaurantOwnerId);
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
}
