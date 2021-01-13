package ca.mcgill.ecse321.tutorapplication.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutorapplication.model.Course;

public interface CourseRepository extends CrudRepository<Course, String>{
	
	Course findCourseByCourseId(String courseId);


}
