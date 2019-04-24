package BicepTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.jupiter.api.Test;

import User.User;
import User.UserController;
import Views.ProfileView;
import bicepBuddyPackage.Master;

public class UserTests {
	
	@After
	public void tearDown() {
		UserController.setUser(null);
	}

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
	
	@Test
	public void testLoginFails() {
		UserController uc = new UserController();
		uc.validateLogin("chasemans", "wowee");
		
		assertNull(UserController.getUser());
	}
	
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

}
