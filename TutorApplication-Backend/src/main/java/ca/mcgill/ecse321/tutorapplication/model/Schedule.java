package ca.mcgill.ecse321.tutorapplication.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Id;

@Entity
public class Schedule{
   private boolean onLeave;

public void setOnLeave(boolean value) {
    this.onLeave = value;
}
public boolean isOnLeave() {
    return this.onLeave;
}
private Tutor tutor;

@OneToOne(mappedBy="schedule" , optional=false, cascade=CascadeType.ALL) //changed to all
public Tutor getTutor() {
   return this.tutor;
}

public void setTutor(Tutor tutor) {
   this.tutor = tutor;
}

private Set<Booking> booking;


@OneToMany(mappedBy="schedule", fetch=FetchType.EAGER)

public Set<Booking> getBooking() {
   return this.booking;
}

public void setBooking(Set<Booking> bookings) {
   this.booking = bookings;
}
public void addScheduleBooking_Helper(Booking booking) {
	this.booking.add(booking);
}

private String scheduleId;

public void setScheduleId(String value) {
    this.scheduleId = value;
}
@Id
@GeneratedValue(generator = "uuid")
@GenericGenerator(name = "uuid", strategy = "uuid2")
public String getScheduleId() {
    return this.scheduleId;
}
}
