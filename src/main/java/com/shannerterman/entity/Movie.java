package com.shannerterman.entity;

public class Movie {
	private int movieId;
	private String movieTitle;
	private String director;
	private int year;
	private float avgRating;
	private String poster;
	private String author;
	
	
	public Movie(int movieId, String movieTitle, String director, int year, float avgRating, String poster, String author) {
		super();
		this.movieId = movieId;
		this.movieTitle = movieTitle;
		this.director = director;
		this.avgRating = avgRating;
		this.year = year;
		this.poster = poster;
		this.author = author;
	}
	
	public Movie() {
		
	}
	
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getMovieTitle() {
		return movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public float getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(float avgRating) {
		this.avgRating = avgRating;
	}
	
	

}
