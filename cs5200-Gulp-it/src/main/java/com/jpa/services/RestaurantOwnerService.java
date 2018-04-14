package com.jpa.services;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.jpa.dao.RestaurantOwnerDao;
import com.jpa.models.RestaurantOwner;

@RestController
public class RestaurantOwnerService {
	RestaurantOwnerDao dao = RestaurantOwnerDao.getInstance();
	
	@RequestMapping(value="api/owner", method=RequestMethod.POST)
	public void addNewRestOwner(@RequestBody RestaurantOwner restaurantOwner) {
		
		dao.createRestaurantOwner(restaurantOwner);
	}

	@RequestMapping(value="api/owner/{ownerId}", method=RequestMethod.GET)
	public RestaurantOwner findOwnerById(@PathVariable(name="ownerId") int ownerId) {
		return dao.findRestaurantOwnerById(ownerId);
	}
	
	@RequestMapping(value="api/owner/{ownerId}", method=RequestMethod.PUT)
	public void updateRestaurantOwner(@PathVariable(name="ownerId")int id,@RequestBody RestaurantOwner newOwner) {
		 dao.updateRestaurantOwner(id, newOwner);
	}
	
	@RequestMapping(value="api/owner", method=RequestMethod.GET)
	public List<RestaurantOwner> getOwner(@RequestParam(value="username",required=false)String username,
										 @RequestParam(value="password",required=false)String password) {
		if(username==null||password==null) {
			return dao.findAllRestaurantOwners();
		}
		else
		return dao.findRestaurantOwnerByCredentials(username, password);
	}
	@RequestMapping(value="api/owner/{ownerId}", method=RequestMethod.DELETE)
	public int deleteOwnerById(@PathVariable (name="ownerId") int ownerId) {
		int result=dao.deleteRestaurantOwner(ownerId);
		return result;
	}
	
	}

