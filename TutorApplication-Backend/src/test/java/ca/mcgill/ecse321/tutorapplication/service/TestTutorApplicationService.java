package ca.mcgill.ecse321.tutorapplication.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutorapplication.dao.BookingRepository;
import ca.mcgill.ecse321.tutorapplication.dao.StudentRepository;
import ca.mcgill.ecse321.tutorapplication.dao.TutorRepository;
import ca.mcgill.ecse321.tutorapplication.dao.CourseRepository;
import ca.mcgill.ecse321.tutorapplication.dao.ReviewRepository;
import ca.mcgill.ecse321.tutorapplication.dao.RoomRepository;
//import ca.mcgill.ecse321.tutorapplication.dao.RoomSizeRepository;
import ca.mcgill.ecse321.tutorapplication.dao.ScheduleRepository;
//import ca.mcgill.ecse321.tutorapplication.dao.UserRepository;
//import ca.mcgill.ecse321.tutorapplication.dao.UserRoleRepository;

import ca.mcgill.ecse321.tutorapplication.model.Booking;
import ca.mcgill.ecse321.tutorapplication.model.Course;
import ca.mcgill.ecse321.tutorapplication.model.Review;
import ca.mcgill.ecse321.tutorapplication.model.Room;
import ca.mcgill.ecse321.tutorapplication.model.RoomSize;
import ca.mcgill.ecse321.tutorapplication.model.Schedule;
import ca.mcgill.ecse321.tutorapplication.model.Student;
import ca.mcgill.ecse321.tutorapplication.model.Tutor;
//import ca.mcgill.ecse321.tutorapplication.model.User;
//import ca.mcgill.ecse321.tutorapplication.model.UserRole;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTutorApplicationService {
	
	/*
	 * We create a new member variable, making it as autowired. Now when spring will create an instance of TutorApplicationService. 
	 * It will inject whichever repository we are referring to
	 * 
	 */
	
	@Autowired
	private TutorApplicationService tas;
	
	@Autowired
	private BookingRepository bookingRepository; //This is a member variable used to make calls on the repository using the crudrepo services
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private TutorRepository tutorRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
//	@Autowired
//	private RoomSizeRepository roomSizeRepository;
	
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	//@Autowired
	//private UserRepository userRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	//@Autowired
	//private UserRoleRepository userRoleRepository;
	
	@Before
	public void clearDatabase() {
		bookingRepository.deleteAll();

		courseRepository.deleteAll();
		tutorRepository.deleteAll();
		roomRepository.deleteAll();
		//roomSizeRepository.deleteAll();
		scheduleRepository.deleteAll();
		studentRepository.deleteAll();
		//userRepository.deleteAll();
		reviewRepository.deleteAll();
		//userRoleRepository.deleteAll();
		
	}
	
	@After
	public void clearDatabases() {
		bookingRepository.deleteAll();

		courseRepository.deleteAll();
		tutorRepository.deleteAll();
		roomRepository.deleteAll();
		//roomSizeRepository.deleteAll();
		scheduleRepository.deleteAll();
		studentRepository.deleteAll();
		//userRepository.deleteAll();
		reviewRepository.deleteAll();
		//userRoleRepository.deleteAll();

	}
	
	//Booking Tests
	
	@Test
	public void testCreateBooking() {
		assertEquals(0, tas.getAllBookings().size());
		String bookingId = "Booking1";
		Student student = tas.createStudent("Jacob", "123", "Booking_1");
		assertEquals(1, tas.getAllStudents().size());
		Course course = tas.createCourse("ECSE321");
		assertEquals(1, tas.getAllCourses().size());
		Tutor tutor = tas.createTutor("Hawk", "12345", "Password_123", 0);
		assertEquals(1, tas.getAllTutors().size());
		Schedule schedule = tutor.getSchedule();
		assertEquals(1,tas.getAllSchedules().size());
		LocalDate BookingDate = LocalDate.parse("2019-08-03");
		LocalTime StartTime = LocalTime.parse("09:00");
		LocalTime EndTime = LocalTime.parse("10:00");
		
		try {
			tas.createBooking(bookingId, student, course, schedule, BookingDate, StartTime, EndTime);
		} catch(IllegalArgumentException e){
			fail();
		}
		
		List <Booking> allBookings = tas.getAllBookings();
		assertEquals(1, allBookings.size());
		assertEquals(bookingId, allBookings.get(0).getBookingId());
		assertEquals(student.getStudentId(), tas.getAllBookings().get(0).getStudent().getStudentId());
		assertEquals(course.getCourseId(), tas.getAllCourses().get(0).getCourseId());
		assertEquals(StartTime, tas.getAllBookings().get(0).getStartTime());
		assertEquals(EndTime, tas.getAllBookings().get(0).getEndTime());
	}
	
	@Test
	public void testCreateBookingEndTimeBeforeStartTime() {
		assertEquals(0, tas.getAllBookings().size());
		String bookingId = "Booking1";
		Student student = tas.createStudent("Jacob", "123", "Booking_1");
		assertEquals(1, tas.getAllStudents().size());
		Course course = tas.createCourse("ECSE321");
		assertEquals(1, tas.getAllCourses().size());
		Tutor tutor = tas.createTutor("Hawk", "12345", "Password_123", 0);
		assertEquals(1, tas.getAllTutors().size());
		Schedule schedule = tutor.getSchedule();
		assertEquals(1,tas.getAllSchedules().size());
		LocalDate BookingDate = LocalDate.parse("2019-08-03");
		LocalTime StartTime = LocalTime.parse("10:00");
		LocalTime EndTime = LocalTime.parse("09:00");
		
		String error = null;
		try {
			tas.createBooking(bookingId, student, course, schedule, BookingDate, StartTime, EndTime);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("Booking end time cannot be before booking start time!", error);

	// check model in memory
	assertEquals(0, tas.getAllBookings().size());
	}
	
	@Test
	public void testCreateBookingIdNull(){
		assertEquals(0, tas.getAllBookings().size());
		String bookingId = null;
		Student student = tas.createStudent("Jacob", "123", "Booking_1");
		assertEquals(1, tas.getAllStudents().size());
		Course course = tas.createCourse("ECSE321");
		assertEquals(1, tas.getAllCourses().size());
		Tutor tutor = tas.createTutor("Hawk", "12345", "Password_123", 0);
		assertEquals(1, tas.getAllTutors().size());
		Schedule schedule = tutor.getSchedule();
		LocalDate BookingDate = LocalDate.parse("2019-08-03");
		LocalTime StartTime = LocalTime.parse("09:00");
		LocalTime EndTime = LocalTime.parse("10:00");
		
		String error = null;
	
	try {
		tas.createBooking(bookingId, student, course, schedule, BookingDate, StartTime, EndTime);
	} catch(IllegalArgumentException e) {
		error = e.getMessage();
	}
	assertEquals("Booking Id cannot be empty!", error);
	assertEquals(0, tas.getAllBookings().size());
	
	}
	@Test
	public void testCreateBookingDateNull(){
		assertEquals(0, tas.getAllBookings().size());
		String bookingId = "Booking1";
		Student student = tas.createStudent("Jacob", "123", "Booking_1");
		assertEquals(1, tas.getAllStudents().size());
		Course course = tas.createCourse("ECSE321");
		assertEquals(1, tas.getAllCourses().size());
		Tutor tutor = tas.createTutor("Hawk", "12345", "Password_123", 0);
		assertEquals(1, tas.getAllTutors().size());
		Schedule schedule = tutor.getSchedule();
		assertEquals(1,tas.getAllSchedules().size());
		LocalDate BookingDate = null;
		LocalTime StartTime = LocalTime.parse("09:00");
		LocalTime EndTime = LocalTime.parse("10:00");
		
		String error = null;
	
	try {
		tas.createBooking(bookingId, student, course, schedule, BookingDate, StartTime, EndTime);
	} catch(IllegalArgumentException e) {
		error = e.getMessage();
	}
	assertEquals("Booking Date cannot be empty!", error);
	assertEquals(0, tas.getAllBookings().size());
	
	}
	@Test
	public void testCreateBookingStartTimeNull(){
		assertEquals(0, tas.getAllBookings().size());
		String bookingId = "Booking1";
		Student student = tas.createStudent("Jacob", "123", "Booking_1");
		assertEquals(1, tas.getAllStudents().size());
		Course course = tas.createCourse("ECSE321");
		assertEquals(1, tas.getAllCourses().size());
		Tutor tutor = tas.createTutor("Hawk", "12345", "Password_123", 0);
		assertEquals(1, tas.getAllTutors().size());
		Schedule schedule = tutor.getSchedule();
		LocalDate BookingDate = LocalDate.parse("2019-08-03");
		LocalTime StartTime = null;
		LocalTime EndTime = LocalTime.parse("10:00");
		
		String error = null;
	
	try {
		tas.createBooking(bookingId, student, course, schedule, BookingDate, StartTime, EndTime);
	} catch(IllegalArgumentException e) {
		error = e.getMessage();
	}
	assertEquals("Booking start time cannot be empty!", error);
	assertEquals(0, tas.getAllBookings().size());
	
	}
	
	@Test
	public void testCreateBookingEndTimeNull(){
		assertEquals(0, tas.getAllBookings().size());
		String bookingId = "Booking1";
		Student student = tas.createStudent("Jacob", "123", "Booking_1");
		assertEquals(1, tas.getAllStudents().size());
		Course course = tas.createCourse("ECSE321");
		assertEquals(1, tas.getAllCourses().size());
		Tutor tutor = tas.createTutor("Hawk", "12345", "Password_123", 0);
		assertEquals(1, tas.getAllTutors().size());
		Schedule schedule = tutor.getSchedule();
		LocalDate BookingDate = LocalDate.parse("2019-08-03");
		LocalTime StartTime = LocalTime.parse("09:00");
		LocalTime EndTime = null;
		
		String error = null;
	
	try {
		tas.createBooking(bookingId, student, course, schedule, BookingDate, StartTime, EndTime);
	} catch(IllegalArgumentException e) {
		error = e.getMessage();
	}
	assertEquals("Booking end time cannot be empty!", error);
	assertEquals(0, tas.getAllBookings().size());
	
	}
	
	@Test
	public void testCreateBookingIdEmpty() {
		assertEquals(0, tas.getAllBookings().size());
		String bookingId = "";
		Student student = tas.createStudent("Jacob", "123", "Booking_1");
		assertEquals(1, tas.getAllStudents().size());
		Course course = tas.createCourse("ECSE321");
		assertEquals(1, tas.getAllCourses().size());
		Tutor tutor = tas.createTutor("Hawk", "12345", "Password_123", 0);
		assertEquals(1, tas.getAllTutors().size());
		Schedule schedule = tutor.getSchedule();
		LocalDate BookingDate = LocalDate.parse("2019-08-03");
		LocalTime StartTime = LocalTime.parse("09:00");
		LocalTime EndTime = LocalTime.parse("10:00");
		
		String error = null;
		
		try {
			tas.createBooking(bookingId, student, course, schedule, BookingDate, StartTime, EndTime);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("Booking Id cannot be empty!", error);
		assertEquals(0, tas.getAllBookings().size());
	}
	
	@Test
	public void testCreateBookingSpaces() {
		assertEquals(0, tas.getAllBookings().size());
		String bookingId = " ";
		Student student = tas.createStudent("Jacob", "123", "Booking_1");
		assertEquals(1, tas.getAllStudents().size());
		Course course = tas.createCourse("ECSE321");
		assertEquals(1, tas.getAllCourses().size());
		Tutor tutor = tas.createTutor("Hawk", "12345", "Password_123", 0);
		assertEquals(1, tas.getAllTutors().size());
		Schedule schedule = tutor.getSchedule();
		LocalDate BookingDate = LocalDate.parse("2019-08-03");
		LocalTime StartTime = LocalTime.parse("09:00");
		LocalTime EndTime = LocalTime.parse("10:00");
		
		String error = null;
		
		try {
			tas.createBooking(bookingId, student, course, schedule, BookingDate, StartTime, EndTime);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("Booking Id cannot be empty!", error);
		assertEquals(0, tas.getAllBookings().size());
	}
	
	
	
//	//Tests for Update 
	
	/*
	 * USELESS UPDATE TESTS. CANT UPDATE PRIMARY KEY
	 */
//	@Test
//	public void testUpdateBookingId() {
//		assertEquals(0, tas.getAllBookings().size());
//		String bookingId = "Booking1";
//		Student student = tas.createStudent("Jacob", "123", "Booking1");
//		assertEquals(1, tas.getAllStudents().size());
//		Course course = tas.createCourse("ECSE321");
//		assertEquals(1, tas.getAllCourses().size());
//		Tutor tutor = tas.createTutor("Hawk", "12345", 0);
//		assertEquals(1, tas.getAllTutors().size());
//		Schedule schedule = tutor.getSchedule();
//		LocalDate BookingDate = LocalDate.parse("2019-08-03");
//		LocalTime StartTime = LocalTime.parse("09:00");
//		LocalTime EndTime = LocalTime.parse("10:00");
//		String newBookingId = "Booking 2";
//		
//		try {
//			tas.createBooking(bookingId, student, course, schedule, BookingDate, StartTime, EndTime);
//			tas.updateBookingId(bookingId, newBookingId);
//		} catch(IllegalArgumentException e){
//			fail();
//		}
//		assertEquals(newBookingId, tas.getAllBookings().get(0).getBookingId());
//	}
//	
//	@Test
//	public void testUpdateBookingIdNull() {
//		assertEquals(0, tas.getAllBookings().size());
//		String bookingId = "Booking1";
//		Student student = tas.createStudent("Jacob", "123", "Booking1");
//		assertEquals(1, tas.getAllStudents().size());
//		Course course = tas.createCourse("ECSE321");
//		assertEquals(1, tas.getAllCourses().size());
//		Tutor tutor = tas.createTutor("Hawk", "12345", 0);
//		assertEquals(1, tas.getAllTutors().size());
//		Schedule schedule = tutor.getSchedule();
//		assertEquals(1,tas.getAllSchedules().size());
//		LocalDate BookingDate = LocalDate.parse("2019-08-03");
//		LocalTime StartTime = LocalTime.parse("09:00");
//		LocalTime EndTime = LocalTime.parse("10:00");
//		String newBookingId = null;
//		String error = null;
//		try {
//			tas.createBooking(bookingId, student, course, schedule, BookingDate, StartTime, EndTime);
//			tas.updateBookingId(bookingId, newBookingId );
//		} catch(IllegalArgumentException e){
//			error = e.getMessage();
//		}
//		
//		assertEquals("Booking Id cannot be empty!", error);
//		assertEquals(bookingId, tas.getAllBookings().get(0).getBookingId());
//	}
//	@Test
//	public void testUpdateBookingIdEmpty() {
//		assertEquals(0, tas.getAllBookings().size());
//		String bookingId = "Booking1";
//		Student student = tas.createStudent("Jacob", "123", "Booking1");
//		assertEquals(1, tas.getAllStudents().size());
//		Course course = tas.createCourse("ECSE321");
//		assertEquals(1, tas.getAllCourses().size());
//		Tutor tutor = tas.createTutor("Hawk", "12345", 0);
//		assertEquals(1, tas.getAllTutors().size());
//		Schedule schedule = tutor.getSchedule();
//		assertEquals(1,tas.getAllSchedules().size());
//		LocalDate BookingDate = LocalDate.parse("2019-08-03");
//		LocalTime StartTime = LocalTime.parse("09:00");
//		LocalTime EndTime = LocalTime.parse("10:00");
//		String newBookingId = "";
//		String error = null;
//		try {
//			tas.createBooking(bookingId, student, course, schedule, BookingDate, StartTime, EndTime);
//			tas.updateBookingId(bookingId, newBookingId);
//		} catch(IllegalArgumentException e){
//			error = e.getMessage();
//		}
//		
//		assertEquals("Booking Id cannot be empty!", error);
//		assertEquals(bookingId, tas.getAllBookings().get(0).getBookingId());
//	}
//	
//	@Test
//	public void testUpdateBookingIdSpaces() {
//		assertEquals(0, tas.getAllBookings().size());
//		String bookingId = "Booking1";
//		Student student = tas.createStudent("Jacob", "123", "Booking1");
//		assertEquals(1, tas.getAllStudents().size());
//		Course course = tas.createCourse("ECSE321");
//		assertEquals(1, tas.getAllCourses().size());
//		Tutor tutor = tas.createTutor("Hawk", "12345", 0);
//		assertEquals(1, tas.getAllTutors().size());
//		Schedule schedule = tutor.getSchedule();
//		assertEquals(1,tas.getAllSchedules().size());
//		LocalDate BookingDate = LocalDate.parse("2019-08-03");
//		LocalTime StartTime = LocalTime.parse("09:00");
//		LocalTime EndTime = LocalTime.parse("10:00");
//		String newBookingId = " ";
//		String error = " ";
//		try {
//			tas.createBooking(bookingId, student, course, schedule, BookingDate, StartTime, EndTime);
//			tas.updateBookingId(bookingId, newBookingId);
//		} catch(IllegalArgumentException e){
//			error = e.getMessage();
//		}
//		
//		assertEquals("Booking Id cannot be empty!", error);
//		assertEquals(bookingId, tas.getAllBookings().get(0).getBookingId());
//	}
	
	@Test
	public void testUpdateBookingDate(){
		assertEquals(0, tas.getAllBookings().size());
		String bookingId = "Booking1";
		Student student = tas.createStudent("Jacob", "123", "Booking_1");
		assertEquals(1, tas.getAllStudents().size());
		Course course = tas.createCourse("ECSE321");
		assertEquals(1, tas.getAllCourses().size());
		Tutor tutor = tas.createTutor("Hawk", "12345", "Password_123", 0);
		assertEquals(1, tas.getAllTutors().size());
		Schedule schedule = tutor.getSchedule();
		assertEquals(1,tas.getAllSchedules().size());
		LocalDate BookingDate = LocalDate.parse("2019-08-03");
		LocalTime StartTime = LocalTime.parse("09:00");
		LocalTime EndTime = LocalTime.parse("10:00");
		
		LocalDate newBookingDate = LocalDate.parse("2019-08-05");
		
		try {
			tas.createBooking(bookingId, student, course, schedule, BookingDate, StartTime, EndTime);
			tas.updateBookingDate(bookingId, newBookingDate);
		} catch(IllegalArgumentException e) {
			fail();
		}
		assertEquals(newBookingDate, tas.getAllBookings().get(0).getBookingDate());
	}
	
	@Test
	public void testUpdateBookingDateNull(){
		assertEquals(0, tas.getAllBookings().size());
		String bookingId = "Booking1";
		Student student = tas.createStudent("Jacob", "123", "Booking_1");
		assertEquals(1, tas.getAllStudents().size());
		Course course = tas.createCourse("ECSE321");
		assertEquals(1, tas.getAllCourses().size());
		Tutor tutor = tas.createTutor("Hawk", "12345", "Password_123", 0);
		assertEquals(1, tas.getAllTutors().size());
		Schedule schedule = tutor.getSchedule();
		assertEquals(1,tas.getAllSchedules().size());
		LocalDate BookingDate = LocalDate.parse("2019-08-03");
		LocalTime StartTime = LocalTime.parse("09:00");
		LocalTime EndTime = LocalTime.parse("10:00");
		
		LocalDate newBookingDate = null;
		
		String error = null;
		try {
			tas.createBooking(bookingId, student, course, schedule, BookingDate, StartTime, EndTime);
			tas.updateBookingDate(bookingId, newBookingDate);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("Booking Date cannot be empty!", error);
		assertEquals(BookingDate, tas.getAllBookings().get(0).getBookingDate());
	}
	
	@Test
	public void testUpdateBookingTime(){
		assertEquals(0, tas.getAllBookings().size());
		String bookingId = "Booking1";
		Student student = tas.createStudent("Jacob", "123", "Booking_1");
		assertEquals(1, tas.getAllStudents().size());
		Course course = tas.createCourse("ECSE321");
		assertEquals(1, tas.getAllCourses().size());
		Tutor tutor = tas.createTutor("Hawk", "12345", "Password_123", 0);
		assertEquals(1, tas.getAllTutors().size());
		Schedule schedule = tutor.getSchedule();
		assertEquals(1,tas.getAllSchedules().size());
		LocalDate BookingDate = LocalDate.parse("2019-08-03");
		LocalTime StartTime = LocalTime.parse("09:00");
		LocalTime EndTime = LocalTime.parse("10:00");
		
		LocalTime newStartTime = LocalTime.parse("11:00");
		LocalTime newEndTime = LocalTime.parse("12:00");
		try {
			tas.createBooking(bookingId, student, course, schedule, BookingDate, StartTime, EndTime);
			tas.updateBookingTime(bookingId, newStartTime, newEndTime);
		} catch(IllegalArgumentException e) {
			fail();
		}
		assertEquals(newStartTime, tas.getAllBookings().get(0).getStartTime());
		assertEquals(newEndTime, tas.getAllBookings().get(0).getEndTime());
	}
	
	@Test
	public void testUpdateBookingStartTimeNull(){
		assertEquals(0, tas.getAllBookings().size());
		String bookingId = "Booking1";
		Student student = tas.createStudent("Jacob", "123", "Booking_1");
		assertEquals(1, tas.getAllStudents().size());
		Course course = tas.createCourse("ECSE321");
		assertEquals(1, tas.getAllCourses().size());
		Tutor tutor = tas.createTutor("Hawk", "12345", "Password_123", 0);
		assertEquals(1, tas.getAllTutors().size());
		Schedule schedule = tutor.getSchedule();
		assertEquals(1,tas.getAllSchedules().size());
		LocalDate BookingDate = LocalDate.parse("2019-08-03");
		LocalTime StartTime = LocalTime.parse("09:00");
		LocalTime EndTime = LocalTime.parse("10:00");
		String error=null;
		LocalTime newStartTime = null;
		LocalTime newEndTime = LocalTime.parse("11:00");		
		try {
			tas.createBooking(bookingId, student, course, schedule, BookingDate, StartTime, EndTime);
			tas.updateBookingTime(bookingId, newStartTime, newEndTime);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("Booking Start Time cannot be empty!", error);
		assertEquals(StartTime, tas.getAllBookings().get(0).getStartTime());
		assertEquals(EndTime, tas.getAllBookings().get(0).getEndTime());
	}
	
	@Test
	public void testUpdateBookingEndTimeNull(){
		assertEquals(0, tas.getAllBookings().size());
		String bookingId = "Booking1";
		Student student = tas.createStudent("Jacob", "123", "Booking_1");
		assertEquals(1, tas.getAllStudents().size());
		Course course = tas.createCourse("ECSE321");
		assertEquals(1, tas.getAllCourses().size());
		Tutor tutor = tas.createTutor("Hawk", "12345", "Password_123", 0);
		assertEquals(1, tas.getAllTutors().size());
		Schedule schedule = tutor.getSchedule();
		assertEquals(1,tas.getAllSchedules().size());
		LocalDate BookingDate = LocalDate.parse("2019-08-03");
		LocalTime StartTime = LocalTime.parse("09:00");
		LocalTime EndTime = LocalTime.parse("10:00");
		String error=null;
		LocalTime newStartTime = LocalTime.parse("10:00");
		LocalTime newEndTime = null;
		try {
			tas.createBooking(bookingId, student, course, schedule, BookingDate, StartTime, EndTime);
			tas.updateBookingTime(bookingId, newStartTime, newEndTime);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("Booking End Time cannot be empty!", error);
		assertEquals(StartTime, tas.getAllBookings().get(0).getStartTime());
		assertEquals(EndTime, tas.getAllBookings().get(0).getEndTime());
	}
	
	
	
	@Test
	public void testDeleteBooking() {
		String bookingId = "Booking1";
		Student student = tas.createStudent("Jacob", "123", "Booking_1");
		assertEquals(1, tas.getAllStudents().size());
		Course course = tas.createCourse("ECSE321");
		assertEquals(1, tas.getAllCourses().size());
		Tutor tutor = tas.createTutor("Hawk", "12345", "Password_123", 0);
		assertEquals(1, tas.getAllTutors().size());
		Schedule schedule = tutor.getSchedule();
		assertEquals(1,tas.getAllSchedules().size());
		LocalDate BookingDate = LocalDate.parse("2019-08-03");
		LocalTime StartTime = LocalTime.parse("09:00");
		LocalTime EndTime = LocalTime.parse("10:00");
		
		
		try {
			tas.createBooking(bookingId, student, course, schedule, BookingDate, StartTime, EndTime);
			tas.deleteBooking("Booking1");
		} catch(IllegalArgumentException e) {
			fail();
		}
		assertEquals(0, tas.getAllBookings().size());
	}
	
	
	
	//Course Tests
	
	@Test
	public void testCreateCourse() {
		assertEquals(0, tas.getAllCourses().size());
		String courseId = "ECSE321";
		
		try {
			tas.createCourse(courseId);
		} catch(IllegalArgumentException e){
			fail();
		}
		assertEquals(1, tas.getAllCourses().size());
		assertEquals(courseId, tas.getAllCourses().get(0).getCourseId());
		
	}
	
	
	@Test
	public void testCreateCourseNull() {
		assertEquals(0, tas.getAllCourses().size());
		String courseId = null;
		
		String error = null;
		
		try {
			tas.createCourse(courseId);
		} catch(IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Course Id cannot be empty!", error);
	}
	
	@Test
	public void testCreateCourseEmpty() {
		assertEquals(0, tas.getAllCourses().size());
		String courseId = "";
		
		String error = null;
		
		try {
			tas.createCourse(courseId);
		} catch(IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Course Id cannot be empty!", error);
	}
	
	@Test
	public void testCreateCourseSpaces() {
		assertEquals(0, tas.getAllCourses().size());
		String courseId = " ";
		
		String error = null;
		
		try {
			tas.createCourse(courseId);
		} catch(IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Course Id cannot be empty!", error);
	}
	
//	@Test
//	public void testUpdateCourseId() {
//		assertEquals(0, tas.getAllCourses().size());
//		String courseId = "ECSE321";
//		String newCourseId = "ECSE223";
//		try {
//			tas.createCourse(courseId);
//			tas.updateCourseId(courseId, newCourseId);
//		} catch(IllegalArgumentException e){
//			fail();
//		}
//		assertEquals(newCourseId, tas.getAllCourses().get(0).getCourseId());
//
//	}
//	@Test
//	public void testUpdateCourseIdNull() {
//		assertEquals(0, tas.getAllCourses().size());
//		String courseId = "ECSE321";
//		String newCourseId = "ECSE223";
//		
//		String error = null;
//		try {
//			tas.createCourse(courseId);
//			tas.updateCourseId(courseId, newCourseId);
//		} catch(IllegalArgumentException e){
//			error = e.getMessage();
//		}
//		assertEquals("Course Id cannot be empty!", error);
//		assertEquals(courseId, tas.getAllCourses().get(0).getCourseId());
//
//	}
//	@Test
//	public void testUpdateCourseIdEmpty() {
//		assertEquals(0, tas.getAllCourses().size());
//		String courseId = "ECSE321";
//		String newCourseId = "";
//		
//		String error = null;
//		try {
//			tas.createCourse(courseId);
//			tas.updateCourseId(courseId, newCourseId);
//		} catch(IllegalArgumentException e){
//			error = e.getMessage();
//		}
//		assertEquals("Course Id cannot be empty!", error);
//		assertEquals(courseId, tas.getAllCourses().get(0).getCourseId());
//
//	}
//	@Test
//	public void testUpdateCourseIdSpaces() {
//		assertEquals(0, tas.getAllCourses().size());
//		String courseId = "ECSE321";
//		String newCourseId = " ";
//		
//		String error = null;
//		try {
//			tas.createCourse(courseId);
//			tas.updateCourseId(courseId, newCourseId);
//		} catch(IllegalArgumentException e){
//			error = e.getMessage();
//		}
//		assertEquals("Course Id cannot be empty!", error);
//		assertEquals(courseId, tas.getAllCourses().get(0).getCourseId());
//
//	}
	
	@Test
	public void testAddTutorToCourse() {
		assertEquals(0, tas.getAllCourses().size());
		String courseId = "ECSE321";
		Tutor tutor = tas.createTutor("Jacob", "123456", "Password_123", 0);
		try {
			tas.createCourse(courseId);
			tas.addCourseTutor(courseId, tutor);
		} catch(IllegalArgumentException e){
			fail();
		}
		assertEquals(false, tas.getAllCourses().get(0).getTutor().isEmpty());
	}
	
	@Test
	public void testTutorToCourseNull() {
		assertEquals(0, tas.getAllCourses().size());
		String courseId = "ECSE321";
		Tutor tutor = null;
		String error = null;
		try {
			tas.createCourse(courseId);
			tas.addCourseTutor(courseId, tutor);
		} catch(IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Tutor cannot be empty!", error);
		assertEquals(true, tas.getAllCourses().get(0).getTutor().isEmpty());
	}
	@Test
	public void testRemoveTutorFromCourse() {
		assertEquals(0, tas.getAllCourses().size());
		String courseId = "ECSE321";
		Tutor tutor = tas.createTutor("Jacob", "123", "Password_123", 0);
		
		try {
			Course course = tas.createCourse(courseId);
			Set<Tutor> tutors = new HashSet<>();
			tutors.add(tutor);
			course.setTutor(tutors);
			tas.removeCourseTutor(courseId, tutor);
		} catch(IllegalArgumentException e){
			fail();
		}
		assertEquals(true, tas.getAllCourses().get(0).getTutor().isEmpty());
	}
	
	@Test
	public void testRemoveTutorNullFromCourse() {
		assertEquals(0, tas.getAllCourses().size());
		String courseId = "ECSE321";
		Tutor tutor = tas.createTutor("Jacob", "123", "Password_123", 0);
		Tutor nullTutor = null;
		Set<Tutor> tutors = new HashSet<>();
		Course course = new Course();
		String error = null;
		try {
			course = tas.createCourse(courseId);
			tutors.add(tutor);
			course.setTutor(tutors);

			tas.removeCourseTutor(courseId, nullTutor);
		} catch(IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals(1, tas.getAllCourses().size());
		assertEquals("Tutor must be chosen for update", error);
		assertEquals(tutors.size(), course.getTutor().size());
	}
	
	
	@Test
	public void testDeleteCourse() {
		String courseId = "ECSE321";
		
		try {
			tas.createCourse(courseId);
			tas.deleteCourse(courseId);
		} catch(IllegalArgumentException e){
			fail();
		}
		assertEquals(0, tas.getAllCourses().size());
	}
	
	
	
	//Review Tests
	
	
	//TODO IMPLEMENT TAS.GETALLREVIEWS METHOD
	
	@Test
	public void testCreateReview() {

		assertEquals(0, tas.getAllBookings().size());

		String bookingId = "Booking1";
		Student student = tas.createStudent("Jacob", "123", "Booking_1");
		assertEquals(1, tas.getAllStudents().size());
		Course course = tas.createCourse("ECSE321");
		assertEquals(1, tas.getAllCourses().size());
		Tutor tutor = tas.createTutor("Hawk", "12345", "Password_123", 0);
		assertEquals(1, tas.getAllTutors().size());
		Schedule schedule = tutor.getSchedule();
		assertEquals(1,tas.getAllSchedules().size());
		LocalDate BookingDate = LocalDate.parse("2019-08-03");
		LocalTime StartTime = LocalTime.parse("09:00");
		LocalTime EndTime = LocalTime.parse("10:00");
		Booking booking = tas.createBooking(bookingId, student, course, schedule, BookingDate, StartTime, EndTime);
		String reviewId = "Review 1";
		int rating = 5;
		String comment = "Comment";
		try {

			tas.createReview(reviewId, booking.getBookingId(), rating, comment);
		} catch(IllegalArgumentException e) {
			fail();
		}
		
		assertEquals(reviewId, tas.getAllBookings().get(0).getReview().getReviewId());
	}
	
	@Test
	public void testCreateReviewNull(){
		assertEquals(0, tas.getAllBookings().size());

		String bookingId = "Booking1";
		Student student = tas.createStudent("Jacob", "123", "Booking_1");
		assertEquals(1, tas.getAllStudents().size());
		Course course = tas.createCourse("ECSE321");
		assertEquals(1, tas.getAllCourses().size());
		Tutor tutor = tas.createTutor("Hawk", "12345", "Password_123", 0);
		assertEquals(1, tas.getAllTutors().size());
		Schedule schedule = tutor.getSchedule();
		assertEquals(1,tas.getAllSchedules().size());
		LocalDate BookingDate = LocalDate.parse("2019-08-03");
		LocalTime StartTime = LocalTime.parse("09:00");
		LocalTime EndTime = LocalTime.parse("10:00");
		int rating = 5;
		String comment = "Comment";
		
		String reviewId = null;
		
		String error = null;
		
		try {
			Booking booking = tas.createBooking(bookingId, student, course, schedule, BookingDate, StartTime, EndTime);
			tas.createReview(reviewId, bookingId, rating, comment);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Review Id cannot be empty!", error);
		assertEquals(null, tas.getAllBookings().get(0).getReview());
		
	}
	
	@Test
	public void testCreateReviewEmpty(){
		assertEquals(0, tas.getAllBookings().size());

		String bookingId = "Booking1";
		Student student = tas.createStudent("Jacob", "123", "Booking_1");
		assertEquals(1, tas.getAllStudents().size());
		Course course = tas.createCourse("ECSE321");
		assertEquals(1, tas.getAllCourses().size());
		Tutor tutor = tas.createTutor("Hawk", "12345", "Password_123", 0);
		assertEquals(1, tas.getAllTutors().size());
		Schedule schedule = tutor.getSchedule();
		assertEquals(1,tas.getAllSchedules().size());
		LocalDate BookingDate = LocalDate.parse("2019-08-03");
		LocalTime StartTime = LocalTime.parse("09:00");
		LocalTime EndTime = LocalTime.parse("10:00");
		int rating = 5;
		String comment = "Comment";
		
		String reviewId = "";
		
		String error = null;
		
		try {
			Booking booking = tas.createBooking(bookingId, student, course, schedule, BookingDate, StartTime, EndTime);
			tas.createReview(reviewId, bookingId, rating, comment);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Review Id cannot be empty!", error);
		assertEquals(null, tas.getAllBookings().get(0).getReview());
	}
	
	@Test
	public void testCreateReviewSpaces(){
		assertEquals(0, tas.getAllBookings().size());

		String bookingId = "Booking1";
		Student student = tas.createStudent("Jacob", "123", "Booking_1");
		assertEquals(1, tas.getAllStudents().size());
		Course course = tas.createCourse("ECSE321");
		assertEquals(1, tas.getAllCourses().size());
		Tutor tutor = tas.createTutor("Hawk", "12345", "Password_123", 0);
		assertEquals(1, tas.getAllTutors().size());
		Schedule schedule = tutor.getSchedule();
		assertEquals(1,tas.getAllSchedules().size());
		LocalDate BookingDate = LocalDate.parse("2019-08-03");
		LocalTime StartTime = LocalTime.parse("09:00");
		LocalTime EndTime = LocalTime.parse("10:00");
		int rating = 5;
		String comment = "Comment";
		
		String reviewId = " ";
		
		String error = null;
		
		try {
			Booking booking = tas.createBooking(bookingId, student, course, schedule, BookingDate, StartTime, EndTime);
			tas.createReview(reviewId, bookingId, rating, comment);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Review Id cannot be empty!", error);
		assertEquals(null, tas.getAllBookings().get(0).getReview());
	}
	/*
	 * no deleting reviews. see deletereview() comment
	 */
//	@Test
//	public void testDeleteReview() {
//
//
//		String bookingId = "Booking1";
//		Student student = tas.createStudent("Jacob", "123", "Booking1");
//		assertEquals(1, tas.getAllStudents().size());
//		Course course = tas.createCourse("ECSE321");
//		assertEquals(1, tas.getAllCourses().size());
//		Tutor tutor = tas.createTutor("Hawk", "12345", 0);
//		assertEquals(1, tas.getAllTutors().size());
//		Schedule schedule = tutor.getSchedule();
//		assertEquals(1,tas.getAllSchedules().size());
//		LocalDate BookingDate = LocalDate.parse("2019-08-03");
//		LocalTime StartTime = LocalTime.parse("09:00");
//		LocalTime EndTime = LocalTime.parse("10:00");
//		String reviewId = "Review 1";
//		try {
//			Booking booking = tas.createBooking(bookingId, student, course, schedule, BookingDate, StartTime, EndTime);
//			Review review = tas.createReview(reviewId, bookingId);
//			tas.deleteReview(reviewId);
//		} catch(IllegalArgumentException e) {
//			fail();
//		}
//		
//		assertEquals(null, tas.getAllBookings().get(0).getReview());
//	}
	
	
	
	
	
	
	//Room Tests
	
	@Test
	public void testCreateRoom() {
		assertEquals(0, tas.getAllRooms().size());
		
		String roomId = "Room1";
		RoomSize size = RoomSize.SMALL;
		
		try {
			tas.createRoom(roomId, size);
		} catch(IllegalArgumentException e){
			fail();
		}
		assertEquals(1, tas.getAllRooms().size());
		assertEquals(roomId, tas.getAllRooms().get(0).getRoomId());
		assertEquals(size, tas.getAllRooms().get(0).getRoomSize());
	}
	
	@Test
	public void testCreateRoomNull() {
		assertEquals(0, tas.getAllRooms().size());
		
		String roomId = null;
		RoomSize size = RoomSize.SMALL;
		
		String error = null;
		try {
			tas.createRoom(roomId, size);
		} catch(IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Room Id cannot be empty!", error);
		assertEquals(0, tas.getAllRooms().size());
		
	}
	
	@Test
	public void testCreateRoomEmpty() {
		assertEquals(0, tas.getAllRooms().size());
		String roomId = "";
		RoomSize size = RoomSize.SMALL;
		
		String error = null;
		try {
			tas.createRoom(roomId, size);
		} catch(IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Room Id cannot be empty!", error);
		assertEquals(0, tas.getAllRooms().size());
		
	}
	
	@Test
	public void testCreateRoomSpaces() {
		assertEquals(0, tas.getAllRooms().size());
		String roomId = " ";
		RoomSize size = RoomSize.SMALL;
		
		String error = null;
		try {
			tas.createRoom(roomId, size);
		} catch(IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Room Id cannot be empty!", error);
		assertEquals(0, tas.getAllRooms().size());
		
	}
	
	@Test
	public void testCreateRoomNullSize() {
		assertEquals(0, tas.getAllRooms().size());
		String roomId = "Room1";
		RoomSize size = null;
		
		String error = null;
		try {
			tas.createRoom(roomId, size);
		} catch(IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Room size cannot be empty!", error);
		assertEquals(0, tas.getAllRooms().size());
	}
	
//	@Test
//	public void testUpdateRoomId(){
//		assertEquals(0, tas.getAllRooms().size());
//		String pastRoomId = "1";
//		String newRoomId = "2";
//		RoomSize size = RoomSize.SMALL;
//		
//		
//
//		try {
//			tas.createRoom(pastRoomId, size);
//			tas.updateRoomId(pastRoomId, newRoomId);
//		} catch(IllegalArgumentException e) {
//			fail();
//		}
//		assertEquals(newRoomId, tas.getAllRooms().get(0).getRoomId());
//	}
//	
//	@Test
//	public void testUpdateRoomIdNull(){
//		assertEquals(0, tas.getAllRooms().size());
//		String pastRoomId = "1";
//		String newRoomId = null;
//		RoomSize size = RoomSize.SMALL;
//		String error = null;
//		
//		try {
//			tas.createRoom(pastRoomId, size);
//			tas.updateRoomId(pastRoomId, newRoomId);
//		} catch(IllegalArgumentException e) {
//			error = e.getMessage();
//		}
//		assertEquals("Room Id cannot be empty!", error);
//		assertEquals(pastRoomId, tas.getAllRooms().get(0).getRoomId());
//	}
//	
	@Test
	public void testUpdateRoomSize() {
		assertEquals(0, tas.getAllRooms().size());
		String roomId = "1";
		RoomSize size = RoomSize.SMALL;
		RoomSize newSize = RoomSize.LARGE;
		
		
		try {
			tas.createRoom(roomId, size);
			tas.updateRoomSize(roomId, newSize);
		} catch(IllegalArgumentException e) {
			fail();
		}
		
		assertEquals(newSize, tas.getAllRooms().get(0).getRoomSize());

	}
	
	@Test
	public void testUpdateRoomSizeNull() {
		assertEquals(0, tas.getAllRooms().size());
		String roomId = "1";
		RoomSize size = RoomSize.SMALL;
		RoomSize newSize = null;
		String error = null;
		
		try {
			tas.createRoom(roomId, size);
			tas.updateRoomSize(roomId, newSize);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("Room size cannot be empty!", error);
		assertEquals(size, tas.getAllRooms().get(0).getRoomSize());

	}
	 /*
	 *DEPRECATED, ROOM-BOOKING UNIDIRECTIONAL
	 */
	
//	@Test
//	public void testAddRoomBooking() {
//		assertEquals(0, tas.getAllRooms().size());
//		String roomId = "1";
//		RoomSize size = RoomSize.SMALL;
//		assertEquals(0, tas.getAllBookings().size());
//		String bookingId = "Booking1";
//		Student student = tas.createStudent("Jacob", "123", "Booking1");
//		assertEquals(1, tas.getAllStudents().size());
//		Course course = tas.createCourse("ECSE321");
//		assertEquals(1, tas.getAllCourses().size());
//		Tutor tutor = tas.createTutor("Hawk", "12345", 0);
//		assertEquals(1, tas.getAllTutors().size());
//		Schedule schedule = tas.createSchedule("1255", tutor);
//		assertEquals(1,tas.getAllSchedules().size());
//		LocalDate BookingDate = LocalDate.parse("2019-08-03");
//		LocalTime StartTime = LocalTime.parse("09:00");
//		LocalTime EndTime = LocalTime.parse("10:00");
//		
//		Booking newBooking = tas.createBooking(bookingId, student, course, schedule, BookingDate, StartTime, EndTime);
//		assertEquals(1,tas.getAllBookings().size());
//
//		try {
//			tas.createRoom(roomId, size);
//			tas.addRoomBooking(roomId, newBooking);
//		} catch(IllegalArgumentException e) {
//			fail();
//		}
//		
//		
//		assertEquals(newBooking, tas.getAllRooms().get(0).getBooking());
//	}
	 /*
	 *DEPRECATED, ROOM-BOOKING UNIDIRECTIONAL
	 */
//	@Test 
//	public void testRemoveRoomBooking() {
//		assertEquals(0, tas.getAllRooms().size());
//		String roomId = "1";
//		RoomSize size = RoomSize.SMALL;
//		assertEquals(0, tas.getAllBookings().size());
//		String bookingId = "Booking1";
//		Student student = tas.createStudent("Jacob", "123", "Booking1");
//		assertEquals(1, tas.getAllStudents().size());
//		Course course = tas.createCourse("ECSE321");
//		assertEquals(1, tas.getAllCourses().size());
//		Tutor tutor = tas.createTutor("Hawk", "12345", 0);
//		assertEquals(1, tas.getAllTutors().size());
//		Schedule schedule = tas.createSchedule("1255", tutor);
//		assertEquals(1,tas.getAllSchedules().size());
//		LocalDate BookingDate = LocalDate.parse("2019-08-03");
//		LocalTime StartTime = LocalTime.parse("09:00");
//		LocalTime EndTime = LocalTime.parse("10:00");
//		
//		try {
//			tas.createRoom(roomId, size);
//			Booking Booking = tas.createBooking(bookingId, student, course, schedule, BookingDate, StartTime, EndTime);
//			tas.removeRoomBooking(roomId, Booking);
//		} catch(IllegalArgumentException e) {
//			fail();
//		}
//		
//		assertEquals(true, tas.getAllRooms().get(0).getBooking().isEmpty());
//	}
//	
//	
//	
	
	

	
	//Schedule Tests
	
	
//	
//	@Test
//	public void testCreateSchedule(){
////		assertEquals(0, tas.getAllSchedules().size());
//		String scheduleId = "125";
//		Tutor tutor = new Tutor();
//		tutor.setTutorId("159");
//		tutor.setName("Jerome Waldispul");
//		
//		try {
//			tas.createSchedule(scheduleId, tutor);
//		} catch (IllegalArgumentException e){
//			fail();
//		}
//		
//		assertEquals(1, tas.getAllSchedules().size());
//		assertEquals(scheduleId, tas.getAllSchedules().get(0).getScheduleId());
//		assertEquals(tutor, tas.getAllSchedules().get(0).getTutor());
//	}
	
//	@Test
//	public void testCreateScheduleIdNull() {
//		assertEquals(0, tas.getAllSchedules().size());
//		String scheduleId = null;
//		Tutor tutor = new Tutor();
//		
//		String error = null;
//		
//		try {
//			tas.createSchedule(scheduleId, tutor);
//		} catch (IllegalArgumentException e){
//			error = e.getMessage();
//		}
//		
//		assertEquals("Schedule Id cannot be empty!", error);
//		assertEquals(0, tas.getAllSchedules().size());
//	}
//	
//	public void testCreateScheduleIdEmpty() {
//		assertEquals(0, tas.getAllSchedules().size());
//		String scheduleId = "";
//		Tutor tutor = new Tutor();
//		
//		String error = null;
//		
//		try {
//			tas.createSchedule(scheduleId, tutor);
//		} catch (IllegalArgumentException e){
//			error = e.getMessage();
//		}
//		
//		assertEquals("Schedule Id cannot be empty!", error);
//		assertEquals(0, tas.getAllSchedules().size());
//	}
//	
//	public void testCreateScheduleIdSpaces() {
//		assertEquals(0, tas.getAllSchedules().size());
//		String scheduleId = " ";
//		Tutor tutor = new Tutor();
//		
//		String error = null;
//		
//		try {
//			tas.createSchedule(scheduleId, tutor);
//		} catch (IllegalArgumentException e){
//			error = e.getMessage();
//		}
//		
//		assertEquals("Schedule Id cannot be empty!", error);
//		assertEquals(0, tas.getAllSchedules().size());
//	}
//	
//	public void testCreateScheduleNoTutor() {
//		assertEquals(0, tas.getAllSchedules().size());
//		String scheduleId = "125";
//		Tutor tutor = null;
//		
//		String error = null;
//		
//		try {
//			tas.createSchedule(scheduleId, tutor);
//		} catch (IllegalArgumentException e){
//			error = e.getMessage();
//		}
//		
//		assertEquals("Schedule must have a tutor!", error);
//		assertEquals(0, tas.getAllSchedules().size());
//	}
	
	
	@Test
	public void testDeleteSchedule(){
		Tutor tutor = new Tutor();
		try {
			tutor = tas.createTutor("Marton", "1532", "Password_123", 0);
			String scheduleId = tutor.getSchedule().getScheduleId();
			
			tas.deleteSchedule(scheduleId);
		} catch (IllegalArgumentException e){
			fail();
		}
		assertEquals(0, tas.getAllSchedules().size());
	}
	
	
	
	
//	@Test 
//	public void testUpdateScheduleId(){
//		assertEquals(0, tas.getAllSchedules().size());
//		Tutor tutor = new Tutor(); //tas.createTutor("Jake", "1234", 0);
//		assertEquals(1, tas.getAllTutors().size());
//		String newScheduleId = "14";
//		try {
//			tutor = tas.createTutor("Gregory", "1782", 0);
//			String scheduleId = tutor.getSchedule().getScheduleId();// Schedule schedule = tutor.getSchedule(); 
//			tas.UpdateScheduleId(scheduleId, newScheduleId);
//			
//		} catch(IllegalArgumentException e) {
//			fail();
//		}
//		assertEquals(newScheduleId, tas.getAllSchedules().get(0).getScheduleId());
//		
//	}
	
	
	/*
	 * wtf this is the dumbest test ever LOL. depreeeeecated
	 */
//	@Test
//	public void testUpdateScheduleTutor(){
//		
//	assertEquals(0, tas.getAllSchedules().size());
//	Tutor tutor = tas.createTutor("Jake", "1234", 0);
//	assertEquals(1, tas.getAllTutors().size());
//	Tutor newTutor = tas.createTutor("Chris", "77", 0);
//	assertEquals(2, tas.getAllTutors().size());
//	String scheduleId = "12";
//	try {
//		tas.createSchedule(scheduleId, tutor);
//		tas.UpdateScheduleTutor(scheduleId, newTutor);
//		
//	} catch(IllegalArgumentException e) {
//		fail();
//	}
//	assertEquals(newTutor, tas.getAllSchedules().get(0).getTutor());
//	
//}
	@Test
	public void testAddScheduleBooking() {
		assertEquals(0, tas.getAllSchedules().size());
		assertEquals(0, tas.getAllBookings().size());
		Booking booking = new Booking();
		Student student = tas.createStudent("Jacob", "123", "Booking_1");
		assertEquals(1, tas.getAllStudents().size());
		Course course = tas.createCourse("ECSE321");
		assertEquals(1, tas.getAllCourses().size());
		Tutor tutor = tas.createTutor("Hawk", "12345", "Password_123", 0);
		assertEquals(1, tas.getAllTutors().size());
		Schedule schedule = tutor.getSchedule();
		assertEquals(1, tas.getAllSchedules().size());
		LocalDate BookingDate = LocalDate.parse("2019-08-03");
		LocalTime StartTime = LocalTime.parse("09:00");
		LocalTime EndTime = LocalTime.parse("10:00");
		booking = tas.createBooking("Booking1", student, course, schedule, BookingDate, StartTime, EndTime);
		String scheduleId = schedule.getScheduleId();
		assertEquals(1, tas.getAllBookings().size());
		try {
			tas.addScheduleBooking(scheduleId, booking);

		} catch(IllegalArgumentException e) {
			fail();
		}
		boolean contained = false;
		for (Booking b : schedule.getBooking()) {
			if(b.getBookingId().equals(booking.getBookingId())){
				contained = true;
			}
		}
		assertEquals(true, contained);
		

		//assertEquals(booking, tas.getAllSchedules().get(0).getBooking());

		assertEquals(1, tas.getAllSchedules().get(0).getBooking().size());
		
	}
	
	@Test
	public void testRemoveScheduleBooking() {
		assertEquals(0, tas.getAllSchedules().size());
		assertEquals(0, tas.getAllBookings().size());
		String bookingId = "Booking1";
		Student student = tas.createStudent("Jacob", "123", "Booking_1");
		assertEquals(1, tas.getAllStudents().size());
		Course course = tas.createCourse("ECSE321");
		assertEquals(1, tas.getAllCourses().size());
		Tutor tutor = tas.createTutor("Hawk", "12345", "Password_123", 0);
		assertEquals(1, tas.getAllTutors().size());
		LocalDate BookingDate = LocalDate.parse("2019-08-03");
		LocalTime StartTime = LocalTime.parse("09:00");
		LocalTime EndTime = LocalTime.parse("10:00");
		Schedule schedule = tutor.getSchedule();
		assertEquals(1, tas.getAllSchedules().size());
		Booking booking = tas.createBooking(bookingId, student, course, schedule, BookingDate, StartTime, EndTime);
		assertEquals(1, tas.getAllBookings().size());
		
//		Set<Booking> bookings = new HashSet<>();
//		bookings.add(booking);
//		schedule.setBooking(bookings);
		String scheduleId = booking.getSchedule().getScheduleId();
		try {
			tas.removeScheduleBooking(scheduleId, bookingId);
		} catch(IllegalArgumentException e) {
			fail();
		}
		assertEquals(1, tas.getAllSchedules().get(0).getBooking().size());
	}
	
	@Test
	public void testUpdateScheduleOnLeave() {
		assertEquals(0, tas.getAllSchedules().size());
		Tutor tutor = new Tutor();
		boolean changeOnLeave = true;
		try {
			tutor = tas.createTutor("Weber", "5738", "Password_123", 0);
			String scheduleId = tutor.getSchedule().getScheduleId();
			tas.createSchedule(scheduleId, tutor);
			tas.UpdateScheduleOnLeave(scheduleId, changeOnLeave);
		} catch (IllegalArgumentException e){
			fail();
		}
		assertEquals(changeOnLeave, tas.getAllSchedules().get(0).isOnLeave());
	}
	
	//Student Tests
	@Test
	public void testCreateStudent() {
		assertEquals(0, tas.getAllStudents().size());
		String name = "Charlie Gil";
		String id = "Duncan_Jacobson";
		String password = "Password_123!";

		
		try {
			tas.createStudent(name, id, password);
		} catch (IllegalArgumentException e){
			fail();
		}
		assertEquals(1, tas.getAllStudents().size());
		assertEquals(name, tas.getAllStudents().get(0).getName());
		assertEquals(id, tas.getAllStudents().get(0).getStudentId());
	}
	
	@Test
	public void  testCreateStudentEmptyName() {
		assertEquals(0, tas.getAllStudents().size());
		String name = "";
		String id = "Duncan_Jacobson";
		String password = "Password_123!";
		
		String error = null;
		
		try {
			tas.createStudent(name, id, password);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Student name cannot be empty!", error);
		assertEquals(0, tas.getAllStudents().size());

	}
	
	@Test
	public void  testCreateStudentSpacesName() {
		assertEquals(0, tas.getAllStudents().size());
		String name = " ";
		String id = "Duncan_Jacobson";
		String password = "Password_123!";
		
		String error = null;
		
		try {
			tas.createStudent(name, id, password);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Student name cannot be empty!", error);
		assertEquals(0, tas.getAllStudents().size());

	}
	
	@Test
	public void  testCreateStudentNullName() {
		assertEquals(0, tas.getAllStudents().size());
		String name = null;
		String id = "Duncan_Jacobson";
		String password = "Password_123!";
		
		String error = null;
		
		try {
			tas.createStudent(name, id, password);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Student name cannot be empty!", error);
		assertEquals(0, tas.getAllStudents().size());

	}
	
	@Test
	public void  testCreateStudentNullId() {
		assertEquals(0, tas.getAllStudents().size());
		String name = "Charlie Gil";
		String id = null;
		String password = "Password_123";
		String error = null;
		
		try {
			tas.createStudent(name, id, password);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Student Id cannot be empty!", error);
		assertEquals(0, tas.getAllStudents().size());
	}
	
	@Test
	public void  testCreateStudentEmptyId() {
		assertEquals(0, tas.getAllStudents().size());
		String name = "Charlie Gil";
		String id = "";
		String password = "Password_123!";
		
		String error = null;
		
		try {
			tas.createStudent(name, id, password);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Student Id cannot be empty!", error);
		assertEquals(0, tas.getAllStudents().size());
	}
	
	@Test
	public void  testCreateStudentSpacesId() {
		assertEquals(0, tas.getAllStudents().size());
		String name = "Charlie Gil";
		String id = " ";
		String password = "Password_123";
		
		String error = null;
		
		try {
			tas.createStudent(name, id, password);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Student Id cannot be empty!", error);
		assertEquals(0, tas.getAllStudents().size());
	}
	@Test
	public void testUpdateStudentPassword() {
		assertEquals(0, tas.getAllStudents().size());
		String name = "Linus Torvalds";
		String Id = "Samuel_Helie";
		String password = "Earthquake_1";
		String newPassword = "Riptide_1";
		
		try {
			tas.createStudent(name, Id, password);
			tas.updateStudentPassword(Id, password, newPassword);
		} catch(IllegalArgumentException e) {
			fail();
		}
		
		assertEquals(newPassword, tas.getAllStudents().get(0).getPassword());
	}
	
	@Test
	public void testUpdateStudentPasswordEmpty() {
		assertEquals(0, tas.getAllStudents().size());
		String name = "Linus Torvalds";
		String Id = "Samuel_Helie";
		String password = "Earthquake_1";
		String newPassword = "";
		
		String error = null;
		try {
			tas.createStudent(name, Id, password);
			tas.updateStudentPassword(Id, password, newPassword);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("Password cannot be empty!", error);
		assertEquals(password, tas.getAllStudents().get(0).getPassword());
	}
	
	@Test
	public void testUpdateStudentPasswordSpaces() {
		assertEquals(0, tas.getAllStudents().size());
		String name = "Linus Torvalds";
		String Id = "Samuel_Helie";
		String password = "Earthquake_1";
		String newPassword = " ";
		
		String error = null;
		try {
			tas.createStudent(name,Id, password);
			tas.updateStudentPassword(Id, password, newPassword);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("Password cannot be empty!", error);
		assertEquals(password, tas.getAllStudents().get(0).getPassword());
	}
	
	@Test
	public void testUpdateStudentPasswordNull() {
		assertEquals(0, tas.getAllStudents().size());
		String name = "Linus Torvalds";
		String Id = "Samuel_Helie";
		String password = "Earthquake_1";
		String newPassword = null;
		
		String error = null;
		try {
			tas.createStudent(name, Id, password);
			tas.updateStudentPassword(Id, password, newPassword);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("Password cannot be empty!", error);
		assertEquals(password, tas.getAllStudents().get(0).getPassword());
	}
	
	@Test
	public void testUpdateStudentPasswordWrongPassword() {
		assertEquals(0, tas.getAllStudents().size());
		String name = "Linus Torvalds";
		String Id = "Samuel_Helie";
		String password = "Earthquake_1";
		String wrongPassword = "Wawaweewa";
		String newPassword = "Doot_doot";
		
		String error = null;
		try {
			tas.createStudent(name, Id, password);
			tas.updateStudentPassword(Id, wrongPassword, newPassword);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("Old password is incorrect", error);
		assertEquals(password, tas.getAllStudents().get(0).getPassword());
	}
	
	@Test
	public void  testDeleteStudent() {
		String name = "Charlie Gil";
		String id = "Duncan_Jacobson";
		String password = "Password_123";

		
		try {
			tas.createStudent(name, id, password);
			tas.deleteStudent(id);
		} catch (IllegalArgumentException e){
			fail();
		}
		assertEquals(0, tas.getAllStudents().size());
		
	}
	
	
	
//	@Test
//	public void testUpdateStudentId(){
//		assertEquals(0, tas.getAllStudents().size());
//		String name = "Charlie Gil";
//		String id = "123";
//		String newid = "145";
//		
//		try {
//			tas.createStudent(name, id);
//			tas.updateStudentId(id, newid);
//		} catch (IllegalArgumentException e){
//			fail();
//		}
//		assertEquals(newid, tas.getAllStudents().get(0).getStudentId());
//	}
	
	@Test
	public void testUpdateStudentName(){
		assertEquals(0, tas.getAllStudents().size());
		String name = "Charlie Gil";
		String newName = "Jonny Goode";
		String id = "Duncan_Jacobson";
		String password = "Password_123";

		
		try {
			tas.createStudent(name, id, password);
			tas.updateStudentName(id, newName);
		} catch (IllegalArgumentException e){
			fail();
		}
		assertEquals(newName, tas.getAllStudents().get(0).getName());
	}
	
	
	@Test
	public void testAddStudentBooking(){
		assertEquals(0, tas.getAllBookings().size());
		String bookingId = "Booking1";
		String name = "Charlie Gil";
		String id = "Duncan_Jacobson";
		String password = "Password_123";
		Student student = tas.createStudent(name, id, password);
		assertEquals(1, tas.getAllStudents().size());
		Course course = tas.createCourse("ECSE321");
		assertEquals(1, tas.getAllCourses().size());
		Tutor tutor = tas.createTutor("Hawk", "12345", "Password_123", 0);
		assertEquals(1, tas.getAllTutors().size());
		Schedule schedule = tutor.getSchedule();
		assertEquals(1,tas.getAllSchedules().size());
		LocalDate BookingDate = LocalDate.parse("2019-08-03");
		LocalTime StartTime = LocalTime.parse("09:00");
		LocalTime EndTime = LocalTime.parse("10:00");

		Booking booking = tas.createBooking(bookingId, student, course, schedule, BookingDate, StartTime, EndTime);
		//Set<Booking> bookings = new HashSet<>();
		//bookings.add(booking);
		
		try {
			tas.addStudentBooking(id, booking);
		} catch(IllegalArgumentException e) {
			fail();
		}
		//assertEquals(bookings, tas.getAllStudents().get(0).getBooking());
		assertEquals(true, student.getBooking().contains(booking));

		assertEquals(1, tas.getAllStudents().get(0).getBooking().size());
	}
	
//	@Test
//	public void testRemoveStudentBooking() {
//		assertEquals(0, tas.getAllBookings().size());
//		assertEquals(0, tas.getAllStudents().size());
//		String bookingId = "Booking1";
//		Student student = tas.createStudent("Jacob", "123", "Booking1");
//		assertEquals(1, tas.getAllStudents().size());
//		Course course = tas.createCourse("ECSE321");
//		assertEquals(1, tas.getAllCourses().size());
//		Tutor tutor = tas.createTutor("Hawk", "12345", 0);
//		assertEquals(1, tas.getAllTutors().size());
//		Schedule schedule = tutor.getSchedule();
//		assertEquals(1,tas.getAllSchedules().size());
//		LocalDate BookingDate = LocalDate.parse("2019-08-03");
//		LocalTime StartTime = LocalTime.parse("09:00");
//		LocalTime EndTime = LocalTime.parse("10:00");
//		Booking booking = tas.createBooking(bookingId, student, course, schedule, BookingDate, StartTime, EndTime);
//		assertEquals(1, tas.getAllBookings().size());
//		Set<Booking> bookings = new HashSet<Booking>();
//		bookings.add(booking);
//		
//		try {
//			student.setBooking(bookings);
//			tas.removeStudentBooking("123", booking);
//			
//		} catch(IllegalArgumentException e) {
//			fail();
//		}
//		assertEquals(0, tas.getAllStudents().get(0).getBooking().size());
//
//	}
	
	// Tutor Tests
	
	@Test
	public void testCreateTutor() {
		assertEquals(0, tas.getAllTutors().size());
		String name = "Xander Bryce";
		String id = "124";
		String password = "Password_123";
		int rating = 0;
		
		try {
			tas.createTutor(name, id, password, rating);
		} catch (IllegalArgumentException e){
			fail();
		}
		assertEquals(1, tas.getAllTutors().size());
		assertEquals(name, tas.getAllTutors().get(0).getName());
		assertEquals(id, tas.getAllTutors().get(0).getTutorId());
		assertEquals(rating, tas.getAllTutors().get(0).getRating());
	}
	
	@Test
	public void testCreateTutorNameNull() {
		assertEquals(0, tas.getAllTutors().size());
		String name = null;
		String id = "124";
		String password = "Password_123";
		int rating = 0;
		
		String error = null;
		try {
			tas.createTutor(name, id, password, rating);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Tutor name cannot be empty!", error);
		
	}
	
	@Test
	public void testCreateTutorNameEmpty() {
		assertEquals(0, tas.getAllTutors().size());
		String name = "";
		String id = "124";
		String password = "Password_123";
		int rating = 0;
		
		String error = null;
		try {
			tas.createTutor(name, id, password, rating);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Tutor name cannot be empty!", error);
		
	}
	
	@Test
	public void testCreateTutorNameSpaces() {
		assertEquals(0, tas.getAllTutors().size());
		String name = " ";
		String id = "124";
		String password = "Password_123";
		int rating = 0;
		
		String error = null;
		try {
			tas.createTutor(name, id, password, rating);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals(error, "Tutor name cannot be empty!");
		
	}
	
	@Test
	public void testCreateTutorIdNull() {
		assertEquals(0, tas.getAllTutors().size());
		String name = "Xander Bryce";
		String id = null;
		String password = "Password_123";
		int rating = 0;
		
		String error = null;
		try {
			tas.createTutor(name, id, password, rating);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Tutor Id cannot be empty!", error);
		
	}
	
	@Test
	public void testCreateTutorIdSpaces() {
		assertEquals(0, tas.getAllTutors().size());
		String name = "Xander Bryce";
		String id = " ";
		String password = "Password_123";
		int rating = 0;
		
		String error = null;
		try {
			tas.createTutor(name, id, password, rating);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Tutor Id cannot be empty!", error);
		
	}
	
	@Test
	public void testDeleteTutor() {
		assertEquals(0, tas.getAllTutors().size());
		String name = "Xander Bryce";
		String id = "124";
		String password = "Password_123";
		int rating = 0;
		
		try {
			tas.createTutor(name, id, password, rating);
			tas.deleteTutor(id);
		} catch(IllegalArgumentException e) {
			fail();
		}
	}
	
	

//	@Test
//	public void testUpdateTutorId(){
//		assertEquals(0, tas.getAllTutors().size());
//		String name = "Jake Henley";
//		String id = "1834";
//		String newId = "321";
//		int rating = 0;
//		try {
//			tas.createTutor(name, id, rating);
//			tas.updateStudentId(id, newId);
//		} catch(IllegalArgumentException e) {
//			fail();
//		}
//		assertEquals(1, tas.getAllTutors().size());
//		assertEquals(newId, tas.getAllTutors().get(0).getTutorId());
//		
//	}
//	@Test
//	public void testUpdateTutorIdEmpty(){
//		assertEquals(0, tas.getAllTutors().size());
//		String name = "Jake Henley";
//		String id = "1834";
//		String newId = "";
//		int rating = 0;
//		
//		String error = null;
//		try {
//			tas.createTutor(name, id, rating);
//			tas.updateStudentId(id, newId);
//		} catch(IllegalArgumentException e) {
//			error = e.getMessage();
//		}
//		assertEquals(id, tas.getAllTutors().get(0).getTutorId());
//		assertEquals("Tutor Id cannot be empty!", error);
//		
//	}
//	
//	@Test
//	public void testUpdateTutorIdSpaces(){
//		assertEquals(0, tas.getAllTutors().size());
//		String name = "Jake Henley";
//		String id = "1834";
//		String newId = " ";
//		int rating = 0;
//		
//		String error = null;
//		try {
//			tas.createTutor(name, id, rating);
//			tas.updateStudentId(id, newId);
//		} catch(IllegalArgumentException e) {
//			error = e.getMessage();
//		}
//		assertEquals(id, tas.getAllTutors().get(0).getTutorId());
//		assertEquals("Tutor Id cannot be empty!", error);
//		
//	}
//	
//	@Test
//	public void testUpdateTutorIdnull(){
//		assertEquals(0, tas.getAllTutors().size());
//		String name = "Jake Henley";
//		String id = "1834";
//		String newId = null;
//		int rating = 0;
//		
//		String error = " ";
//		try {
//			tas.createTutor(name, id, rating);
//			tas.updateStudentId(id, newId);
//		} catch(IllegalArgumentException e) {
//			error = e.getMessage();
//		}
//		assertEquals(id, tas.getAllTutors().get(0).getTutorId());
//		assertEquals("Tutor Id cannot be empty!", error);
//		
//	}
	
	@Test
	public void testUpdateTutorName() {
		assertEquals(0, tas.getAllTutors().size());
		String name = "Jack Henley";
		String id = "1834";
		String newName = "Sam Helie";
		String password = "Password_123";
		int rating = 0;
		
		try {
			tas.createTutor(name, id, password, rating);
			tas.updateTutorName(id, newName);
		} catch(IllegalArgumentException e) {
			fail();
		}
		assertEquals(1, tas.getAllTutors().size());
		assertEquals(newName, tas.getAllTutors().get(0).getName());
	}
	
	@Test
	public void testUpdateTutorNameEmpty() {
		assertEquals(0, tas.getAllTutors().size());
		String name = "Jack Henley";
		String id = "1834";
		String newName = "";
		String password = "Password_123";
		int rating = 0;
		
		String error = null;
		try {
			tas.createTutor(name, id, password, rating);
			tas.updateTutorName(id, newName);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals(name, tas.getAllTutors().get(0).getName());
		assertEquals("Tutor name cannot be empty!", error);
	}
	
	@Test
	public void testUpdateTutorNameSpaces() {
		assertEquals(0, tas.getAllTutors().size());
		String name = "Jack Henley";
		String id = "1834";
		String newName = " ";
		String password = "Password_123";
		int rating = 0;
		
		String error = null;
		try {
			tas.createTutor(name, id, password, rating);
			tas.updateTutorName(id, newName);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals(name, tas.getAllTutors().get(0).getName());
		assertEquals("Tutor name cannot be empty!", error);
	}
	
	@Test
	public void testUpdateTutorNameNull() {
		assertEquals(0, tas.getAllTutors().size());
		String name = "Jack Henley";
		String id = "1834";
		String newName = null;
		String password = "Password_123";
		int rating = 0;
		
		String error = null;
		try {
			tas.createTutor(name, id, password, rating);
			tas.updateTutorName(id, newName);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals(name, tas.getAllTutors().get(0).getName());
		assertEquals("Tutor name cannot be empty!", error);
	}
	
	@Test 
	public void testUpdateTutorRating() {
		assertEquals(0, tas.getAllTutors().size());
		String name = "Jack Henley";
		String id = "1834";
		String password = "Password_123";
		int rating = 0;
		int newRating = 5;
		try {
			tas.createTutor(name, id, password, rating);
			tas.updateTutorRating(id, name, newRating);
		}catch(IllegalArgumentException e) {
			fail();
		}
		assertEquals(1,tas.getAllTutors().size());
		assertEquals(newRating, tas.getAllTutors().get(0).getRating());
	}
	
	// CANCELLED HERE
	
//	@Test
//	public void testAddTutorCourse() {
//		assertEquals(0, tas.getAllTutors().size());
//		String name = "Jack Henley";
//		String id = "1834";
//		int rating = 0;
//		String courseId = "ECSE321";
//		Course course = new Course();
//		Tutor tutor = new Tutor();
//		
//		try {
//			tutor = tas.createTutor(name, id, rating);
//			course = tas.createCourse(courseId);
//			tas.addTutorCourse(id, course);
//		}catch(IllegalArgumentException e) {
//			fail();
//		}
//		boolean contained = false;
//		for (Course c: tutor.getCourse()) {
//			if (c.getCourseId().equals(course.getCourseId())) {
//				contained = true;
//			}
//		}
//		System.out.println(tutor.getCourse().size());
//		assertEquals(true, contained);
//		
//		assertEquals(1, tutor.getCourse().size());
//
//	}
	
	@Test
	public void testAddTutorCourseEmpty() {
		assertEquals(0, tas.getAllTutors().size());
		String name = "Jack Henley";
		String id = "1834";
		String password = "Password_123";
		int rating = 0;
		String courseId = "";
		
		String error = null;
		try {
			tas.createTutor(name, id, password, rating);
			Course course = tas.createCourse(courseId);
			tas.addTutorCourse(id, course);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals(true, tas.getAllTutors().get(0).getCourse().isEmpty());
		assertEquals("Course Id cannot be empty!", error);
	}
	
	@Test
	public void testAddTutorCourseSpaces() {
		assertEquals(0, tas.getAllTutors().size());
		String name = "Jack Henley";
		String id = "1834";
		String password = "Password_123";
		int rating = 0;
		String courseId = " ";
		
		String error = null;
		try {
			tas.createTutor(name, id, password, rating);
			Course course = tas.createCourse(courseId);
			tas.addTutorCourse(id, course);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals(true, tas.getAllTutors().get(0).getCourse().isEmpty());
		assertEquals("Course Id cannot be empty!", error);

	}
	
	@Test
	public void testAddTutorCourseNull() {
		assertEquals(0, tas.getAllTutors().size());
		String name = "Jack Henley";
		String id = "1834";
		String password = "Password_123";
		int rating = 0;
		String courseId = null;
		
		String error = null;
		try {
			tas.createTutor(name, id, password, rating);
			Course course = tas.createCourse(courseId);
			tas.addTutorCourse(id, course);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals(true, tas.getAllTutors().get(0).getCourse().isEmpty());
		assertEquals("Course Id cannot be empty!", error);
	}
	
	@Test
	public void testRemoveTutorCourse() {
		assertEquals(0, tas.getAllTutors().size());
		String name = "Jack Henley";
		String id = "1834";
		String password = "Password_123";
		int rating = 0;
		String courseId = "ECSE321";
		Set <Course> courses = new HashSet<>();
		try {
			Tutor tutor = tas.createTutor(name, id, password, rating);
			Course course = tas.createCourse(courseId);
			courses.add(course);
			tutor.setCourse(courses);
			tas.removeTutorCourse(id, course);
		}catch(IllegalArgumentException e) {
			fail();
		}
		assertEquals(true, tas.getAllTutors().get(0).getCourse().isEmpty());
	}
	@Test 
	public void testCreateTutorPasswordNull() {
		assertEquals(0, tas.getAllTutors().size());
		String name = "Linus Torvalds";
		String id = "Duncan_Jacobson5";
		int rating = 0;
		String password = null;
		
		String error = null;
		
		try {
			tas.createTutor(name, id, password, rating);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals(0, tas.getAllTutors().size());
		assertEquals("Password cannot be empty!", error);
	}
	
	@Test 
	public void testCreateUserPasswordEmpty() {
		assertEquals(0, tas.getAllTutors().size());
		String name = "Linus Torvalds";
		String id = "Duncan_Jacobson5";
		int rating = 0;
		String password = "";
		
		String error = null;
		
		try {
			tas.createTutor(name, id, password, rating);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals(0, tas.getAllTutors().size());
		assertEquals("Password cannot be empty!", error);
	}
	
	@Test 
	public void testCreateUserPasswordSpaces() {
		assertEquals(0, tas.getAllTutors().size());
		String name = "Linus Torvalds";
		String id = "Duncan_Jacobson5";
		int rating = 0;
		String password = " ";
		
		String error = null;
		
		try {
			tas.createTutor(name, id, password, rating);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals(0, tas.getAllTutors().size());
		assertEquals("Password cannot be empty!", error);
	}
	
	
	//TODO Ask about how to do different tests for this method
	//TODO PUT IN TIME SLOTS TO FIX THIS TEST
	/*@Test
	public void testUpdateTutorSchedule() {
		assertEquals(0, tas.getAllTutors().size());
		String name = "Jack Henley";
		String id = "1834";
		int rating = 0;
		String newScheduleId = "983";
		Schedule newSchedule = new Schedule();
		
		try {
			Tutor tutor = tas.createTutor(name, id, rating);
			// tas.createSchedule(scheduleId, tutor);
			newSchedule = tas.createSchedule(newScheduleId, tutor);
			tas.updateTutorSchedule(id, newSchedule);
		} catch(IllegalArgumentException e) {
			fail();
		}
		assertEquals(newSchedule, tas.getAllTutors().get(0).getSchedule());
	}
	*/
	// User Tests
	
	// TODO password validation tests
/*	
	@Test 
	public void testCreateUser() {
		assertEquals(0, tas.getAllUsers().size());

		String username = "Duncan_Jacobson";
		String password = "Password_123!";

		try {
			tas.createUser(username, password);
		} catch (IllegalArgumentException e){
			fail();
		}
		
		assertEquals(1, tas.getAllUsers().size());
		assertEquals(username, tas.getAllUsers().get(0).getUsername());
		assertEquals(password, tas.getAllUsers().get(0).getPassword());
	}
	
	@Test 
	public void testCreateUsernameNull() {
		assertEquals(0, tas.getAllUsers().size());
		String username = null;
		String password = "Password123";
		
		String error = null;
		
		try {
			tas.createUser(username, password);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals(0, tas.getAllUsers().size());
		assertEquals("Username cannot be empty!", error);
	}
	
	@Test 
	public void testCreateUsernameEmpty() {
		assertEquals(0, tas.getAllUsers().size());
		String username = "";
		String password = "Password123";
		
		String error = null;
		
		try {
			tas.createUser(username, password);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals(0, tas.getAllUsers().size());
		assertEquals("Username cannot be empty!", error);
	}
	
	@Test 
	public void testCreateUsernameSpaces() {
		assertEquals(0, tas.getAllUsers().size());
		String username = " ";
		String password = "Password123";
		
		String error = null;
		
		try {
			tas.createUser(username, password);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals(0, tas.getAllUsers().size());
		assertEquals("Username cannot be empty!", error);
	}
	
	@Test 
	public void testCreateUserPasswordNull() {
		assertEquals(0, tas.getAllUsers().size());
		String username = "Duncan_Jacobson5";
		String password = null;
		
		String error = null;
		
		try {
			tas.createUser(username, password);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals(0, tas.getAllUsers().size());
		assertEquals("Password cannot be empty!", error);
	}
	
	@Test 
	public void testCreateUserPasswordEmpty() {
		assertEquals(0, tas.getAllUsers().size());
		String username = "Duncan_Jacobson5";
		String password = "";
		
		String error = null;
		
		try {
			tas.createUser(username, password);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals(0, tas.getAllUsers().size());
		assertEquals("Password cannot be empty!", error);
	}
	
	@Test 
	public void testCreateUserPasswordSpaces() {
		assertEquals(0, tas.getAllUsers().size());
		String username = "Duncan_Jacobson5";
		String password = " ";
		
		String error = null;
		
		try {
			tas.createUser(username, password);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals(0, tas.getAllUsers().size());
		assertEquals("Password cannot be empty!", error);
	}
	
//	@Test
//	public void testUpdateUserUsername() {
//		assertEquals(0, tas.getAllUsers().size());
//		String username = "Samuel_Helie";
//		String newUsername = "Richie_Moore";
//		String password = "Earthquake";
//		
//		try {
//			tas.createUser(username, password);
//			tas.updateUserUsername(username, newUsername);
//		} catch(IllegalArgumentException e) {
//			fail();
//		}
//		
//		assertEquals(newUsername, tas.getAllUsers().get(0).getUsername());
//	}
//	
//	@Test
//	public void testUpdateUserUsernameEmpty() {
//		assertEquals(0, tas.getAllUsers().size());
//		String username = "Samuel_Helie";
//		String newUsername = "";
//		String password = "Earthquake";
//		
//		String error = null;
//		
//		try {
//			tas.createUser(username, password);
//			tas.updateUserUsername(username, newUsername);
//		} catch(IllegalArgumentException e) {
//			error = e.getMessage();
//		}
//		assertEquals("Username cannot be empty!", error);
//		assertEquals(newUsername, tas.getAllUsers().get(0).getUsername());
//	}
//	
//	@Test
//	public void testUpdateUserUsernameSpaces() {
//		assertEquals(0, tas.getAllUsers().size());
//		String username = "Samuel_Helie";
//		String newUsername = " ";
//		String password = "Earthquake";
//		
//		String error = null;
//		
//		try {
//			tas.createUser(username, password);
//			tas.updateUserUsername(username, newUsername);
//		} catch(IllegalArgumentException e) {
//			error = e.getMessage();
//		}
//		assertEquals("Username cannot be empty!", error);
//		assertEquals(newUsername, tas.getAllUsers().get(0).getUsername());
//	}
//	
//	@Test
//	public void testUpdateUserUsernameNull() {
//		assertEquals(0, tas.getAllUsers().size());
//		String username = "Samuel_Helie";
//		String newUsername = null;
//		String password = "Earthquake";
//		
//		String error = null;
//		
//		try {
//			tas.createUser(username, password);
//			tas.updateUserUsername(username, newUsername);
//		} catch(IllegalArgumentException e) {
//			error = e.getMessage();
//		}
//		assertEquals("Username cannot be empty!", error);
//		assertEquals(newUsername, tas.getAllUsers().get(0).getUsername());
//	}
	

	
	@Test
	public void testDeleteUser() {
		String username = "Duncan_Jacobson5";
		String password = "Password_123";
		
		try {
			tas.createUser(username, password);
			tas.deleteUser(username, password);
		} catch (IllegalArgumentException e){
			fail();
		}
		assertEquals(0, tas.getAllUsers().size());
	}
	*/

	
}