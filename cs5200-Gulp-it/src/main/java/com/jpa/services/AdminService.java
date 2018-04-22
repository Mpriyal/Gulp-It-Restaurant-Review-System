package com.jpa.services;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.dao.AdminDao;
import com.jpa.dao.CustomerDao;
import com.jpa.dao.RestaurantOwnerDao;
import com.jpa.models.Customer;
import com.jpa.models.User;


@RestController
public class AdminService {
	AdminDao admindao = AdminDao.getInstance();
	CustomerDao dao = CustomerDao.getInstance();
	RestaurantOwnerDao ODao = RestaurantOwnerDao.getInstance();


	@RequestMapping("/api/admin/users")
	public List<User> findAllUsers(@RequestParam(value="usertype",required=false)String usertype) {
		return admindao.UserfindAllUsers();
	}

	@RequestMapping(value="/api/admin/users/{UserId}", method=RequestMethod.DELETE)
	public int deleteUser(@PathVariable(name="UserId") int UserId) {
		return admindao.deleteUserById(UserId);
	}

	@RequestMapping("/api/admin/users/{UserId}")
	public User findUserById(@RequestParam(value="usertype",required=false)String usertype,
			@PathVariable(name="UserId") int UserId) {
		if(usertype=="customer") {
			return dao.findCustomerById(UserId);
		}
		return 
				ODao.findOwnerById(UserId);

	}

}
