package com.jpa.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jpa.models.Event;
/**
 * 
 * @author priyalmittal
 *
 */
public class EventDao {
	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	final String DB_URL = "jdbc:mysql://cs5200-spring2018-mittal.c9fddtskt253.us-east-2.rds.amazonaws.com/GulpIt";
	final String USER = "Mpriyal";
	final String PASS = "Priyaldbms94!";
	private static final String DELETE_EVENT = "DELETE FROM Event WHERE Event.id=? AND event_provider=?";
	static Connection conn = null;
	static PreparedStatement statement = null;
	static ResultSet resultset = null;

	public static EventDao instance = null;

	public static EventDao getInstance() {
		if (instance == null) {
			instance = new EventDao();
		}
		return instance;
	}
	private EventDao() {}

	/**
	 * This function is used to Add a new Event
	 * @param event
	 * @param ProviderId
	 * @return
	 */
	public int addEventForProvider(Event event,int ProviderId) {
		int result = -1;
		try {

			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String addEvent = "INSERT INTO Event "
					+ "(`event_name`,`description`,`date`,`event_provider`) "
					+ "VALUES (?,?,?,?)";
			statement= conn.prepareStatement(addEvent);

			statement.setString(1, event.getEvent_name());
			statement.setString(2, event.getDescription());
			statement.setDate(3, event.getDate());
			statement.setInt(4,ProviderId);
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
	
	/**
	 * This method is used to find all Events 
	 * @return
	 */
	public List<Event> findAllEvents() {
		List <Event> events = new ArrayList<>();
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String AllEvents = "SELECT * FROM Event";
			statement= conn.prepareStatement(AllEvents);
			resultset = statement.executeQuery();
			while(resultset.next()) {
				int id= resultset.getInt("id");
				String event_name = resultset.getString("event_name");
				String description = resultset.getString("description");
				Date date = resultset.getDate("date");
				int event_provider = resultset.getInt("event_provider");

				Event event = new Event(id,event_name,description,date,event_provider);
				events.add(event);
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
		return events;
	}

	/**
	 * This method is used to find Events by Names
	 * @param eventName
	 * @return
	 */
	public List<Event> findAllEventsByName(String eventName) {
		List <Event> events = new ArrayList<>();
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String EventName = "SELECT * FROM Event WHERE event_name LIKE ?";
			statement= conn.prepareStatement(EventName);
			statement.setString(1,"%" + eventName + "%");
			resultset = statement.executeQuery();
			while(resultset.next()) {
				int id= resultset.getInt("id");
				String event_name = resultset.getString("event_name");
				String description = resultset.getString("description");
				Date date = resultset.getDate("date");
				int event_provider = resultset.getInt("event_provider");

				Event event = new Event(id,event_name,description,date,event_provider);
				events.add(event);
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
		return events;
	}
	
	/**
	 * This method is used to find Event by Id
	 * @param EventId
	 * @return
	 */
	public Event findEventById(int EventId) {
		Event event =null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String EventById = "SELECT * FROM Event WHERE id = ?";
			statement= conn.prepareStatement(EventById);
			statement.setInt(1,EventId);
			resultset = statement.executeQuery();
			if(resultset.next()){
				int id= resultset.getInt("id");
				String event_name = resultset.getString("event_name");
				String description = resultset.getString("description");
				Date date = resultset.getDate("date");
				int event_provider = resultset.getInt("event_provider");
				event = new Event(id,event_name,description,date,event_provider);
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
		return event;
	}
	
	/**
	 * This method is used to find Events by Event Provider
	 * @param provider
	 * @return
	 */
	public List<Event> findAllEventByProvider(int provider) {
		List <Event> events = new ArrayList<>();
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String AllEvent = "SELECT * FROM Event WHERE event_provider=?";
			statement= conn.prepareStatement(AllEvent);
			statement.setInt(1, provider);
			resultset = statement.executeQuery();
			while(resultset.next()) {
				int id= resultset.getInt("id");
				String event_name = resultset.getString("event_name");
				String description = resultset.getString("description");
				Date date = resultset.getDate("date");
				int event_provider = resultset.getInt("event_provider");

				Event event = new Event(id,event_name,description,date,event_provider);
				events.add(event);
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
		return events;
	}
	
	/**
	 * This function is used for updating the information about the event 
	 * @param ProviderId
	 * @param event
	 * @param eventId
	 * @return
	 */
	public int updateEvent (int ProviderId, Event event, int eventId){
		int result = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String EventUpdate = "UPDATE Event SET event_name =?, description =? ,\n" + 
					"date =? WHERE id = ? and event_provider=?";
			statement = conn.prepareStatement(EventUpdate);
			statement.setString(1, event.getEvent_name());
			statement.setString(2, event.getDescription());
			statement.setDate(3, event.getDate());
			statement.setFloat(4, eventId);
			statement.setInt(5, ProviderId);
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
	
	//this function is to delete an event
	public int deleteEvent(int eventId, int provider_id){
		Connection connection = null;
		PreparedStatement statement = null;
		int result = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			statement = connection.prepareStatement(DELETE_EVENT);
			statement.setInt(1, eventId);
			statement.setInt(2, provider_id);
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
	
	public Event findEventByProviderId(int EventId, int ProviderId) {
		Event event =null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String RestaurantByName = "SELECT * FROM Event WHERE id = ? AND event_provider =?";
			statement= conn.prepareStatement(RestaurantByName);
			statement.setInt(1,EventId);
			statement.setInt(2,ProviderId);
			resultset = statement.executeQuery();
			if(resultset.next()){
				int id= resultset.getInt("id");
				String event_name = resultset.getString("event_name");
				String description = resultset.getString("description");
				Date date = resultset.getDate("date");
				int event_provider = resultset.getInt("event_provider");
				event = new Event(id,event_name,description,date,event_provider);
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
		return event;
	}
	
	public static void main(String[] args) {
		EventDao eDao = new EventDao();
		Event event = new Event(1,"new event","this is a new event",null,2);
//		rDao.addRestaurantForOwner(shawarma, 18);
//		System.out.println(rDao.findAllRestaurantByName("amelia"));
//		System.out.println(rDao.findAllRestaurantByCity("bos"));
//		System.out.println(rDao.findAllRestaurantByCountry("U"));
//		System.out.println(rDao.findAllRestaurant());
//		rDao.deleterestaurant(14);
	}
	
}


