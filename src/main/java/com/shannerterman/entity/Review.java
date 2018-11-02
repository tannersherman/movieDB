package com.shannerterman.entity;

public class Review {
	private int id;
	private String review;
	private int movie;
	private String author;
	
	public Review(int id, String review, int movie, String author) {
		super();
		this.id = id;
		this.review = review;
		this.movie = movie;
		this.author = author;
	}
	
	public Review() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getMovie() {
		return movie;
	}

	public void setMovie(int movie) {
		this.movie = movie;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	
	
	
	

}
