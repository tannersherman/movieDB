package com.shannerterman.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shannerterman.dao.RatingsDao;
import com.shannerterman.entity.Rating;

@Service
public class RatingService {
	
	@Autowired
	@Qualifier("ratingsDao")
	private RatingsDao ratingsDao;
	
	// Add Rating
	
	public boolean insertRating(int movie, float ratingValue) {
		return this.ratingsDao.insertRating(movie, ratingValue);
	}

	// Get Rating
	public float getRatingById(int movie) {
		return ratingsDao.getRatingById(movie);
	}
	
	public Collection<Rating> getRatingsbyId(int movie){
		return this.ratingsDao.getRatingsById(movie);
	}

	
	// Delete rating
	public String deleteRating(int movie, int id) {
		return this.ratingsDao.deleteRating(movie, id);
		
	}
}
