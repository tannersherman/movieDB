package com.shannerterman.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.shannerterman.entity.ApplicationUser;
import com.shannerterman.entity.AvgMovieRating;
import com.shannerterman.entity.Rating;

@Repository
@Qualifier("ratingsDao")
public class RatingsDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static class RatingRowMapper implements RowMapper<Rating> {

		public Rating mapRow(ResultSet resultSet, int i) throws SQLException {
			Rating rating = new Rating();
			rating.setId(resultSet.getInt("id"));
			rating.setRatingValue(resultSet.getFloat("ratingValue"));
			rating.setMovie(resultSet.getInt("movie"));
			rating.setAuthor(resultSet.getString("author"));
			return rating;
			
		}
		
	}
	
	private static class AverageRatingRowMapper implements RowMapper<AvgMovieRating> {

		public AvgMovieRating mapRow(ResultSet resultSet, int i) throws SQLException {
			AvgMovieRating avgMovieRating = new AvgMovieRating();
			avgMovieRating.setAverageRating(resultSet.getFloat(1));
			return avgMovieRating;
		}
		
	}

	// Insert Rating **********************************************************
	
	public boolean insertRating(int movie, float ratingValue) {
		final String sql = "INSERT INTO ratings (ratingValue, movie, author) VALUES (?,?,?)";
		final String author = ((ApplicationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername().toString();
		final String query = "Select * from ratings where (author = ? and movie = ?)";
		String checkedAuthor = "";
		try {
			checkedAuthor = jdbcTemplate.queryForObject(query, new RatingRowMapper(), author, movie).getAuthor();
		} catch (EmptyResultDataAccessException e) {
			checkedAuthor = null;
		}
		
		if(author.equals(checkedAuthor)) {
			return false;
		} else if ((ratingValue >= 0.0) && (ratingValue <= 10.0) && (!author.equals(checkedAuthor))){
			jdbcTemplate.update(sql, new Object[] {ratingValue, movie, author});
			return true;
		} else {
			return false;
		}
	}
	
	// Get Ratings for movie **************************************************
	
	public float getRatingById(int movie) {
		String sql = "select avg(ratingValue) from ratings where movie = ?";
		AvgMovieRating avgMovieRating = jdbcTemplate.queryForObject(sql, new AverageRatingRowMapper(), movie);
		final float rating = avgMovieRating.getAverageRating();
		return rating;
	}
	
	public Collection<Rating> getRatingsById(int movie){
		String sql = "select * from ratings where movie = ?";
		List<Rating> ratings = jdbcTemplate.query(sql, new RatingRowMapper(), movie);
		return ratings;
	}
	
	// Delete Ratings **************************************************

	public String deleteRating(int movie, int id) {
		String sql1 = "SELECT * from ratings where id = ? and movie = ?";
		Rating rating = jdbcTemplate.queryForObject(sql1, new RatingRowMapper(), id, movie);
		final String checkedAuthor = ((ApplicationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername().toString();
		final String author = rating.getAuthor();
		
		if(checkedAuthor.equals(author)) {
			final String sql2 = "DELETE FROM ratings WHERE id = ? and movie = ?";
			jdbcTemplate.update(sql2, id, movie);
			return "Rating Sucessfully Deleted";
		} else {
			return "You are not permitted to delete this rating";
		}
		
	}
	
	
}
