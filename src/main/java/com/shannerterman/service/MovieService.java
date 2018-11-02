package com.shannerterman.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shannerterman.dao.MovieDao;
import com.shannerterman.entity.Movie;

@Service
public class MovieService {
	
	@Autowired
	@Qualifier("movieDao")
	private MovieDao movieDao;
	
	// Find Movies
	
	public Collection<Movie> getAllMovies(){
		return this.movieDao.getAllMovies();
	}
	
	public Collection<Movie> getAllMoviesByDirector(String director){
		return this.movieDao.getAllMoviesByDirector(director);
	}
	
	public Collection<Movie> getAllMoviesByYear(int year){
		return this.movieDao.getAllMoviesByYear(year);
	}
	
	
	public Movie getMovieById(int movieId) {
		return this.movieDao.getMovieById(movieId);
	}
	
	
	// Delete Movies
	
	public String deleteMovieById(int movieId) {
		return this.movieDao.deleteMovieById(movieId);
	}
	
	// Update Movies
	
	public void updateMovie(Movie movie) {
		this.movieDao.updateMovie(movie);
	}
	
	public void updateAvgRating(int movieId, float avgRating) {
		this.movieDao.updateAvgRating(movieId, avgRating);
	}
	
	
	// Add Movie
	
	public void insertMovie(Movie movie) {
		
		this.movieDao.insertMovie(movie);
	}

	public Collection<Movie> getTopMovies() {
		return this.movieDao.getTopMovies();
	}
}
