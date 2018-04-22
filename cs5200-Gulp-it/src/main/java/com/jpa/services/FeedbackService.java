package com.jpa.services;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.dao.FeedbackDao;
import com.jpa.models.Feedback;
import com.jpa.models.User;

@RestController
@CrossOrigin
public class FeedbackService {

	FeedbackDao dao = FeedbackDao.getInstance();

	@RequestMapping("api/feedback/{restId}/{CustId}")
	public List<Feedback> getAllFeedbackForRestaurantByaCustomer(@PathVariable(name="restId")int RestaurantId,
																@PathVariable(name="CustId")int CustomerId){
		return dao.getAllFeedbackForRestaurantByaCustomer(RestaurantId, CustomerId);
	}

	@RequestMapping("api/feedback/{restId}")
	public List<Feedback> getAllFeedbackForRestaurantId(@PathVariable(name="restId")int RestaurantId){
		return dao.getAllFeedbackForRestaurantId(RestaurantId);
	}
	
	
//	@RequestMapping("api/feedback/{restId}")
//	public List<Feedback> getAllFeedbackForRestaurantId(@QueryParam(value="restId")int RestaurantId){
//		return dao.getAllFeedbackForRestaurantId(RestaurantId);
//	}
	
//	@RequestMapping("/api/admin/users")
//	public List<User> findAllUsers(@RequestParam(value="usertype",required=false)String usertype) {
//		return admindao.UserfindAllUsers();
//	}

	public List<Feedback> getAllFeedbackByCustomerId(int CustomerId) {
		return dao.getAllFeedbackByCustomerId(CustomerId);
	}

	@RequestMapping(value="api/feedback/{restId}/{CustId}", method=RequestMethod.POST)
	public int addFeedbackForRestaurantByCustomer(@RequestBody Feedback feedback, 
												@PathVariable(name="restId")int Restaurant,@PathVariable(name="CustId") int Customer) {
		return dao.addFeedbackForRestaurantByCustomer(feedback, Restaurant, Customer);
	}

	@RequestMapping(value="api/feedback/{feedbackid}", method=RequestMethod.DELETE)
	public int deleteFeedbackById(@PathVariable(name="feedbackid")int FeedbackId) {
		return dao.deleteFeedbackById(FeedbackId);
	}
	
}
