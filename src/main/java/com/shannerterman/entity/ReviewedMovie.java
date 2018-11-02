package com.shannerterman.entity;

import java.util.Collection;
import java.util.List;

public class ReviewedMovie {
	private Movie movie;
	private List<Review> reviews;
	
	public ReviewedMovie(Movie movie, List<Review> reviews) {
		super();
		this.movie = movie;
		this.reviews = reviews;
	}
	
	public ReviewedMovie() {
		
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
	public Collection<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	
	
	
	
}
