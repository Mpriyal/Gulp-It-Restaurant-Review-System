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

import com.jpa.dao.RestaurantDao;
import com.jpa.dao.RestaurantOwnerDao;
import com.jpa.models.Customer;
import com.jpa.models.Restaurant;
import com.jpa.models.RestaurantOwner;

@CrossOrigin
@RestController
public class RestaurantOwnerService {
	RestaurantOwnerDao dao = RestaurantOwnerDao.getInstance();
	RestaurantDao rDao = RestaurantDao.getInstance();
	
	@RequestMapping(value="api/owner", method=RequestMethod.POST)
	public void addNewRestOwner(@RequestBody RestaurantOwner restaurantOwner) {
		
		dao.createOwner(restaurantOwner);
	}
	
	@RequestMapping(value="api/owner/{ownerId}", method=RequestMethod.GET)
	public RestaurantOwner findOwnerById(@PathVariable(name="ownerId") int ownerId) {
		return dao.findOwnerById(ownerId);
	}
	
	@RequestMapping(value="api/owner", method=RequestMethod.GET)
	public List<RestaurantOwner> getOwnerByCredentials(@RequestParam(value="username",required=false)String username,
			@RequestParam(value="password",required=false)String password) {
			if(username==null&&password==null) {
				return dao.findAllOwners();
			}
			else if(password==null) {
				List<RestaurantOwner> owner_list = new ArrayList<>();
				RestaurantOwner owner = dao.findOwnerByUsername(username);
				owner_list.add(owner);
				return owner_list;
			}
			else {
				List<RestaurantOwner> owner_list1 = new ArrayList<>();
				RestaurantOwner owner1 = dao.findOwnerByCredentials(username, password);
				owner_list1.add(owner1);
			return owner_list1;
			}
	}
	
	@RequestMapping(value="api/owner/{ownerId}", method=RequestMethod.PUT)
	public void updateRestaurantOwner(@PathVariable(name="ownerId")int id,@RequestBody RestaurantOwner newOwner) {
		  dao.updateOwner(id, newOwner);
	}
	
	@RequestMapping(value="api/owner/{ownerId}", method=RequestMethod.DELETE)
	public int deleteOwnerById(@PathVariable (name="ownerId") int ownerId) {
		int result=dao.deleteOwner(ownerId);
		return result;
	}
	
	@RequestMapping(value="api/owner/{ownerId}/restaurant", method=RequestMethod.GET)
	public List<Restaurant> findRestofOwner(@PathVariable (name="ownerId") int ownerId) {
		int Owner_id = dao.findOwnerIdByPersonId(ownerId);
		List<Restaurant> result=rDao.findAllRestaurantByOwner(Owner_id);
		return result;
	}
	
	@RequestMapping(value="api/owner/{ownerId}/restaurant/{restId}", method=RequestMethod.GET)
	public Restaurant findRestofOwnerbyId(@PathVariable (name="ownerId") int ownerId,@PathVariable (name="restId") int restId) {
		int Owner_id = dao.findOwnerIdByPersonId(ownerId);
		Restaurant result=rDao.findRestaurantByOwnerId(restId, Owner_id);
		return result;
	}
	
	@RequestMapping(value="api/owner/{ownerId}/restaurant", method=RequestMethod.POST)
	public void addRestofOwner(@RequestBody Restaurant restaurant,@PathVariable (name="ownerId") int ownerId) {
		int Owner_id = dao.findOwnerIdByPersonId(ownerId);
		rDao.addRestaurantForOwner(restaurant, Owner_id);
	}
	
	@RequestMapping(value="api/owner/{ownerId}/restaurant/{restaurantId}", method=RequestMethod.PUT)
	public void updateRestofOwner(@RequestBody Restaurant newrestaurant,@PathVariable (name="ownerId") int ownerId,@PathVariable (name="restaurantId") int restId) {
		int Owner_id = dao.findOwnerIdByPersonId(ownerId);
		rDao.updateRestaurant(Owner_id, newrestaurant, restId);
	}
	
	@RequestMapping(value="api/owner/{ownerId}/restaurant/{restaurantId}", method=RequestMethod.DELETE)
	public void deleteRestofOwner(@PathVariable (name="ownerId") int ownerId,@PathVariable (name="restaurantId") int restId) {
		int Owner_id = dao.findOwnerIdByPersonId(ownerId);
		rDao.deleterestaurant(restId, Owner_id);
	}
	
	}

