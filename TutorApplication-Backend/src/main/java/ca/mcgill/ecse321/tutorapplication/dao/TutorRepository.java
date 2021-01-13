package ca.mcgill.ecse321.tutorapplication.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutorapplication.model.Tutor;


public interface TutorRepository extends CrudRepository<Tutor, String>{

	Tutor findByTutorId(String Id);
	Tutor findByName(String name);
	Tutor findByTutorIdAndName(String Id, String name);

}