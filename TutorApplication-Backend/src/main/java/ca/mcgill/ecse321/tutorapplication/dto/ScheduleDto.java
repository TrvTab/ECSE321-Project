package ca.mcgill.ecse321.tutorapplication.dto;

import java.util.Set;


public class ScheduleDto {
	
	private String scheduleID;
	private boolean onLeave;
	private TutorDto tutor;
	private Set<BookingDto> bookings;
	private String tutorName;
	
	public ScheduleDto(){
		
	}
	
	public ScheduleDto(boolean onLeave, String ScheduleID) {
		this.scheduleID = ScheduleID;
		this.onLeave = onLeave;
	}

	public ScheduleDto(boolean onLeave, String ScheduleID, TutorDto tutor, Set<BookingDto> bookings) {
		this.scheduleID = ScheduleID;
		this.onLeave = onLeave;
		this.tutor = tutor;
		this.bookings = bookings;
	}
	
	public ScheduleDto (boolean onLeave, String ScheduleID, String tutorName, Set<BookingDto> bookings) {
		this.onLeave = onLeave;
		this.scheduleID = ScheduleID;
		this.tutorName = tutorName;
		this.bookings = bookings;
	}
	
	public String getTutorName() {
		return this.tutorName;
	}
	
	public void setTutorName(String tutorName) {
		this.tutorName = tutorName;
	}
	
	public String getScheduleID() {
		return scheduleID;
	}
	
	public void setScheduleID(String ScheduleID) {
		this.scheduleID = ScheduleID;
	}
	
	public boolean isOnLeave() {
		return onLeave;
	}
	
	public void setOnLeave(boolean onLeave) {
		this.onLeave = onLeave;
	}
	
	public TutorDto getTutor() {
		return tutor;
	}
	public void setTutor(TutorDto tutor) {
		this.tutor = tutor;
	}
	
	public Set<BookingDto> getBooking(){
		return bookings;
	}
	public void setBooking(Set<BookingDto> bookings) {
		this.bookings = bookings;
	}
}
