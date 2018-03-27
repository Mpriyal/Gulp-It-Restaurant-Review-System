package com.jpa.services;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.dao.RestaurantDao;
import com.jpa.models.Restaurant;

@RestController
public class RestaurantService {

	@RequestMapping("api/restaurant")
	public List<Restaurant>findAllRestaurants() {
		RestaurantDao dao = RestaurantDao.getInstance();
		return dao.findAllRestaurant();
	}
	
	@RequestMapping("api/restaurant/name/{restaurantName}")
	public List<Restaurant>findAllRestaurantByName(@PathVariable(name="restaurantName") String restaurantName) {
		RestaurantDao dao = RestaurantDao.getInstance();
		return dao.findAllRestaurantByName(restaurantName);
	}

	@RequestMapping("api/restaurant/id/{restaurantId}")
	public Restaurant findRestaurantById(@PathVariable(name="restaurantId") int restaurantId) {
		RestaurantDao dao = RestaurantDao.getInstance();
		return dao.findRestaurantById(restaurantId);
	}
	
	@RequestMapping("api/restaurant/type/{restauranttype}")
	public List<Restaurant> findAllRestaurantByType(@PathVariable(name="restauranttype") int restauranttype) {
		RestaurantDao dao = RestaurantDao.getInstance();
		return dao.findAllRestaurantByType(restauranttype);
	}

	@RequestMapping(value="/api/owner/{OwnerId}/restaurant", method=RequestMethod.POST)
	public int addRestaurantForOwner(@RequestBody Restaurant restaurant,@PathVariable(name="OwnerId") int OwnerId) {
		RestaurantDao dao = RestaurantDao.getInstance();
		System.out.println(OwnerId);
		return dao.addRestaurantForOwner(restaurant,OwnerId);
	}
}
