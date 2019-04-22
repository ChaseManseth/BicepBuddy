package bicepBuddyPackage;

import java.awt.FlowLayout;
import java.nio.charset.StandardCharsets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/*
 * User Controller will be able to speak with user and the "DB".
 */

public class UserController {
	private boolean loggedIn;
	private static User user = null;
	// Testing
//	private String baseUrl = "http://localhost:3000/user/";
	// Production
	private String baseUrl = "http://bb.manseth.com/user/";

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
	@SuppressWarnings("unchecked")
	public void validateLogin(String email, String pass) {
//		UserDB udb = new UserDB();
//		if(udb.login(email, pass)) {
//			Master.appLogger.info(":: User was logged in.");
//			Master.getInstance().loggedInMenuLoad();
//			Master.getInstance().updateFrame(new ProfileView());
//		}
		
		// Actual Database login
		// Create the object in JSON
		JSONObject loginJSON = new JSONObject();
		loginJSON.put("email", email);
		loginJSON.put("password", pass);
		
		// Open the post response
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(baseUrl + "login");
		
		try {
			// Write to the body and set the Headers
			StringEntity params = new StringEntity(loginJSON.toJSONString());
		    request.addHeader("content-type", "application/json");
		    request.setEntity(params);
		    
			 // Execute the POST
		    HttpResponse response = httpClient.execute(request);
		    
		    // Get the Respose Back
		    HttpEntity entity = response.getEntity();
		    String json = EntityUtils.toString(entity, StandardCharsets.UTF_8);
		    
		    // Check if response was successful
		    if(response.getStatusLine().getStatusCode() == 200) {
		    	// Get response object
			    JSONObject o = (JSONObject) new JSONParser().parse(json);
			    
			    // Get JWT
			    String jwt = (String) o.get("token");
			    
			    // Get User Info Namely ID
			    JSONArray userArr = (JSONArray) o.get("user");
			    JSONObject userObj = (JSONObject) userArr.get(0);
			    
			    // Get all user data
			    String id = (String) userObj.get("_id");
				String fName = (String) userObj.get("firstname");
				String lName = (String) userObj.get("lastname");
				String emailDB = (String) userObj.get("email");
				String phone = (String) userObj.get("phoneNumber");
				String age = (String) userObj.get("age");
				String gender = (String) userObj.get("gender");
				String prefGender = (String) userObj.get("preferredGender");
				String goals = (String) userObj.get("goals");
				String frequency = (String) userObj.get("frequency");
				String timeOfDay = (String) userObj.get("timeOfDay");
				String style = (String) userObj.get("workoutStyle");
				String weight = (String) userObj.get("weight");
				String experience = (String) userObj.get("experience");
				
				// Build the user 
				user = new User(fName, lName, emailDB, phone, age, gender, prefGender, 
						goals, frequency, timeOfDay, style, weight, experience);
				user.setId(id);
				user.setJwt(jwt);
				
				// Return the profile page
				Master.getInstance().loggedInMenuLoad();
				Master.getInstance().updateFrame(new ProfileView());
				
		    } else {
		    	ErrorGUI eg = new ErrorGUI("Please enter valid username and password.");
		    }
		    
			
		    
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			request.releaseConnection();
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
//		UserDB udb = new UserDB();
//		udb.deleteUser(u);
//		Master.getInstance().getFrame().dispose();
		
		// Actually Delete a User from the DB
		// Open the post response
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpDelete request = new HttpDelete(baseUrl + user.getId());
		
		try {
			// Set headers
			request.addHeader("content-type", "application/json");
			request.addHeader("Authorization", "Bearer " + user.getJwt());
			request.addHeader("id", user.getId());
			
			// Execute the request
			HttpResponse response = httpClient.execute(request);
			
			// Get the Respose Back
		    if(response.getStatusLine().getStatusCode() == 200) {
		    	// User Profile was Deleted!
		    	user = null;
		    	Master.getInstance().loggedOutMenuLoad();
		    	Master.getInstance().updateFrame(new Login());
		    } else {
		    	ErrorGUI eg = new ErrorGUI("You don't have access to this action!");
		    }
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			request.releaseConnection();
		}
	}

	@SuppressWarnings("unchecked")
	public void editUser(String email, String fName, String lName, Object style, Object timeOfDay,
			Object gender, Object prefGender, Object freq, Object goal, Object weight, Object exp, 
			String age, String phone) {
//		UserDB udb = new UserDB();
//		if(udb.editUser(UserController.getUser(), email, fName, lName, (String) style, (String) timeOfDay,
//				     (String) gender, (String) prefGender, (String) freq, (String) goal, (String) weight,
//				     (String) exp, age, phone)) {
//			SettingsView.saved();
//		}
		
		// Create the JSON to post to the API
		JSONObject editProfileJSON = new JSONObject();
		editProfileJSON.put("firstname", fName);
		editProfileJSON.put("lastname", lName);
		editProfileJSON.put("email", email);
		editProfileJSON.put("phoneNumber", phone);
		editProfileJSON.put("age", age);
		editProfileJSON.put("gender", (String) gender);
		editProfileJSON.put("preferredGender", (String) prefGender);
		editProfileJSON.put("goals", (String) goal);
		editProfileJSON.put("frequency", (String) freq);
		editProfileJSON.put("timeOfDay", (String) timeOfDay);
		editProfileJSON.put("workoutStyle", (String) style);
		editProfileJSON.put("weight", (String) weight);
		editProfileJSON.put("experience", (String) exp);
				
		// Actual DB edit User
		// Open the post response
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPatch request = new HttpPatch(baseUrl + user.getId());
		
		try {
			// Set headers
			request.addHeader("content-type", "application/json");
			request.addHeader("Authorization", "Bearer " + user.getJwt());
			request.addHeader("id", user.getId());
			
			// Attach Body
			StringEntity params = new StringEntity(editProfileJSON.toJSONString());
			request.setEntity(params);
			
			// Execute the request
			HttpResponse response = httpClient.execute(request);
			
			// Get the Respose Back
		    if(response.getStatusLine().getStatusCode() == 200) {
		    	// SUCCESS!
		    	
		    	// Update User info from DB
		    	user.updateUser(email, fName, lName, style, timeOfDay, gender, 
		    			prefGender, freq, goal, weight, exp, age, phone);
		    	
		    	// Indicate save and load profile
		    	Master.getInstance().updateFrame(new ProfileView());
		    	SettingsView.saved();
		    } else {
		    	// FAIL!
		    	Master.getInstance().updateFrame(new ProfileView());
		    	ErrorGUI eg = new ErrorGUI("Your changes couldn't be saved!");
		    }
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			request.releaseConnection();
		}
		
	}
	
}
