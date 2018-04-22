package com.jpa.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.dao.FeedbackDao;
import com.jpa.dao.MenuDao;
import com.jpa.dao.RestaurantDao;
import com.jpa.dao.RestaurantOwnerDao;
import com.jpa.models.Customer;
import com.jpa.models.Feedback;
import com.jpa.models.Menu;
import com.jpa.models.Restaurant;
import com.jpa.models.RestaurantOwner;

/**
 * 
 * @author priyalmittal
 *
 */

@CrossOrigin
@RestController
public class RestaurantService {
	
	RestaurantDao dao = RestaurantDao.getInstance();

//	@RequestMapping(value= "api/restaurant", method = RequestMethod.GET)
//	public List<Restaurant>findAllRestaurant(
//			@RequestParam(value="name",required= false) String name,
//			@RequestParam(value="type",required= false) Integer type,
//			@RequestParam(value="OwnerId",required= false) Integer OwnerId,
//			@RequestParam(value="city",required= false) String city,
//			@RequestParam(value="country",required= false) String country)
//	{
//		if(name!=null&&type==null&&city==null&&country==null) {
//			return dao.findAllRestaurantByName(name); 
//
//		}
//		else if(type!=null) {
//			return dao.findAllRestaurantByType(type);
//		}
//		else if (OwnerId!=null) {
//			return dao.findAllRestaurantByOwner(OwnerId);
//		}
//		else 
//			return dao.findAllRestaurant();
//	}
	
	@RequestMapping(value="api/restaurant", method=RequestMethod.GET)
	public List<Restaurant> getRestaurantFromInfo(
			@RequestParam(value="name",required=false)String name,
			@RequestParam(value="city",required=false)String city,
			@RequestParam(value="country",required=false)String country,
			@RequestParam(value="type",required=false)Integer type)
	{
			if(name!=null) {
				return dao.findAllRestaurantByName(name);
			}
			else if(city!=null) {
				return dao.findAllRestaurantByCity(city);
			}
			else if(country!=null) {
				return dao.findAllRestaurantByCountry(country);
			}
			else if(type!=null) {
				return dao.findAllRestaurantByType(type);
				}
			else {
				return dao.findAllRestaurant();
			}
	}

	@RequestMapping(value= "api/restaurant/{Id}", method = RequestMethod.GET)
	public Restaurant findRestaurantById(@PathVariable(name="Id") int Id) {
		RestaurantDao dao = RestaurantDao.getInstance();
		return dao.findRestaurantById(Id);
	}
	
	@RequestMapping(value= "api/restaurant/{Id}/owner", method = RequestMethod.GET)
	public RestaurantOwner findOwnerByRestaurantId(@PathVariable(name="Id") int Id) {
		RestaurantOwnerDao rDao = RestaurantOwnerDao.getInstance();
		int owner_id = rDao.findOwnerByRestaurantId(Id);
		int person_id = rDao.findPersonIdByOwnerId(owner_id);
		return rDao.findOwnerById(person_id);
	}
	
	//view all comments of a restaurant by the customers
	@RequestMapping(value="api/restaurant/{restId}/feedback", method=RequestMethod.GET)
	public List<Feedback> getFeedbackByRestaurantId(@PathVariable(name="restId")int id,@RequestParam(value="comments",required=false)String comments,
			@RequestParam(value="favourite",required=false)Boolean favourite) {
		
		FeedbackDao feedDao = FeedbackDao.getInstance();
		List<Feedback> feed1 = new ArrayList<>();
		if(favourite!=null) {
			List<Feedback> feed = feedDao.getAllFavouritesByRestaurantId(id);
			return feed;
		}
		else if(comments!=null) {
			List<Feedback> feed2 = feedDao.getAllCommentsByRestaurantId(id);
			return feed2;
		}
		else
			feed1 = feedDao.getAllFeedbackByRestaurantId(id);
			return feed1;
	}
	
//	//view all menu item of a restaurant by the restaurant id
//	@RequestMapping(value="api/restaurant/{restId}/menu", method=RequestMethod.GET)
//	public List<Menu> getAllMenuItemsByRestaurantId(@PathVariable(name="restId")int id,
//			@RequestParam(value="itemName",required=false)String itemName) {
//		
//		MenuDao mDao = MenuDao.getInstance();
//		if(itemName!=null) {
//			return mDao.findMenuItemsByName(itemName,id);
//		}
//		else
//			return mDao.findAllMenuItemsByRestaurantId(id);	
//	}
//	
//	//view a menu item with id, menuId, of a restaurant with id, restId
//		@RequestMapping(value="api/restaurant/{restId}/menu/{menuId}", method=RequestMethod.GET)
//		public Menu getMenuItemByMenuId(@PathVariable(name="restId")int restId,@PathVariable(name="menuId")int menuId) {
//			
//			MenuDao mDao = MenuDao.getInstance();
//			return mDao.findMenuItemByMenuId(menuId,restId);
//		}
	
}
