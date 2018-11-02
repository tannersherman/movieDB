package com.shannerterman.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shannerterman.entity.Movie;
import com.shannerterman.entity.Review;
import com.shannerterman.entity.ReviewedMovie;
import com.shannerterman.service.MovieService;
import com.shannerterman.service.ReviewService;

@RestController
@RequestMapping("/movies")
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	@Autowired
	private ReviewService reviewService;
	
	
	
	// Get Movies by ***********************************
	
	@Autowired
	@GetMapping
	public Collection<Movie> getAllMovies(){
		return movieService.getAllMovies();
	}
	
	@GetMapping(value = "/view/{movieId}")
	public ReviewedMovie getMovieById(@PathVariable("movieId") int movieId){
		Movie movie = movieService.getMovieById(movieId);
		List<Review> reviews = reviewService.getReviewsByMovie(movieId);
		return new ReviewedMovie(movie,reviews);
		
	}
	
	
	@GetMapping(value = "/view/year/{year}")
	public Collection<Movie> getMoviesByYear(@PathVariable("year") int year){
		return movieService.getAllMoviesByYear(year);
	}
	
	@GetMapping(value = "view/director/{director}")
	public Collection<Movie> getMoviesByDirector(@PathVariable("director") String director){
		return movieService.getAllMoviesByDirector(director);
	}
	
	@GetMapping(value = "top")
	public Collection<Movie> getTopMovies(){
		return movieService.getTopMovies();
	}
	
	// New Movies ***********************************
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_REVIEWER') or hasRole('ROLE_USER')")
	public void insertMovie(@RequestBody Movie movie) {
		movieService.insertMovie(movie);
	}
	
	// Update Movies ***********************************
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_REVIEWER') or hasRole('ROLE_USER')")
	public void updateMovie(@RequestBody Movie movie) {
		movieService.updateMovie(movie);
	}
	
	// Delete Movies ***********************************
	
	@DeleteMapping(value = "/{movieId}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_REVIEWER') or hasRole('ROLE_USER')")
	public String deleteMovie(@PathVariable ("movieId") int movieId) {
		return movieService.deleteMovieById(movieId);
	}
	
	
	
	
	
	
	
	
	
	
}
