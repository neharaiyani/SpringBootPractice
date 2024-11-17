package com.codingshots.aopDemo;

import com.codingshots.aopDemo.dao.AccountDAO;
import com.codingshots.aopDemo.dao.MembershipDAO;
import com.codingshots.aopDemo.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopDemoApplication.class, args);
	}

	// Spring Boot will automatically inject the dependency: AccountDAO ...  bcz of @Bean annotation, no @Autowired needed
	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO theAccountDAO,
											   MembershipDAO theMembershipDAO,
											   TrafficFortuneService theTrafficFortuneService){
		return runner -> {
//			demoTheBeforeAdvice(theAccountDAO, theMembershipDAO);

//			demoTheAfterReturningAdvice(theAccountDAO);
			
//			demoTheAfterThrowingAdvice(theAccountDAO);

//			demoTheAfterAdvice(theAccountDAO);

//			demoTheAroundAdvice(theTrafficFortuneService);

//			demoTheAroundAdviceHandleException(theTrafficFortuneService);

			demoTheAroundAdviceRethrowException(theTrafficFortuneService);
		};
	}

	private void demoTheAroundAdviceRethrowException(TrafficFortuneService theTrafficFortuneService) {
		System.out.println("\nMain Program: demoTheAroundAdviceRethrowException");

		System.out.println("Calling getFortune()");

		// add a boolean flag to simulate exceptions
		boolean tripWire = true;
		String data = theTrafficFortuneService.getFortune(tripWire);

		System.out.println("\nMy Fortune: " + data);

		System.out.println("Finished.");
	}

	private void demoTheAroundAdviceHandleException(TrafficFortuneService theTrafficFortuneService) {
		System.out.println("\nMain Program: demoTheAroundAdviceHandleException");

		System.out.println("Calling getFortune()");

		// add a boolean flag to simulate exceptions
		boolean tripWire = true;
		String data = theTrafficFortuneService.getFortune(tripWire);

		System.out.println("\nMy Fortune: " + data);

		System.out.println("Finished.");
	}

	private void demoTheAroundAdvice(TrafficFortuneService theTrafficFortuneService) {
		System.out.println("\nMain Program: demoTheAroundAdvice");

		System.out.println("Calling getFortune()");

		String data = theTrafficFortuneService.getFortune();

		System.out.println("\nMy Fortune: " + data);

		System.out.println("Finished.");
	}

	private void demoTheAfterAdvice(AccountDAO theAccountDAO) {
		// call the method to find accounts
		List<Account> theAccounts = null;

		try {
			// add a boolean flag to simulate exceptions
			boolean tripWire = false;

			theAccounts = theAccountDAO.findAccounts(tripWire);
		} catch (Exception exc){
			System.out.println("\nMain Program: Caught Exception: " + exc);
		}

		// display the accounts
		System.out.println("\nMain Program: demoTheAfterThrowingAdvice");
		System.out.println("----");

		System.out.println(theAccounts);
		System.out.println("\n");
	}

	private void demoTheAfterThrowingAdvice(AccountDAO theAccountDAO) {
		// call the method to find accounts
		List<Account> theAccounts = null;

		try {
			// add a boolean flag to simulate exceptions
			boolean tripWire = true;

			theAccounts = theAccountDAO.findAccounts(tripWire);
		} catch (Exception exc){
			System.out.println("\nMain Program: Caught Exception: " + exc);
		}

		// display the accounts
		System.out.println("\nMain Program: demoTheAfterThrowingAdvice");
		System.out.println("----");

		System.out.println(theAccounts);
		System.out.println("\n");
	}

	private void demoTheAfterReturningAdvice(AccountDAO theAccountDAO) {
		// call the method to find accounts
		List<Account> theAccounts = theAccountDAO.findAccounts();

		// display the accounts
		System.out.println("\nMain Program: demoTheAfterReturningAdvice");
		System.out.println("----");

		System.out.println(theAccounts);
		System.out.println("\n");
	}

	private void demoTheBeforeAdvice(AccountDAO theAccountDAO, MembershipDAO thMembershipDAO) {
		// call the business method
		theAccountDAO.addAccount();

		// create new Account object
		Account myAccount = new Account();
		myAccount.setName("Savings");
		myAccount.setLevel("Silver");

//		theAccountDAO.addAccount(myAccount);
		theAccountDAO.addAccount(myAccount, false);

//		theAccountDAO.doWork();

		// call the theAccountDAO's getter/setter methods
		System.out.println("----------------------------- Getter / Setter -----------------------------");
		theAccountDAO.setName("foobar");
		theAccountDAO.setServiceCode("silver");

		String name = theAccountDAO.getName();
		String service = theAccountDAO.getServiceCode();

		// call the membership business method
		System.out.println("---------------------------------------------------------------------------");
		thMembershipDAO.addAccount();
//		thMembershipDAO.addSillyMember();
		thMembershipDAO.goToSleep();
	}

}
