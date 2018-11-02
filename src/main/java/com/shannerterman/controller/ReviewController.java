package com.shannerterman.controller;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shannerterman.entity.Review;
import com.shannerterman.service.ReviewService;

@RestController
@RequestMapping("/movies/view/")
public class ReviewController {
	
	@Autowired
	ReviewService reviewService;
	
	// Get Reviews per movie **********************************************************
	
	@GetMapping(value="{movie}/review")
	public Collection<Review> getReviewsByMovie(@PathVariable("movie") int movie){
		return this.reviewService.getReviewsByMovie(movie);
	}
	
	
	// Insert Review ***********************************
	
	@PostMapping(value="{movie}/review/new",consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_REVIEWER')")
	public String insertReview(@RequestBody @PathVariable("movie") int movie, @RequestBody Review review) {
		return this.reviewService.insertReview(movie, review.getReview());
	}
	
	// Delete Review ******************************************
	
	@DeleteMapping(value = "/{movie}/review/{reviewId}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_REVIEWER')")
	public String deleteRating(@PathVariable("movie") int movie, @PathVariable("reviewId") int id) {
		return this.reviewService.deleteReview(movie, id);
	}
	
	
}
