package ca.mcgill.ecse321.tutorapplication;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ca.mcgill.ecse321.tutorapplication.dao.BookingRepository;
import ca.mcgill.ecse321.tutorapplication.dao.StudentRepository;
import ca.mcgill.ecse321.tutorapplication.dao.TutorRepository;
import ca.mcgill.ecse321.tutorapplication.dao.CourseRepository;
import ca.mcgill.ecse321.tutorapplication.dao.ReviewRepository;
import ca.mcgill.ecse321.tutorapplication.dao.RoomRepository;
//import ca.mcgill.ecse321.tutorapplication.dao.RoomSizeRepository;
import ca.mcgill.ecse321.tutorapplication.dao.ScheduleRepository;
//import ca.mcgill.ecse321.tutorapplication.dao.UserRepository;

import ca.mcgill.ecse321.tutorapplication.service.TutorApplicationService;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutorapplication.controller.TutorApplicationRestController;

import ca.mcgill.ecse321.tutorapplication.model.Booking;
import ca.mcgill.ecse321.tutorapplication.model.Course;
import ca.mcgill.ecse321.tutorapplication.model.Review;
import ca.mcgill.ecse321.tutorapplication.model.Room;
import ca.mcgill.ecse321.tutorapplication.model.RoomSize;
import ca.mcgill.ecse321.tutorapplication.model.Schedule;
import ca.mcgill.ecse321.tutorapplication.model.Student;
import ca.mcgill.ecse321.tutorapplication.model.Tutor;
//import ca.mcgill.ecse321.tutorapplication.model.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TutorapplicationServiceTests {
	
	@Mock
	private StudentRepository studentDao;

	@InjectMocks
	private TutorApplicationService service;

	/* Student name and Id are the only parameters for the creation of a tutor
	therefore the only keys we need are student Id and student name for the creation 
	of a mock student */
	private static final String STUDENT_ID_KEY = "TestStudentID"; 
	private static final String STUDENT_NAME_KEY = "TestStudentName";
	private static final String NONEXISTING_STUDENT_KEY = "Noservicetudent";
	private static final String PASSWORD_KEY = "TestPassword"; 


	// Make a mock output for the service using mockito
	@Before
	public void setMockOutputStudent() {
		when(studentDao.findByStudentId(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(STUDENT_NAME_KEY) && invocation.getArgument(1).equals(STUDENT_ID_KEY)) {
				Student student = new Student();
				student.setStudentId(STUDENT_ID_KEY);
				student.setName(STUDENT_NAME_KEY);
				student.setPassword(PASSWORD_KEY);
				return student;
			} else {
				return null;
			}
		});
	}
	
	
	// Testing the creation of a student works when all parameters are valid
	@Test
	public void testCreateStudent() {
		assertEquals(0, service.getAllStudents().size());
		String name = "Charlie Gil";
		String id = "123";
		String password = "Password_123";
		Student student = new Student();
		
		try {
			student = service.createStudent(name, id, password);
		} catch (IllegalArgumentException e){
			fail();
		}
		assertEquals(name, student.getName());
		assertEquals(id, student.getStudentId());
	}
	
	// Testing that the creation of a student with an empty name returns an error
	@Test
	public void  testCreateStudentEmptyName() {
		String name = "";
		String id = "123";
		String password = "Password_123";

		
		String error = null;
		
		try {
			service.createStudent(name, id, password);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Student name cannot be empty!", error);

	}
	
	// Testing that the creation of a student using spaces as a name returns an error
	@Test
	public void  testCreateStudentSpacesName() {
		String name = " ";
		String id = "123";
		String password = "Password_123";

		
		String error = null;
		
		try {
			service.createStudent(name, id, password);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Student name cannot be empty!", error);

	}
	
	// Testing that the creation of a student with name = null returns an error
	@Test
	public void  testCreateStudentNullName() {
		String name = null;
		String id = "123";
		String password = "Password_123";

		
		String error = null;
		
		try {
			service.createStudent(name, id, password);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Student name cannot be empty!", error);

	}
	
	// Testing that the creation of a student with a null Id does not work
	@Test
	public void  testCreateStudentNullId() {
		String name = "Charlie Gil";
		String id = null;
		String password = "Password_123";

		
		String error = null;
		
		try {
			service.createStudent(name, id, password);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Student Id cannot be empty!", error);
	}
	
	// Testing that the creation of a student with an empty Id returns an error
	@Test
	public void  testCreateStudentEmptyId() {
		String name = "Charlie Gil";
		String id = "";
		String password = "Password_123";

		
		String error = null;
		
		try {
			service.createStudent(name, id, password);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Student Id cannot be empty!", error);
	}
	
	// Testing that the creation of a student with spaces as an Id returns an error
	@Test
	public void  testCreateStudentSpacesId() {
		String name = "Charlie Gil";
		String id = " ";
		String password = "Password_123";

		
		String error = null;
		
		try {
			service.createStudent(name, id, password);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Student Id cannot be empty!", error);
	}
	
	// Testing that deleting a student actually works
	@Test
	public void  testDeleteStudent() {
		String name = "Charlie Gil";
		String id = "123";
		String password = "Password_123";
		
		try {
			service.createStudent(name, id, password);
			service.deleteStudent(id);
		} catch (IllegalArgumentException e){
			fail();
		}
		assertEquals(0, service.getAllStudents().size());
		
	}
	
	// Tutor
	@Mock
	private TutorRepository tutorDao;
	
	private static final String TUTOR_ID_KEY = "TestTutorId"; 
	private static final String TUTOR_NAME_KEY = "TestTutorName";
	private static final String NONEXISTING_TUTOR_KEY = "NotATutor";
	private static final String TUTOR_PASSWORD = "Password_123";
	
	// Set up for mock output for mockito 
	@Before
	public void setMockOutputTutor() {
		when(tutorDao.findByTutorId(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(TUTOR_NAME_KEY) && invocation.getArgument(1).equals(TUTOR_ID_KEY)) {
				Tutor tutor = new Tutor();
				tutor.setTutorId(TUTOR_ID_KEY);
				tutor.setName(TUTOR_NAME_KEY);
				tutor.setPassword(TUTOR_PASSWORD);
				return tutor;
			} else {
				return null;
			}
		});
	}
	
	// Testing that the creation of a tutor with correct parameters actually works
	@Test
	public void testCreateTutor() {
		assertEquals(0, service.getAllTutors().size());
		String name = "Xander Bryce";
		String id = "124";
		String password = "Password_123";
		int rating = 0;
		Tutor tutor = new Tutor();
		
		try {
			tutor = service.createTutor(name, id, password, rating);
		} catch (IllegalArgumentException e){
			fail();
		}
		assertEquals(name, tutor.getName());
		assertEquals(id, tutor.getTutorId());
		assertEquals(rating, tutor.getRating());
	}
	
	// Testing that the creation of a tutor with a null name returns an error
	@Test
	public void testCreateTutorNameNull() {
		String name = null;
		String id = "124";
		String password = "Password_123";
		int rating = 0;

		
		String error = null;
		try {
			service.createTutor(name, id, password, rating);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Tutor name cannot be empty!", error);
		
	}
	
	// Testing that the creation of a tutor with an empty name returns an error
	@Test
	public void testCreateTutorNameEmpty() {
		String name = "";
		String id = "124";
		String password = "Password_123";
		int rating = 0;
		
		String error = null;
		try {
			service.createTutor(name, id, password, rating);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Tutor name cannot be empty!", error);
		
	}
	
	// Testing that the creation of a tutor with a name made up of spaces returns an error
	@Test
	public void testCreateTutorNameSpaces() {
		String name = " ";
		String id = "124";
		String password = "Password_123";
		int rating = 0;
		
		String error = null;
		try {
			service.createTutor(name, id, password, rating);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals(error, "Tutor name cannot be empty!");
		
	}
	
	@Test
	public void testCreateTutorIdNull() {
		String name = "Xander Bryce";
		String id = null;
		String password = "Password_123";
		int rating = 0;
		
		String error = null;
		try {
			service.createTutor(name, id, password, rating);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Tutor Id cannot be empty!", error);
		
	}
	
	// Testing that the creation of a tutor with an Id of spaces returns an error
	@Test
	public void testCreateTutorIdSpaces() {
		String name = "Xander Bryce";
		String id = " ";
		String password = "Password_123";
		int rating = 0;
		
		String error = null;
		try {
			service.createTutor(name, id, password, rating);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Tutor Id cannot be empty!", error);
		
	}
	
	// Testing that the deletion of a tutor actually works
	@Test
	public void testDeleteTutor() {
		String name = "Xander Bryce";
		String id = "124";
		String password = "Password_123";
		int rating = 0;
		
		try {
			service.createTutor(name, id, password, rating);
			service.deleteTutor(id);
		} catch(IllegalArgumentException e) {
			fail();
		}
		assertEquals(0, service.getAllTutors().size());
	}
	
	@Mock
	private CourseRepository courseDao;
	
	private static final String COURSE_KEY = "TestCourse"; 
	private static final String NONEXISTING_COURSE_KEY = "NotACourse";
	
	// Set up for mock output to be used by mockito
	@Before
	public void setMockOutputCourse() {
		when(courseDao.findCourseByCourseId(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(COURSE_KEY)) {
				Course course = new Course();
				course.setCourseId(COURSE_KEY);
				return course;
			} else {
				return null;
			}
		});
	}
	
	// Testing that the creation of a course with the correct parameters actually works
	@Test
	public void testCreateCourse() {
		assertEquals(0, service.getAllCourses().size());
		String courseId = "ECSE321";
		Course course = new Course();
		
		try {
			course = service.createCourse(courseId);
		} catch(IllegalArgumentException e){
			fail();
		}
		assertEquals(courseId, course.getCourseId());
		
	}
	
	// Testing that the creation of a course with a null Id returns an error
	@Test
	public void testCreateCourseNull() {
		String courseId = null;
		
		String error = null;
		
		try {
			service.createCourse(courseId);
		} catch(IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Course Id cannot be empty!", error);
	}
	
	// Testing that the creation of a course with an empty Id returns an error
	@Test
	public void testCreateCourseEmpty() {
		String courseId = "";
		
		String error = null;
		
		try {
			service.createCourse(courseId);
		} catch(IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Course Id cannot be empty!", error);
	}
	
	// Testing that the creation of a course that has a course Id that 
	// only consists of spaces returns an error
	@Test
	public void testCreateCourseSpaces() {
		String courseId = " ";
		
		String error = null;
		
		try {
			service.createCourse(courseId);
		} catch(IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Course Id cannot be empty!", error);
	}
	
	// Testing that the deletion of a course actually works
	@Test
	public void testDeleteCourse() {
		String courseId = "ECSE321";
		
		try {
			service.createCourse(courseId);
			service.deleteCourse(courseId);
		} catch(IllegalArgumentException e){
			fail();
		}
		assertEquals(0, service.getAllCourses().size());
	}
	// Review
	
	
	@Mock
	private ReviewRepository reviewDao;
	
	private static final String REVIEW_ID_KEY = "TestReviewId"; 
	
	// Set up of the mock output with mockito
	@Before
	public void setMockOutputReview() {
		when(reviewDao.findReviewByReviewId(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(REVIEW_ID_KEY)) {
				Review review = new Review();
				review.setReviewId(REVIEW_ID_KEY);
				return review;
			} else {
				return null;
			}
		});
	}
	
	// Testing if the creation of a review works with the correct parameters actually works
/*	@Test
	public void testCreateReview() {

		String bookingId = "Booking1";
		Student student = service.createStudent("Jacob", "123");
		Course course = service.createCourse("ECSE321");
		Tutor tutor = service.createTutor("Hawk", "12345", 0);
		Schedule schedule = service.createSchedule("1255", tutor);
		LocalDate BookingDate = LocalDate.parse("2019-08-03");
		LocalTime StartTime = LocalTime.parse("09:00");
		LocalTime EndTime = LocalTime.parse("10:00");
		String reviewId = "Review 1";
		Review review = new Review();
		Booking booking = service.createBooking(bookingId, student, course, schedule, BookingDate, StartTime, EndTime);

		try {
			review = service.createReview(reviewId, booking.getBookingId());
		} catch(IllegalArgumentException e) {
			fail();
		}
		
		assertEquals(reviewId, review.getReviewId());
		assertEquals(bookingId, booking.getBookingId());
	}*/
	
	// Testing that the creation of a review with a null Id returns an error
	@Test
	public void testCreateReviewNull(){
		
		String bookingId = "Booking1";
		Student student = service.createStudent("Jacob", "123","Password_123");
		Course course = service.createCourse("ECSE321");
		Tutor tutor = service.createTutor("Hawk", "12345", "Password_123", 0);
		Schedule schedule = service.createSchedule("1255", tutor);
		int rating = 5;
		String comment = "Comment";
		LocalDate BookingDate = LocalDate.parse("2019-08-03");
		LocalTime StartTime = LocalTime.parse("09:00");
		LocalTime EndTime = LocalTime.parse("10:00");
		
		String reviewId = null;
		
		String error = null;
		
		try {
			Booking booking = service.createBooking(bookingId, student, course, schedule, BookingDate, StartTime, EndTime);
			service.createReview(reviewId, bookingId, rating ,comment);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Review Id cannot be empty!", error);
		
	}
	
	// Testing that the creation of a review with an empty Id returns an error
	@Test
	public void testCreateReviewEmpty(){

		String bookingId = "Booking1";
		Student student = service.createStudent("Jacob", "123","Password_123");
		Course course = service.createCourse("ECSE321");
		Tutor tutor = service.createTutor("Hawk", "12345", "Password_123", 0);
		Schedule schedule = service.createSchedule("1255", tutor);
		int rating = 5;
		String comment = "Comment";
		LocalDate BookingDate = LocalDate.parse("2019-08-03");
		LocalTime StartTime = LocalTime.parse("09:00");
		LocalTime EndTime = LocalTime.parse("10:00");
		
		String reviewId = "";
		
		String error = null;
		
		try {
			Booking booking = service.createBooking(bookingId, student, course, schedule, BookingDate, StartTime, EndTime);
			service.createReview(reviewId, bookingId, rating, comment);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Review Id cannot be empty!", error);
	}
	
	// Testing that the creation of a review with a review Id 
	// composed of only spaces returns an error
	@Test
	public void testCreateReviewSpaces(){

		String bookingId = "Booking1";
		Student student = service.createStudent("Jacob", "123", "Password_123");
		Course course = service.createCourse("ECSE321");
		Tutor tutor = service.createTutor("Hawk", "12345", "Password_123", 0);
		Schedule schedule = service.createSchedule("1255", tutor);
		int rating = 5;
		String comment = "Comment";
		LocalDate BookingDate = LocalDate.parse("2019-08-03");
		LocalTime StartTime = LocalTime.parse("09:00");
		LocalTime EndTime = LocalTime.parse("10:00");
		
		String reviewId = " ";
		
		String error = null;
		
		try {
			Booking booking = service.createBooking(bookingId, student, course, schedule, BookingDate, StartTime, EndTime);
			service.createReview(reviewId, bookingId, rating, comment);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Review Id cannot be empty!", error);
	}
	
	
	// User Tests
/*	
	@Mock
	private UserRepository userDao;
	
	private static final String USER_KEY = "TestUser"; 
	private static final String PASSWORD_KEY = "TestPassword"; 
	private static final String NONEXISTING_USER_KEY = "NotAUser";
	
	// Setting up mock output for mockito
	@Before
	public void setMockOutputUser() {
		when(userDao.findUserByUsername(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
			if((invocation.getArgument(0).equals(USER_KEY))&& (invocation.getArgument(1).equals(PASSWORD_KEY))) {
				User user = new User();
				user.setUsername(USER_KEY);
				user.setPassword(PASSWORD_KEY);
				return user;
			} else {
				return null;
			}
		});
	}
	
	// Testing that the creation of a user actually works 
	// when the parameters are correct
	@Test 
	public void testCreateUser() {
		assertEquals(0, service.getAllUsers().size());
		String username = "Duncan_Jacobson";
		String password = "Password_123";
		User user = new User();
		try {
			user = service.createUser(username, password);
		} catch (IllegalArgumentException e){
			fail();
		}
		
		assertEquals(username, user.getUsername());
		assertEquals(password, user.getPassword());
	}
	
	// Testing that the creation of a user with a null username returns an error
	@Test 
	public void testCreateUsernameNull() {
		String username = null;
		String password = "Password123";
		
		String error = null;
		
		try {
			service.createUser(username, password);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Username cannot be empty!", error);
	}
	
	// Testing that the creation of a user with an empty username returns an error
	@Test 
	public void testCreateUsernameEmpty() {
		String username = "";
		String password = "Password123";
		User user = new User();
		String error = null;
		
		try {
			user = service.createUser(username, password);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Username cannot be empty!", error);
	}
	
	// Testing that the creation of a user 
	// with a username of spaces returns an error
	@Test 
	public void testCreateUsernameSpaces() {
		assertEquals(0, service.getAllUsers().size());
		String username = " ";
		String password = "Password123";
		User user = new User();

		String error = null;
		
		try {
			user = service.createUser(username, password);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Username cannot be empty!", error);
	}
	
	// Testing that the creation of a user with a null password returns an error
	@Test 
	public void testCreateUserPasswordNull() {
		assertEquals(0, service.getAllUsers().size());
		String username = "Duncan_Jacobson5";
		String password = null;
		User user = new User();

		String error = null;
		
		try {
			user = service.createUser(username, password);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Password cannot be empty!", error);
	}
	
	// Testing that the creation of a user with an empty password returns an error
	@Test 
	public void testCreateUserPasswordEmpty() {
		String username = "Duncan_Jacobson5";
		String password = "";
		User user = new User();

		String error = null;
		
		try {
			user = service.createUser(username, password);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Password cannot be empty!", error);
	}
	
	// Testing that the creation of a user with a password
	// consisting of a space returns an error
	@Test 
	public void testCreateUserPasswordSpaces() {
		String username = "Duncan_Jacobson5";
		String password = " ";
		User user = new User();
		String error = null;
		
		try {
			user = service.createUser(username, password);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Password cannot be empty!", error);
	}
	
	// Testing that the deletion of a user with the correct parameters actually works
	@Test
	public void testDeleteUser() {
		String username = "Duncan_Jacobson5";
		String password = "Password_123";
		
		try {
			service.createUser(username, password);
			service.deleteUser(username, password);
		} catch (IllegalArgumentException e){
			fail();
		}
		assertEquals(0, service.getAllUsers().size());
	}
	*/
	@Mock
	private BookingRepository bookingDao;

	private static final String BOOKING_ID_KEY = "TestBookingID";
	private static final Student STUDENT_KEY = new Student();
	private static final Course COURSE1_KEY = new Course();
	private static final Schedule SCHEDULE_KEY = new Schedule();
	private static final LocalDate BOOKINGDATE_KEY = LocalDate.parse("2019-03-02");
	private static final LocalTime STARTTIME_KEY = LocalTime.parse("09:00");
	private static final LocalTime ENDTIME_KEY = LocalTime.parse("10:00");
	private static final String NONEXISTING_BOOKING_KEY = "NoBooking";

	
	// Setting up of mock output for mockito
	@Before
	public void setMockOutputBooking() {
		when(bookingDao.findBookingByBookingId(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(BOOKING_ID_KEY) && invocation.getArgument(1).equals(STUDENT_KEY) && 
					invocation.getArgument(2).equals(COURSE1_KEY) && invocation.getArgument(3).equals(SCHEDULE_KEY)
					&& invocation.getArgument(4).equals(BOOKINGDATE_KEY) && invocation.getArgument(5).equals(STARTTIME_KEY)
					&& invocation.getArgument(6).equals(ENDTIME_KEY)) {
				Booking booking = new Booking();
				booking.setBookingId(BOOKING_ID_KEY);
				booking.setStudent(STUDENT_KEY);
				booking.setCourse(COURSE1_KEY);
				booking.setSchedule(SCHEDULE_KEY);
				booking.setBookingDate(BOOKINGDATE_KEY);
				booking.setStartTime(STARTTIME_KEY);
				booking.setEndTime(ENDTIME_KEY);
				return booking;
			} else {
				return null;
			}
		});
	}
	
	// Testing that the creation of a booking 
	// with the correct parameters actually works
	@Test
	public void testCreateBooking() {
		String bookingId = "Booking1";
		Student student = service.createStudent("Jacob", "123", "Password_123");
		Course course = service.createCourse("ECSE321");
		Tutor tutor = service.createTutor("Hawk", "12345", "Password_123", 0);
		Schedule schedule = service.createSchedule("1255", tutor);
		LocalDate BookingDate = LocalDate.parse("2019-08-03");
		LocalTime StartTime = LocalTime.parse("09:00");
		LocalTime EndTime = LocalTime.parse("10:00");
		Booking booking = new Booking();
		
		try {
			booking = service.createBooking(bookingId, student, course, schedule, BookingDate, StartTime, EndTime);
		} catch(IllegalArgumentException e){
			fail();
		}
		
		assertEquals(bookingId, booking.getBookingId());
		assertEquals(student, booking.getStudent());
		assertEquals(course, booking.getCourse());
		assertEquals(StartTime, booking.getStartTime());
		assertEquals(EndTime, booking.getEndTime());
	}
	
	// Testing that the creation of a booking returns an error if the booking id is null
	@Test
	public void testCreateBookingIdNull(){
		String bookingId = null;
		Student student = service.createStudent("Jacob", "123", "Password_123");
		Course course = service.createCourse("ECSE321");
		Tutor tutor = service.createTutor("Hawk", "12345", "Password_123", 0);
		Schedule schedule = service.createSchedule("1255", tutor);
		LocalDate BookingDate = LocalDate.parse("2019-08-03");
		LocalTime StartTime = LocalTime.parse("09:00");
		LocalTime EndTime = LocalTime.parse("10:00");
		
		String error = null;
	
	try {
		service.createBooking(bookingId, student, course, schedule, BookingDate, StartTime, EndTime);
	} catch(IllegalArgumentException e) {
		error = e.getMessage();
	}
	assertEquals("Booking Id cannot be empty!", error);
	
	}
	
	// Testing that the creation of a booking returns an error if the booking date is null
	@Test
	public void testCreateBookingDateNull(){
		
		String bookingId = "Booking1";
		Student student = service.createStudent("Jacob", "123", "Password_123");
		Course course = service.createCourse("ECSE321");
		Tutor tutor = service.createTutor("Hawk", "12345", "Password_123", 0);
		Schedule schedule = service.createSchedule("1255", tutor);
		LocalDate BookingDate = null;
		LocalTime StartTime = LocalTime.parse("09:00");
		LocalTime EndTime = LocalTime.parse("10:00");
		
		String error = null;
	
	try {
		service.createBooking(bookingId, student, course, schedule, BookingDate, StartTime, EndTime);
	} catch(IllegalArgumentException e) {
		error = e.getMessage();
	}
	assertEquals("Booking Date cannot be empty!", error);
	
	}
	
	// Testing that the creation of a booking returns 
	// an error if the start time of the booking is null
	@Test
	public void testCreateBookingStartTimeNull(){
		String bookingId = "Booking1";
		Student student = service.createStudent("Jacob", "123", "Password_123");
		Course course = service.createCourse("ECSE321");
		Tutor tutor = service.createTutor("Hawk", "12345", "Password_123", 0);
		Schedule schedule = service.createSchedule("1255", tutor);
		LocalDate BookingDate = LocalDate.parse("2019-08-03");
		LocalTime StartTime = null;
		LocalTime EndTime = LocalTime.parse("10:00");
		
		String error = null;
	
	try {
		service.createBooking(bookingId, student, course, schedule, BookingDate, StartTime, EndTime);
	} catch(IllegalArgumentException e) {
		error = e.getMessage();
	}
	assertEquals("Booking start time cannot be empty!", error);
	
	}
	
	// Testing that the creation of a booking returns 
	// an error if the end time of the booking is null

	@Test
	public void testCreateBookingEndTimeNull(){
		String bookingId = "Booking1";
		Student student = service.createStudent("Jacob", "123", "Password_123");
		Course course = service.createCourse("ECSE321");
		Tutor tutor = service.createTutor("Hawk", "12345", "Password_123", 0);
		Schedule schedule = service.createSchedule("1255", tutor);
		LocalDate BookingDate = LocalDate.parse("2019-08-03");
		LocalTime StartTime = LocalTime.parse("09:00");
		LocalTime EndTime = null;
		
		String error = null;
	
	try {
		service.createBooking(bookingId, student, course, schedule, BookingDate, StartTime, EndTime);
	} catch(IllegalArgumentException e) {
		error = e.getMessage();
	}
	assertEquals("Booking end time cannot be empty!", error);
	
	}
	
	// Testing that the creation of a booking 
	// returns an error if the booking Id is empty
	@Test
	public void testCreateBookingIdEmpty() {
		String bookingId = "";
		Student student = service.createStudent("Jacob", "123", "Password_123");
		Course course = service.createCourse("ECSE321");
		Tutor tutor = service.createTutor("Hawk", "12345", "Password_123", 0);
		Schedule schedule = service.createSchedule("1255", tutor);
		LocalDate BookingDate = LocalDate.parse("2019-08-03");
		LocalTime StartTime = LocalTime.parse("09:00");
		LocalTime EndTime = LocalTime.parse("10:00");
		
		String error = null;
		
		try {
			service.createBooking(bookingId, student, course, schedule, BookingDate, StartTime, EndTime);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("Booking Id cannot be empty!", error);
	}
	
	// Testing that the creation of a booking returns 
	// an error if the booking Id is made up of space
	@Test
	public void testCreateBookingSpaces() {
		String bookingId = " ";
		Student student = service.createStudent("Jacob", "123", "Password_123");
		Course course = service.createCourse("ECSE321");
		Tutor tutor = service.createTutor("Hawk", "12345", "Password_123", 0);
		Schedule schedule = service.createSchedule("1255", tutor);
		LocalDate BookingDate = LocalDate.parse("2019-08-03");
		LocalTime StartTime = LocalTime.parse("09:00");
		LocalTime EndTime = LocalTime.parse("10:00");
		
		String error = null;
		
		try {
			service.createBooking(bookingId, student, course, schedule, BookingDate, StartTime, EndTime);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("Booking Id cannot be empty!", error);
	}
	
	// Testing that the deletion of a booking actually works when the parameters are correct
/*	@Test
	public void testDeleteBooking() {
		String bookingId = "Booking1";
		Student student = service.createStudent("Jacob", "123");
		Course course = service.createCourse("ECSE321");
		Tutor tutor = service.createTutor("Hawk", "12345", 0);
		Schedule schedule = service.createSchedule("1255", tutor);
		LocalDate BookingDate = LocalDate.parse("2019-08-03");
		LocalTime StartTime = LocalTime.parse("09:00");
		LocalTime EndTime = LocalTime.parse("10:00");
		
		
		try {
			Booking booking= service.createBooking(bookingId, student, course, schedule, BookingDate, StartTime, EndTime);
			System.out.println(booking.getCourse().getCourseId());
			service.deleteBooking(booking.getBookingId());
		} catch(IllegalArgumentException e) {
			fail();
		}
		assertEquals(0, service.getAllBookings().size());
	}*/
	
	@Mock
	private ScheduleRepository scheduleDao;

	private static final String SCHEDULE_ID_KEY = "TestScheduleID";
	private static final Tutor TUTOR_KEY = new Tutor();
	private static final String NONEXISTING_SCHEDULE_KEY = "NoSchedule";

	
	// Setting up mock output for mockito
	@Before
	public void setMockOutputSchedule() {
		when(scheduleDao.findByScheduleId(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(SCHEDULE_ID_KEY) && invocation.getArgument(1).equals(TUTOR_KEY)) {
				Schedule schedule = new Schedule();
				schedule.setScheduleId(SCHEDULE_ID_KEY);
				schedule.setTutor(TUTOR_KEY);
				return schedule;
			} else {
				return null;
			}
		});
	}
	
	// Testing that the creation of a schedule with the correct parameters actually works
	@Test
	public void testCreateSchedule(){
		assertEquals(0, service.getAllSchedules().size());
		String scheduleId = "125";
		Tutor tutor = service.createTutor("Hawk", "123", "Password_123", 5);
		Schedule schedule = new Schedule();
		
		try {
			schedule = service.createSchedule(scheduleId, tutor);
		} catch (IllegalArgumentException e){
			fail();
		}
		
		assertEquals(scheduleId, schedule.getScheduleId());
		assertEquals(tutor, schedule.getTutor());
	}
	
	// Testing that the creation of a schedule with a null Id returns an error
	@Test
	public void testCreateScheduleIdNull() {
		String scheduleId = null;
		Tutor tutor = new Tutor();
		
		String error = null;
		
		try {
			service.createSchedule(scheduleId, tutor);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		
		assertEquals("Schedule Id cannot be empty!", error);
	}
	
	// Testing that the creation of a schedule with an empty Id returns an error 
	@Test
	public void testCreateScheduleIdEmpty() {
		String scheduleId = "";
		Tutor tutor = new Tutor();
		
		String error = null;
		
		try {
			service.createSchedule(scheduleId, tutor);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		
		assertEquals("Schedule Id cannot be empty!", error);
	}
	
	// Testing that the creation of a schedule with an Id 
	// only consisting of a space returns an error
	@Test
	public void testCreateScheduleIdSpaces() {
		String scheduleId = " ";
		Tutor tutor = new Tutor();
		
		String error = null;
		
		try {
			service.createSchedule(scheduleId, tutor);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		
		assertEquals("Schedule Id cannot be empty!", error);
	}
	// Testing that the creation of a schedule with no tutor will return an error
	@Test
	public void testCreateScheduleNoTutor() {
		String scheduleId = "125";
		Tutor tutor = null;
		
		String error = null;
		
		try {
			service.createSchedule(scheduleId, tutor);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		
		assertEquals("Schedule must have a tutor!", error);
	}
	
	// Testing that the deletion of a schedule with the correct parameters actually works
	@Test
	public void testDeleteSchedule(){
		String scheduleId = "125";
		Tutor tutor = new Tutor();
		try {
			service.createSchedule(scheduleId, tutor);
			service.deleteSchedule(scheduleId);
		} catch (IllegalArgumentException e){
			fail();
		}
		assertEquals(0, service.getAllSchedules().size());
	}
	
	@Mock
	private RoomRepository roomDao;
	
	private static final String ROOM_ID_KEY = "TestRoomId"; 
	//private static final RoomSize ROOMSIZE_KEY = RoomSize.valueOf(ROOM_ID_KEY);//WHAT DO WE DO HERE???
	private static final RoomSize ROOM_SIZE_KEY = RoomSize.LARGE;
	private static final String NONEXISTING_ROOM_KEY = "NotARoom";
	
	// Setting up the mock output for mockito
	@Before
	public void setMockOutputRoom() {
		when(roomDao.findByRoomId(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
			if((invocation.getArgument(0).equals(ROOM_ID_KEY))&& (invocation.getArgument(1).equals(ROOM_SIZE_KEY))) {
				Room room = new Room();
				room.setRoomId(ROOM_ID_KEY);
				room.setRoomSize(ROOM_SIZE_KEY);
				return room;
			} else {
				return null;
			}
		});
	}
	
	// Testing that the creation of a room with the correct parameters actually works
	@Test
	public void testCreateRoom() {
		assertEquals(0, service.getAllRooms().size());
		
		String roomId = "Room1";
		RoomSize size = RoomSize.SMALL;
		Room room = new Room();
		
		try {
			room = service.createRoom(roomId, size);
		} catch(IllegalArgumentException e){
			fail();
		}
		assertEquals(roomId, room.getRoomId());
		assertEquals(size, room.getRoomSize());
	}
	
	// Testing that the creation of a room with a null Id returns an error
	@Test
	public void testCreateRoomNull() {
		
		String roomId = null;
		RoomSize size = RoomSize.SMALL;
		Room room = new Room();
		
		String error = null;
		try {
			room = service.createRoom(roomId, size);
		} catch(IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Room Id cannot be empty!", error);
		
	}
	
	// Testing that the creation of a room with an empty Id returns an error
	@Test
	public void testCreateRoomEmpty() {
		String roomId = "";
		RoomSize size = RoomSize.SMALL;
		Room room = new Room();
		
		String error = null;
		try {
			room = service.createRoom(roomId, size);
		} catch(IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Room Id cannot be empty!", error);
		
	}
	
	// Testing that the creation of a room with a space for an Id returns an error
	@Test
	public void testCreateRoomSpaces() {
		String roomId = " ";
		RoomSize size = RoomSize.SMALL;
		Room room = new Room();
		
		String error = null;
		try {
			room = service.createRoom(roomId, size);
		} catch(IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Room Id cannot be empty!", error);
		
	}
	
	// Testing that the creation of a room with a null room size returns an error
	@Test
	public void testCreateRoomNullSize() {
		String roomId = "Room1";
		RoomSize size = null;
		Room room = new Room();
		
		String error = null;
		try {
			room = service.createRoom(roomId, size);
		} catch(IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Room size cannot be empty!", error);
	}
	
	// Testing that the deletion of an error with the correct parameters actually works
	@Test
	public void testDeleteRoom(){
		assertEquals(0, service.getAllRooms().size());
		String roomId = "1";
		RoomSize size = RoomSize.SMALL;
		
		try {
			service.createRoom(roomId, size);
			service.deleteRoom(roomId);
		} catch(IllegalArgumentException e) {
			fail();
		}
		assertEquals(0, service.getAllRooms().size());

	}
}

	