package com.jpa.services;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.dao.DrinksDao;
import com.jpa.models.Drinks;

@CrossOrigin
@RestController
public class DrinksService {

	@RequestMapping(value= "api/restaurant/{RestaurantId}/drinks", method = RequestMethod.GET)
	public List<Drinks> findAllDrinksForRestaurant(@PathVariable(name="RestaurantId") int RestaurantId) {
		DrinksDao drinksDao = DrinksDao.getInstance();
		return drinksDao.findAllDrinksByRestaurant(RestaurantId);
	}
	
	@RequestMapping(value="/api/owner/{OwnerId}/restaurant/{RestaurantId}/drinks", method=RequestMethod.POST)
	public int addDrinksForRestaurant(@RequestBody Drinks drinks,@PathVariable(name="RestaurantId") int RestaurantId) {
		DrinksDao dao = DrinksDao.getInstance();
		return dao.addDrinksForRestaurant(drinks, RestaurantId);
	}
}
