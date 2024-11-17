package com.codingshots.aopDemo;

import com.codingshots.aopDemo.dao.AccountDAO;
import com.codingshots.aopDemo.dao.MembershipDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AopDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopDemoApplication.class, args);
	}

	// Spring Boot will automatically inject the dependency: AccountDAO ...  bcz of @Bean annotation, no @Autowired needed
	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO){
		return runner -> {
			demoTheBeforeAdvice(theAccountDAO, theMembershipDAO);
		};
	}

	private void demoTheBeforeAdvice(AccountDAO theAccountDAO, MembershipDAO thMembershipDAO) {
		// call the business method
		theAccountDAO.addAccount();

		// create new Account object
		Account myAccount = new Account();
		myAccount.setName("Savings");
		myAccount.setLevel("basic");

		theAccountDAO.addAccount(myAccount);
		theAccountDAO.addAccount(myAccount, false);

		theAccountDAO.doWork();

		// call the membership business method
		thMembershipDAO.addAccount();
		thMembershipDAO.addSillyMember();
		thMembershipDAO.goToSleep();
	}

}
