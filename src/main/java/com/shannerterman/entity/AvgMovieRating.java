package com.shannerterman.entity;

public class AvgMovieRating {
	private float averageRating;

	public float getAverageRating() {
		return (float)(Math.round(averageRating * 10) / 10.0);
	}

	public void setAverageRating(float averageRating) {
		this.averageRating = averageRating;
	}
	
}
