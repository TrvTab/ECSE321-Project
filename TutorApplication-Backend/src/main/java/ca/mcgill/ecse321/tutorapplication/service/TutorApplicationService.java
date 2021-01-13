package ca.mcgill.ecse321.tutorapplication.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.lang.IllegalArgumentException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import ca.mcgill.ecse321.tutorapplication.dao.BookingRepository;
import ca.mcgill.ecse321.tutorapplication.dao.StudentRepository;
import ca.mcgill.ecse321.tutorapplication.dao.TutorRepository;
import ca.mcgill.ecse321.tutorapplication.dao.CourseRepository;
import ca.mcgill.ecse321.tutorapplication.dao.ReviewRepository;
import ca.mcgill.ecse321.tutorapplication.dao.RoomRepository;
import ca.mcgill.ecse321.tutorapplication.dao.ScheduleRepository;


import ca.mcgill.ecse321.tutorapplication.model.Booking;
import ca.mcgill.ecse321.tutorapplication.model.Course;
import ca.mcgill.ecse321.tutorapplication.model.Review;
import ca.mcgill.ecse321.tutorapplication.model.Room;
import ca.mcgill.ecse321.tutorapplication.model.RoomSize;
import ca.mcgill.ecse321.tutorapplication.model.Schedule;
import ca.mcgill.ecse321.tutorapplication.model.Student;
import ca.mcgill.ecse321.tutorapplication.model.Tutor;


@Service
public class TutorApplicationService {
	
	/*
	 * We create a new member variable, making it as autowired. Now when spring will create an instance of TutorApplicationService. 
	 * It will inject whichever repository we are referring to
	 * 
	 */
	@Autowired
	BookingRepository bookingRepository; //This is a member variable used to make calls on the repository using the crudrepo services
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	TutorRepository tutorRepository;
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	RoomRepository roomRepository;

	
	@Autowired
	ScheduleRepository scheduleRepository;
	

	
	@Autowired
	ReviewRepository reviewRepository;
	
	
	
	public void clearAll() {
		bookingRepository.deleteAll();
		courseRepository.deleteAll();
		tutorRepository.deleteAll();
		roomRepository.deleteAll();
		scheduleRepository.deleteAll();
		studentRepository.deleteAll();
		reviewRepository.deleteAll();
	}
	
	@Transactional
	public void linkTutorAndCourse(Course course, Tutor tutor) {
		course.getTutor().add(tutor);
		tutor.getCourse().add(course);
		courseRepository.save(course);
	}
	
	
	
	//BOOKING METHODS
	
	//Create 
	@Transactional 
	public Booking createBooking(String bookingId, Student student, Course course, Schedule schedule, LocalDate BookingDate,  LocalTime StartTime, LocalTime EndTime) {
		if (bookingId==null) {
			throw new IllegalArgumentException("Booking Id cannot be empty!");
		}
		if (BookingDate==null) {
			throw new IllegalArgumentException("Booking Date cannot be empty!");
		}
		if (StartTime==null) {
			throw new IllegalArgumentException("Booking start time cannot be empty!");
		}
		if (EndTime==null) {
			throw new IllegalArgumentException("Booking end time cannot be empty!");
		}
		if (bookingId.equals("")) {
			throw new IllegalArgumentException("Booking Id cannot be empty!");
		}
		if (bookingId.equals(" ")) {
			throw new IllegalArgumentException("Booking Id cannot be empty!");
		}
		if (EndTime.isBefore(StartTime)) {
			throw new IllegalArgumentException("Booking end time cannot be before booking start time!");
		}
		
	
		
		
		Booking booking = bookingRepository.findBookingByBookingId(bookingId);
		if (booking == null) {
			booking = new Booking();
			
			booking.setBookingId(bookingId);
			booking.setStudent(student);
			student.addStudentBooking_Helper(booking);
		//	this.addStudentBooking(student.getStudentId(), booking);
			booking.setCourse(course);
			course.addCourseBooking_Helper(booking);
		//	this.addCourseBooking(course.getCourseId(), booking);
			booking.setSchedule(schedule);
			schedule.addScheduleBooking_Helper(booking);
		//	this.addScheduleBooking(schedule.getScheduleId(), booking);
			booking.setBookingDate(BookingDate);
			booking.setStartTime(StartTime);
			booking.setEndTime(EndTime);
		
			bookingRepository.save(booking);
			
		}
		return booking;
	}
	
	//Read 
	@Transactional 
	public Booking getBooking(String bookingId) {
		Booking booking = bookingRepository.findBookingByBookingId(bookingId);
		return booking;
	}
	
	public List<Booking> getAllBookings(){
		return toList(bookingRepository.findAll());
	}
	
	
	

	//Update 
	@Transactional
	public Booking updateBookingId(String pastBookingId, String newBookingId) {
		if (pastBookingId==null) {
			throw new IllegalArgumentException("Booking Id cannot be empty!");
			
		}
		if (newBookingId==null) {
			throw new IllegalArgumentException("Booking Id cannot be empty!");
			
		}
		if (pastBookingId.equals("")) {
			throw new IllegalArgumentException("Booking Id cannot be empty!");
			
		}
		if (newBookingId.equals("")) {
			throw new IllegalArgumentException("Booking Id cannot be empty!");
			
		}
		if (pastBookingId.equals(" ")) {
			throw new IllegalArgumentException("Booking Id cannot be empty!");
			
		}
		if (newBookingId.equals(" ")) {
			throw new IllegalArgumentException("Booking Id cannot be empty!");
			
		}
		
		Booking bookingToBeChanged = bookingRepository.findBookingByBookingId(pastBookingId);
		bookingToBeChanged.setBookingId(newBookingId);
		bookingRepository.save(bookingToBeChanged);
		return bookingToBeChanged;
	}
	
	@Transactional
	public Booking updateBookingDate(String bookingId, LocalDate newBookingDate) {
		if (newBookingDate==null) {
			throw new IllegalArgumentException("Booking Date cannot be empty!");
			
		}
		Booking bookingToBeChanged = bookingRepository.findBookingByBookingId(bookingId);
		bookingToBeChanged.setBookingDate(newBookingDate);
		bookingRepository.save(bookingToBeChanged);
		return bookingToBeChanged;
	}
	
	@Transactional
	public Booking updateBookingTime(String bookingId, LocalTime newStartTime, LocalTime newEndTime) {
		if (newStartTime==null) {
			throw new IllegalArgumentException("Booking Start Time cannot be empty!");
			
		}
		if (newEndTime==null) {
			throw new IllegalArgumentException("Booking End Time cannot be empty!");
		}
		Booking bookingToBeChanged = bookingRepository.findBookingByBookingId(bookingId);
		bookingToBeChanged.setStartTime(newStartTime);
		bookingToBeChanged.setEndTime(newEndTime);
		bookingRepository.save(bookingToBeChanged);
		return bookingToBeChanged;
	}
	
	
	@Transactional
	public Booking updateBookingRoom(String bookingId, String newRoomId) {
		Booking bookingToBeChanged = bookingRepository.findBookingByBookingId(bookingId);
		Room roomToBeAdded = roomRepository.findByRoomId(newRoomId);
		bookingToBeChanged.setRoom(roomToBeAdded);
		bookingRepository.save(bookingToBeChanged);
		return bookingToBeChanged;
		
	}
	
	//Delete 
	@Transactional
	public void deleteBooking(String bookingToBeDeletedId){
		Booking bookingToBeDeleted = bookingRepository.findBookingByBookingId(bookingToBeDeletedId);
		this.removeCourseBooking(bookingToBeDeleted.getCourse().getCourseId(), bookingToBeDeleted);
		this.removeScheduleBooking(bookingToBeDeleted.getSchedule().getScheduleId(), bookingToBeDeletedId);
		this.removeStudentBooking(bookingToBeDeleted.getStudent().getStudentId(), bookingToBeDeleted);
		bookingRepository.delete(bookingToBeDeleted);
	}
	
	
	
	//COURSE METHODS
	
	
	//Create
	@Transactional 
	public Course createCourse(String courseId) {
		if (courseId==null) {
			throw new IllegalArgumentException("Course Id cannot be empty!");	
		}
		if (courseId.equals("")) {
			throw new IllegalArgumentException("Course Id cannot be empty!");
			
		}
		if (courseId.equals(" ")) {
			throw new IllegalArgumentException("Course Id cannot be empty!");
		}

		Course course = courseRepository.findCourseByCourseId(courseId);
		if (course == null) {
			course = new Course();
			course.setCourseId(courseId);
			course.setTutor(new HashSet<Tutor>());
			course.setBooking(new HashSet<Booking>());
			courseRepository.save(course);
		}
		return course;

	}
	
	//Read 
	@Transactional
	public Course getCourse(String CourseId) {
		Course course = courseRepository.findCourseByCourseId(CourseId);
		return course;
	}

	public List<Course> getAllCourses(){
		return toList(courseRepository.findAll());
	}
	
	//Update 
	
	@Transactional 
	public Course updateCourseId(String pastCourseId, String newCourseId) {
		if (pastCourseId==null) {
			throw new IllegalArgumentException("Course Id cannot be empty!");
			
		}
		if (newCourseId==null) {
			throw new IllegalArgumentException("Course Id cannot be empty!");
			
		}
		if (pastCourseId.equals("")) {
			throw new IllegalArgumentException("Course Id cannot be empty!");
			
		}
		if (newCourseId.equals("")) {
			throw new IllegalArgumentException("Course Id cannot be empty!");
			
		}
		if (pastCourseId.equals(" ")) {
			throw new IllegalArgumentException("Course Id cannot be empty!");
			
		}
		if (newCourseId.equals(" ")) {
			throw new IllegalArgumentException("Course Id cannot be empty!");
			
		}
		Course CourseToBeChanged = courseRepository.findCourseByCourseId(pastCourseId);
		CourseToBeChanged.setCourseId(newCourseId);
		courseRepository.save(CourseToBeChanged);
		return CourseToBeChanged;
	}
	
//	@Transactional 
//	public Course updateCourseTutor(String CourseId, Set<Tutor> newtutor) {
//		Course CourseToBeChanged = courseRepository.findCourseById(CourseId);
//		CourseToBeChanged.setTutor(newtutor);
//		courseRepository.save(CourseToBeChanged);
//		return CourseToBeChanged;
//	}
	
	@Transactional
	public Course addCourseTutor(String courseId, Tutor tutor) {
		if (tutor==null) {
			throw new IllegalArgumentException("Tutor cannot be empty!");
			
		}
		Course CourseToBeChanged = courseRepository.findCourseByCourseId(courseId);
		CourseToBeChanged.addCourseTutor_Helper(tutor);
		courseRepository.save(CourseToBeChanged);
		return CourseToBeChanged;
	}
	
	@Transactional
	public Course removeCourseTutor(String courseId, Tutor tutor) {
		if (tutor==null) {
			throw new IllegalArgumentException("Tutor must be chosen for update");
		}
		
		Course CourseToBeChanged = courseRepository.findCourseByCourseId(courseId);
		Set<Tutor> tutors = CourseToBeChanged.getTutor(); // make a helper method for this?

		Iterator<Tutor> tutor_itr = tutors.iterator();
		while (tutor_itr.hasNext()) {
			if (tutor_itr.next().equals(tutor)){
				tutor_itr.remove();
			}
		}
		Tutor tutorToBeChanged = tutorRepository.findByTutorId(tutor.getTutorId());
		Set<Course> courses = tutorToBeChanged.getCourse();
		Iterator<Course> course_itr = courses.iterator();
		while (course_itr.hasNext()) {
			if (course_itr.next().equals(CourseToBeChanged)){
				course_itr.remove();
			}
		}
		tutorRepository.save(tutorToBeChanged);
		courseRepository.save(CourseToBeChanged);
		return CourseToBeChanged;
	}
	
//	@Transactional 
//	public Course updateCourseBooking(String CourseId, Set<Booking> newBooking) {
//		Course CourseToBeChanged = courseRepository.findCourseById(CourseId);
//		CourseToBeChanged.setBooking(newBooking);
//		courseRepository.save(CourseToBeChanged);
//		return CourseToBeChanged;
//	}
	
	@Transactional
	public Course addCourseBooking(String courseId, Booking newBooking) {
		Course courseToBeChanged = courseRepository.findCourseByCourseId(courseId);
		Set<Booking> bookings = courseToBeChanged.getBooking();
		bookings.add(newBooking);
		courseRepository.save(courseToBeChanged);
		return courseToBeChanged;
		
	}
	
	@Transactional
	public Course removeCourseBooking(String courseId, Booking booking) {
		Course courseToBeChanged = courseRepository.findCourseByCourseId(courseId);
		Set<Booking> bookings = courseToBeChanged.getBooking();
		Iterator<Booking> itr = bookings.iterator();
		while(itr.hasNext()) {
			if(itr.next().equals(booking)){
				itr.remove();
			}
		}
		courseRepository.save(courseToBeChanged);
		return courseToBeChanged;
	}
	
	//Delete 
	@Transactional
	public void deleteCourse(String courseToBeDeletedId){
		Course courseToBeDeleted = courseRepository.findCourseByCourseId(courseToBeDeletedId);
		courseRepository.delete(courseToBeDeleted);
	}
	
	
	
	//ROOM METHODS
	@Transactional 
	public Room createRoom(String roomId,RoomSize size){
		if (roomId==null) {
			throw new IllegalArgumentException("Room Id cannot be empty!");
		}
		if (roomId.equals("")) {
			throw new IllegalArgumentException("Room Id cannot be empty!");
		}
		if (roomId.equals(" ")) {
			throw new IllegalArgumentException("Room Id cannot be empty!");
		}
		if (size==null) {
			throw new IllegalArgumentException("Room size cannot be empty!");
		}
		
		Room room = roomRepository.findByRoomId(roomId);
		if (room == null) {
			room = new Room();
			room.setRoomId(roomId);
			room.setRoomSize(size);
			roomRepository.save(room);
		}
		return room;
	}

	//Read 
	@Transactional
	public Room getRoom(String roomId) {
		Room room = roomRepository.findByRoomId(roomId);
		return room;
	}
	
	@Transactional 
	public List<Room> getAllRooms(){
		return toList(roomRepository.findAll());
	}
	
	//UpdateRoom
	
	@Transactional
	public Room updateRoomId(String pastRoomId, String newRoomId) {
		if (pastRoomId==null) {
			throw new IllegalArgumentException("Room Id cannot be empty!");
		}
		if (pastRoomId.equals("")) {
			throw new IllegalArgumentException("Room Id cannot be empty!");
		}
		if (pastRoomId.equals(" ")) {
			throw new IllegalArgumentException("Room Id cannot be empty!");
		}
		if (newRoomId==null) {
			throw new IllegalArgumentException("Room Id cannot be empty!");
		}
		if (newRoomId.equals("")) {
			throw new IllegalArgumentException("Room Id cannot be empty!");
		}
		if (newRoomId.equals(" ")) {
			throw new IllegalArgumentException("Room Id cannot be empty!");
		}
		Room roomToBeChanged = roomRepository.findByRoomId(pastRoomId);
		roomToBeChanged.setRoomId(newRoomId);
		roomRepository.save(roomToBeChanged);
		return roomToBeChanged;
	}
	
	@Transactional
	public Room updateRoomSize(String roomId, RoomSize newRoomSize) {
		if (newRoomSize==null) {
			throw new IllegalArgumentException("Room size cannot be empty!");
		}
		Room roomToBeChanged = roomRepository.findByRoomId(roomId);
		roomToBeChanged.setRoomSize(newRoomSize);
		roomRepository.save(roomToBeChanged);
		return roomToBeChanged;
	}
	

	
	//Delete 
	@Transactional
	public void deleteRoom(String roomToBeDeletedId){
		Room roomToBeDeleted = roomRepository.findByRoomId(roomToBeDeletedId);
		roomRepository.delete(roomToBeDeleted);
	}
	
	//RoomSIZE METHODS
	/*
	 * TO-DO: 
	 * There is no create roomSize, I'm pretty sure, as there is no delete, we can have update and read though, 
	 * these can be found in the Room methods
	 * 
	 */
	

	//SCHEDULE METHODS
	@Transactional 
	public Schedule createSchedule(String scheduleId,Tutor tutor) {
		if (scheduleId==null) {
			throw new IllegalArgumentException("Schedule Id cannot be empty!");
		}
		if (scheduleId.equals("")) {
			throw new IllegalArgumentException("Schedule Id cannot be empty!");
		}
		if (scheduleId.equals(" ")) {
			throw new IllegalArgumentException("Schedule Id cannot be empty!");
		}
		if (tutor==null) {
			throw new IllegalArgumentException("Schedule must have a tutor!");
		}
		Schedule schedule = scheduleRepository.findByScheduleId(scheduleId);
		if ( schedule == null ) {
			schedule = new Schedule();
			schedule.setOnLeave(false);
			schedule.setScheduleId(scheduleId);
			schedule.setTutor(tutor);
			schedule.setBooking(new HashSet<Booking>());
			scheduleRepository.save(schedule);
		}
		return schedule;
	}
	
		//Read 
	
	
	@Transactional 
	public Schedule getSchedule(String scheduleId) {
		Schedule schedule = scheduleRepository.findByScheduleId(scheduleId);
		return schedule;
	}
	
	@Transactional 
	public List<Schedule> getAllSchedules(){
		return toList(scheduleRepository.findAll());
	}
	

	@Transactional 
	public Schedule UpdateScheduleId(String pastScheduleId,String newScheduleId) {
		Schedule scheduleToBeChanged =  scheduleRepository.findByScheduleId(pastScheduleId);
		scheduleToBeChanged.setScheduleId(newScheduleId);
		scheduleRepository.save(scheduleToBeChanged);
		return scheduleToBeChanged;
	}
	
	// USELESS
	@Transactional 
	public Schedule UpdateScheduleTutor(String scheduleId,Tutor newTutor) {
		Schedule scheduleToBeChanged =  scheduleRepository.findByScheduleId(scheduleId);
		scheduleToBeChanged.setTutor(newTutor);
		scheduleRepository.save(scheduleToBeChanged);
		return scheduleToBeChanged;
	}
	
//	@Transactional 
//	public Schedule UpdateScheduleBooking(String scheduleId,Set<Booking> newBooking) {
//		Schedule scheduleToBeChanged =  scheduleRepository.findByScheduleId(scheduleId);
//		scheduleToBeChanged.setBooking(newBooking);
//		scheduleRepository.save(scheduleToBeChanged);
//		return scheduleToBeChanged;
//	}
	
	@Transactional
	public Schedule addScheduleBooking(String scheduleId, Booking newBooking) {
		Schedule scheduleToBeChanged = scheduleRepository.findByScheduleId(scheduleId);
		Set<Booking> bookings = scheduleToBeChanged.getBooking();
		bookings.add(newBooking);
		scheduleRepository.save(scheduleToBeChanged);
		return scheduleToBeChanged;
	}
	
	@Transactional
	public Schedule removeScheduleBooking(String scheduleId, String bookingId) {
		Schedule scheduleToBeChanged = scheduleRepository.findByScheduleId(scheduleId);
		Set<Booking> bookings = scheduleToBeChanged.getBooking();
		Iterator<Booking> itr = bookings.iterator();
		
		while (itr.hasNext()) {
			
			if (itr.next().getBookingId() == bookingId) {
				itr.remove();
			}
		}
		scheduleRepository.save(scheduleToBeChanged);
		return scheduleToBeChanged;
	}
	
	
	@Transactional 
	public Schedule UpdateScheduleOnLeave(String scheduleId,boolean changeOnLeave) {
		Schedule scheduleToBeChanged =  scheduleRepository.findByScheduleId(scheduleId);
		scheduleToBeChanged.setOnLeave(changeOnLeave);
		scheduleRepository.save(scheduleToBeChanged);
		return scheduleToBeChanged;
	}
	
	//Delete 
	@Transactional
	public void deleteSchedule(String scheduleToBeDeletedId) {
		Schedule scheduleToBeDeleted =  scheduleRepository.findByScheduleId(scheduleToBeDeletedId);
		scheduleRepository.delete(scheduleToBeDeleted);
		
	}
	
	//STUDENT METHODS
	
	public Student createStudent(String name, String id, String password) {
		if (name==null) {
			throw new IllegalArgumentException("Student name cannot be empty!");
		}
		if (name.equals("")) {
			throw new IllegalArgumentException("Student name cannot be empty!");
		}
		if (name.equals(" ")) {
			throw new IllegalArgumentException("Student name cannot be empty!");
		}
		if (id==null) {
			throw new IllegalArgumentException("Student Id cannot be empty!");
		}
		if (id.equals("")) {
			throw new IllegalArgumentException("Student Id cannot be empty!");
		}
		if (id.equals(" ")) {
			throw new IllegalArgumentException("Student Id cannot be empty!");
		}
		if (password==null) {
			throw new IllegalArgumentException("Password cannot be empty!");
		}
		if (password.equals("")) {
			throw new IllegalArgumentException("Password cannot be empty!");
		}
		if (password.equals(" ")) {
			throw new IllegalArgumentException("Password cannot be empty!");
		}
		if ((password.length() < 8)){ 
			throw new IllegalArgumentException("Password must be at least 8 characters!");
		}
		// Code to make sure that code contains at least 1 uppercase letter and 1 special character
		Pattern upperCasePattern = Pattern.compile("[A-Z]");
		Pattern specialCasePattern = Pattern.compile("[^a-z0-9]", Pattern.CASE_INSENSITIVE);
		if (!upperCasePattern.matcher(password).find()) {
			throw new IllegalArgumentException (" Password must contain at least 1 uppercase character!");
		}
		if (!specialCasePattern.matcher(password).find()) {
			throw new IllegalArgumentException (" Password must contain at least 1 special character!");
		}
		Student student = studentRepository.findByStudentId(id);
		if ( student ==  null) {
			student = new Student();
			student.setName(name);
			student.setStudentId(id);
			
			student.setPassword(password);
			student.setBooking(new HashSet<Booking>());
			
			studentRepository.save(student);
		}
		
		return student;
	}
	
	//Read 
	@Transactional 
	public Student getStudent(String studentId) {
		Student student = studentRepository.findByStudentId(studentId);
		return student;
	}
		
	@Transactional 
	public List<Student> getAllStudents(){
		return toList(studentRepository.findAll());
	}
		
	//Update 
	@Transactional 
	public Student updateStudentId(String pastId, String newId) {
		Student studentToBeChanged = studentRepository.findByStudentId(pastId);
		studentToBeChanged.setStudentId(newId);
		studentRepository.save(studentToBeChanged);
		return studentToBeChanged;
	}
	
	@Transactional 
	public Student updateStudentName(String id, String newName) {
		Student studentToBeChanged = studentRepository.findByStudentId(id);
		studentToBeChanged.setName(newName);
		studentRepository.save(studentToBeChanged);
		return studentToBeChanged;
	}
	



	
	@Transactional
	public Student addStudentBooking(String id, Booking newBooking) {
		Student studentToBeChanged = studentRepository.findByStudentId(id);
		Set<Booking> bookings = studentToBeChanged.getBooking();
		bookings.add(newBooking);
		studentRepository.save(studentToBeChanged);
		return studentToBeChanged;
		
	}
	
	@Transactional
	public Student removeStudentBooking(String id, Booking booking) {
		Student studentToBeChanged = studentRepository.findByStudentId(id);
		Set<Booking> bookings = studentToBeChanged.getBooking();
		Iterator<Booking> itr = bookings.iterator();
		while (itr.hasNext()) {
			if (itr.next().equals(booking)) {
				itr.remove();
			}
		}
		studentRepository.save(studentToBeChanged);
		return studentToBeChanged;
	}
	@Transactional 
	public Student updateStudentPassword(String Id, String oldPassword, String newPassword) {
		if (Id==null) {
			throw new IllegalArgumentException("Username cannot be empty!");
			
		}
		if (newPassword==null) {
			throw new IllegalArgumentException("Password cannot be empty!");
			
		}
		if (Id.equals("")) {
			throw new IllegalArgumentException("Username cannot be empty!");
			
		}
		if (newPassword.equals("")) {
			throw new IllegalArgumentException("Password cannot be empty!");
			
		}
		if (Id.equals(" ")) {
			throw new IllegalArgumentException("Username cannot be empty!");
			
		}
		if (newPassword.equals(" ")) {
			throw new IllegalArgumentException("Password cannot be empty!");
			
		}
		Pattern upperCasePattern = Pattern.compile("[A-Z]");
		Pattern specialCasePattern = Pattern.compile("[^a-z0-9]", Pattern.CASE_INSENSITIVE);
		if (!upperCasePattern.matcher(newPassword).find()) {
			throw new IllegalArgumentException (" Password must contain at least 1 uppercase character!");
		}
		if (!specialCasePattern.matcher(newPassword).find()) {
			throw new IllegalArgumentException (" Password must contain at least 1 special character!");
		}
		Student student = studentRepository.findByStudentId(Id);
		if (oldPassword.equals(student.getPassword())) {
			student.setPassword(newPassword);
		}
		else {
			throw new IllegalArgumentException ("Old password is incorrect");
		}

		studentRepository.save(student);
		return student;
	}
	//Delete 
	@Transactional
	public void deleteStudent(String studentToBeDeletedId) {
		Student studentToBeDeleted =  studentRepository.findByStudentId(studentToBeDeletedId);
		studentRepository.delete(studentToBeDeleted);
	}
	
	
	//TUTOR METHODS
	
	@Transactional
	public Tutor createTutor(String name, String id, String password, int rating) {
		if (name==null) {
			throw new IllegalArgumentException("Tutor name cannot be empty!");
		}
		if (name.equals("")) {
			throw new IllegalArgumentException("Tutor name cannot be empty!");
		}
		if (name.equals(" ")) {
			throw new IllegalArgumentException("Tutor name cannot be empty!");
		}
		if (id==null) {
			throw new IllegalArgumentException("Tutor Id cannot be empty!");
		}
		if (id.equals("")) {
			throw new IllegalArgumentException("Tutor Id cannot be empty!");
		}
		if (id.equals(" ")) {
			throw new IllegalArgumentException("Tutor Id cannot be empty!");
		}
		if (password==null) {
			throw new IllegalArgumentException("Password cannot be empty!");
		}
		if (password.equals("")) {
			throw new IllegalArgumentException("Password cannot be empty!");
		}
		if (password.equals(" ")) {
			throw new IllegalArgumentException("Password cannot be empty!");
		}
		if ((password.length() < 8)){
			throw new IllegalArgumentException("Password must be at least 8 characters!");
		}
		// Code to make sure that code contains at least 1 uppercase letter and 1 special character
		Pattern upperCasePattern = Pattern.compile("[A-Z]");
		Pattern specialCasePattern = Pattern.compile("[^a-z0-9]", Pattern.CASE_INSENSITIVE);
		if (!upperCasePattern.matcher(password).find()) {
			throw new IllegalArgumentException (" Password must contain at least 1 uppercase character!");
		}
		if (!specialCasePattern.matcher(password).find()) {
			throw new IllegalArgumentException (" Password must contain at least 1 special character!");
		}
		Tutor tutor = tutorRepository.findByTutorId(id);
		if ( tutor == null) {
			tutor = new Tutor();
			tutor.setName(name);
			tutor.setTutorId(id);
			tutor.setPassword(password);
			tutor.setRating(rating);
			tutor.setCourse(new HashSet<Course>());
			Schedule schedule = new Schedule();
			schedule.setBooking(new HashSet<Booking>());
			tutor.setSchedule(schedule);
			scheduleRepository.save(schedule);
			tutorRepository.save(tutor);
		}
		return tutor;
	}
	
	//Read 
	@Transactional 
	public Tutor getTutor(String tutorId) {
		Tutor tutor = tutorRepository.findByTutorId(tutorId);
		return tutor;
	}

	@Transactional 
	public List<Tutor> getAllTutors(){
		return toList(tutorRepository.findAll());
	}
	
	//Update 
	@Transactional 
	public Tutor updateTutorId(String pastId, String newId) {
		if (pastId==null) {
			throw new IllegalArgumentException("Tutor Id cannot be empty!");
		}
		if (pastId.equals("")) {
			throw new IllegalArgumentException("Tutor Id cannot be empty!");
		}
		if (pastId.equals(" ")) {
			throw new IllegalArgumentException("Tutor Id cannot be empty!");
		}
		if (newId==null) {
			throw new IllegalArgumentException("Tutor Id cannot be empty!");
		}
		if (newId.equals("")) {
			throw new IllegalArgumentException("Tutor Id cannot be empty!");
		}
		if (newId.equals(" ")) {
			throw new IllegalArgumentException("Tutor Id cannot be empty!");
		}
			Tutor tutorToBeChanged = tutorRepository.findByTutorId(pastId);
			tutorToBeChanged.setTutorId(newId);
			tutorRepository.save(tutorToBeChanged);
			return tutorToBeChanged;
		}
		
		@Transactional 
	public Tutor updateTutorName(String id, String newName) {
			if (newName==null) {
				throw new IllegalArgumentException("Tutor name cannot be empty!");
			}
			if (newName.equals("")) {
				throw new IllegalArgumentException("Tutor name cannot be empty!");
			}
			if (newName.equals(" ")) {
				throw new IllegalArgumentException("Tutor name cannot be empty!");
			}
			Tutor tutorToBeChanged = tutorRepository.findByTutorId(id);
			tutorToBeChanged.setName(newName);
			tutorRepository.save(tutorToBeChanged);
			return tutorToBeChanged;
	}
		
	@Transactional 
	public Tutor updateTutorRating(String id, String name, int newRating) {
			Tutor tutorToBeChanged = tutorRepository.findByTutorIdAndName(id, name);
			tutorToBeChanged.setRating(newRating);
			tutorRepository.save(tutorToBeChanged);
			return tutorToBeChanged;
		}
		


	// For addTutorCourse, in test class we are checking for courseId and not for newCourse???
	@Transactional
	public Tutor addTutorCourse(String id, Course newCourse) {		
			Tutor tutorToBeChanged = tutorRepository.findByTutorId(id);
			tutorToBeChanged.addTutorCourse_Helper(newCourse);
			this.addCourseTutor(newCourse.getCourseId(), tutorToBeChanged);
			tutorRepository.save(tutorToBeChanged);
			return tutorToBeChanged;
	}
	
	@Transactional
	public Tutor removeTutorCourse(String id, Course course) {
			Tutor tutorToBeChanged = tutorRepository.findByTutorId(id);
			Set<Course> courses = tutorToBeChanged.getCourse();
			Iterator<Course> itr = courses.iterator();
			while(itr.hasNext()) {
				if(itr.next().equals(course)) { //TODO: might need to remove course's tutor as well
					itr.remove();
				}
			}
			
			tutorRepository.save(tutorToBeChanged);
			return tutorToBeChanged;
	}
	@Transactional 
	public Tutor updateTutorSchedule(String id, Schedule newSchedule) {
			Tutor tutorToBeChanged = tutorRepository.findByTutorId(id);
			tutorToBeChanged.setSchedule(newSchedule);
			newSchedule.setTutor(tutorToBeChanged);
	//		scheduleRepository.save(newSchedule);
			tutorRepository.save(tutorToBeChanged);
			return tutorToBeChanged;
	}
	
	@Transactional 
	public Tutor updateTutorPassword(String Id, String newPassword) {
		if (Id==null) {
			throw new IllegalArgumentException("Username cannot be empty!");
			
		}
		if (newPassword==null) {
			throw new IllegalArgumentException("Password cannot be empty!");
			
		}
		if (Id.equals("")) {
			throw new IllegalArgumentException("Username cannot be empty!");
			
		}
		if (newPassword.equals("")) {
			throw new IllegalArgumentException("Password cannot be empty!");
			
		}
		if (Id.equals(" ")) {
			throw new IllegalArgumentException("Username cannot be empty!");
			
		}
		if (newPassword.equals(" ")) {
			throw new IllegalArgumentException("Password cannot be empty!");
			
		}
		Pattern upperCasePattern = Pattern.compile("[A-Z]");
		Pattern specialCasePattern = Pattern.compile("[^a-z0-9]", Pattern.CASE_INSENSITIVE);
		if (!upperCasePattern.matcher(newPassword).find()) {
			throw new IllegalArgumentException (" Password must contain at least 1 uppercase character!");
		}
		if (!specialCasePattern.matcher(newPassword).find()) {
			throw new IllegalArgumentException (" Password must contain at least 1 special character!");
		}
		Tutor tutor = tutorRepository.findByTutorId(Id);
		tutor.setPassword(newPassword);
		tutorRepository.save(tutor);
		return tutor;
	}
		
	//Delete 

	@Transactional
	public void deleteTutor(String tutorToBeDeletedId) {
			Tutor tutorToBeDeleted =  tutorRepository.findByTutorId(tutorToBeDeletedId);
			tutorRepository.delete(tutorToBeDeleted);
		}

	@Transactional
	public Review createReview(String reviewId, String bookingId, int rating, String comment) {
		if  (reviewId == null) {
			throw new IllegalArgumentException("Review Id cannot be empty!");
		}
		if  (reviewId.equals("")) {
			throw new IllegalArgumentException("Review Id cannot be empty!");
		}
		if  (reviewId.equals(" ")) {
			throw new IllegalArgumentException("Review Id cannot be empty!");
		}
		if  (bookingId == "") {
			throw new IllegalArgumentException("Booking Id cannot be empty!");
		}
		if  (bookingId == "") {
			throw new IllegalArgumentException("Booking Id cannot be empty!");
		}
		if (rating < 0 || rating > 5) {
			throw new IllegalArgumentException("Review rating must be an integer between 0 and 5");
		}
		
		
		Review review = reviewRepository.findReviewByReviewId(reviewId);
		Booking booking = bookingRepository.findBookingByBookingId(bookingId);
		
		if (review == null) {
			review = new Review();
			review.setReviewId(reviewId);
			review.setRating(rating);
			review.setComment(comment);
		//	bookingRepository.save(booking);

			booking.setReview(review);
			System.out.println("\n" + booking.getReview());
			System.out.println("\n\n\n\n\n\n");
			bookingRepository.save(booking);
			Booking booking2 = bookingRepository.findBookingByBookingId(bookingId);
			System.out.println(booking2.getReview());
			//reviewRepository.save(review);
			
		}
		return review;
	}
	
	
	@Transactional
	public Review getReview(String reviewId) {
		Review review = reviewRepository.findReviewByReviewId(reviewId);
		return review;
	}
	
	/*
	 * No deleting reviews since review must exist. Instead, just modify the contents of review.
	 */
//	@Transactional
//	public void deleteReview(String reviewId) {
//		Review reviewToBeDeleted = reviewRepository.findReviewByReviewId(reviewId);
//		reviewRepository.delete(reviewToBeDeleted);	
//	}
	
		
		
		//This method is used to allow to toList that will iterate through entire list of that type and stores it in an arrayList, 
		private<T> List<T> toList(Iterable<T> iterable ){
			List<T> resultList = new ArrayList<T>();
			for (T t : iterable)	{
				resultList.add(t);
			}
			return resultList;
			}
	
}
	
	

	
	

