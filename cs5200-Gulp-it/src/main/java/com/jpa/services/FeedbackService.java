package com.jpa.services;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.jpa.dao.FeedbackDao;
import com.jpa.models.Feedback;

@RestController
public class FeedbackService {

	FeedbackDao dao = FeedbackDao.getInstance();

	public List<Feedback> getAllFeedbackForRestaurantByaCustomer(int RestaurantId,int CustomerId){
		return dao.getAllFeedbackForRestaurantByaCustomer(RestaurantId, CustomerId);
	}

	public List<Feedback> getAllFeedbackForRestaurantId(int RestaurantId){
		return dao.getAllFeedbackForRestaurantId(RestaurantId);
	}

	public List<Feedback> getAllFeedbackByCustomerId(int CustomerId) {
		return dao.getAllFeedbackByCustomerId(CustomerId);
	}

	public int addFeedbackForRestaurantByCustomer(Feedback feedback, int Restaurant, int Customer) {
		return dao.addFeedbackForRestaurantByCustomer(feedback, Restaurant, Customer);
	}

	public int deleteFeedbackById(int FeedbackId) {
		return dao.deleteFeedbackById(FeedbackId);
	}

	public int deleteFeedbackByIdandCustomerId(int FeedbackId,int CustomerId) {
		return dao.deleteFeedbackByIdandCustomerId(FeedbackId, CustomerId);
	}

	public int updateFeedback(int CustomerId, Feedback feedback){
		return dao.updateFeedback(CustomerId, feedback);
	}

}
