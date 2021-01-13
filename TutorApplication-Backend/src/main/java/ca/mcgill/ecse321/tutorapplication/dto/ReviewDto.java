package ca.mcgill.ecse321.tutorapplication.dto;

import java.util.Set;

import ca.mcgill.ecse321.tutorapplication.model.Booking;

public class ReviewDto {
	
	private int rating;
	private String reviewId;
	private String comment;
	
	public ReviewDto() {
	}
	
	public ReviewDto(int rating, String reviewId, String comment) {
		this.rating = rating;
		this.reviewId = reviewId;
		this.comment = comment;
	}
	
	public int getRating() {
		return rating;
	}
	
	public void setRating(int value) {
	    this.rating = value;
	}
	
	
	public String getReviewId() {
		return reviewId;
	}
	
	public void setReviewId(String value) {
	    this.reviewId = value;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	} 

}
