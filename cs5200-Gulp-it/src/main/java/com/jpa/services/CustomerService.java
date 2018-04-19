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

import com.jpa.dao.CustomerDao;
import com.jpa.models.Customer;

@CrossOrigin
@RestController
public class CustomerService {
	CustomerDao dao = CustomerDao.getInstance();

	@RequestMapping(value="api/customer", method=RequestMethod.POST)
	public void addNewCustomer(@RequestBody Customer customer) {

		dao.createCustomer(customer);
	}

	@RequestMapping(value="api/customer/{custId}", method=RequestMethod.GET)
	public Customer findCustomerById(@PathVariable(name="custId") int custId) {
		return dao.findCustomerById(custId);
	}
	
	@RequestMapping(value="api/customer", method=RequestMethod.GET)
	public Customer getCustomerByCredentials(@RequestParam(value="username",required=false)String username,
			@RequestParam(value="password",required=false)String password) {
<<<<<<< HEAD
			if(username==null&&password==null) {
				return dao.findAllCustomers();
			}
			else if(password==null) {
				List<Customer> customer_list = new ArrayList<>();
				Customer cust = dao.findCustomerByUsername(username);
				customer_list.add(cust);
				return customer_list;
			}
			else {
				List<Customer> customer_list1 = new ArrayList<>();
			Customer cust1 = dao.findCustomerByCredentials(username, password);
			customer_list1.add(cust1);
			return customer_list1;
			}
	}
	
	@RequestMapping(value="api/customer/{custId}", method=RequestMethod.PUT)
	public void updateCustomer(@PathVariable(name="custId")int id,@RequestBody Customer newCustomer) {
		dao.updateCustomer(id, newCustomer);
=======
		
			return dao.findCustomerByCredentials(username, password);
		
>>>>>>> a37e4bc31f5b4fbc186b6ef1b52930a6dc05851b
	}
	
	@RequestMapping(value="api/customers", method=RequestMethod.GET)
	public List<Customer> findallCustomers() {
			return dao.findAllCustomers();
		}
		
	@RequestMapping(value="api/customer/{custId}", method=RequestMethod.DELETE)
	public int deleteCustomerById(@PathVariable (name="custId") int custId) {
		int result=dao.deleteCustomer(custId);
		return result;
	}

}

