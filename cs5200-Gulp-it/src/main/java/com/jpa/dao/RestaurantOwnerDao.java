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

import com.jpa.models.Customer;
import com.jpa.models.RestaurantOwner;
import com.jpa.models.User;

public class RestaurantOwnerDao {

	private static final String URL = "jdbc:mysql://cs5200-spring2018-mittal.c9fddtskt253.us-east-2.rds.amazonaws.com/GulpIt";
	private static final String USERNAME = "Mpriyal";
	private static final String PASSWORD = "Priyaldbms94!";
	private static final String CREATE_PERSON = "INSERT INTO Person (firstName, lastName, username, password,email,dob,type)"
			+ "VALUES (?,?,?,?,?,?,?)";
	private static final String CREATE_OWNER = "INSERT INTO restaurant_owner (owner_key, Person) VALUES (?,LAST_INSERT_ID())";
	private static final String FIND_ALL_OWNERS = "SELECT * FROM restaurant_owner, Person WHERE restaurant_owner.Person = Person.Id";
	private static final String FIND_OWNER_ID = "SELECT * FROM restaurant_owner, Person WHERE restaurant_owner.Person = Person.Id AND "
			+ "restaurant_owner.Person =?";
	private static final String FIND_OWNER_USERNAME = "SELECT * FROM restaurant_owner, Person WHERE restaurant_owner.Person = Person.Id AND "
			+ "Person.username =?";
	private static final String FIND_OWNER_CREDENTIALS = "SELECT * FROM restaurant_owner, Person WHERE restaurant_owner.Person = Person.Id AND"
			+ " Person.username =? AND Person.password =?";
	private static final String UPDATE_OWNER = "UPDATE Person, restaurant_owner SET firstName =?, lastName =?,username=?, password =?,dob =?, email=?"
			+ " ,owner_key=? WHERE restaurant_owner.Person = Person.Id AND restaurant_owner.Person =?";
	private static final String DELETE_OWNER = "DELETE p.*, r.* FROM Person p LEFT JOIN restaurant_owner r ON r.Person = p.id WHERE r.Person =?";
	private static final String FIND_OWNER_ID_BY_USERNAME = "SELECT r.Person FROM restaurant_owner r, Person p WHERE r.Person = p.id AND username =?";
	private static final String FIND_OWNERID_BY_PERSON = "SELECT Id FROM restaurant_owner WHERE Person=?";
	private static final String FIND_OWNER_RESTAURANT_ID = "SELECT restaurant_owner FROM Restaurant WHERE id=?";
	private static final String FIND_PERSON_ID_BY_USER_ID = "SELECT Person FROM restaurant_owner WHERE restaurant_owner.Id=?";
	
	public static RestaurantOwnerDao instance = null;
	public static RestaurantOwnerDao getInstance() {
		if(instance==null) {
			instance = new RestaurantOwnerDao();
		}
		return instance;
	}

	private RestaurantOwnerDao() {}

	// this function is to create a restaurant owner;
	public int createOwner(RestaurantOwner Owner) {
		Connection connection = null;
		PreparedStatement statement = null;
		int result  = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			statement = connection.prepareStatement(CREATE_PERSON);
			statement.setString(1, Owner.getFirstName());
			statement.setString(2, Owner.getLastName());
			statement.setString(3, Owner.getUsername());
			statement.setString(4, Owner.getPassword());
			statement.setString(5, Owner.getEmail());
			statement.setDate(6, Owner.getDOB());
			statement.setString(7, Owner.getType());
			result = statement.executeUpdate();
			statement = connection.prepareStatement(CREATE_OWNER);
			statement.setString(1, Owner.getOwnerKey());
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

	//this function is used to find all the restaurant owners
	public List<RestaurantOwner> findAllOwners(){
		List<RestaurantOwner> owners = new ArrayList<RestaurantOwner>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			statement = connection.createStatement();
			result = statement.executeQuery(FIND_ALL_OWNERS);
			while(result.next()) {
				String firstName = result.getString("firstName");
				String lastName = result.getString("lastName");
				String username = result.getString("username");
				String password = result.getString("password");
				String email = result.getString("email");
				Date dob = result.getDate("dob");
				String type = result.getString("type");
				String owner_key = result.getString("owner_key");
				int Person = result.getInt("Person");
				RestaurantOwner Owner = new RestaurantOwner(Person, firstName, lastName, username, password, email, dob, type, owner_key);
				owners.add(Owner);
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
		return owners;
	}
	
	//this function is to find a restaurant owner, given its id
	//given ownerId is nothing but the PersonId
	public RestaurantOwner findOwnerById(int ownerId){
		RestaurantOwner owner = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			statement = connection.prepareStatement(FIND_OWNER_ID);
			statement.setInt(1, ownerId);
			result = statement.executeQuery();
			if(result.next()) {
				String firstName = result.getString("firstName");
				String lastName = result.getString("lastName");
				String username = result.getString("username");
				String password = result.getString("password");
				String email = result.getString("email");
				Date dob = result.getDate("dob");
				String type = result.getString("type");
				String owner_key = result.getString("owner_key");
				int Person = result.getInt("Person");
				owner = new RestaurantOwner(Person, firstName, lastName, username, password,email, dob,type, owner_key);
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
		return owner;
		}
	
		//this function is to to find a restaurant owner, given its username
		public RestaurantOwner findOwnerByUsername(String username){
			RestaurantOwner owner = null;
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet result = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				statement = connection.prepareStatement(FIND_OWNER_USERNAME);
				statement.setString(1, username);
				result = statement.executeQuery();
				if(result.next()) {
					String firstName = result.getString("firstName");
					String lastName = result.getString("lastName");
					String username1 = result.getString("username");
					String password1 = result.getString("password");
					String email = result.getString("email");
					Date dob = result.getDate("dob");
					String type = result.getString("type");
					int Person = result.getInt("Person");
					String owner_key = result.getString("owner_key");
					owner = new RestaurantOwner(Person, firstName, lastName, username1, password1,email, dob,type, owner_key);
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
			return owner;
			}
		
		//this function is to to find a restaurant owner id, given its username
		public int findOwnerIdByUsername(String user){
			int owner_id = 0;
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet result = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				statement = connection.prepareStatement(FIND_OWNER_ID_BY_USERNAME);
				statement.setString(1, user);
				result = statement.executeQuery();
				if(result.next()) {
				int Person = result.getInt("Person");
				owner_id = Person;
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
			return owner_id;
			}
		
		//this function is to find a restaurant owner, given its credentials (username, password)
		public RestaurantOwner findOwnerByCredentials(String username, String password){
			RestaurantOwner owner = null;
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet result = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				statement = connection.prepareStatement(FIND_OWNER_CREDENTIALS);
				statement.setString(1, username);
				statement.setString(2, password);
				result = statement.executeQuery();
				if(result.next()) {
					String firstName = result.getString("firstName");
					String lastName = result.getString("lastName");
					String username1 = result.getString("username");
					String password1 = result.getString("password");
					String email = result.getString("email");
					Date dob = result.getDate("dob");
					String type = result.getString("type");
					int Person = result.getInt("Person");
					String owner_key = result.getString("owner_key");
					owner = new RestaurantOwner(Person, firstName, lastName, username1, password1, email, dob,type, owner_key);
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
			return owner;
			}
	

		//this function is to update data of a restaurant owner
		//given ownerId is nothing but the PersonId
		public int updateOwner(int ownerId, RestaurantOwner owner){
			Connection connection = null;
			PreparedStatement statement = null;
			int result = 0;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				statement = connection.prepareStatement(UPDATE_OWNER);
				statement.setString(1, owner.getFirstName());
				statement.setString(2, owner.getLastName());
				statement.setString(3, owner.getUsername());
				statement.setString(4, owner.getPassword());
				statement.setDate(5, owner.getDOB());
				statement.setString(6, owner.getEmail());
				statement.setString(7, owner.getOwnerKey());
				statement.setInt(8, ownerId);
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
		
		//this function is to delete a restaurant owner
		//given ownerId is nothing but the PersonId
		public int deleteOwner(int ownerId){
			Connection connection = null;
			PreparedStatement statement = null;
			int result = 0;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				statement = connection.prepareStatement(DELETE_OWNER);
				statement.setInt(1, ownerId);
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
		
		public int findOwnerIdByPersonId(int PersonId){
			int owner_id = 0;
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet result = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				statement = connection.prepareStatement(FIND_OWNERID_BY_PERSON);
				statement.setInt(1, PersonId);
				result = statement.executeQuery();
				if(result.next()) {
					int Id = result.getInt("id");
					owner_id = Id;
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
			return owner_id;
			}
		
		public int findPersonIdByOwnerId(int OwnerId){
			int person_id = 0;
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet result = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				statement = connection.prepareStatement(FIND_PERSON_ID_BY_USER_ID);
				statement.setInt(1, OwnerId);
				result = statement.executeQuery();
				if(result.next()) {
					int Person = result.getInt("Person");
					person_id = Person;
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
			return person_id;
			}
		
		public int findOwnerByRestaurantId(int restId){
			int owner_id = 0;
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet result = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				statement = connection.prepareStatement(FIND_OWNER_RESTAURANT_ID);
				statement.setInt(1, restId);
				result = statement.executeQuery();
				if(result.next()) {
					int restaurant_owner = result.getInt("restaurant_owner");
					owner_id = restaurant_owner;
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
			return owner_id;
			}
		
		public static void main(String[] args) {
			
			RestaurantOwnerDao rDao = new RestaurantOwnerDao();
			RestaurantOwner rest_rubi = new RestaurantOwner("RestRubi","RestCoffee","restrub","restrub","restrubi@neu.edu",null,"Restaurant Owner","restrub123");
			RestaurantOwner rest_rubi_new = new RestaurantOwner("RestRubiNew","RestCoffee","restrub","restrub12","restrubi@neu.edu",null,"Restaurant Owner","restrub1234");
//			rDao.createOwner(rest_rubi);
			RestaurantOwner delete_me = new RestaurantOwner("Delete","Karo","d","me","delete@neu.edu",null,"Customer","del123");
//			rDao.createOwner(delete_me);
//			System.out.println(rDao.findOwnerIdByPersonId(31));
//			System.out.println(rDao.findAllOwners());
//			System.out.println(rDao.findOwnerByCredentials(rest_rubi.getUsername(), rest_rubi.getPassword()));
//			System.out.println(rDao.findOwnerById(rDao.findOwnerIdByUsername(rest_rubi.getUsername())));
//			rDao.updateOwner(rDao.findOwnerIdByUsername(rest_rubi.getUsername()), rest_rubi_new);
//			System.out.println(rDao.findAllOwners());
//			rDao.deleteOwner(rDao.findOwnerIdByUsername(delete_me.getUsername()));
//			System.out.println(rDao.findAllOwners());
			
		}
}
