package User;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

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

import Matching.Match;
import Views.Login;
import Views.ProfileView;
import Views.SettingsView;
import bicepBuddyPackage.ErrorGUI;
import bicepBuddyPackage.Master;

/*
 * User Controller will be able to speak with user and the "DB".
 */

public class UserController {
	private boolean loggedIn;
	private static User user = null;
	private static UserController uc = null;
	private HttpClient httpClient = HttpClientBuilder.create().build();
	// Testing
	private String baseUrl = "http://localhost:3000/user/";
	// Production
//	private String baseUrl = "http://bb.manseth.com/user/";
	
	// Singleton getInstance
	public static UserController getInstance() {
		if(uc == null) {
			uc = new UserController();
		}
		
		return uc;
	}

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
		
//		UserDB dbInstance = new UserDB();
//		if(!dbInstance.notExists(email)) {
//			ErrorGUI eg = new ErrorGUI("This email exists in the system already.");
//			return;
//		}
		
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
//		HttpClient httpClient = HttpClientBuilder.create().build();
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
				
				// Get all the match arrays
				List<Match> accepted = new ArrayList<Match>();
				List<Match> rejected = new ArrayList<Match>();
				List<Match> idle = new ArrayList<Match>();
				List<Match> waiting = new ArrayList<Match>();
				
				JSONArray accept = (JSONArray) userObj.get("acceptedMatches");
				JSONArray reject = (JSONArray) userObj.get("rejectedMatches");
				JSONArray idl = (JSONArray) userObj.get("idleMatches");
				JSONArray wait = (JSONArray) userObj.get("waitingMatches");
				
				// Go through all the accepted matches and get the from the DB, build them and store it
				// Accept Matches
				for(int i = 0; i < accept.size(); i++) {
					String matchId = (String) accept.get(i);
					
					// Create the Match
					// TODO move function to MatchController
					Match m = getMatchById(matchId);
					accepted.add(m);
					
					// System.out.println(matchId);
				}
				
				// Reject Matches
				for(int i = 0; i < reject.size(); i++) {
					String matchId = (String) reject.get(i);
					
					// Create the Match
					// TODO move function to MatchController
					Match m = getMatchById(matchId);
					rejected.add(m);
					
					//System.out.println(matchId);
				}
				
				// Idle Matches
				for(int i = 0; i < idl.size(); i++) {
					String matchId = (String) idl.get(i);
					
					// Create the Match
					// TODO move function to MatchController
					Match m = getMatchById(matchId);
					idle.add(m);
					
					//System.out.println(matchId);
				}
				
				// Waiting Matches
				for(int i = 0; i < wait.size(); i++) {
					String matchId = (String) wait.get(i);
					
					// Create the Match
					// TODO move function to MatchController
					Match m = getMatchById(matchId);
					waiting.add(m);
					
					//System.out.println(matchId);
				}
				
				// Build the user 
				user = new User(fName, lName, emailDB, phone, age, gender, prefGender, 
						goals, frequency, timeOfDay, style, weight, experience);
				
				// Set ID, JWT and Matchs Arrays
				user.setId(id);
				user.setJwt(jwt);
				
				user.setAccepted(accepted);
				user.setRejected(rejected);
				user.setIdle(idle);
				user.setWaiting(waiting);
				
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
	
	// User Signup
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
//		HttpClient httpClient = HttpClientBuilder.create().build();
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
//		HttpClient httpClient = HttpClientBuilder.create().build();
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
//		HttpClient httpClient = HttpClientBuilder.create().build();
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
	
	// TODO - Create an entire User Object will all arrays
	// Gets all users based on gender
	public List<User> getUsersByGender(String gender) {
		List<User> userList = new ArrayList<User>();
		
		// Open the post response
//		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet request;
		
		// Chose the right url to query
		if(gender.toLowerCase().equalsIgnoreCase("male")) {
			request = new HttpGet(baseUrl + "/get_users_by_gender/" + "male");
		} else if(gender.toLowerCase().equalsIgnoreCase("female")) {
			request = new HttpGet(baseUrl + "/get_users_by_gender/" + "female");
		} else {
			request = new HttpGet(baseUrl + "/get_users_by_gender/" + "none");
		}
		
		// Try to execute the request
		try {
			// Set headers
			request.addHeader("content-type", "application/json");
			
			// Execute the request
			HttpResponse response = httpClient.execute(request);
			
			// Get the Respose Back
		    HttpEntity entity = response.getEntity();
		    String json = EntityUtils.toString(entity, StandardCharsets.UTF_8);
			
			// Get the Respose Back
		    if(response.getStatusLine().getStatusCode() == 200) {
		    	// SUCCESS!
		    	
		    	// Get response object
			    JSONObject o = (JSONObject) new JSONParser().parse(json);
			    
			    // Get the Array of Users
			    JSONArray userArr = (JSONArray) o.get("users");
			    
			    // Loop through each user
			    for(int i = 0; i < userArr.size(); i++) {
			    	JSONObject user = (JSONObject) userArr.get(i);
			    	
			    	// Getting the data of that user
			    	String id = (String) user.get("_id");
			    	String firstname = (String) user.get("firstname");
			    	String lastname = (String) user.get("lastname");
			    	String genderDB = (String) user.get("gender");
			    	String profilePic = (String) user.get("profilePic");
			    	String workoutStyle = (String) user.get("workoutStyle");
			    	String goals = (String) user.get("goals");
			    	String experience = (String) user.get("experience");
			    	String timeOfDay = (String) user.get("timeOfDay");
			    	String frequency = (String) user.get("frequency");
			    	String prefGender = (String) user.get("preferredGender");
			    	String age = (String) user.get("age");
			    	
			    	User otherUser = new User(id, firstname, lastname, genderDB, profilePic, workoutStyle, 
			    			goals, experience, timeOfDay, frequency, prefGender, age);
			    	
		    		userList.add(otherUser);	
			    }
		    	
		    } else {
		    	// FAIL!
		    	System.err.println("Shit failed yo");
		    }
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			request.releaseConnection();
		}
		
		
		return userList;
	}
	
	// TODO Get matches by Id
	public Match getMatchById(String id) {
		return null;
	}
	
	// TODO Get user by Id
	public User getUserById(String id) {
		User newUser = null;
		// Open the Connection
		HttpGet request = new HttpGet(baseUrl + id);
		
		try {
			// Add Headers
			request.addHeader("content-type", "application/json");
			
			// Execute the Request
			HttpResponse response = httpClient.execute(request);
			
			// Get the Respose Back
		    HttpEntity entity = response.getEntity();
		    String json = EntityUtils.toString(entity, StandardCharsets.UTF_8);
		    
		    if(response.getStatusLine().getStatusCode() == 200) {
		    	JSONObject o = (JSONObject) new JSONParser().parse(json);
			    
			    // Result
			    JSONObject result = (JSONObject) o.get("result");
			    
			    // Get Data to build a User object
			    String fName = (String) result.get("firstname");
				String lName = (String) result.get("lastname");
				String email = (String) result.get("email");
				String phone = (String) result.get("phoneNumber");
				String age = (String) result.get("age");
				String gender = (String) result.get("gender");
				String prefGender = (String) result.get("preferredGender");
				String goals = (String) result.get("goals");
				String frequency = (String) result.get("frequency");
				String timeOfDay = (String) result.get("timeOfDay");
				String style = (String) result.get("workoutStyle");
				String weight = (String) result.get("weight");
				String experience = (String) result.get("experience");
				
				// Created a new user
				newUser = new User(fName, lName, email, phone, age, gender, prefGender, 
						goals, frequency, timeOfDay, style, weight, experience);
				
				// Add its ID
				String idDB = (String) result.get("_id");
				newUser.setId(idDB);
				
				// Load its Match Arrays
				// Get all the match arrays
				List<Match> accepted = new ArrayList<Match>();
				List<Match> rejected = new ArrayList<Match>();
				List<Match> idle = new ArrayList<Match>();
				List<Match> waiting = new ArrayList<Match>();
				
				JSONArray accept = (JSONArray) result.get("acceptedMatches");
				JSONArray reject = (JSONArray) result.get("rejectedMatches");
				JSONArray idl = (JSONArray) result.get("idleMatches");
				JSONArray wait = (JSONArray) result.get("waitingMatches");
				
				// Go through all the accepted matches and get the from the DB, build them and store it
				// Accept Matches
				for(int i = 0; i < accept.size(); i++) {
					String matchId = (String) accept.get(i);
					
					// Create the Match
					// TODO move function to MatchController
					Match m = getMatchById(matchId);
					accepted.add(m);
					
					// System.out.println(matchId);
				}
				
				// Reject Matches
				for(int i = 0; i < reject.size(); i++) {
					String matchId = (String) reject.get(i);
					
					// Create the Match
					// TODO move function to MatchController
					Match m = getMatchById(matchId);
					rejected.add(m);
					
					//System.out.println(matchId);
				}
				
				// Idle Matches
				for(int i = 0; i < idl.size(); i++) {
					String matchId = (String) idl.get(i);
					
					// Create the Match
					// TODO move function to MatchController
					Match m = getMatchById(matchId);
					idle.add(m);
					
					//System.out.println(matchId);
				}
				
				// Waiting Matches
				for(int i = 0; i < wait.size(); i++) {
					String matchId = (String) wait.get(i);
					
					// Create the Match
					// TODO move function to MatchController
					Match m = getMatchById(matchId);
					waiting.add(m);
					
					//System.out.println(matchId);
				}
				
				// Add the Match Arrays to the User object
				newUser.setAccepted(accepted);
				newUser.setRejected(rejected);
				newUser.setIdle(idle);
				newUser.setWaiting(waiting);
			    
			    
		    } else {
		    	System.out.println("Failed to get the user! Maybe invalid id?");
		    }
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			request.releaseConnection();
		}
		
		return newUser;
	}
	
}
