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
import com.jpa.dao.FeedbackDao;
import com.jpa.models.Customer;
import com.jpa.models.Feedback;
import com.jpa.models.RestaurantOwner;

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
	public List<Customer> getCustomerByCredentials(@RequestParam(value="username",required=false)String username,
			@RequestParam(value="password",required=false)String password) {

		if(username!=null&&password!=null) {
			List<Customer> cust_list = new ArrayList<>();
			Customer cust = dao.findCustomerByCredentials(username,password);
			cust_list.add(cust);
			return cust_list;
		}
		
		else if(username!=null) {
			List<Customer> cust_list2 = new ArrayList<>();
			Customer cust1 = dao.findCustomerByUsername(username);
			cust_list2.add(cust1);
			return cust_list2;
		}
		
		else if(password!=null) {
			List<Customer> cust_list3 = new ArrayList<>();
			return cust_list3;
		}
		else {
			return dao.findAllCustomers();
		}
	}

	@RequestMapping(value="api/customer/{custId}", method=RequestMethod.PUT)
	public void updateCustomer(@PathVariable(name="custId")int id,@RequestBody Customer newCustomer) {
		dao.updateCustomer(id, newCustomer);
	}

//	@RequestMapping(value="api/customer", method=RequestMethod.GET)
//	public List<Customer> findallCustomers() {
//		return dao.findAllCustomers();
//	}

	@RequestMapping(value="api/customer/{custId}", method=RequestMethod.DELETE)
	public int deleteCustomerById(@PathVariable (name="custId") int custId) {
		int result=dao.deleteCustomer(custId);
		return result;
	}
	
	//view all comments of a customer on a restaurant
	@RequestMapping(value="api/customer/{custId}/feedback", method=RequestMethod.GET)
	public List<Feedback> getFeedbackByCustomerId(@PathVariable(name="custId")int id,@RequestParam(value="comments",required=false)String comments,
			@RequestParam(value="favourite",required=false)Boolean favourite) {
		
		FeedbackDao feedDao = FeedbackDao.getInstance();
		List<Feedback> feed1 = new ArrayList<>();
		int cust_id = dao.findCustIdByPersonId(id);
		if(favourite!=null) {
			List<Feedback> feed = feedDao.getAllFavouritesByCustomerId(cust_id);
			return feed;
		}
		else if(comments!=null) {
			List<Feedback> feed2 = feedDao.getAllCommentsByCustomerId(cust_id);
			return feed2;
		}
		else
			feed1 = feedDao.getAllFeedbackByCustomerId(cust_id);
			return feed1;
	}

}

