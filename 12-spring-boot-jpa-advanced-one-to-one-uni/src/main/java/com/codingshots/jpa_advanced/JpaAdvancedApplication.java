package com.codingshots.jpa_advanced;

import com.codingshots.jpa_advanced.dao.AppDAO;
import com.codingshots.jpa_advanced.entity.Instructor;
import com.codingshots.jpa_advanced.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
//			createInstructor(appDAO);

//			findInstructor(appDAO);

			deleteInstructor(appDAO);
		};
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
