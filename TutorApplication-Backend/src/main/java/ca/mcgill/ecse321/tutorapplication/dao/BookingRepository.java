package ca.mcgill.ecse321.tutorapplication.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutorapplication.model.Booking;


public interface BookingRepository extends CrudRepository<Booking, String>{

	Booking findBookingByBookingId(String bookingId);
	

}
