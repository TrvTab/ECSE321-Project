package ca.mcgill.ecse321.tutorapplication.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

@Entity
public class Course{
   private String courseId;

public void setCourseId(String value) {
    this.courseId = value;
}
@Id
public String getCourseId() {
    return this.courseId;
}
   private Set<Tutor> tutor;
   
   @ManyToMany (fetch=FetchType.EAGER) //Changed 
   public Set<Tutor> getTutor() {
      return this.tutor;
   }
   
   public void setTutor(Set<Tutor> tutors) {
      this.tutor = tutors;
   }
   
   public void addCourseTutor_Helper(Tutor tutor) {
	   this.tutor.add(tutor);
   }
   

   private Set<Booking> booking;
   
   @OneToMany(mappedBy="course") // Changed 
   public Set<Booking> getBooking() {
      return this.booking;
   }
   
   
   public void setBooking(Set<Booking> bookings) {
      this.booking = bookings;
   }
   
   public void addCourseBooking_Helper(Booking booking) {
	   this.booking.add(booking);
   }
   
   }
