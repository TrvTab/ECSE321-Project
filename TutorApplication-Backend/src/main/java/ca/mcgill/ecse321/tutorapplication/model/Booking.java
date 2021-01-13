package ca.mcgill.ecse321.tutorapplication.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Booking{
   private LocalTime startTime;

public void setStartTime(LocalTime value) {
    this.startTime = value;
}
public LocalTime getStartTime() {
    return this.startTime;
}
private LocalTime endTime;

public void setEndTime(LocalTime value) {
    this.endTime = value;
}
public LocalTime getEndTime() {
    return this.endTime;
}

	private LocalDate bookingDate;

	public void setBookingDate(LocalDate value) {
		this.bookingDate = value;
	}
	
	public LocalDate getBookingDate() {
		return this.bookingDate;
	}
	

private String bookingId;

public void setBookingId(String value) {
    this.bookingId = value;
}
@Id
public String getBookingId() {
    return this.bookingId;
}
   private Course course;
   
   @ManyToOne(optional=false)
   public Course getCourse() {
      return this.course;
   }
   
   public void setCourse(Course course) {
      this.course = course;
   }
   
   private Review review;
   
   @OneToOne(cascade=CascadeType.ALL)
   public Review getReview() {
      return this.review;
   }
   
   public void setReview(Review review) {
      this.review = review;
   }
   
   private Student student;
   
   @ManyToOne(optional=false, cascade=CascadeType.PERSIST)
   public Student getStudent() {
      return this.student;
   }
   
   public void setStudent(Student student) {
      this.student = student;
   }
   
   private Room room;
   
   @ManyToOne(cascade=CascadeType.PERSIST) //changed to all
   public Room getRoom() {
      return this.room;
   }
   
   public void setRoom(Room room) {
      this.room = room;
   }
   
   private Schedule schedule;
   
   @ManyToOne(optional=false, cascade=CascadeType.PERSIST) //changed to all
   public Schedule getSchedule() {
      return this.schedule;
   }
   
   public void setSchedule(Schedule schedule) {
      this.schedule = schedule;
   }
}