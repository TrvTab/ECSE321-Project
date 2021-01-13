package ca.mcgill.ecse321.tutorapplication.dto;

import java.util.Set;

public class StudentDto {
	
	private String name;
	private int rating;
	private String studentId;
	private Set<BookingDto> bookings;
	private String password;
	
	public StudentDto() {
		
	}
	
	public StudentDto(String name, String studentId, String password) {
		this.name = name;
		this.studentId = studentId;
		this.password = password;
	}
	
	public int getRating() {
		return rating;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public String getStudentId() {
		return studentId;
	}
	
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
	public Set<BookingDto> getBooking(){
		return bookings;
	}
	
	public void setBooking(Set<BookingDto> bookings) {
		this.bookings = bookings;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String value) {
	    this.password = value;
	}
	public String getPassword() {
	    return this.password;
	}


}
