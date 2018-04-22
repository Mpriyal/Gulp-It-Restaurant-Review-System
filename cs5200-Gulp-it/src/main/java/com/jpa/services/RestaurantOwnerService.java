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

import com.jpa.dao.MenuDao;
import com.jpa.dao.RestaurantDao;
import com.jpa.dao.RestaurantOwnerDao;
import com.jpa.models.Customer;
import com.jpa.models.Menu;
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
		
			if(username!=null&&password!=null) {
				List<RestaurantOwner> owner_list1 = new ArrayList<>();
				RestaurantOwner owner1 = dao.findOwnerByCredentials(username, password);
				owner_list1.add(owner1);
				return owner_list1;
			}
			else if(username!=null) {
				List<RestaurantOwner> owner_list = new ArrayList<>();
				RestaurantOwner owner = dao.findOwnerByUsername(username);
				owner_list.add(owner);
				return owner_list;
			}
			else if(password!=null) {
				List<RestaurantOwner> owner_list3 = new ArrayList<>();
				return owner_list3;
			}
			else {
				return dao.findAllOwners();
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
	
	//to add a new restaurant by the owner, with id as ownerId
	@RequestMapping(value="api/owner/{ownerId}/restaurant", method=RequestMethod.POST)
	public void addRestofOwner(@RequestBody Restaurant restaurant,@PathVariable (name="ownerId") int ownerId) {
		int Owner_id = dao.findOwnerIdByPersonId(ownerId);
		rDao.addRestaurantForOwner(restaurant, Owner_id);
	}
	
	//to add a new menu for a restaurant by the owner, with id as ownerId
	@RequestMapping(value="api/owner/{ownerId}/restaurant/{restId}/menu", method=RequestMethod.POST)
	public void addMenuforRest(@RequestBody Menu menu,@PathVariable (name="ownerId") int ownerId,@PathVariable (name="restId") int restId) {
		MenuDao mDao = MenuDao.getInstance();
		int Owner_id = dao.findOwnerIdByPersonId(ownerId);
		mDao.addMenuForRestaurant(menu, restId, Owner_id);
	}
	
	//to update a restaurant, with id as restaurantId, by owner, with id ownerId
	@RequestMapping(value="api/owner/{ownerId}/restaurant/{restaurantId}", method=RequestMethod.PUT)
	public void updateRestofOwner(@RequestBody Restaurant newrestaurant,@PathVariable (name="ownerId") int ownerId,@PathVariable (name="restaurantId") int restId) {
		int Owner_id = dao.findOwnerIdByPersonId(ownerId);
		rDao.updateRestaurant(Owner_id, newrestaurant, restId);
	}
	
	//to delete a restaurant, with id as restaurantId, by owner, with id ownerId
	@RequestMapping(value="api/owner/{ownerId}/restaurant/{restaurantId}", method=RequestMethod.DELETE)
	public void deleteRestofOwner(@PathVariable (name="ownerId") int ownerId,@PathVariable (name="restaurantId") int restId) {
		int Owner_id = dao.findOwnerIdByPersonId(ownerId);
		rDao.deleterestaurant(restId, Owner_id);
	}
	
	//view all menu item of a restaurant by the restaurant id
	@RequestMapping(value="api/owner/{ownerId}/restaurant/{restId}/menu", method=RequestMethod.GET)
	public List<Menu> getAllMenuItemsByRestaurantId(@PathVariable(name="restId")int restId,@PathVariable(name="ownerId")int ownerId,
			@RequestParam(value="itemName",required=false)String itemName) {
		
		MenuDao mDao = MenuDao.getInstance();
		int Owner_id1 = 0;
		
		if(itemName!=null) {
			int Owner_id = dao.findOwnerIdByPersonId(ownerId);
			return mDao.findMenuItemsByName(itemName,restId,Owner_id);
		}
		else
			Owner_id1 = dao.findOwnerIdByPersonId(ownerId);
			return mDao.findAllMenuItemsByRestaurantId(restId,Owner_id1);	
	}
	//view a menu item with id, menuId, of a restaurant with id, restId
			@RequestMapping(value="api/restaurant/{restId}/menu", method=RequestMethod.GET)
			public List<Menu> getMenuFromRestId(@PathVariable(name="restId")int restId) {
				MenuDao mDao = MenuDao.getInstance();
				return mDao.getMenuFromRestId(restId);
			}
	//view a menu item with id, menuId, of a restaurant with id, restId
		@RequestMapping(value="api/owner/{ownerId}/restaurant/{restId}/menu/{menuId}", method=RequestMethod.GET)
		public Menu getMenuItemByMenuId(@PathVariable(name="restId")int restId,@PathVariable(name="menuId")int menuId,
				@PathVariable(name="ownerId")int ownerId) {
			
			MenuDao mDao = MenuDao.getInstance();
			int Owner_id = dao.findOwnerIdByPersonId(ownerId);
			return mDao.findMenuItemByMenuId(menuId,restId,Owner_id);
		}
	
	//to update a menu, with id as menuId, of a restaurant, with id as restaurantId, by owner, with id ownerId
	@RequestMapping(value="api/owner/{ownerId}/restaurant/{restaurantId}/menu/{menuId}", method=RequestMethod.PUT)
	public void updateRestMenuItem(@RequestBody Menu newMenu,@PathVariable (name="menuId") int menuId, @PathVariable (name="ownerId") int ownerId
			,@PathVariable (name="restaurantId") int restId) {
		int Owner_id = dao.findOwnerIdByPersonId(ownerId);
		MenuDao mDao = MenuDao.getInstance();
		mDao.updateMenuItem(menuId, restId, Owner_id, newMenu);
	}
	
	//to delete a menu, with id as menuId, of a restaurant, with id as restaurantId, by owner, with id ownerId
	@RequestMapping(value="api/owner/{ownerId}/restaurant/{restaurantId}/menu/{menuId}", method=RequestMethod.DELETE)
	public void deleteRestMenuItem(@PathVariable (name="menuId") int menuId, @PathVariable (name="ownerId") int ownerId,@PathVariable (name="restaurantId") int restId) {
		int Owner_id = dao.findOwnerIdByPersonId(ownerId);
		MenuDao mDao = MenuDao.getInstance();
		mDao.deleteMenuForRestaurant(menuId, Owner_id, restId);
		
	}
	

	
	}

