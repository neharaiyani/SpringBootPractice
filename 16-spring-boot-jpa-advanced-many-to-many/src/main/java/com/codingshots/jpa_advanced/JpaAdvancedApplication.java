package com.codingshots.jpa_advanced;

import com.codingshots.jpa_advanced.dao.AppDAO;
import com.codingshots.jpa_advanced.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class JpaAdvancedApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaAdvancedApplication.class, args);
	}

	// CommandLineRunner from Spring Boot (executed after the Spring Beans have loaded)
	// Due to @Bean, Spring automatically injects AppDAO, no need for @Autowired
	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		// Java Lambda Expression
		return runner -> {
			/* NEW: course + student */

			// create course + student
//			createCourseAndStudents(appDAO);
			
			// retrieve course and students
//			findCourseAndStudents(appDAO);

			// retrieve student and courses
//			findStudentAndCourses(appDAO);

			// update students (add more courses)
//			addMoreCoursesForStudent(appDAO);

			// delete course -> should delete specific course <-> student association from join table (We already have this method)
//			deleteCourse(appDAO);

			// delete student -> should delete specific course <-> student association from join table
			deleteStudent(appDAO);
		};
	}

	private void deleteStudent(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Deleting Student Id: " + theId);

		appDAO.deleteStudentById(theId);

		System.out.println("Done!");
	}

	private void addMoreCoursesForStudent(AppDAO appDAO) {
		int theId = 2;
		Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);

		// create more courses
		Course tempCourse1 = new Course("Rubik's Cube - How To Speed Cube");
		Course tempCourse2 = new Course("Atari 2000 - Game Development");

		// add courses to student
		tempStudent.addCourse(tempCourse1);
		tempStudent.addCourse(tempCourse2);

		System.out.println("Updating Student: " + tempStudent);
		System.out.println("Associated Courses: " + tempStudent.getCourses());

		// this will also ADD New Courses (due to "Cascade ALL")
		appDAO.updateStudent(tempStudent);

		System.out.println("Done!");
	}

	private void findStudentAndCourses(AppDAO appDAO) {
		int theId = 1;
		Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);

		System.out.println("Loaded Student: " + tempStudent);
		System.out.println("Associated Courses: " + tempStudent.getCourses());

		System.out.println("Done!");
	}

	private void findCourseAndStudents(AppDAO appDAO) {
		int theId = 10;
		Course tempCourse = appDAO.findCourseAndStudentsByCourseId(theId);

		System.out.println("Loaded Course: " + tempCourse);
		System.out.println("Associated Students: " + tempCourse.getStudents());

		System.out.println("Done!");
	}

	private void createCourseAndStudents(AppDAO appDAO) {
		// create a course
		Course tempCourse = new Course("Pacman - How To Score One Million Points");

		// create the students
		Student tempStudent1 = new Student("John", "Doe", "john@codingshots.com");
		Student tempStudent2 = new Student("Mary", "Public", "mary@codingshots.com");

		// add students to the course
		tempCourse.addStudent(tempStudent1);
		tempCourse.addStudent(tempStudent2);

		// save the course and the associated students
		System.out.println("Saving The Course: " + tempCourse);
		System.out.println("Associated Students: " + tempCourse.getStudents());

		// ALSO, cascade Save for Students
		appDAO.saveCourse(tempCourse);

		System.out.println("Done!");
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int theId = 10;

		System.out.println("Deleting Course Id: " + theId);

		// delete the course AND reviews (due to "Cascade ALL")
		appDAO.deleteCourseById(theId);

		System.out.println("Done!");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {
		int theId = 10;

		// get the course and reviews
		Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);

		// print the course
		System.out.println(tempCourse);

		// print the reviews
		System.out.println(tempCourse.getReviews());

		System.out.println("Done!");
	}

	private void createCourseAndReviews(AppDAO appDAO) {
		// create a course
		Course tempCourse = new Course("Pacman - How To Score One Million Points");

		// add some reviews
		tempCourse.addReview(new Review("Great course ... loved it!!!"));
		tempCourse.addReview(new Review("Cool course, job well done."));
		tempCourse.addReview(new Review("What a dumb course, you are an idiot."));

		// save the course ... and leverage the "Cascade ALL"
		System.out.println("Saving The Course:");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());

		appDAO.saveCourse(tempCourse);

		System.out.println("Done!");

	}

	private void deleteCourse(AppDAO appDAO) {
		int theId = 10;

		System.out.println("Deleting Course Id: " + theId);

		appDAO.deleteCourseById(theId);

		System.out.println("Done!");
	}

	private void updateCourse(AppDAO appDAO) {
		int theId = 10;

		// find the course
		System.out.println("Finding Course Id: " + theId);
		Course tempCourse = appDAO.findCourseById(theId);

		// update the course
		System.out.println("Updating Course Id: " + theId);
		tempCourse.setTitle("Enjoy The Simple Things");

		appDAO.updateCourse(tempCourse);

		System.out.println("Done!");
	}

	private void updateInstructor(AppDAO appDAO) {
		int theId = 1;

		// find the instructor
		System.out.println("Finding Instructor Id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorByID(theId);

		// update the instructor
		System.out.println("Updating Instructor Id: " + theId);
		tempInstructor.setLastName("TESTER");

		appDAO.updateInstructor(tempInstructor);

		System.out.println("Done!");
	}

	// 3. JOIN FETCH: get Instructor AND Courses in a Single Query, ALSO keep the LAZY option available
	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int theId = 1;

		// find the instructor
		System.out.println("Finding Instructor Id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorByJoinFetch(theId);

		System.out.println("Instructor: " + tempInstructor);
		System.out.println("The Associated Courses: " + tempInstructor.getCourses());

		System.out.println("Done!");
	}

	// 2. Another Solution: add another method to find courses later in Repository (REQUIRES AN EXTRA QUERY)
	private void findCoursesForInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding Instructor Id: " + theId);

		// Only loads the Instructor, doesn't load the courses since they are "Lazy Loaded".
		Instructor tempInstructor = appDAO.findInstructorByID(theId);

		System.out.println("The Instructor: " + tempInstructor);

		// find courses for instructor
		System.out.println("Finding Courses for Instructor Id: " + theId);
		List<Course> courses = appDAO.findCoursesByInstructorId(theId);

		/**
		 * As of now, these 2 objects, tempInstructor and courses are separate entities.
		 * These are not associated with each other.
		 * Associate the objects. Plug them together.
		 */
		tempInstructor.setCourses(courses);

		System.out.println("The Associated Courses: " + tempInstructor.getCourses());

		System.out.println("Done!");
	}

	// 1. QUICK SOLUTION: change FetchType to "Eager" in Instructor class
	private void findInstructorWithCourses(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding Instructor Id: " + theId);

		// Only loads the Instructor, doesn't load the courses since they are "Lazy Loaded" by default -> changed to Eager.
		Instructor tempInstructor = appDAO.findInstructorByID(theId);

		System.out.println("The Instructor: " + tempInstructor);

		// This will throw an "Exception" as FetchType for @OntToMany defaults to "Lazy"
		// Session Closed before we could access the courses
		// 1. QUICK SOLUTION: change FetchType to "Eager" in Instructor class
		// 2. Another Solution: add another method to find courses later in Repository (REQUIRES AN EXTRA QUERY)
		// 3. JOIN FETCH: get Instructor AND Courses in a Single Query, ALSO keep the LAZY option available
		System.out.println("The Associated Courses: " + tempInstructor.getCourses());

		System.out.println("Done!");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {

		/****************** Instructor Tables ******************/

		// create the Instructor
		Instructor tempInstructor =
				new Instructor("Susan", "Public", "susan.public@codingshots.com");

		// create the InstructorDetail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail("https://www.youtube.com/gamingshots", "Video Games");

		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		/****************** Course Table ******************/

		// create some courses
		Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
		Course tempCourse2 = new Course("The Pinball Masterclass");

		// add these courses to the instructor (and -> associate the objects, inside add() method)
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		/****************** Database Work (above things were just In Memory) ******************/

		/**
		 * save the instructor (in database)
		 *
		 * NOTE: This will ALSO save the "Courses" bcz of "CascadeType.PERSIST"
		 */
		System.out.println("Saving Instructor: " + tempInstructor);
		System.out.println("The Courses: " + tempInstructor.getCourses());

		appDAO.save(tempInstructor);

		System.out.println("Done!");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int theId = 3;
		System.out.println("Deleting Instructor Detail Id: " + theId);

		appDAO.deleteInstructorDetailById(theId);

		System.out.println("Done!");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		// bidirectional

		// get the instructor detail object
		int theId = 2;
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);

		// print instructor detail
		System.out.println("Temp Instructor Detail: " + tempInstructorDetail);

		// print the associated instructor
		System.out.println("The Associated Instructor: " + tempInstructorDetail.getInstructor());

		System.out.println("Done!");
	}

	private void deleteInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Deleting Instructor Id: " + theId);

		appDAO.deleteInstructorById(theId);

		System.out.println("Done!");
	}

	private void findInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding Instructor Id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorByID(theId);

		System.out.println("Temp Instructor: " + tempInstructor);
		System.out.println("The Associated Instructor Details Only: " + tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {

		/*
		// create the Instructor
		Instructor tempInstructor =
				new Instructor("John", "Doe", "john@codingshots.com");

		// create the InstructorDetail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail("https://www.codingshots.com/youtube", "Coding");

		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);
		*/

		// create the Instructor
		Instructor tempInstructor =
				new Instructor("Mary", "Smith", "mary@codingshots.com");

		// create the InstructorDetail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail("https://www.musicshots.com/youtube", "Guitar");

		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		/** save the instructor
		 *
		 * NOTE: This will also save the details object
		 */
		System.out.println("Saving Instructor: " + tempInstructor);
		appDAO.save(tempInstructor);
	}

}
