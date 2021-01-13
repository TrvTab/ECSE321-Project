package ca.mcgill.ecse321.tutorapplication.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutorapplication.model.Room;
import ca.mcgill.ecse321.tutorapplication.model.Schedule;

public interface ScheduleRepository extends CrudRepository<Schedule, String>{
	
	Schedule findByScheduleId(String scheduleId);
	
	
	//List<Schedule> findBySchedule(Schedule schedule);
	
	

	
	
	
	
	

}
