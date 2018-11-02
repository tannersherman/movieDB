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
import com.shannerterman.entity.Rating;
import com.shannerterman.service.MovieService;
import com.shannerterman.service.RatingService;

@RestController
@RequestMapping("/movies/view/")
public class RatingController {

	@Autowired
	private RatingService ratingService;
	@Autowired
	private MovieService movieService;

	// Get Ratings ******************************************
	@GetMapping(value="{movie}/rating")
	public Collection<Rating> getRatingsById(@PathVariable("movie") int movie){
		return this.ratingService.getRatingsbyId(movie);
	}
	
	
	// Insert Rating ******************************************
	
	@PostMapping(value="{movie}/rating/new",consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_REVIEWER')")
	public String insertRating(@RequestBody @PathVariable("movie") int movie, @RequestBody Rating rating) {
		if(this.ratingService.insertRating(movie, rating.getRatingValue())){
			final float avgRating = ratingService.getRatingById(movie);
			movieService.updateAvgRating(movie, avgRating);
			return "Rating Sucessfully Added";
		} else {
			return "A user can only rate a movie once and the value must be between 0.0 and 10.0";
		}
	}
	
	// Delete rating ******************************************
	
	@DeleteMapping(value = "{movie}/rating/{ratingId}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_REVIEWER')")
	public String deleteRating(@PathVariable("movie") int movie, @PathVariable("ratingId") int id) {
		return this.ratingService.deleteRating(movie, id);
	}

}
