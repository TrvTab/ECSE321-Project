package ca.mcgill.ecse321.tutorapplication.dao;


import org.springframework.data.repository.CrudRepository;
import java.util.List;

import ca.mcgill.ecse321.tutorapplication.model.Course;
import ca.mcgill.ecse321.tutorapplication.model.Room;

public interface RoomRepository extends CrudRepository<Room, String> {
	
	Room findByRoomId(String roomId);
	boolean existsByRoomId(Room roomId);

	
	
	
	
	
	
	
	/*
	 * THIS IS THE EXAMPLE BY WHICH YOU SHOULD BASE YOURSELF ON. 
	 * 
	 * public interface RegistrationRepository extends CrudRepository<Registration, Integer> {

	List<Registration> findByPerson(Person personName);

	boolean existsByPersonAndEvent(Person person, Event eventName);

	Registration findByPersonAndEvent(Person person, Event eventName);

}

*/
	
	

	
	

}
