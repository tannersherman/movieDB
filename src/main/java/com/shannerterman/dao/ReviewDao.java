package com.shannerterman.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.shannerterman.entity.ApplicationUser;
import com.shannerterman.entity.Review;

@Repository
public class ReviewDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static class ReviewRowMapper implements RowMapper<Review>{

		@Override
		public Review mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Review review = new Review();
			review.setId(resultSet.getInt("id"));
			review.setReview(resultSet.getString("review"));
			review.setMovie(resultSet.getInt("movie"));
			review.setAuthor(resultSet.getString("author"));
			return review;
		}
		
	}
	
	
	// Insert Review  **********************************************************
	public String insertReview(int movie, String review) {
		final String sql = "INSERT INTO reviews (review, movie, author) VALUES (?,?,?)";
		final String author = ((ApplicationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername().toString();
		final String query = "Select * from reviews where (author = ? and movie = ?)";
		String checkedAuthor = "";
		
		try{
			checkedAuthor = jdbcTemplate.queryForObject(query, new ReviewRowMapper(), author, movie).getAuthor();
		} catch(EmptyResultDataAccessException e) {
			checkedAuthor = null;
		}
		
		if(author.equals(checkedAuthor)) {
			return "You have already submitted a review for this film";
		} else {
			jdbcTemplate.update(sql, new Object[] {review, movie, author});
			return "Review Successfully Inserted";
		}
		
		
		
	}
	
	
	// Get Reviews for Movie **********************************************************
	
	public List<Review> getReviewsByMovie(int movie){
		final String sql = "Select * from reviews where movie = ?";
		List<Review> reviews =  jdbcTemplate.query(sql, new ReviewRowMapper(), movie);
		return reviews;
	}

	// Delete Review **************************************************

	public String deleteReview(int movie, int id) {
		String sql1 = "SELECT * from reviews where id = ? and movie = ?";
		Review review = jdbcTemplate.queryForObject(sql1, new ReviewRowMapper(), id, movie);
		final String checkedAuthor = ((ApplicationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername().toString();
		final String author = review.getAuthor();
		
		if(checkedAuthor.equals(author)) {
			final String sql2 = "DELETE FROM reviews WHERE id = ? and movie = ?";
			jdbcTemplate.update(sql2, id, movie);
			return "Review Sucessfully Deleted";
		} else {
			return "You are not permitted to delete this Review";
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
