package BicepTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Matching.MatchAlgorithm;
import User.User;

// TODO: Auto-generated Javadoc
/**
 * The Class MatchTesting.
 * @authors: Zachary Steudel, Hunter Long, Chase Manseth, Bob Rein, Reece Kemball-Cook
 */
public class MatchTesting {
	
	/** The user 1. */
	User user1 = new User("Mark","Zucc","markzucc@gmail.com","123-456-7890","25","Male","Male","Get Swole","Multiple Times","Early Morning","Cardio","150-175","Regular");
	
	/** The user 2. */
	User user2 = new User("Other","Dude","otherdude@gmail.com","098-765-4321","26","Male","Male","Stay Healthy","Every Day","Morning","Cardio","125-150","Moderate");
	
	/** The user 3. */
	User user3 = new User("Lady","Dudette","ladydudette@gmail.com","111-111-1111","32","Male","Male","Stay Healthy","Every Day","Morning","Cardio","125-150","Moderate");
	
	/** The user 4. */
	User user4 = new User("Lady","Dudette2","ladydudette2@gmail.com","222-222-2222","35","Male","Female","Stay Healthy","Every Day","Morning","Cardio","125-150","Moderate");
	
	/** The users. */
	List<User> users;
	
	/**
	 * Inits the.
	 */
	@BeforeEach
	public void init() {
		users = new ArrayList<>();
		users.add(user2);
		users.add(user3);
		users.add(user4);
	}
	
	/**
	 * Calculate ratio return value.
	 */
	@Test @DisplayName("Testing calculate Ratio return")
	public void calculateRatioReturnValue() {
		assertEquals(MatchAlgorithm.calculateRatio(3,1,2),new Integer(66));
	}
	
	/**
	 * Calculate ratios test.
	 */
	@Test @DisplayName("Testing calculate Ratios")
	public void calculateRatiosTest() {
		/*Integer test = MatchAlgorithm.calculateRatios(user1,user2);
		assertEquals(MatchAlgorithm.calculateRatios(user1,user2),new Integer(0));*/
	}
	
	/**
	 * Match user test.
	 */
	@Test @DisplayName("Testing matchUser")
	public void matchUserTest() {
		/*Match match1 = new Match(user1,user2);
		Match match2 = new Match(user1,user2);
		assertTrue();*/
	}
	
	/**
	 * Tear down.
	 */
	@AfterEach
	public void tearDown() {
		
	}
}
