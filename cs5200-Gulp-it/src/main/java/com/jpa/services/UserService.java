package com.jpa.services;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.dao.CustomerDao;
import com.jpa.dao.RestaurantOwnerDao;
import com.jpa.models.Customer;
import com.jpa.models.User;

@CrossOrigin
@RestController
public class UserService {
	CustomerDao dao = CustomerDao.getInstance();
	RestaurantOwnerDao rdao = RestaurantOwnerDao.getInstance();
	
	@RequestMapping(value="api/user", method=RequestMethod.GET)
	public User getCustomerByCredentials(@RequestParam(value="username",required=false)String username,
			@RequestParam(value="password",required=false)String password) {
		if(dao.findCustomerByUsername(username)==null) {
			return rdao.findOwnerByCredentials(username, password);
		}	
		else return dao.findCustomerByCredentials(username, password);
	}	
	
	@RequestMapping(value="api/user/{userId}", method=RequestMethod.GET)
	public int getCustomerFromUser (@PathVariable(name="userId") int UserId) {
		return dao.findCustIdByPersonId(UserId);
}
}

