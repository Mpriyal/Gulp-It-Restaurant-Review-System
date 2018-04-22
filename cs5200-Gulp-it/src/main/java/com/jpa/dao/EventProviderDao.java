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

import com.jpa.models.EventProvider;

public class EventProviderDao {
	
	private static final String URL = "jdbc:mysql://cs5200-spring2018-mittal.c9fddtskt253.us-east-2.rds.amazonaws.com/GulpIt";
	private static final String USERNAME = "Mpriyal";
	private static final String PASSWORD = "Priyaldbms94!";
	
	private static final String CREATE_PERSON = "INSERT INTO Person (firstName, lastName, username, password,email,dob,type)"
			+ "VALUES (?,?,?,?,?,?,?)";
	private static final String CREATE_EVENT_PROVIDER = "INSERT INTO event_provider (event_key, Person) VALUES (?,LAST_INSERT_ID())";
	private static final String FIND_ALL_EVENT_PROVIDERS = "SELECT * FROM event_provider, Person WHERE event_provider.Person = Person.Id";
	private static final String FIND_EVENT_PROVIDER_ID = "SELECT * FROM event_provider, Person WHERE event_provider.Person = Person.Id AND "
			+ "Person.Id =?";
	private static final String FIND_EVENT_PROVIDER_USERNAME = "SELECT * FROM event_provider, Person WHERE event_provider.Person = Person.Id AND "
			+ "Person.username =?";
	private static final String FIND_EVENT_PROVIDER_CREDENTIALS = "SELECT * FROM event_provider, Person WHERE event_provider.Person = Person.Id AND"
			+ " Person.username =? AND Person.password =?";
	private static final String FIND_EVENT_PROVIDER_ID_BY_USERNAME = "SELECT e.Person FROM event_provider e, Person p WHERE e.Person = p.id AND username =?";
	private static final String FIND_PERSON_ID_BY_PROVIDER_ID = "SELECT Person FROM event_provider WHERE event_provider.Id=?";
	private static final String FIND_PROVIDERID_BY_PERSON = "SELECT id FROM event_provider WHERE Person=?";
	private static final String UPDATE_EVENT_PROVIDER = "UPDATE Person, event_provider SET firstName =?, lastName =?,username=?, password =?,dob =?,email=?"
			+ ",event_key=? WHERE event_provider.Person = Person.Id AND Person.Id =?";
	private static final String DELETE_EVENT_PROVIDER = "DELETE p.*, e.* FROM Person p LEFT JOIN event_provider e ON e.Person = p.id WHERE p.id =?";
	private static final String ADD_EVENT_RESTAURANT = "INSERT INTO Event2Restaurant(Event,Restaurant) VALUES((SELECT id from Event WHERE id = ? AND event_provider=?),?);";
	private static final String FIND_EVENTPROVIDER_EVENT_ID = "SELECT event_provider FROM Event WHERE id=?";
	private static final String DELETE_EVENT_RESTAURANT = "DELETE FROM Event2Restaurant WHERE (SELECT id from Event WHERE id = ? AND event_provider=?) "
			+ "AND Restaurant=?";
	public static EventProviderDao instance = null;
	public static EventProviderDao getInstance() {
		if (instance == null) {
			instance = new EventProviderDao();
		}
		return instance;
	}
	
	private EventProviderDao() {}
	
	/**
	 * 
	 * @param EventProvider
	 * @return
	 */
	public int createEventProvider(EventProvider EventProvider) {
		Connection connection = null;
		PreparedStatement statement = null;
		int result  = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			statement = connection.prepareStatement(CREATE_PERSON);
			statement.setString(1, EventProvider.getFirstName());
			statement.setString(2, EventProvider.getLastName());
			statement.setString(3, EventProvider.getUsername());
			statement.setString(4, EventProvider.getPassword());
			statement.setString(5, EventProvider.getEmail());
			statement.setDate(6, EventProvider.getDOB());
			statement.setString(7, EventProvider.getType());
			result = statement.executeUpdate();
			statement = connection.prepareStatement(CREATE_EVENT_PROVIDER);
			statement.setString(1, EventProvider.getEvent_key());
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

	//this function is used to find all the event providers
	public List<EventProvider> findAllEventProviders(){
		List<EventProvider> eventProviders = new ArrayList<EventProvider>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			statement = connection.createStatement();
			result = statement.executeQuery(FIND_ALL_EVENT_PROVIDERS);
			while(result.next()) {
				String firstName = result.getString("firstName");
				String lastName = result.getString("lastName");
				String username = result.getString("username");
				String password = result.getString("password");
				String email = result.getString("email");
				Date dob = result.getDate("dob");
				String type = result.getString("type");
				String event_key = result.getString("event_key");
				int Person = result.getInt("Person");
				EventProvider EventProvider = new EventProvider(Person, firstName, lastName, username, password, email, dob, type, event_key);
				eventProviders.add(EventProvider);
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
		return eventProviders;
	}
	
	//this function is to find a event provider, given its id
	public EventProvider findEventProviderById(int providerId){
		EventProvider provider = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			statement = connection.prepareStatement(FIND_EVENT_PROVIDER_ID);
			statement.setInt(1, providerId);
			result = statement.executeQuery();
			if(result.next()) {
				String firstName = result.getString("firstName");
				String lastName = result.getString("lastName");
				String username = result.getString("username");
				String password = result.getString("password");
				String email = result.getString("email");
				Date dob = result.getDate("dob");
				String type = result.getString("type");
				String event_key = result.getString("event_key");
				int Person = result.getInt("Person");
				provider = new EventProvider(Person, firstName, lastName, username, password,email, dob,type, event_key);
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
		return provider;
		}
	
		//this function is to to find an event provider, given its username
		public EventProvider findEventProviderByUsername(String username){
			EventProvider provider = null;
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet result = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				statement = connection.prepareStatement(FIND_EVENT_PROVIDER_USERNAME);
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
					String event_key = result.getString("event_key");
					provider = new EventProvider(Person, firstName, lastName, username1, password,email, dob,type, event_key);
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
			return provider;
			}
		
		//this function is to to find an event provider id, given its username
		//returns person id instead of event provider id
		public int findEventProviderIdByUsername(String user){
			int provider_id = 0;
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet result = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				statement = connection.prepareStatement(FIND_EVENT_PROVIDER_ID_BY_USERNAME);
				statement.setString(1, user);
				result = statement.executeQuery();
				if(result.next()) {
				int Person = result.getInt("Person");
				provider_id = Person;
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
			return provider_id;
			}
		
		//this function is to find an event provider, given its credentials (username, password)
		public EventProvider findEventProviderByCredentials(String username, String password){
			EventProvider provider = null;
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet result = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				statement = connection.prepareStatement(FIND_EVENT_PROVIDER_CREDENTIALS);
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
					String event_key = result.getString("event_key");
					provider = new EventProvider(Person, firstName, lastName, username1, password1, email, dob,type, event_key);
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
			return provider;
			}
		
		public int findPersonIdByProviderId(int provId){
			int person_id = 0;
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet result = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				statement = connection.prepareStatement(FIND_PERSON_ID_BY_PROVIDER_ID);
				statement.setInt(1, provId);
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
		
		public int findProvIdByPersonId(int PersonId){
			int provider_id = 0;
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet result = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				statement = connection.prepareStatement(FIND_PROVIDERID_BY_PERSON);
				statement.setInt(1, PersonId);
				result = statement.executeQuery();
				if(result.next()) {
					int Id = result.getInt("id");
					provider_id = Id;
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
			return provider_id;
			}
		
		//this function is to update data of an event provider
		//given providerId indicates PersonId
		public int updateProvider(int providerId, EventProvider provider){
			Connection connection = null;
			PreparedStatement statement = null;
			int result = 0;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				statement = connection.prepareStatement(UPDATE_EVENT_PROVIDER);
				statement.setString(1, provider.getFirstName());
				statement.setString(2, provider.getLastName());
				statement.setString(3, provider.getUsername());
				statement.setString(4, provider.getPassword());
				statement.setDate(5, provider.getDOB());
				statement.setString(6, provider.getEmail());
				statement.setString(7, provider.getEvent_key());
				statement.setInt(8, providerId);
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
		
		//this function is to delete an event provider
		//given providerId indicates PersonId
		public int deleteProvider(int providerId){
			Connection connection = null;
			PreparedStatement statement = null;
			int result = 0;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				statement = connection.prepareStatement(DELETE_EVENT_PROVIDER);
				statement.setInt(1, providerId);
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
		
		public int addEventToRestaurant(int EventId,int RestId, int ProviderId) {
			int result = -1;
			Connection connection = null;
			PreparedStatement statement = null;
			try {

				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				statement = connection.prepareStatement(ADD_EVENT_RESTAURANT);
				statement.setInt(1, EventId);
				statement.setInt(2, ProviderId);
				statement.setInt(3, RestId);
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
		
		public int deleteEventFromRestaurant(int eventId, int restId, int providerId){
			Connection connection = null;
			PreparedStatement statement = null;
			int result = 0;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				statement = connection.prepareStatement(DELETE_EVENT_RESTAURANT);
				statement.setInt(1, eventId);
				statement.setInt(2, providerId);
				statement.setInt(3, restId);
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
		
		public int findEventProviderByEventId(int eventId){
			int provider_id = 0;
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet result = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				statement = connection.prepareStatement(FIND_EVENTPROVIDER_EVENT_ID);
				statement.setInt(1, eventId);
				result = statement.executeQuery();
				if(result.next()) {
					int event_provider = result.getInt("event_provider");
					provider_id = event_provider;
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
			return provider_id;
			}

}
