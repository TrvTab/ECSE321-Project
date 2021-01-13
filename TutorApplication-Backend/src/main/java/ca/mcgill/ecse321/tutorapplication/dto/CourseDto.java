package ca.mcgill.ecse321.tutorapplication.dto;

import java.util.Set;

import ca.mcgill.ecse321.tutorapplication.model.Booking;
import ca.mcgill.ecse321.tutorapplication.model.Tutor;

public class CourseDto {
	
	private String courseId;
	private Set<TutorDto> tutor;
	private Set<BookingDto> booking;
	
	public CourseDto() {
	}
	
	public CourseDto(String courseId) {
		this.courseId = courseId;
	}
	
	public CourseDto(String courseId, Set<TutorDto> tutor, Set<BookingDto> booking) {
		this.courseId = courseId;
		this.tutor = tutor;
		this.booking = booking;
	}
	
	public CourseDto(String courseId, Set<TutorDto> tutors) {
		this.courseId = courseId;
		this.tutor = tutors;
	}

	public String getcourseId() {
		return courseId;
	}
	
	public void setCourseId(String value) {
	    this.courseId = value;
	}
	
	public Set<TutorDto> getTutor() { //Should these two be with TutorDto?? Nah eh
	      return tutor;
	   }
	
	public void setTutor(Set<TutorDto> tutors) {
	      this.tutor = tutors;
	   }
	
	public Set<BookingDto> getBooking() {
	      return booking;
	}
	
	public void setBooking(Set<BookingDto> bookings) {
	      this.booking = bookings;
	   }
	   
}
