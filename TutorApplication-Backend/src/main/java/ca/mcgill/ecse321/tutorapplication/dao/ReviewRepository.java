package ca.mcgill.ecse321.tutorapplication.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutorapplication.model.Course;
import ca.mcgill.ecse321.tutorapplication.model.Review;

public interface ReviewRepository extends CrudRepository<Review, String>{
	
	Review findReviewByReviewId(String reviewId);

}