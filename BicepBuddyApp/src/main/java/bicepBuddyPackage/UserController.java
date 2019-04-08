package bicepBuddyPackage;

/*
 * User Controller will be able to speak with user and the "DB".
 */

public class UserController {
	private boolean loggedIn;

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	public boolean validateSignup(String email, String pass, String confPass, String phone, String age) {
		return true;
	}
	
	public boolean validateLogin() {
		return true;
	}
	
}
