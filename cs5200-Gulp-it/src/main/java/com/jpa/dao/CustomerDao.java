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
import com.jpa.models.User;

/**
 * 
 * @author priyalmittal
 *
 */
public class CustomerDao {

	private static final String URL = "jdbc:mysql://cs5200-spring2018-mittal.c9fddtskt253.us-east-2.rds.amazonaws.com/GulpIt";
	private static final String USERNAME = "Mpriyal";
	private static final String PASSWORD = "Priyaldbms94!";
	private static final String CREATE_PERSON = "INSERT INTO Person (firstName, lastName, username, password,email,dob,type)"
			+ "VALUES (?,?,?,?,?,?,?)";
	private static final String CREATE_CUSTOMER = "INSERT INTO Customer (customer_key, Person) VALUES (?,LAST_INSERT_ID())";
	private static final String FIND_ALL_CUSTOMERS = "SELECT * FROM Customer, Person WHERE Customer.Person = Person.Id";
	private static final String FIND_CUSTOMER_ID = "SELECT * FROM Customer, Person WHERE Customer.Person = Person.Id AND "
			+ "Person.Id =?";
	private static final String FIND_CUSTOMER_USERNAME = "SELECT * FROM Customer, Person WHERE Customer.Person = Person.Id AND "
			+ "Person.username =?";
	private static final String FIND_CUSTOMER_CREDENTIALS = "SELECT * FROM Customer, Person WHERE Customer.Person = Person.Id AND"
			+ " Person.username =? AND Person.password =?";
	private static final String UPDATE_CUSTOMER = "UPDATE Person, Customer SET firstName =?, lastName =?,username=?, password =?,dob =?,email=?"
			+ ",customer_key=? WHERE Customer.Person = Person.Id AND Person.Id =?";
	private static final String DELETE_CUSTOMER = "DELETE p.*, c.* FROM Person p LEFT JOIN Customer c ON c.Person = p.id WHERE p.id =?";
	private static final String FIND_CUSTOMER_ID_BY_USERNAME = "SELECT c.Person FROM Customer c, Person p WHERE c.Person = p.id AND username =?";
	private static final String FIND_PERSON_ID_BY_CUSTOMER_ID = "SELECT Person FROM Customer WHERE Customer.Id=?";
	private static final String FIND_CUSTID_BY_PERSON = "SELECT Id FROM Customer WHERE Person=?";

	public static CustomerDao instance = null;
	
	public static CustomerDao getInstance() {
		if(instance==null) {
			instance = new CustomerDao();
		}
		return instance;
	}

	private CustomerDao() {}

	// this function is to create a person;
	public int createCustomer(Customer Customer) {
		Connection connection = null;
		PreparedStatement statement = null;
		int result  = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			statement = connection.prepareStatement(CREATE_PERSON);
			statement.setString(1, Customer.getFirstName());
			statement.setString(2, Customer.getLastName());
			statement.setString(3, Customer.getUsername());
			statement.setString(4, Customer.getPassword());
			statement.setString(5, Customer.getEmail());
			statement.setDate(6, Customer.getDOB());
			statement.setString(7, Customer.getType());
			result = statement.executeUpdate();
			statement = connection.prepareStatement(CREATE_CUSTOMER);
			statement.setString(1, Customer.getCustomerKey());
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
	public List<Customer> findAllCustomers(){
		List<Customer> customers = new ArrayList<Customer>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			statement = connection.createStatement();
			result = statement.executeQuery(FIND_ALL_CUSTOMERS);
			while(result.next()) {
				String firstName = result.getString("firstName");
				String lastName = result.getString("lastName");
				String username = result.getString("username");
				String password = result.getString("password");
				String email = result.getString("email");
				Date dob = result.getDate("dob");
				String type = result.getString("type");
				String customer_key = result.getString("customer_key");
				int Person = result.getInt("Person");
				Customer Customer = new Customer(Person, firstName, lastName, username, password, email, dob, type, customer_key);
				customers.add(Customer);
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
		return customers;
	}
	
	//this function is to find a customer, given its id
	public Customer findCustomerById(int customerId){
		Customer customer = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			statement = connection.prepareStatement(FIND_CUSTOMER_ID);
			statement.setInt(1, customerId);
			result = statement.executeQuery();
			if(result.next()) {
				String firstName = result.getString("firstName");
				String lastName = result.getString("lastName");
				String username = result.getString("username");
				String password = result.getString("password");
				String email = result.getString("email");
				Date dob = result.getDate("dob");
				String type = result.getString("type");
				String customer_key = result.getString("customer_key");
				int Person = result.getInt("Person");
				customer = new Customer(Person, firstName, lastName, username, password,email, dob,type, customer_key);
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
	
		//this function is to to find a customer, given its username
		public Customer findCustomerByUsername(String username){
			Customer customer = null;
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet result = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				statement = connection.prepareStatement(FIND_CUSTOMER_USERNAME);
				statement.setString(1, username);
				result = statement.executeQuery();
				if(result.next()) {
					String firstName = result.getString("firstName");
					String lastName = result.getString("lastName");
					String username1 = result.getString("username");
					String password = result.getString("password");
					String email = result.getString("email");
					Date dob = result.getDate("dob");
					String type = result.getString("type");
					int Person = result.getInt("Person");
					String customer_key = result.getString("customer_key");
					customer = new Customer(Person, firstName, lastName, username1, password,email, dob,type, customer_key);
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
		
		//this function is to to find a customer id, given its username
		//returns person id instead of customer id
		public int findCustomerIdByUsername(String user){
			int customer_id = 0;
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet result = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				statement = connection.prepareStatement(FIND_CUSTOMER_ID_BY_USERNAME);
				statement.setString(1, user);
				result = statement.executeQuery();
				if(result.next()) {
				int Person = result.getInt("Person");
				customer_id = Person;
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
			return customer_id;
			}
		
		//this function is to find a customer, given its credentials (username, password)
		public Customer findCustomerByCredentials(String username, String password){
			Customer customer = null;
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet result = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				statement = connection.prepareStatement(FIND_CUSTOMER_CREDENTIALS);
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
					String customer_key = result.getString("customer_key");
					customer = new Customer(Person, firstName, lastName, username1, password1, email, dob,type, customer_key);
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
	

		//this function is to update data of a customer
		//given customerId indicates PersonId
		public int updateCustomer(int customerId, Customer customer){
			Connection connection = null;
			PreparedStatement statement = null;
			int result = 0;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				statement = connection.prepareStatement(UPDATE_CUSTOMER);
				statement.setString(1, customer.getFirstName());
				statement.setString(2, customer.getLastName());
				statement.setString(3, customer.getUsername());
				statement.setString(4, customer.getPassword());
				statement.setDate(5, customer.getDOB());
				statement.setString(6, customer.getEmail());
				statement.setString(7, customer.getCustomerKey());
				statement.setInt(8, customerId);
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
		//given customerId indicates PersonId
		public int deleteCustomer(int customerId){
			Connection connection = null;
			PreparedStatement statement = null;
			int result = 0;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				statement = connection.prepareStatement(DELETE_CUSTOMER);
				statement.setInt(1, customerId);
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
		
		public int findPersonIdBycustomerId(int CustId){
			int person_id = 0;
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet result = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				statement = connection.prepareStatement(FIND_PERSON_ID_BY_CUSTOMER_ID);
				statement.setInt(1, CustId);
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
		
		public int findCustIdByPersonId(int PersonId){
			int owner_id = 0;
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet result = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				statement = connection.prepareStatement(FIND_CUSTID_BY_PERSON);
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
		
		public static void main(String[] args) {
			
			CustomerDao cDao = new CustomerDao();
			Customer rubi = new Customer("Rubi","Coffee","rub","rub","rubi@neu.edu",null,"Customer","rub123");
			Customer rubi_new = new Customer("RubiNew","Coffee","rub","rub12","rubi@neu.edu",null,"Customer","rub1234");
//			cDao.createCustomer(rubi);
			Customer delete_me = new Customer("Delete","Karo","d","me","delete@neu.edu",null,"Customer","del123");
			System.out.println(cDao.findCustIdByPersonId(28));
////			cDao.createCustomer(delete_me);
//			System.out.println(cDao.findAllCustomers());
//			System.out.println(cDao.findCustomerByCredentials(rubi.getUsername(), rubi.getPassword()));
//			System.out.println(cDao.findCustomerById(cDao.findCustomerIdByUsername("priyal")));
//			cDao.updateCustomer(cDao.findCustomerIdByUsername(rubi.getUsername()), rubi_new);
//			System.out.println(cDao.findAllCustomers());
//			cDao.deleteCustomer(cDao.findCustomerIdByUsername(delete_me.getUsername()));
//			System.out.println(cDao.findAllCustomers());
			
		}
}
