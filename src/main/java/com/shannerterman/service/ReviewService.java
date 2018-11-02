package com.shannerterman.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shannerterman.dao.ReviewDao;
import com.shannerterman.entity.Review;

@Service
public class ReviewService {

	@Autowired
	ReviewDao reviewDao;
	
	// Insert Review ******************************************
	public String insertReview(int movie, String review) {
		return this.reviewDao.insertReview(movie, review);
	}
	
	// Get Reviews ******************************************
	
	public List<Review> getReviewsByMovie(int movie){
		return this.reviewDao.getReviewsByMovie(movie);
	}
	
	// Delete Review ******************************************
	
	public String deleteReview(int movie, int id) {
		return this.reviewDao.deleteReview(movie, id);
	}
	
	
	
}
