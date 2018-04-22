package com.jpa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jpa.models.Feedback;
import com.jpa.models.Restaurant;
/**
 * 
 * @author amanrayat
 *
 */
public class RestaurantDao {
	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	final String DB_URL = "jdbc:mysql://cs5200-spring2018-mittal.c9fddtskt253.us-east-2.rds.amazonaws.com/GulpIt";
	final String USER = "Mpriyal";
	final String PASS = "Priyaldbms94!";
	private static final String DELETE_RESTAURANT = "DELETE FROM Restaurant WHERE Restaurant.id=? AND restaurant_owner=?";
	static Connection conn = null;
	static PreparedStatement statement = null;
	static ResultSet resultset = null;

	public static RestaurantDao instance = null;

	public static RestaurantDao getInstance() {
		if (instance == null) {
			instance = new RestaurantDao();
		}
		return instance;
	}
	private RestaurantDao() {}

	/**
	 * This function is used to Add a new Restaurant
	 * @param restaurant
	 * @param OwnerId
	 * @return
	 */
	public int addRestaurantForOwner(Restaurant restaurant,int OwnerId) {
		int result = -1;
		try {

			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String addRestaurant = "INSERT INTO Restaurant "
					+ "(`name`,`description`,`image_link`,`cost_for_two`,`restaurant_owner`,`restaurant_type`) "
					+ "VALUES (?,?,?,?,?,?)";
			statement= conn.prepareStatement(addRestaurant);

			statement.setString(1, restaurant.getName());
			statement.setString(2, restaurant.getDescription());
			statement.setString(3, restaurant.getImage_link());
			statement.setFloat(4,restaurant.getCost_for_two());
			statement.setInt(5,OwnerId);
			statement.setInt(6, restaurant.getRestaurant_type());

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
	 * This method is used to find all Restaurant 
	 * @return
	 */
	public List<Restaurant> findAllRestaurant() {
		List <Restaurant> restaurants = new ArrayList<>();
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String AllRestaurant = "SELECT * FROM Restaurant";
			statement= conn.prepareStatement(AllRestaurant);
			resultset = statement.executeQuery();
			while(resultset.next()) {
				int id= resultset.getInt("id");
				String name = resultset.getString("name");
				String description = resultset.getString("description");
				String image_link = resultset.getString("image_link");
				float cost_for_two = resultset.getFloat("cost_for_two");
				int restaurant_owner = resultset.getInt("restaurant_owner");
				int restaurant_type = resultset.getInt("restaurant_type");

				Restaurant restaurant = new Restaurant(id,name,description,image_link,cost_for_two,restaurant_owner,restaurant_type);
				restaurants.add(restaurant);
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

		return restaurants;
	}

	/**
	 * This method is used to find Restaurants by Names
	 * @param restaurantName
	 * @return
	 */
	public List<Restaurant> findAllRestaurantByName(String restaurantName) {
		List <Restaurant> restaurants = new ArrayList<>();
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String RestaurantName = "SELECT * FROM Restaurant WHERE name LIKE ?";
			statement= conn.prepareStatement(RestaurantName);
			statement.setString(1,"%" + restaurantName + "%");
			resultset = statement.executeQuery();
			while(resultset.next()) {
				int id= resultset.getInt("id");
				String name = resultset.getString("name");
				String description = resultset.getString("description");
				String image_link = resultset.getString("image_link");
				float cost_for_two = resultset.getFloat("cost_for_two");
				int restaurant_owner = resultset.getInt("restaurant_owner");
				int restaurant_type = resultset.getInt("restaurant_type");

				Restaurant restaurant = new Restaurant(id,name,description,image_link,cost_for_two,restaurant_owner,restaurant_type);
				restaurants.add(restaurant);
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
		return restaurants;
	}
	
	/**
	 * This method is used to find Restaurants by City
	 * @param restaurantCity
	 * @return
	 */
	
	public List<Restaurant> findAllRestaurantByCity(String restaurantCity) {
		List <Restaurant> restaurants = new ArrayList<>();
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String RestaurantCity = "SELECT * FROM Restaurant r, Address a WHERE a.Restaurant = r.id AND city LIKE ?";
			statement= conn.prepareStatement(RestaurantCity);
			statement.setString(1,"%" + restaurantCity + "%");
			resultset = statement.executeQuery();
			while(resultset.next()) {
				int id= resultset.getInt("id");
				String name = resultset.getString("name");
				String description = resultset.getString("description");
				String image_link = resultset.getString("image_link");
				float cost_for_two = resultset.getFloat("cost_for_two");
				int restaurant_owner = resultset.getInt("restaurant_owner");
				int restaurant_type = resultset.getInt("restaurant_type");

				Restaurant restaurant = new Restaurant(id,name,description,image_link,cost_for_two,restaurant_owner,restaurant_type);
				restaurants.add(restaurant);
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
		return restaurants;
	}
	
	/**
	 * This method is used to find Restaurants by Country
	 * @param restaurantCountry
	 * @return
	 */
	
	public List<Restaurant> findAllRestaurantByCountry(String restaurantCountry) {
		List <Restaurant> restaurants = new ArrayList<>();
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String RestaurantCountry = "SELECT * FROM Restaurant r, Address a WHERE a.Restaurant = r.id AND country LIKE ?";
			statement= conn.prepareStatement(RestaurantCountry);
			statement.setString(1,"%" + restaurantCountry + "%");
			resultset = statement.executeQuery();
			while(resultset.next()) {
				int id= resultset.getInt("id");
				String name = resultset.getString("name");
				String description = resultset.getString("description");
				String image_link = resultset.getString("image_link");
				float cost_for_two = resultset.getFloat("cost_for_two");
				int restaurant_owner = resultset.getInt("restaurant_owner");
				int restaurant_type = resultset.getInt("restaurant_type");

				Restaurant restaurant = new Restaurant(id,name,description,image_link,cost_for_two,restaurant_owner,restaurant_type);
				restaurants.add(restaurant);
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
		return restaurants;
	}
	
	/**
	 * This method is used to find Restaurant by Id
	 * @param RestaurantId
	 * @return
	 */
	public Restaurant findRestaurantById(int RestaurantId) {
		Restaurant restaurant =null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String RestaurantById = "SELECT * FROM Restaurant WHERE id = ?";
			statement= conn.prepareStatement(RestaurantById);
			statement.setInt(1,RestaurantId);
			resultset = statement.executeQuery();
			if(resultset.next()){
				int id= resultset.getInt("id");
				String name = resultset.getString("name");
				String description = resultset.getString("description");
				String image_link = resultset.getString("image_link");
				float cost_for_two = resultset.getFloat("cost_for_two");
				int restaurant_owner = resultset.getInt("restaurant_owner");
				int restaurant_type = resultset.getInt("restaurant_type");
				restaurant = new Restaurant(id,name,description,image_link,cost_for_two,restaurant_owner,restaurant_type);
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
		return restaurant;
	}
	/**
	 * This method is used to find Restaurants By Type
	 * @param restaurantType
	 * @return
	 */
	public List<Restaurant> findAllRestaurantByType(int restaurantType) {
		List <Restaurant> restaurants = new ArrayList<>();
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String AllRestaurant = "SELECT * FROM Restaurant WHERE restaurant_type=?";
			statement= conn.prepareStatement(AllRestaurant);
			statement.setInt(1, restaurantType);
			resultset = statement.executeQuery();
			while(resultset.next()) {
				int id= resultset.getInt("id");
				String name = resultset.getString("name");
				String description = resultset.getString("description");
				String image_link = resultset.getString("image_link");
				float cost_for_two = resultset.getFloat("cost_for_two");
				int restaurant_owner = resultset.getInt("restaurant_owner");
				int restaurant_type = resultset.getInt("restaurant_type");

				Restaurant restaurant = new Restaurant(id,name,description,image_link,cost_for_two,restaurant_owner,restaurant_type);
				restaurants.add(restaurant);
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
		return restaurants;
	}
	/**
	 * This method is used to find Restaurants by Owner
	 * @param restaurantOwner
	 * @return
	 */
	public List<Restaurant> findAllRestaurantByOwner(int restaurantOwner) {
		List <Restaurant> restaurants = new ArrayList<>();
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String AllRestaurant = "SELECT * FROM Restaurant WHERE restaurant_owner=?";
			statement= conn.prepareStatement(AllRestaurant);
			statement.setInt(1, restaurantOwner);
			resultset = statement.executeQuery();
			while(resultset.next()) {
				int id= resultset.getInt("id");
				String name = resultset.getString("name");
				String description = resultset.getString("description");
				String image_link = resultset.getString("image_link");
				float cost_for_two = resultset.getFloat("cost_for_two");
				int restaurant_owner = resultset.getInt("restaurant_owner");
				int restaurant_type = resultset.getInt("restaurant_type");

				Restaurant restaurant = new Restaurant(id,name,description,image_link,cost_for_two,restaurant_owner,restaurant_type);
				restaurants.add(restaurant);
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
		return restaurants;
	}
	
	/**
	 * This function is used for updating the information about the restaurant 
	 * @param RestaurantOwnerId
	 * @param restaurant
	 * @return
	 */
	public int updateRestaurant (int RestaurantOwnerId, Restaurant restaurant, int restId){
		int result = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String RestaurantUpdate = "UPDATE Restaurant SET name =?, description =? ,\n" + 
					"image_link =?,cost_for_two =?,restaurant_type=?  WHERE \n" + 
					"id = ? and restaurant_owner=?";
			statement = conn.prepareStatement(RestaurantUpdate);
			statement.setString(1, restaurant.getName());
			statement.setString(2, restaurant.getDescription());
			statement.setString(3, restaurant.getImage_link());
			statement.setFloat(4, restaurant.getCost_for_two());
			statement.setInt(5, restaurant.getRestaurant_type());
			statement.setInt(6, restId);
			statement.setInt(7, RestaurantOwnerId);

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
	
	//this function is to delete a restaurant
	public int deleterestaurant(int restaurantId, int owner_id){
		Connection connection = null;
		PreparedStatement statement = null;
		int result = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			statement = connection.prepareStatement(DELETE_RESTAURANT);
			statement.setInt(1, restaurantId);
			statement.setInt(2, owner_id);
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
	
	public Restaurant findRestaurantByOwnerId(int RestaurantId, int OwnerId) {
		Restaurant restaurant =null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String RestaurantByName = "SELECT * FROM Restaurant WHERE id = ? AND restaurant_owner =?";
			statement= conn.prepareStatement(RestaurantByName);
			statement.setInt(1,RestaurantId);
			statement.setInt(2,OwnerId);
			resultset = statement.executeQuery();
			if(resultset.next()){
				int id= resultset.getInt("id");
				String name = resultset.getString("name");
				String description = resultset.getString("description");
				String image_link = resultset.getString("image_link");
				float cost_for_two = resultset.getFloat("cost_for_two");
				int restaurant_owner = resultset.getInt("restaurant_owner");
				int restaurant_type = resultset.getInt("restaurant_type");
				restaurant = new Restaurant(id,name,description,image_link,cost_for_two,restaurant_owner,restaurant_type);
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
		return restaurant;
	}
	
	public List<Restaurant> findAllRestaurantByEvent(int eventId, int providerId) {
		List <Restaurant> restaurants = new ArrayList<>();
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String RestaurantFromEvent = "SELECT * FROM Restaurant r,Event2Restaurant er, Event e "
					+ "WHERE r.id=er.Restaurant AND e.id = er.Event AND e.id = ? AND e.event_provider =?";
			statement= conn.prepareStatement(RestaurantFromEvent);
			statement.setInt(1,eventId);
			statement.setInt(2,providerId);
			resultset = statement.executeQuery();
			while(resultset.next()) {
				int id= resultset.getInt("id");
				String name = resultset.getString("name");
				String description = resultset.getString("description");
				String image_link = resultset.getString("image_link");
				float cost_for_two = resultset.getFloat("cost_for_two");
				int restaurant_owner = resultset.getInt("restaurant_owner");
				int restaurant_type = resultset.getInt("restaurant_type");
				Restaurant restaurant = new Restaurant(id,name,description,image_link,cost_for_two,restaurant_owner,restaurant_type);
				restaurants.add(restaurant);
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
		return restaurants;
	}
	
	public Restaurant findRestaurantByIdAndEvent(int restId, int eventId, int providerId) {
		Restaurant restaurant = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String RestaurantFromEvent = "SELECT * FROM Restaurant r,Event2Restaurant er, Event e "
					+ "WHERE r.id=er.Restaurant AND e.id = er.Event AND r.id=? AND e.id = ? AND e.event_provider =?";
			statement= conn.prepareStatement(RestaurantFromEvent);
			statement.setInt(1,restId);
			statement.setInt(2,eventId);
			statement.setInt(3,providerId);
			resultset = statement.executeQuery();
			while(resultset.next()) {
				int id= resultset.getInt("id");
				String name = resultset.getString("name");
				String description = resultset.getString("description");
				String image_link = resultset.getString("image_link");
				float cost_for_two = resultset.getFloat("cost_for_two");
				int restaurant_owner = resultset.getInt("restaurant_owner");
				int restaurant_type = resultset.getInt("restaurant_type");
				restaurant = new Restaurant(id,name,description,image_link,cost_for_two,restaurant_owner,restaurant_type);
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
		return restaurant;
	}
	
	public List<Restaurant> findAllRestaurantsOfFeedbackByCustId(int custId) {
		List<Restaurant> restaurants = new ArrayList<>();
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String RestaurantFromEvent = "SELECT * FROM Feedback f, Restaurant r,Customer c "
					+ "WHERE r.id=f.Restaurant AND c.id=f.Customer AND c.id=?";
			statement= conn.prepareStatement(RestaurantFromEvent);
			statement.setInt(1,custId);
			resultset = statement.executeQuery();
			while(resultset.next()) {
				int id= resultset.getInt("id");
				String name = resultset.getString("name");
				String description = resultset.getString("description");
				String image_link = resultset.getString("image_link");
				float cost_for_two = resultset.getFloat("cost_for_two");
				int restaurant_owner = resultset.getInt("restaurant_owner");
				int restaurant_type = resultset.getInt("restaurant_type");
				Restaurant restaurant = new Restaurant(id,name,description,image_link,cost_for_two,restaurant_owner,restaurant_type);
				restaurants.add(restaurant);
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
		return restaurants;
	}
	
	public List<Restaurant> getAllCommentedRestByCustomerId(int CustomerId) {
		List<Restaurant> restaurants = new ArrayList<>();
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String commentsByCustomer = "SELECT * FROM Feedback f, Restaurant r,Customer c "
					+ "WHERE r.id=f.Restaurant AND c.id=f.Customer AND c.id=? AND comments IS NOT NULL";
			statement= conn.prepareStatement(commentsByCustomer);
			statement.setInt(1,CustomerId);
			resultset = statement.executeQuery();
			while(resultset.next()){
				int id= resultset.getInt("id");
				String name = resultset.getString("name");
				String description = resultset.getString("description");
				String image_link = resultset.getString("image_link");
				float cost_for_two = resultset.getFloat("cost_for_two");
				int restaurant_owner = resultset.getInt("restaurant_owner");
				int restaurant_type = resultset.getInt("restaurant_type");
				Restaurant restaurant = new Restaurant(id,name,description,image_link,cost_for_two,restaurant_owner,restaurant_type);
				restaurants.add(restaurant);
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
		return restaurants;
	}
	
	public List<Restaurant> getAllFavouriteRestByCustomerId(int CustomerId) {
		List<Restaurant> restaurants = new ArrayList<>();
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String favouritesByCustomer = "SELECT * FROM Feedback f, Restaurant r,Customer c "
					+ "WHERE r.id=f.Restaurant AND c.id=f.Customer AND c.id=? AND favourite <>0";
			statement= conn.prepareStatement(favouritesByCustomer);
			statement.setInt(1,CustomerId);
			resultset = statement.executeQuery();
			while(resultset.next()){
				int id= resultset.getInt("id");
				String name = resultset.getString("name");
				String description = resultset.getString("description");
				String image_link = resultset.getString("image_link");
				float cost_for_two = resultset.getFloat("cost_for_two");
				int restaurant_owner = resultset.getInt("restaurant_owner");
				int restaurant_type = resultset.getInt("restaurant_type");
				Restaurant restaurant = new Restaurant(id,name,description,image_link,cost_for_two,restaurant_owner,restaurant_type);
				restaurants.add(restaurant);
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
		return restaurants;
	}
	
	public static void main(String[] args) {
		RestaurantDao rDao = new RestaurantDao();
		Restaurant shawarma = new Restaurant("boston shawarma","It serves good mexican food","abcdef",10.80f,1);
//		rDao.addRestaurantForOwner(shawarma, 18);
//		System.out.println(rDao.findAllRestaurantByName("amelia"));
//		System.out.println(rDao.findAllRestaurantByCity("bos"));
//		System.out.println(rDao.findAllRestaurantByCountry("U"));
//		System.out.println(rDao.findAllRestaurant());
//		rDao.deleterestaurant(14);
	}
	
}


