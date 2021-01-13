package ca.mcgill.ecse321.tutorapplication.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutorapplication.model.Student;

public interface StudentRepository extends CrudRepository<Student, String>{

	Student findByStudentId(String Id);
	Student findByName(String name);

}