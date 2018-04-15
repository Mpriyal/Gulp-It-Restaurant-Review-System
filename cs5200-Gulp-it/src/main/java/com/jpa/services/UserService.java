package com.jpa.services;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.dao.CustomerDao;
import com.jpa.models.Customer;

@CrossOrigin
@RestController
public class UserService {
	CustomerDao dao = CustomerDao.getInstance();
	@RequestMapping(value="api/user", method=RequestMethod.GET)
	public Customer getCustomerByCredentials(@RequestParam(value="username",required=false)String username,
			@RequestParam(value="password",required=false)String password) {
		return dao.findCustomerByUsername(username);	
	}	
}

