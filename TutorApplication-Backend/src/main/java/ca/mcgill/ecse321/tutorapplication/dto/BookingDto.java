package ca.mcgill.ecse321.tutorapplication.dto;

import java.time.LocalDate;
import java.time.LocalTime;



public class BookingDto {

	private String bookingID;
	private LocalDate bookingDate;
	private LocalTime startTime;
	private LocalTime endTime;
	private RoomDto room;
	private CourseDto course;
	private StudentDto student;
	private ScheduleDto schedule;
	private String tutorName;
	private ReviewDto review;
	
	public ReviewDto getReview() {
		return review;
	}

	public void setReview(ReviewDto review) {
		this.review = review;
	}

	public BookingDto() {
	}
	
	public BookingDto(String bookingID) {
		this(bookingID, LocalDate.parse("2019-01-01"), LocalTime.parse("00:00:00"), LocalTime.parse("23:59:59"));
	}
	public BookingDto(String bookingId, StudentDto student, CourseDto course, 
			ScheduleDto schedule, LocalDate bookingDate, LocalTime startTime, LocalTime endTime, String tutorName) {
		this.bookingID = bookingId;
		this.student = student;
		this.course = course;
		this.schedule = schedule;
		this.bookingDate = bookingDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.tutorName = tutorName;
		
	}
	
	public BookingDto(String bookingId, StudentDto student, CourseDto course, 
			ScheduleDto schedule, LocalDate bookingDate, LocalTime startTime, LocalTime endTime, String tutorName, ReviewDto review) {
		this.bookingID = bookingId;
		this.student = student;
		this.course = course;
		this.schedule = schedule;
		this.bookingDate = bookingDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.tutorName = tutorName;
		this.review = review;
		
	}

	
	public BookingDto(String bookingID, LocalDate bookingDate, LocalTime startTime, LocalTime endTime) {
		this.bookingID = bookingID;
		this.bookingDate = bookingDate;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public String getBookingID() {
	    return bookingID;
	}
	
	public void setBookingID(String value) {
	    this.bookingID = value;
	}
	
	public LocalDate getBookingDate() {
	    return bookingDate;
	}
	
	public void setBookingDate(LocalDate value) {
	    this.bookingDate = value;
	}
	
	public LocalTime getStartTime() {
		return startTime;
	}
	
	public void setStartTime(LocalTime value) {
	    this.startTime = value;
	}
	
	public LocalTime getEndTime() {
		return endTime;
	}
	
	public void setEndTime(LocalTime value) {
	    this.endTime = value;
	}
	
	public RoomDto getRoom() {
		return room;
	}
	
	public void setRoom(RoomDto room) {
	      this.room = room;
	}
	
	public CourseDto getCourse() {
		return course;
	}
	
	public void setCourse(CourseDto course) {
	      this.course = course;
	}
	
	public StudentDto getStudent() {
		return student;
	}
	
	public void setStudent(StudentDto student) {
		this.student = student;
	}
	
	public ScheduleDto getSchedule() {
		return schedule;
	}
	
	public void setSchedule(ScheduleDto schedule) {
		this.schedule = schedule;
	}

	public String getTutorName() {
		return tutorName;
	}

	public void setTutorName(String tutorName) {
		this.tutorName = tutorName;
	}
}
