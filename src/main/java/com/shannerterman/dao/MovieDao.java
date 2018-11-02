package com.shannerterman.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.shannerterman.entity.ApplicationUser;
import com.shannerterman.entity.Movie;


@Repository
@Qualifier("movieDao")
public class MovieDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static class MovieRowMapper implements RowMapper<Movie> {

		public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
			Movie movie = new Movie();
			movie.setMovieId(resultSet.getInt("movieId"));
			movie.setMovieTitle(resultSet.getString("movieTitle"));
			movie.setDirector(resultSet.getString("director"));
			movie.setYear(resultSet.getInt("year"));
			movie.setAvgRating(resultSet.getFloat("avgRating"));
			movie.setPoster(resultSet.getString("poster"));
			movie.setAuthor(resultSet.getString("author"));
			return movie;
		}
		
	}
	
	
	// Get Movies **********************************************************
	
	public Collection<Movie> getAllMovies(){
		final String sql = "SELECT * FROM movies";
		List<Movie> movies = jdbcTemplate.query(sql, new MovieRowMapper());
		return movies;
	}
	
	public Collection<Movie> getAllMoviesByDirector(String director){
		final String spacesDirector = director.replaceAll("_", " ");
		final String sql = "SELECT * FROM movies WHERE director = ?";
		List<Movie> movies = jdbcTemplate.query(sql, new MovieRowMapper(), spacesDirector);
		return movies;
	}
	
	public Collection<Movie> getAllMoviesByYear(int year){
		final String sql = "SELECT * FROM movies WHERE year = ?";
		List<Movie> movies = jdbcTemplate.query(sql, new MovieRowMapper(), year);
		return movies;
	}
	
	
	public Collection<Movie> getTopMovies() {
		final String sql = "SELECT * FROM movies ORDER BY avgRating desc";
		List<Movie> movies = jdbcTemplate.query(sql, new MovieRowMapper());
		return movies;
	}
	
	public Movie getMovieById(int movieId) {
		final String sql = "SELECT * FROM movies where movieId = ?";
		Movie movie = jdbcTemplate.queryForObject(sql, new MovieRowMapper(), movieId);
		return movie;
	}
	
	

	// Delete Movies **********************************************************
	
	public String deleteMovieById(int movieId) {
		// SELECT column_name(s) FROM table_name where id  = value
		
		final String sql1 = "SELECT * FROM movies where movieId = ?";
		Movie movie = jdbcTemplate.queryForObject(sql1, new MovieRowMapper(), movieId);
		final String checkedAuthor = ((ApplicationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername().toString();
		final String author = movie.getAuthor();
		
		// DELETE FROM table_name WHERE some_column = some_value
		if(checkedAuthor.equals(author)) {
			final String sql2 = "DELETE FROM movies WHERE movieId = ?";
			jdbcTemplate.update(sql2, movieId);
			return "Movie Sucessfully Deleted";
		} else {
			return "You are not permitted to delete this Movie";
		}
		
		
	}

	
	// Update Movies **********************************************************
	
	public void updateMovie(Movie movie) {
		final String sql = "UPDATE movies SET movieTitle = ?, director = ?, year = ?, poster = ? WHERE movieId = ?";
		int movieId = movie.getMovieId();
		final String movieTitle = movie.getMovieTitle();
		final String director = movie.getDirector();
		final int year = movie.getYear();
		final String poster = movie.getPoster();
		jdbcTemplate.update(sql, new Object[] {movieTitle, director, year, poster, movieId});
	}
	
	public void updateAvgRating(int movieId, float avgRating) {
		final String sql = "UPDATE movies SET avgRating = ? WHERE movieId = ?";
		jdbcTemplate.update(sql, new Object[] {avgRating, movieId});
	}
		
	
	// Add Movie **********************************************************
	public void insertMovie(Movie movie) {
		// INSERT INTO table_name (column1, column2, column3) VALUES (value1, value2, value3)
		
		final String sql = "INSERT INTO movies (movieTitle, director, year, poster, author) VALUES (?,?,?,?,?)";
		final String movieTitle = movie.getMovieTitle();
		final String director = movie.getDirector();
		final int year = movie.getYear();
		final String poster = movie.getPoster();
		final String author = ((ApplicationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername().toString();
		jdbcTemplate.update(sql, new Object[] {movieTitle, director, year, poster, author});
	}
	
	
	
}
