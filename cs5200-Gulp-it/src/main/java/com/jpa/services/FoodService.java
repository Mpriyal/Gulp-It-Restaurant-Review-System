package com.jpa.services;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.jpa.dao.FoodDao;
import com.jpa.models.Food;

@RestController
public class FoodService {

	@RequestMapping(value= "api/restaurant/{RestaurantId}/food", method = RequestMethod.GET)
	public List<Food> findAllFoodForRestaurant(@PathVariable(name="RestaurantId") int RestaurantId) {
		FoodDao foodDao = FoodDao.getInstance();
		return foodDao.findAllFoodByRestaurant(RestaurantId);
	}
	
	@RequestMapping(value="/api/owner/{OwnerId}/restaurant/{RestaurantId}/food", method=RequestMethod.POST)
	public int addFoodForRestaurant(@RequestBody Food food,@PathVariable(name="RestaurantId") int RestaurantId) {
		FoodDao dao = FoodDao.getInstance();
		return dao.addFoodForRestaurant(food, RestaurantId);
	}
}
