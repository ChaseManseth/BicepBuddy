package BicepTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.jupiter.api.Test;

import User.User;
import User.UserController;

class UserTests {

	@Test
	void testLoginCorrect() {
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
	void testLoginFails() {
		UserController uc = new UserController();
		uc.validateLogin("chasemans", "wowee");
		
		assertNull(UserController.getUser());
	}
	
	@After //teardown
	public void after() {
		UserController.setUser(null);
	}

}
