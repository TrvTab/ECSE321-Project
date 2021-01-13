package ca.mcgill.ecse321.tutorapplication.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Student{
	private String name;
	
public void setName(String name) {
	this.name = name;
}

public String getName() {
	return this.name;
}
	
   private int rating;

public void setRating(int value) {
    this.rating = value;
}
public int getRating() {
    return this.rating;
}
private String studentId;

public void setStudentId(String value) {
    this.studentId = value;
}
private String password;

public void setPassword(String value) {
    this.password = value;
}
public String getPassword() {
    return this.password;
}
@Id
public String getStudentId() {
    return this.studentId;
}
   private Set<Booking> booking;
   
   @OneToMany(mappedBy="student", cascade=CascadeType.REMOVE, fetch=FetchType.EAGER ) //Changed Here 
   public Set<Booking> getBooking() {
      return this.booking;
   }
   
   public void setBooking(Set<Booking> bookings) {
      this.booking = bookings;
   }
   
   public void addStudentBooking_Helper(Booking booking) {
	   this.booking.add(booking);
   }

   
   /*private User user;
   
   @OneToOne(mappedBy="user")
   public User getUser() {
	   return this.user;
   }
   public void setUser(User user) {
	   this.user = user;
   }*/
   

}
