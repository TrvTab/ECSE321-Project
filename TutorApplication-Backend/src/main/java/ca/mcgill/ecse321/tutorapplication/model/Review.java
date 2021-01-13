package ca.mcgill.ecse321.tutorapplication.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Id;

@Entity
public class Review{
   private int rating;

public void setRating(int value) {
    this.rating = value;
}
public int getRating() {
    return this.rating;
}

private String reviewId;

public void setReviewId(String value) {
    this.reviewId = value;
}
@Id
public String getReviewId() {
    return this.reviewId;
}

	public String getComment() {
	return comment;
}
public void setComment(String comment) {
	this.comment = comment;
}

	private String comment;
	
}
