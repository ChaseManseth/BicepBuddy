package BicepTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.After;
import org.junit.jupiter.api.Test;

import User.User;
import User.UserController;
import Views.ProfileView;
import bicepBuddyPackage.Master;

// TODO: Auto-generated Javadoc
/**
 * The Class UserTests.
 * @authors: Zachary Steudel, Hunter Long, Chase Manseth, Bob Rein, Reece Kemball-Cook
 */
public class UserTests {
	
	/**
	 * Tear down.
	 */
	@After
	public void tearDown() {
		UserController.setUser(null);
	}

	/**
	 * Test login correct.
	 */
	@Test
	public void testLoginCorrect() {
		UserController uc = new UserController();
		uc.validateLogin("chasemanseth@gmail.com", "password");
		User u = UserController.getUser();
		//assert that we received the correct user from the DB
		//Coverage plan: User: a., b.
		assertNotNull(UserController.getUser());
		assertEquals(u.getEmail(), "chasemanseth@gmail.com");
		UserController.setUser(null);
	}
	
	/**
	 * Test login fails.
	 */
	@Test
	public void testLoginFails() {
		UserController uc = new UserController();
		uc.validateLogin("chasemans", "wowee");
		
		assertNull(UserController.getUser());
	}
	
	/**
	 * Test new user and delete.
	 */
	@Test
	public void testNewUserAndDelete() {
		UserController uc = new UserController();
		uc.createUser("friendo", "bendo", "wowzaMan@gmail.com", "1", "1", "N/A", "N/A", 
				"N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "123");
		
		assertNotNull(UserController.getUser());
		assertEquals(UserController.getUser().getEmail(), "wowzaMan@gmail.com");
		
		//deletion
		uc.deleteAccount(UserController.getUser());
		assertNull(UserController.getUser());
	}
	
	/**
	 * Change settings.
	 */
	@Test
	public void changeSettings() {
		UserController uc = new UserController();
		uc.validateLogin("chasemanseth@gmail.com", "password");
		User u = UserController.getUser();
		
		String curPhone = u.getPhone();
		String curAge = u.getAge();
		
		uc.editUser("chasemanseth@gmail.com", u.getfName(), u.getlName(), u.getStyle(), 
				u.getTimeOfDay(), u.getGender(), u.getPrefGender(), u.getFrequency(), 
				u.getGoals(), u.getWeight(), u.getExperience(), "222", "111");
		
		assertNotEquals(curPhone, u.getPhone());
		assertNotEquals(curAge, u.getAge());
		
		//change it back
		uc.editUser("chasemanseth@gmail.com", u.getfName(), u.getlName(), u.getStyle(), 
				u.getTimeOfDay(), u.getGender(), u.getPrefGender(), u.getFrequency(), 
				u.getGoals(), u.getWeight(), u.getExperience(), curAge, curPhone);
		
		assertEquals(curPhone, u.getPhone());
		assertEquals(curAge, u.getAge());
	}
	
	@Test
	public void getsAllUsersByGender() {
		List<User> users = UserController.getInstance().getUsersByGender("Male");
		
		assertTrue(!users.isEmpty());
	}
	
	@Test
	public void genderIsCorrect() {
		List<User> users = UserController.getInstance().getUsersByGender("Male");
		
		for(int i = 0; i < users.size(); i++) {
			assertTrue(users.get(i).getGender().contentEquals("Male"));
		}
	}
	
	@Test
	public void getsCorrectUserByID() {
		User u = UserController.getInstance().getUserById("5cc7c4c38554b0748e750726");
		
		assertTrue(u.getId().contentEquals("5cc7c4c38554b0748e750726"));
		assertTrue(u.getEmail().contentEquals("chasemanseth@gmail.com"));
	}
	
	@Test
	public void onlyGetUserByIdDoesntGetMatches() {
		User u = UserController.getInstance().onlyGetUserById("5cc7c4c38554b0748e750726");
		
		assertTrue(u.getAccepted().isEmpty());
		assertTrue(u.getWaiting().isEmpty());
		
		assertTrue(u.getEmail().contentEquals("chasemanseth@gmail.com"));
	}

	@Test
	public void matchActuallyPopulates() {
		UserController.getInstance().validateLogin("zacharysteudel@gmail.com", "password");
						
		assertTrue(!UserController.getUser().getAccepted().isEmpty());
		UserController.setUser(null);
	}
}
