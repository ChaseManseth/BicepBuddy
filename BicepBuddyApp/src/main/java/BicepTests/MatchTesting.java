package BicepTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Matching.Match;
import Matching.Match.Status;
import Matching.MatchAlgorithm;
import Matching.MatchController;
import User.User;
import User.UserController;

// TODO: Auto-generated Javadoc
/**
 * The Class MatchTesting.
 * @authors: Zachary Steudel, Hunter Long, Chase Manseth, Bob Rein, Reece Kemball-Cook
 */
public class MatchTesting {
	
	/** The user 1. */
	static User user1 = new User("Mark","Zucc","markzucc@gmail.com","123-456-7890","25","Male","Male","Get Swole","Multiple Times","Early Morning","Cardio","150-175","Regular");
	
	/** The user 2. */
	static User user2 = new User("Other","Dude","otherdude@gmail.com","098-765-4321","26","Male","Male","Stay Healthy","Every Day","Morning","Cardio","125-150","Moderate");
	
	/** The user 3. */
	static User user3 = new User("Lady","Dudette","ladydudette@gmail.com","111-111-1111","32","Male","Male","Stay Healthy","Every Day","Morning","Cardio","125-150","Moderate");
	
	/** The users. */
	static List<User> users;
	
	static Match m1;
	static Match m2;
	
	static List<Match> matchesGenerated;
	static List<Match> matchesGenerated2;
	static Match matchDirect;
	
	/**
	 * Inits the.
	 */
	@BeforeAll
	public static void init() {
		user1 = UserController.getInstance().getUserById("5cc8f596caacc31bf511765c");
		user2 = UserController.getInstance().getUserById("5cc8f597caacc31bf511765d");
		user3 = UserController.getInstance().getUserById("5cc8f598caacc31bf511765e");
		/*UserController.getInstance().createUser("Mark","Zucc","markzucc@gmail.com","123-456-7890","25","Male","Male","Get Swole","Multiple Times","Early Morning","Cardio","150-175","Regular"," ");
		UserController.getInstance().createUser("Other","Dude","otherdude@gmail.com","098-765-4321","26","Male","Male","Stay Healthy","Every Day","Morning","Cardio","125-150","Moderate"," ");
		UserController.getInstance().createUser("Lady","Dudette","ladydudette@gmail.com","111-111-1111","32","Male","Male","Stay Healthy","Every Day","Morning","Cardio","125-150","Moderate"," ");*/
		UserController.setUser(user1);
		m1 = MatchController.directMatch(user2);
		m2 = MatchController.directMatch(user3);
		matchesGenerated = MatchAlgorithm.matchUser();
		matchesGenerated2 = MatchAlgorithm.matchUser();
		matchDirect = MatchAlgorithm.directMatch(user2);
	}
	
	/*
	 * Match object testing
	 */
	@Test @DisplayName("Test match .equals() true")
	public void matchEqualsTest() {
		assertTrue(m1.equals(m1));
	}
	@Test @DisplayName("Test match .equals() false")
	public void matchEqualsTestFalse() {
		assertFalse(m1.equals(m2));
	}
	@Test @DisplayName("Test match getOther")
	public void testGetOther() {
		assertTrue(user2.getId().equals(m1.getOther()));
	}
	
	/*
	 * Match Algorithm testing
	 */
	@Test @DisplayName("Testing calculate Ratio return")
	public void calculateRatioReturnValue() {
		assertEquals(MatchAlgorithm.calculateRatio(3,1,2),new Integer(66));
	}
	@Test @DisplayName("Testing calculate Ratios")
	public void calculateRatiosTest() {
		assertEquals(MatchAlgorithm.calculateRatios(user1),new Integer(100));
	}
	@Test @DisplayName("Testing matchUser")
	public void matchUserTest() {
		assertTrue(matchesGenerated.size() != 0);
	}
	@Test @DisplayName("Testing matchUser with equals")
	public void matchUserTest2() {
		assertTrue(matchesGenerated.equals(matchesGenerated2));
	}
	@Test @DisplayName("Testing directMatch in database")
	public void directMatchTestDB() {
		Match tempDirect = MatchController.getMatchById(matchDirect.getId());
		assertTrue(matchDirect.equals(tempDirect));
	}
	@Test @DisplayName("Testing directMatch creation")
	public void directMatchTestCreate() {
		UserController.setUser(user2);
		Match match2 = MatchAlgorithm.directMatch(user1);
		UserController.setUser(user1);
		assertTrue(matchDirect.equals(match2));
	}
	
	/*
	 * MatchController Testing
	 */
	@Test @DisplayName("Testing directMatch in database")
	public void generateMatchesTest() {
		MatchController.generateMatches();
		assertTrue(!UserController.getUser().getIdle().isEmpty());
	}
	@Test @DisplayName("Testing findMatch")
	public void findMatchTest() {
		Match tempMatch = new Match(UserController.getUser(),user2);
		List<Match> matches = UserController.getUser().getIdle();
		matches.add(tempMatch);
		UserController.getUser().setIdle(matches);
		assertTrue(MatchController.findMatch(tempMatch) != null);
	}
	@Test @DisplayName("Testing getMatchById")
	public void getMatchById() {
		Match tempDirect = MatchController.getMatchById(matchDirect.getId());
		assertTrue(matchDirect.equals(tempDirect));
	}
	@Test @DisplayName("Testing createMatch")
	public void createMatchTest() {
		Match m = new Match(user3,user2);
		m.setId(MatchController.createMatch(m));
		assertEquals(m, MatchController.getMatchById(m.getId()));
	}
	@Test @DisplayName("Testing updateMatch")
	public void updateMatchTest() {
		Match m = MatchController.getMatchById(m1.getId());
		m.setStrength(6);
		MatchController.updateMatch(m);
		assertTrue(m.getStrength() == 6);
	}
	@Test @DisplayName("Testing acceptMatchInitial")
	public void initialAcceptTest() {
		MatchController.acceptMatchInitial(m2);
		assertTrue(UserController.getUser().getAccepted().contains(m2));
	}
	@Test @DisplayName("Testing acceptMatchOther")
	public void otherAcceptTest() {
		MatchController.acceptMatchInitial(m1);
		UserController.setUser(user2);
		MatchController.acceptMatchOther(m1);
		UserController.setUser(user1);
		assertTrue(UserController.getUser().getAccepted().contains(m1) && m1.getStatus() == Status.Accepted);
	}
	@Test @DisplayName("Testing rejectMatch")
	public void rejectTest() {
		MatchController.rejectMatch(m2);
		user3 = UserController.getInstance().getUserById(user3.getId());
		assertTrue(UserController.getUser().getRejected().contains(m2) && m2.getStatus() == Status.Rejected);
	}
	
	/**
	 * Tear down.
	 */
	@AfterAll
	public static void tearDown() {

	}
}
