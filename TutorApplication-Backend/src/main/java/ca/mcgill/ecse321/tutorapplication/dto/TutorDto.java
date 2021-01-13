package ca.mcgill.ecse321.tutorapplication.dto;

import java.time.LocalTime;

import java.util.Set;

public class TutorDto{
	
	private String name;
	private int rating;
	private String tutorId;
	private ScheduleDto schedule;
	private String password;
	private Set<CourseDto> courses;
	
	
	public TutorDto(){
		
	}
	

	public TutorDto(String name, String tutorId, String password, int rating) {

		this.name = name;
		this.tutorId = tutorId;
		this.password = password;
		this.rating = rating;

	}
	
	public TutorDto(String name, String tutorId, String password, int rating, ScheduleDto schedule) {
		this.name = name;
		this.tutorId = tutorId;
		this.password = password;
		this.rating = rating;
		this.schedule = schedule;

	}
	
	public LocalTime startTime;

	
	
	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	
	public LocalTime endTime;

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public int getRating() {
		return rating;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public String getTutorId() {
		return tutorId;
	}
	
	public void setTutorId(String tutorId) {
		this.tutorId = tutorId;
	}
	
	public ScheduleDto getSchedule() {
		return schedule;
	}
	
	public void setSchedule(ScheduleDto schedule) {
		this.schedule = schedule;
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
	public Set<CourseDto> getCourse() { //Should these two be with TutorDto?? Nah eh
	      return this.courses;
	   }
	
	public void setCourse(Set<CourseDto> courses) {
	      this.courses = courses;
	   }
	
	

}
