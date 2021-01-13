package ca.mcgill.ecse321.tutorapplication.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;

import java.time.LocalTime;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Tutor{

	public Tutor() {
		startTime = LocalTime.parse("09:00");
		endTime = LocalTime.parse("17:00");
	}
private LocalTime startTime;

public void setStartTime(LocalTime startTime) {
	this.startTime = startTime;
}

public LocalTime getStartTime() {
	return this.startTime;
}

private LocalTime endTime;

public void setEndTime(LocalTime endTime) {
	this.endTime = endTime;
}

public LocalTime getEndTime() {
	return this.endTime;
}

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
private String tutorId;

public void setTutorId(String value) {
    this.tutorId = value;
}
@Id
public String getTutorId() {
    return this.tutorId;
}
   private Set<Course> course;
   
   @ManyToMany(mappedBy="tutor", fetch=FetchType.EAGER)
   public Set<Course> getCourse() {
      return this.course;
   }
   
   public void addTutorCourse_Helper(Course course) {
	   this.course.add(course);
   }
   
   public void setCourse(Set<Course> courses) {
      this.course = courses;
   }
   
   private Schedule schedule;
   
   @OneToOne(optional=false, cascade=CascadeType.ALL)
   public Schedule getSchedule() {
      return this.schedule;
   }
   
   public void setSchedule(Schedule schedule) {
      this.schedule = schedule;
   }
   private String password;

   public void setPassword(String value) {
       this.password = value;
   }
   public String getPassword() {
       return this.password;
   }
   
  /*
   private User user;
   
   @OneToOne(mappedBy="user")
   public User getUser() {
	   return this.user;
   }
   
   public void setUser(User user) {
	   this.user = user;
   }*/
}
