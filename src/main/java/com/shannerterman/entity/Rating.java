package com.shannerterman.entity;

public class Rating {
	private int id;
	private float ratingValue;
	private int movie;
	private String author;
	
	public Rating(int id, float ratingValue, int movie, String author) {
		super();
		this.id = id;
		this.ratingValue = ratingValue;
		this.movie = movie;
		this.author = author;
		
	}
	
	public Rating() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getRatingValue() {
		return (float)(Math.round(ratingValue * 10) / 10.0);
	}

	public void setRatingValue(float ratingValue) {
		this.ratingValue = ratingValue;
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
