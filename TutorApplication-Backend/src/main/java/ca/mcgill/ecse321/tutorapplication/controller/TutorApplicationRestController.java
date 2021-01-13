package ca.mcgill.ecse321.tutorapplication.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.tutorapplication.dto.BookingDto;
import ca.mcgill.ecse321.tutorapplication.dto.CourseDto;
import ca.mcgill.ecse321.tutorapplication.dto.ReturnDto;
import ca.mcgill.ecse321.tutorapplication.dto.ReviewDto;
import ca.mcgill.ecse321.tutorapplication.dto.RoomDto;
import ca.mcgill.ecse321.tutorapplication.dto.RoomSizeDto;
import ca.mcgill.ecse321.tutorapplication.dto.ScheduleDto;
import ca.mcgill.ecse321.tutorapplication.dto.StudentDto;
import ca.mcgill.ecse321.tutorapplication.dto.TutorDto;
import ca.mcgill.ecse321.tutorapplication.dto.UserDto;
import ca.mcgill.ecse321.tutorapplication.model.Booking;
import ca.mcgill.ecse321.tutorapplication.model.Course;
import ca.mcgill.ecse321.tutorapplication.model.Review;
import ca.mcgill.ecse321.tutorapplication.model.Room;
import ca.mcgill.ecse321.tutorapplication.model.RoomSize;
import ca.mcgill.ecse321.tutorapplication.model.Schedule;
import ca.mcgill.ecse321.tutorapplication.model.Student;
import ca.mcgill.ecse321.tutorapplication.model.Tutor;
//import ca.mcgill.ecse321.tutorapplication.model.User;
import ca.mcgill.ecse321.tutorapplication.service.TutorApplicationService;

@CrossOrigin(origins = "*")


@RestController
public class TutorApplicationRestController {
	
	@Autowired
	TutorApplicationService service;
	
	/*
	 *  COURSE METHODS
	 */
	
	@DeleteMapping(value = {"/clear", "/clear/"})
	public void clearAll() throws IllegalArgumentException {
		service.clearAll();
	}
	
	@PostMapping(value= {"/initialize/alpha", "/initialize/alpha/"})
	public boolean Initialize() throws IllegalArgumentException {
		Course course1 = service.createCourse("ECSE321");
		Tutor tutor11 = service.createTutor("Linus Torvalds", "Linus_Torvalds", "Password_123", 4);
		Tutor tutor12 = service.createTutor("Martin Fowler", "Martin_Fowler", "Pass_123", 5);
		service.linkTutorAndCourse(course1, tutor11);
		service.linkTutorAndCourse(course1, tutor12);

		
		Course course2 = service.createCourse("ECSE223");
		Tutor tutor21 = service.createTutor("Marcus Hutchins", "MalwareTech", "Password_1233", 5);
		Tutor tutor22 = service.createTutor("Jeffrey Epstein", "Jeffrey_Epstein", "Pass_12333", 3);
		service.linkTutorAndCourse(course2, tutor21);
		service.linkTutorAndCourse(course2, tutor22);

		
		Course course3 = service.createCourse("ECSE324");
		Tutor tutor31 = service.createTutor("Edward Snowden", "NSA_Exposed", "Passwords_123", 4);
		Tutor tutor32 = service.createTutor("Mike Downey", "Mike_Downey", "Password_1234", 5);
		service.linkTutorAndCourse(course3, tutor31);
		service.linkTutorAndCourse(course3, tutor32);
		
		System.out.println(service.getTutor("NSA_Exposed").getCourse());
		System.out.println(service.getCourse("ECSE223").getTutor());

		
		
		
		Student student1 = service.createStudent("Joe Slant", "Joe_Slant", "Password_135");
		
		
		// student 1 with tutor11 (Linus Torvalds) for ECSE 321
		Booking booking1 = service.createBooking("Booking1", student1, course1, tutor11.getSchedule(), 
				LocalDate.parse("2019-12-19"), LocalTime.parse("10:00"), LocalTime.parse("11:00"));
		
		// student 1 with tutor22 (Jeffrey Epstein) 
		Booking booking2 = service.createBooking("Booking2", student1, course2, tutor22.getSchedule(),
				LocalDate.parse("2019-11-29"), LocalTime.parse("14:00"), LocalTime.parse("15:00"));
		
		// student 1 with tutor31 (Edward Snowden) for ECSE 324
		//Booking booking3 = service.createBooking("Booking 3", student1, course3, tutor31.getSchedule(), 
		//		LocalDate.parse("2019-11-24"), LocalTime.parse("9:00"), LocalTime.parse("10:00"));
		
		//Review review = service.createReview("Review2", "Booking2", 1, "Didn't show up");
		return (service.getAllBookings().size()==2);
			
		}
	
	// Method to create a course
	@PostMapping(value = {"/courses/{courseId}", "/courses/{courseId}/"})
	public CourseDto createCourse(@PathVariable("courseId") String courseId)
	throws IllegalArgumentException {
		Course course = service.createCourse(courseId);
		return convertToDto(course);
	}
	
	// Method gets a course Dto by using its course Id
	@GetMapping (value = {"/courses/{courseId}", "/courses/{courseId}/"})
	public CourseDto getCourseByCourseId(@PathVariable ("courseId") String courseId) 
	throws IllegalArgumentException {
		return convertToDto(service.getCourse(courseId));
		
	}
	
	// Method to return all the courses available as list of courseDtos
	@GetMapping (value = {"/courses", "/courses/"})
	public List <CourseDto> getAllCourses(){
		List <CourseDto> courseDtos = new ArrayList<>();
		for (Course course: service.getAllCourses()) {
			courseDtos.add(convertToDto(course));
		}
		return courseDtos;
	}
	
	// Deletes a course by using its course Id
	@DeleteMapping(value = {"/courses/{course}", "/courses/{course}/"})
	public void deleteCourse(@PathVariable("course") String courseId) {
		service.deleteCourse(courseId);
	}
	
	/*
	 *  TUTOR METHODS
	 */
	
	// Gets a list of tutors that teach a specific course
	@GetMapping(value = {"/courses/{courseId}/tutors", "/courses/{courseId}/tutors/"})
	public List<TutorDto> getAllTutorsOfCourse(@PathVariable ("courseId") String courseId){
		
		Course course = service.getCourse(courseId);
		List<TutorDto> tutorDtos = createTutorDtosForCourse(course);
		return tutorDtos;
	}
	
	// Method to create a tutor
	@PostMapping(value = {"/tutors/{tutor}", "/tutors/{tutor}"})
	public TutorDto createTutor (@PathVariable ("tutor") String tutorId, 
			@RequestParam String name, @RequestParam String password, @RequestParam int rating) throws IllegalArgumentException{
		Tutor tutor = service.createTutor(name, tutorId, password, rating);
		return convertToDto(tutor);
		
	}
	
	// Method to get all tutors and convert to Dtos
	@GetMapping(value = {"/tutors", "tutors/"})
	public List<TutorDto> getAllTutors(){
		List <TutorDto> tutorDtos = new ArrayList<>();
		for (Tutor tutor : service.getAllTutors()) {
			tutorDtos.add(convertToDto(tutor));
		}
		return tutorDtos;
	}
	/*
	// Method to get all course of a tutor
	@GetMapping(value = {"/tutors/{courses}", "/tutors/{courses}/"})
	public List<CourseDto> getCoursesOfTutor(@PathVariable ("courses") TutorDto tDto){
		Tutor tutor = convertToDomainObject(tDto);
		return createCourseDtosForTutor(tutor);
			
		}*/
	
	// Method to get a tutor by their tutorId
	@GetMapping (value = {"/tutors/{tutor}", "/tutors/{tutor}/"})
	public TutorDto getTutorByName(@PathVariable ("tutor") String tutorId) {
		Tutor tutor = service.getTutor(tutorId);
		TutorDto tutorDto = convertToDto(tutor);
		return tutorDto;
	}
	
	// Method to delete a tutor by their tutorId
	@DeleteMapping(value = {"/tutors/{tutor}", "/tutors/{tutor}/"})
	public void deleteTutor(@PathVariable("tutor") String tutorId) {
		service.deleteTutor(tutorId);
	}
	
	
	/*
	 *  USER METHODS
	 */
/*
	// Method to create a user
	@PostMapping(value = { "/users/{user}", "/users/{user}/" })
	public UserDto createPerson(@PathVariable("user") String user, @RequestParam String password) throws IllegalArgumentException {
		User person = service.createUser(user, password);
		return convertToDto(person);
	}
	
	/* THIS IS A TEST METHOD TO TRY WITHOUT PASSWORD CUZ IDK ABOUT REQUESTPARAM
	// Method to create a user
		@PostMapping(value = { "/users/{user}", "/users/{user}/" })
		public UserDto createPerson(@PathVariable("user") String user) throws IllegalArgumentException {
			String password = "Password_123";
			User person = service.createUser(user, password);
			return convertToDto(person);
		}*/
		
	
	/*
	// Method to get a user by username
	@GetMapping(value = { "/users/{user}", "/users/{user}/"})
	public UserDto getUser(@PathVariable("user") String username) throws IllegalArgumentException{
		User person = service.getUser(username);
		return convertToDto(person);
	}
	

	
	// Method to get a list of all users
	 	@GetMapping (value = {"/users", "/users/"})
	 	public List <UserDto> getAllUsers(){
	 		List <UserDto> userDtos = new ArrayList<>();
	 		for (User user : service.getAllUsers()) {
	 			userDtos.add(convertToDto(user));
	 		}
	 		return userDtos;
	 	}
	 	*/
	 	
	 	
	 //TODO PUT THE NEXT TWO METHODS INTO EFFECT
	 // Method for login
	 // NOTE: this is put in later and not sure about implementation (path is debatable)
	 /*	@GetMapping(value = { "/users/{user}", "/users/{user}/"})
	 
		public UserDto Login(@PathVariable("user") String user, @RequestParam String password) throws IllegalArgumentException{
			User person = service.getUser(user);
			if (password == person.getPassword()) {
				return convertToDto(person);
			}
			return null;
			
		}*/
	 
	 	
	 // Method to get the student object of a user
	 // Essentially this is the method that will be used to go from user to student
	 	/*@GetMapping(value = {"/users/{user}/student", "/users/{user}/student/"})
	 	public StudentDto getStudentOfUser(@PathVariable("user") String user) {
	 		User person = service.getUser(user);
	 		Student student = person.getStudent();
	 		return convertToDto(student);
	 		
	 	}
	 	*/
	/* 	
	// Method to delete a user by username
	@DeleteMapping(value= {"/users/{user}", "/users/{user}/"})
	public void deleteUser(@PathVariable("user") String user, @RequestParam String password) throws IllegalArgumentException{
		service.deleteUser(user, password);
	}
*/
	/*
	 *  SCHEDULE METHODS
	 */
	
	// Method to create a schedule given a scheduleId and TutorDto
	@PostMapping(value = {"/schedules/{schedule}", "/schedules/{schedule}/"})
	public ScheduleDto createSchedule(@PathVariable("schedule") String scheduleId, @RequestParam TutorDto tutorDto) {
		Tutor tutor = service.getTutor(tutorDto.getTutorId());
		Schedule schedule = service.createSchedule(scheduleId, tutor);
		
		System.out.println("\n \n \n \n" + "YEET" + "\n \n \n \n");
		return convertToDto(schedule);
	}
	
	// Method to get a schedule by scheduleId
	@GetMapping(value = {"/schedules/{schedule}", "/schedules/{schedule}/"})
	public ScheduleDto getSchedule(@PathVariable("schedule") String ScheduleId) {
		Schedule schedule = service.getSchedule(ScheduleId);
		return convertToDto(schedule);
	}
	
	// Method to delete a schedule by scheduleId
	@DeleteMapping(value = {"/schedules/{schedule}", "/schedules/{schedule}/"})
	public void deleteSchedule(@PathVariable("schedule") String ScheduleId) {
		service.deleteSchedule(ScheduleId);
	}
	
	/*
	 *  REVIEW METHODS
	 */
	
	// Method to create a review given a reviewId and BookingDto
	@PostMapping(value = {"/reviews/{review}", "/reviews/{review}/"})
	public ReviewDto createReview(@PathVariable("review") String reviewId, @RequestParam String bookingId, @RequestParam int rating, @RequestParam String comment){

		Review review = service.createReview(reviewId, bookingId, rating, comment);
		
		return convertToDto(review);
		
	}
	
	// Method to get a review given a reviewId
	@GetMapping(value = {"/reviews/{review}", "/reviews/{review}/"})
	public ReviewDto getReview(@PathVariable("review") String reviewId) {
		Review review = service.getReview(reviewId);
		return convertToDto(review);
	}
	
//	// Method to delete a review given a reviewId
//	
//	@DeleteMapping(value = {"/reviews/{review}", "/reviews/{review}/"})
//	public void deleteReview(@PathVariable("review") String reviewId) {
//		service.deleteReview(reviewId);
//	}
	
	/*
	 *  BOOKING METHODS
	 */
	
	// Method to create a booking for a student
	@PostMapping(value = {"/{studentId}/booking", "/{studentId}/booking/"})
	public BookingDto createBooking(@PathVariable("studentId") String studentId,@RequestParam String bookingId, @RequestParam String tutorId,
			@RequestParam String courseId, @RequestParam String date, @RequestParam String startTime, @RequestParam String endTime) 
	throws IllegalArgumentException{
		Student student = service.getStudent(studentId);
		Tutor tutor = service.getTutor(tutorId);
		Schedule schedule = tutor.getSchedule();
		Course course = service.getCourse(courseId);
		LocalDate bookingDate = LocalDate.parse(date);
		LocalTime bookingStartTime = LocalTime.parse(startTime);
		LocalTime bookingEndTime = LocalTime.parse(endTime);
		Booking booking = service.createBooking(bookingId, student, course, schedule, bookingDate, bookingStartTime, bookingEndTime);
		
		return convertToDto(booking);
		
		
		
	}
	
	
	// Method to return a set of bookingDtos
		@GetMapping(value = {"/bookings", "/bookings/"})
		public List<BookingDto> getAllBookings(){
			List<BookingDto> bookingDtos = new ArrayList<>();
				for (Booking booking: service.getAllBookings()) {
					bookingDtos.add(convertToDto(booking));
				}
			return bookingDtos;
		}
		
		// Method to return a booking by bookingId
		@GetMapping(value = {"/bookings/{booking}","/bookings/{booking}/"}) 
			public BookingDto getBookingById(@PathVariable("booking") String bookingId ) throws IllegalArgumentException{
			return convertToDto(service.getBooking(bookingId));
		}
		
		// Method to return the booking of a specific student
		@GetMapping(value = {"/bookings/{student}/","/bookings/{student}/"} )
		public List<BookingDto> getBookingsOfStudent(@PathVariable("student") StudentDto stuDto ) {
			Student s = convertToDomainObject(stuDto);
			return createBookingDtosForStudent(s);
		}
		
		//Second method to get bookings of a student but using a student id
		@GetMapping(value = {"/{student}/bookings","/{student}/bookings/"})
		public List <BookingDto> getAllBookingsOfStudent(@PathVariable("student") String studentId){
			Student s = service.getStudent(studentId);
			return createBookingDtosForStudent(s);
		}
		
		@GetMapping (value = {"/test" , "/test/"})
		public String printTest() {
			return "HelloW";
		}
		
		// Method to convert a studentDto to Student domain object
		private Student convertToDomainObject(StudentDto stuDto){
			List<Student> allStudents = service.getAllStudents();
			for (Student student: allStudents) { 
				if(student.getStudentId().equals(stuDto.getStudentId())) {
					return student;
				}
			}
			return null;
		}
		
		// Method to return a list of bookingDtos for a specific student
		private List<BookingDto> createBookingDtosForStudent(Student s) {
			Set<Booking> bookingsForStudent = s.getBooking();
			List<BookingDto> bookings = new ArrayList<>();
			for (Booking booking: bookingsForStudent ) {
				bookings.add(convertToDto(booking));
			}
			return bookings;
		}
		
		// Method to create Booking given a bookingId, studentDto, courseDto, bookingDate, startTime, endTime
		@PostMapping(value = {"/bookings/{booking}", "/bookings/{booking}/"})
		public BookingDto createBooking(@PathVariable("booking") String bookingId, @RequestParam String studentId, @RequestParam CourseDto course, @RequestParam ScheduleDto schedule, @RequestParam LocalDate bookingDate, 
										@RequestParam LocalTime startTime, @RequestParam LocalTime endTime ) throws IllegalArgumentException {
			Course real_course = service.getCourse(course.getcourseId());
			Student real_student = service.getStudent(studentId);
			Schedule real_schedule = service.getSchedule(schedule.getScheduleID());
			Booking booking = service.createBooking(bookingId, real_student, real_course, real_schedule, bookingDate, startTime, endTime);
			return convertToDto(booking);
		}
		
		// Method to delete a booking given a bookingId
		@DeleteMapping(value = {"/bookings/{booking}", "/reviews/{booking}/"})
		public void deletebooking(@PathVariable("booking") String bookingId) {
			service.deleteBooking(bookingId);
		}
		
		/*
		 *  ROOM METHODS
		 */
		
		// Method to create a room given a roomId and roomSize
		@PostMapping (value = {"/rooms/{roomId}", "/rooms/{roomId}/"})
		public RoomDto createRoom(@PathVariable("roomId") String roomId, @RequestParam RoomSize roomsize) {
			Room room = service.createRoom(roomId, roomsize);
			return convertToDto(room);
		}
		
		// Method to return a list of roomDtos
		@GetMapping ( value = {"/rooms", "/rooms/"}) 
		public List<RoomDto> getAllRooms(){
			List<RoomDto> roomDtos = new ArrayList<>();
				for( Room room: service.getAllRooms()) {
					roomDtos.add(convertToDto(room));
				}
				return roomDtos;
		}
		
		// Method to get a room given a roomId
		@GetMapping (value  = {"/rooms/{room}", "/rooms/{room}/" })
		public RoomDto getRoomById(@PathVariable("room") String roomId) throws IllegalArgumentException {
			return convertToDto(service.getRoom(roomId));
		}
		
		// Method to delete a room given a roomId
		@DeleteMapping(value = {"/rooms/{roomId}", "/rooms/{roomId}/"})
		public void deleteRoom(@PathVariable("roomId") String roomId) throws IllegalArgumentException {
			service.deleteRoom(roomId);
		}
		
		
//		@GetMapping(value = {"/bookings/{booking}","/bookings/{booking}/"}) 
//		public BookingDto getBookingById(@PathVariable("booking") String bookingId ) throws IllegalArgumentException{
//		return convertToDto(service.getBooking(bookingId));
//	
//		
		
		
		
		
		
	
	
	
		/*
		 *  STUDENT METHODS
		 */
		
	// Method to create a student given a studentName and studentId
	@PostMapping(value = {"/students/{student}", "students/{student}/"})
	public StudentDto createStudent(@PathVariable("student") String studentId, @RequestParam String name,
			@RequestParam String password) throws IllegalArgumentException {
		Student student = service.createStudent(name, studentId, password);
		return convertToDto(student);
	}
	
	@GetMapping (value = {"/students", "/students/"})
	public List <StudentDto> getStudents (){
		List <StudentDto> students = new ArrayList<>();
		for (Student student : service.getAllStudents()) {
			students.add(convertToDto(student));
		}
		return students;
	}
	
	// Checks if a student Id already exists
	@GetMapping (value = {"/students/specificstudent/{studentId}", "/students/specificstudent/{studentId}/"})
	public boolean nameExists(@PathVariable("studentId") String studentId) {
		List <Student> existingStudents = service.getAllStudents();
		for (Student s : existingStudents) {
			if (s.getStudentId().equals(studentId)){
				return true;
			}
		}
		return false;
		
	}
	
	// Checks if the password is correct
	@GetMapping (value = {"/students/passwordcheck/{studentId}", "/students/passwordcheck/{studentId}/"})
	public ReturnDto checkPassword(@PathVariable ("studentId") String studentId, @RequestParam String password) {
		ReturnDto value = new ReturnDto();
		for (Student s : service.getAllStudents()) {
			
			if (s.getStudentId().equals(studentId) && (s.getPassword().equals(password))) {
				value.setValue(0);
				return value;
			}
			else if (s.getStudentId().equals(studentId)) {
				value.setValue(1);
				return value;
			}
		}
		value.setValue(2);
		return value;
	}
	
		
	
	// Method to get a student by studentId
	@GetMapping(value = {"/students/{student}", "/students/{student}/"})
	public StudentDto getStudent(@PathVariable("student") String studentId) throws IllegalArgumentException {
		Student student = service.getStudent(studentId);
		return convertToDto(student);
	}
	
	// Method to update a student password
	@PutMapping(value= {"/students/{student}", "/students/{student}/"})
	public StudentDto updateStudentPassword(@PathVariable("student") String studentId, @RequestParam String oldPassword, @RequestParam String newPassword) throws IllegalArgumentException{
		Student student = service.updateStudentPassword(studentId, oldPassword, newPassword);
		return convertToDto(student);
	}
	
	// Method to delete a student by studentId
	@DeleteMapping(value = {"/students/{student}", "/students/{student}/"})
	public void deleteStudent(@PathVariable("student") String studentId) throws IllegalArgumentException {
		service.deleteStudent(studentId);
	}
	
	// Method to return a list of a specific student's bookings, given a studentId
	@GetMapping(value = {"/students/{student}/bookings", "/students/{student}/bookings/"})
	public List <BookingDto> getBookingsOfStudent(@PathVariable ("student") String studentId) 
	throws IllegalArgumentException{
		List<BookingDto> bookings = new ArrayList<>();
		for (Booking booking: service.getStudent(studentId).getBooking()) {
			bookings.add(convertToDto(booking));
		}
		return bookings;
		
		
	}
	
	// CONVERSION METHODS
	
	
	
	// Convert a Booking to a BookingDto
	private BookingDto convertToDto(Booking b) {
		if (b == null) {
			throw new IllegalArgumentException("There is no such booking!");
		}
		StudentDto sDto = convertToDto(b.getStudent());
		CourseDto cDto = convertToDto(b.getCourse());
		ScheduleDto scDto = convertToDto(b.getSchedule());
		String tutorName = b.getSchedule().getTutor().getName();
		Review review = b.getReview();
		BookingDto bookingDto;
		if (review != null) {
			bookingDto = new BookingDto(b.getBookingId(), sDto,
					cDto, scDto, b.getBookingDate(), b.getStartTime(), b.getEndTime(), tutorName, convertToDto(review));
		//	System.out.println("\n\n Review IS NOT null \n \n");
		} else {
			bookingDto = new BookingDto(b.getBookingId(), sDto,
				cDto, scDto, b.getBookingDate(), b.getStartTime(), b.getEndTime(), tutorName);
		//	System.out.println("\n\n Review IS null \n \n");
		}
		return bookingDto;
	}
	
	// Convert a Course to a CourseDto
	private CourseDto convertToDto(Course c) {
		if (c == null) {
			throw new IllegalArgumentException("There is no such Course!");
		}
		Set <TutorDto> tutors = new HashSet<>();
		for (Tutor t : c.getTutor()) {
			tutors.add(convertToDto(t));
		}
		
		
		
		CourseDto courseDto = new CourseDto(c.getCourseId(), tutors);
		return courseDto;
	}
	
	// Convert a Review to a ReviewDto
	private ReviewDto convertToDto(Review r) {
		if (r == null) {
			throw new IllegalArgumentException("There is no such review!");
		}
		
		ReviewDto reviewDto = new ReviewDto(r.getRating(), r.getReviewId(), r.getComment());
		return reviewDto;
	}
	
	// Convert a Room to a RoomDto
	private RoomDto convertToDto(Room r) {
		if (r == null) {
			throw new IllegalArgumentException("There is no such room!");
		}
		
		RoomSizeDto rDto = convertToDto(r.getRoomSize());
		
		
		RoomDto roomDto = new RoomDto(rDto, r.getRoomId());
		return roomDto;
	}
	
	// Convert a RoomSize to a RoomSizeDto
	private RoomSizeDto convertToDto(RoomSize r) {
		if (r == null) {
			throw new IllegalArgumentException("There is no such room size!");
		}
		if (r.equals(RoomSize.SMALL)) {
			RoomSizeDto roomSizeDto = RoomSizeDto.SMALL;
			return roomSizeDto;
		}
		if (r.equals(RoomSize.LARGE)) {
			RoomSizeDto roomSizeDto = RoomSizeDto.LARGE;
			return roomSizeDto;
		}
		return null;
	}
	
	//private ScheduleDto
	
	// Convert a Schedule to a ScheduleDto
	private ScheduleDto convertToDto(Schedule s) {
		if (s == null) {
			throw new IllegalArgumentException("There is no such schedule!");
		}
		

		
		ScheduleDto scheduleDto = new ScheduleDto(s.isOnLeave(), s.getScheduleId());
		return scheduleDto;
	}
	
	//private StudentDto
	
	// Convert a student to a StudentDto
	private StudentDto convertToDto(Student s) {
		if (s == null) {
			throw new IllegalArgumentException("There is no such student!");
		}
		
		StudentDto studentDto = new StudentDto(s.getName(), s.getStudentId(), s.getPassword());
		return studentDto;
	}
	
	//private TutorDto
	// Convert a Tutor to a TutorDto
	private TutorDto convertToDto(Tutor t) {
		if (t == null) {
			throw new IllegalArgumentException("There is no such tutor!");
		}
		

		ScheduleDto scheduledto = convertToDto(t.getSchedule());
		TutorDto tutorDto = new TutorDto(t.getName(), t.getTutorId(), t.getPassword(), t.getRating(), scheduledto);
		scheduledto.setTutorName(tutorDto.getName());

		return tutorDto;
	}
	
	/*//private UserDto
	// Convert a User to a UserDto
	private UserDto convertToDto(User u) {
		if (u == null) {
			throw new IllegalArgumentException("There is no such user!");
		}
		UserDto userDto = new UserDto(u.getUsername(), u.getPassword());
		return userDto;
	}*/
	
	// Convert a TutorDto to a Tutor Domain Object
	private Tutor convertToDomainObject(TutorDto t) {
		List <Tutor> allTutors = service.getAllTutors();
		for (Tutor tutor : allTutors) {
			if (tutor.getTutorId().equals(t.getTutorId())) {
				return tutor;
			}
		}
		return null;
	}
	
		// HELPER METHODS
	
	// Return a list of TutorDtos for a specific Course
	private List<TutorDto> createTutorDtosForCourse(Course c){
		List<TutorDto> tutorDtos = new ArrayList<>();
		for (Tutor tutor : c.getTutor()) {
			tutorDtos.add(convertToDto(tutor));
		}
		return tutorDtos;
				
	}
	// return a list of CourseDtos for a specific Tutor
	private List<CourseDto> createCourseDtosForTutor(Tutor t){
		List <CourseDto> courseDtos = new ArrayList<>();
		for (Course course : t.getCourse()) {
			courseDtos.add(convertToDto(course));
		}
		
		return courseDtos;
		
	}
	
}

