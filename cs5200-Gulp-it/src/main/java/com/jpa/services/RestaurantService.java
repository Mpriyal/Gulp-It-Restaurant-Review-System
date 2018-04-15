package com.jpa.services;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.jpa.dao.RestaurantDao;
import com.jpa.models.Restaurant;

/**
 * 
 * @author amanrayat
 *
 */

@CrossOrigin
@RestController
public class RestaurantService {

	@RequestMapping(value= "api/restaurant", method = RequestMethod.GET)
	public List<Restaurant>findAllRestaurant(
			@RequestParam(value="name",required= false) String name,
			@RequestParam(value="id",required= false) Integer id,
			@RequestParam(value="type",required= false) Integer type,
			@RequestParam(value="OwnerId",required= false) Integer OwnerId)
	{
		RestaurantDao dao = RestaurantDao.getInstance();
		if(name!=null) {
			return dao.findAllRestaurantByName(name); 

		}
		else if(id!=null) {
			return (List<Restaurant>) dao.findRestaurantById(id);
		}
		else if(type!=null) {
			return dao.findAllRestaurantByType(type);
		}
		else if (OwnerId!=null) {
			return dao.findAllRestaurantByOwner(OwnerId);
		}
		else 
			return dao.findAllRestaurant();
	}

	@RequestMapping(value= "api/restaurant/{Id}", method = RequestMethod.GET)
	public Restaurant findRestaurantById(@PathVariable(name="Id") int Id) {
		RestaurantDao dao = RestaurantDao.getInstance();
		return dao.findRestaurantById(Id);
	}

	@RequestMapping(value="/api/owner/{OwnerId}/restaurant", method=RequestMethod.POST)
	public int addRestaurantForOwner(@RequestBody Restaurant restaurant,@PathVariable(name="OwnerId") int OwnerId) {
		RestaurantDao dao = RestaurantDao.getInstance();
		System.out.println(OwnerId);
		return dao.addRestaurantForOwner(restaurant,OwnerId);
	}
}
