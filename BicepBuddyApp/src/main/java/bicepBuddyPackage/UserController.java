package bicepBuddyPackage;

import java.awt.FlowLayout;
import java.nio.charset.StandardCharsets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/*
 * User Controller will be able to speak with user and the "DB".
 */

public class UserController {
	private boolean loggedIn;
	private static User user = null;
	private String baseUrl = "http://localhost:3000/user/";

	public static User getUser() {
		return user;
	}

	public static void setUser(User curUser) {
		user = curUser;
	}
	
	//TODO
	public static void setUserFromDB(String[] uDetails) {
		user = new User();
		Master.appLogger.info(":: Setting the static user object.");
		
		user.setEmail(uDetails[0]);
		user.setfName(uDetails[2]);
		user.setlName(uDetails[3]);
		user.setStyle(uDetails[4]);
		user.setTimeOfDay(uDetails[5]);
		user.setGender(uDetails[6]);
		user.setPrefGender(uDetails[7]);
		user.setFrequency(uDetails[8]);;
		user.setWeight(uDetails[9]);
		user.setPhone(uDetails[10]);
		user.setAge(uDetails[11]);
		user.setGoals(uDetails[12]);
		user.setExperience(uDetails[13]);
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	
	
	//TODO
	public void validateSignup(String fName, String lname, String email, String phone, String age, 
			String gender, String prefGender, String goals, String freq, 
			String timeOfDay, String style, String weight, String exp, String pass,
			String confPass) {
		if(fName.isEmpty() || lname.isEmpty() || email.isEmpty() || pass.isEmpty() ||
		   confPass.isEmpty()) {
			ErrorGUI eg = new ErrorGUI("Required fields: first name, last name, email, password");
			return;
		}
		
		UserDB dbInstance = new UserDB();
		if(!dbInstance.notExists(email)) {
			ErrorGUI eg = new ErrorGUI("This email exists in the system already.");
			return;
		}
		
		if(!pass.contentEquals(confPass)) {
			ErrorGUI eg = new ErrorGUI("Make the passwords match to create account.");
			return;
		}
		
		try {
			if(!phone.isEmpty()) {
				Long.parseLong(phone);
			}
			if(!age.isEmpty()) {
				Integer.parseInt(age);
			}
		}
		catch(NumberFormatException e) {
			ErrorGUI eg = new ErrorGUI("Phone and/or Age must be integer values ONLY.");
			return;
		}
		//we got here because their stuff was accepted
		Master.appLogger.info(":: User's signup information was accepted.");
		
		createUser(fName, lname, email, phone, age, gender, prefGender, goals,
				   freq, timeOfDay, style, weight, exp, pass);
		
	}
	
	//TODO
	public void validateLogin(String email, String pass) {
		UserDB udb = new UserDB();
		if(udb.login(email, pass)) {
			Master.appLogger.info(":: User was logged in.");
			Master.getInstance().loggedInMenuLoad();
			Master.getInstance().updateFrame(new ProfileView());
		}
	}
	
	@SuppressWarnings("unchecked")
	public void createUser(String fName, String lName, String email, String phone, String age, 
			String gender, String prefGender, String goals, String frequency, 
			String timeOfDay, String style, String weight, String experience, String password) {
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
		if(phone.isEmpty()) {
			phone = "N/A";
		}
		if(age.isEmpty()) {
			age = "N/A";
		}
		
		// Create the JSON to post to the API
		JSONObject signUpJSON = new JSONObject();
		signUpJSON.put("firstname", fName);
		signUpJSON.put("lastname", lName);
		signUpJSON.put("email", email);
		signUpJSON.put("password", password);
		signUpJSON.put("phoneNumber", phone);
		signUpJSON.put("age", age);
		signUpJSON.put("gender", gender);
		signUpJSON.put("preferredGender", prefGender);
		signUpJSON.put("goals", goals);
		signUpJSON.put("frequency", frequency);
		signUpJSON.put("timeOfDay", timeOfDay);
		signUpJSON.put("workoutStyle", style);
		signUpJSON.put("weight", weight);
		signUpJSON.put("experience", experience);
		
		// Open the post response
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(baseUrl + "signup");
		
		try {
			// Add JSON to the body and headers indicating type
			StringEntity params = new StringEntity(signUpJSON.toJSONString());
		    request.addHeader("content-type", "application/json");
		    request.setEntity(params);
			
		    // Execute the request
		    HttpResponse response = httpClient.execute(request);
		    
		    // Get the body of the response
		    HttpEntity entity = response.getEntity();
		    String json = EntityUtils.toString(entity, StandardCharsets.UTF_8);
		    
		    JSONParser parse = new JSONParser();
		    JSONObject o = (JSONObject) parse.parse(json);
		    
//		    System.out.println(o.toJSONString());
		    
		    // Successful Signup
		    if(response.getStatusLine().getStatusCode() == 201) {
		    	// Add the user Object Info
		    	user = new User(fName, lName, email, phone, age, gender, prefGender, goals,
						frequency, timeOfDay, style, weight, experience);

		    	// Add it to the DB
				UserDB udb = new UserDB();
				udb.addUser(user, password);
				
				// Add the ID to the new User as well as the JWT
				JSONObject userJSON = (JSONObject) o.get("user");
				user.setId((String) userJSON.get("_id"));
				user.setJwt((String) o.get("token"));
				
				// Update the frame and Profile plus the Menu BAR
				Master.getInstance().updateFrame(new ProfileView());
				Master.getInstance().loggedInMenuLoad();
				
		    } else {
		    	ErrorGUI eg = new ErrorGUI((String) o.get("message"));
				return;
		    }
			
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			// Close or stop the connection
		    request.releaseConnection();
		}
		
		
	}

	public void deleteAccount(User u) {
		UserDB udb = new UserDB();
		udb.deleteUser(u);
		Master.getInstance().getFrame().dispose();
	}

	public void editUser(String email, String fName, String lName, Object style, Object timeOfDay,
			Object gender, Object prefGender, Object freq) {
		UserDB udb = new UserDB();
		if(udb.editUser(UserController.getUser(), email, fName, lName, (String) style, (String) timeOfDay,
				     (String) gender, (String) prefGender, (String) freq)) {
			SettingsView.saved();
		}
		
	}
	
}
