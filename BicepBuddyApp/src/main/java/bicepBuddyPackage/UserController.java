package bicepBuddyPackage;

/*
 * User Controller will be able to speak with user and the "DB".
 */

public class UserController {
	private boolean loggedIn;
	private User curUser;

	public User getCurUser() {
		return curUser;
	}

	public void setCurUser(User curUser) {
		this.curUser = curUser;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	public boolean validateSignup(String email, String pass, String confPass, String phone, String age) {
		UserDB dbInstance = new UserDB();
		if(!dbInstance.notExists(email)) {
			ErrorGUI eg = new ErrorGUI("This email exists in the system already.");
			return false;
		}
		
		if(!pass.contentEquals(confPass)) {
			ErrorGUI eg = new ErrorGUI("Make the passwords match to create account.");
			return false;
		}
		
		try {
			Integer.parseInt(phone);
			Integer.parseInt(age);
		}
		catch(NumberFormatException e) {
			ErrorGUI eg = new ErrorGUI("Phone and/or Age must be integer values ONLY.");
			return false;
		}
		
		Master.appLogger.info(":: User's signup information was accepted.");
		return true;
	}
	
	//TO-DO
	public boolean validateLogin() {
		return true;
	}
	
	public void createUser(String fName, String lName, String email, int phone, int age, String gender, String prefGender,
			String goals, String frequency, String timeOfDay, String style, String weight, String experience,
			String password) {
		//name and email fields must have been filled, but if other fields are empty,
		//then set them to N/A.
		
		if(gender.isEmpty()) {
			gender = "N/A";
		}
		if(prefGender.isEmpty()) {
			prefGender = "N/A";
		}
		if(goals.isEmpty()) {
			goals = "N/A";
		}
		if(frequency.isEmpty()) {
			frequency = "N/A";
		}
		if(timeOfDay.isEmpty()) {
			timeOfDay = "N/A";
		}
		if(style.isEmpty()) {
			style = "N/A";
		}
		if(weight.isEmpty()) {
			weight = "N/A";
		}
		if(experience.isEmpty()) {
			experience = "N/A";
		}
		
		this.curUser = new User(fName, lName, email, phone, age, gender, prefGender, goals,
								frequency, timeOfDay, style, weight, experience);
		
		UserDB udb = new UserDB();
		udb.addUser(curUser, password);
	}
	
}
