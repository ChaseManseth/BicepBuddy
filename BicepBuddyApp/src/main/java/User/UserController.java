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
import Matching.MatchController;
import Matching.Match.Status;
import Views.Login;
import Views.ProfileView;
import Views.SettingsView;
import bicepBuddyPackage.ErrorGUI;
import bicepBuddyPackage.Master;

// TODO: Auto-generated Javadoc
/*
 * User Controller will be able to speak with user and the "DB".
 */

/**
 * The Class UserController.
 *
 * UserController is an example of the PROXY PATTERN, acting as a wrapper for
 * the User object and acting as an intermediate between other classes.
 *
 * Also an example of the CHAIN OF RESPONSIBILITY PATTERN. Pattern explains that
 * there is a class that decouples the sender and receiver of requests. UserController
 * will decouple requesters from the User object.
 * 
 * @authors: Zachary Steudel, Hunter Long, Chase Manseth, Bob Rein, Reece Kemball-Cook
 */
public class UserController {
	
	/** The logged in. */
	private boolean loggedIn;
	
	/** The user. */
	private static User user = null;
	
	/** The changes to matches. */
	private boolean changesToMatches = true;
	
	/** The uc. */
	private static UserController uc = null;
	
	/** The http client. */
	private HttpClient httpClient = HttpClientBuilder.create().build();
	// Testing
//	private String baseUrl = "http://localhost:3000/user/";
	/** The base url. */
	// Production
	private String baseUrl = "http://bb.manseth.com/user/";

	/**
	 * Gets the single instance of UserController.
	 *
	 * @return single instance of UserController
	 */
	// Singleton getInstance
	public static UserController getInstance() {
		if(uc == null) {
			Master.appLogger.info(":: Instantiating instance of UserController.");
			uc = new UserController();
		}

		return uc;
	}

	/**
	 * Checks if is changes to matches.
	 *
	 * @return true, if is changes to matches
	 */
	public boolean isChangesToMatches() {
		return changesToMatches;
	}



	/**
	 * Sets the changes to matches.
	 *
	 * @param changesToMatches the new changes to matches
	 */
	public void setChangesToMatches(boolean changesToMatches) {
		this.changesToMatches = changesToMatches;
	}



	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public static User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param curUser the new user
	 */
	public static void setUser(User curUser) {
		user = curUser;
	}

	/**
	 * Sets the user from DB.
	 *
	 * @param uDetails the new user from DB
	 */
	//FACTORY METHOD PATTERN
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

	/**
	 * Checks if is logged in.
	 *
	 * @return true, if is logged in
	 */
	public boolean isLoggedIn() {
		return loggedIn;
	}

	/**
	 * Sets the logged in.
	 *
	 * @param loggedIn the new logged in
	 */
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}



	/**
	 * Validate signup.
	 *
	 * @param fName the f name
	 * @param lname the lname
	 * @param email the email
	 * @param phone the phone
	 * @param age the age
	 * @param gender the gender
	 * @param prefGender the pref gender
	 * @param goals the goals
	 * @param freq the freq
	 * @param timeOfDay the time of day
	 * @param style the style
	 * @param weight the weight
	 * @param exp the exp
	 * @param pass the pass
	 * @param confPass the conf pass
	 */
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

	/**
	 * Validate login.
	 *
	 * @param email the email
	 * @param pass the pass
	 */
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
			    user = getUserById(id);
//				String fName = (String) userObj.get("firstname");
//				String lName = (String) userObj.get("lastname");
//				String emailDB = (String) userObj.get("email");
//				String phone = (String) userObj.get("phoneNumber");
//				String age = (String) userObj.get("age");
//				String gender = (String) userObj.get("gender");
//				String prefGender = (String) userObj.get("preferredGender");
//				String goals = (String) userObj.get("goals");
//				String frequency = (String) userObj.get("frequency");
//				String timeOfDay = (String) userObj.get("timeOfDay");
//				String style = (String) userObj.get("workoutStyle");
//				String weight = (String) userObj.get("weight");
//				String experience = (String) userObj.get("experience");
//
//				// Get all the match arrays
//				List<Match> accepted = new ArrayList<Match>();
//				List<Match> rejected = new ArrayList<Match>();
//				List<Match> idle = new ArrayList<Match>();
//				List<Match> waiting = new ArrayList<Match>();
//
//				JSONArray accept = (JSONArray) userObj.get("acceptedMatches");
//				JSONArray reject = (JSONArray) userObj.get("rejectedMatches");
//				JSONArray idl = (JSONArray) userObj.get("idleMatches");
//				JSONArray wait = (JSONArray) userObj.get("waitingMatches");
//
//				// Go through all the accepted matches and get the from the DB, build them and store it
//				// Accept Matches
//				for(int i = 0; i < accept.size(); i++) {
//					String matchId = (String) accept.get(i);
//
//					// Create the Match
//					// TODO move function to MatchController
//					Match m = getMatchById(matchId);
//					accepted.add(m);
//
//					// System.out.println(matchId);
//				}
//
//				// Reject Matches
//				for(int i = 0; i < reject.size(); i++) {
//					String matchId = (String) reject.get(i);
//
//					// Create the Match
//					// TODO move function to MatchController
//					Match m = getMatchById(matchId);
//					rejected.add(m);
//
//					//System.out.println(matchId);
//				}
//
//				// Idle Matches
//				for(int i = 0; i < idl.size(); i++) {
//					String matchId = (String) idl.get(i);
//
//					// Create the Match
//					// TODO move function to MatchController
//					Match m = getMatchById(matchId);
//					idle.add(m);
//
//					//System.out.println(matchId);
//				}
//
//				// Waiting Matches
//				for(int i = 0; i < wait.size(); i++) {
//					String matchId = (String) wait.get(i);
//
//					// Create the Match
//					// TODO move function to MatchController
//					Match m = getMatchById(matchId);
//					waiting.add(m);
//
//					//System.out.println(matchId);
//				}
//
//				// Build the user
//				user = new User(fName, lName, emailDB, phone, age, gender, prefGender,
//						goals, frequency, timeOfDay, style, weight, experience);
//
//				// Set ID, JWT and Matchs Arrays
//				user.setId(id);
//				user.setJwt(jwt);
//
//				user.setAccepted(accepted);
//				user.setRejected(rejected);
//				user.setIdle(idle);
//				user.setWaiting(waiting);

				// Return the profile page
				Master.getInstance().loggedInMenuLoad();
				Master.getInstance().updateFrame(new ProfileView());
				Master.appLogger.info(":: User was logged in.");

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
	/**
     * Creates the user.
     *
     * @param fName the f name
     * @param lName the l name
     * @param email the email
     * @param phone the phone
     * @param age the age
     * @param gender the gender
     * @param prefGender the pref gender
     * @param goals the goals
     * @param frequency the frequency
     * @param timeOfDay the time of day
     * @param style the style
     * @param weight the weight
     * @param experience the experience
     * @param password the password
     */
    //FACTORY METHOD PATTERN
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
				Master.getInstance().loggedInMenuLoad();
				Master.getInstance().updateFrame(new ProfileView());

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

	/**
	 * Delete account.
	 *
	 * @param u the u
	 */
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
		    	Master.appLogger.info(":: Deleting user " + u.getfName() + " " + u.getlName() + " from DB.");
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
	
	
	
	
	public void deleteUser(User u) {
//		UserDB udb = new UserDB();
//		udb.deleteUser(u);
//		Master.getInstance().getFrame().dispose();

		// Actually Delete a User from the DB
		// Open the post response
//		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpDelete request = new HttpDelete(baseUrl + u.getId());

		try {
			// Set headers
			request.addHeader("content-type", "application/json");
			request.addHeader("Authorization", "Bearer " + user.getJwt());
			request.addHeader("id", u.getId());

			// Execute the request
			HttpResponse response = httpClient.execute(request);

			// Get the Respose Back
		    if(response.getStatusLine().getStatusCode() == 200) {
		    	// User Profile was Deleted!
		    	Master.appLogger.info(":: Deleting user " + u.getfName() + " " + u.getlName() + " from DB.");
		    	//user = null;
		    	//Master.getInstance().loggedOutMenuLoad();
		    	//Master.getInstance().updateFrame(new Login());
		    } else {
		    	ErrorGUI eg = new ErrorGUI("You don't have access to this action!");
		    }

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			request.releaseConnection();
		}
	}

	/**
	 * Edits the user.
	 *
	 * @param email the email
	 * @param fName the f name
	 * @param lName the l name
	 * @param style the style
	 * @param timeOfDay the time of day
	 * @param gender the gender
	 * @param prefGender the pref gender
	 * @param freq the freq
	 * @param goal the goal
	 * @param weight the weight
	 * @param exp the exp
	 * @param age the age
	 * @param phone the phone
	 */
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
		    	Master.appLogger.info(":: Editing user " + user.getfName() + " " + user.getlName() + ".");
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

	/**
	 * Gets the users by gender.
	 *
	 * @param gender the gender
	 * @return the users by gender
	 */
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
//			    JSONObject o = (JSONObject) new JSONParser().parse(json);

			    // Get the Array of Users
			    JSONArray userArr = (JSONArray) new JSONParser().parse(json);
			    Master.appLogger.info(":: Retrieving users by gender: " + gender);

			    // Loop through each user
			    for(int j = 0; j < userArr.size(); j++) {
			    	JSONObject result = (JSONObject) userArr.get(j);

			    	// Getting the data of that user
			    	String id = (String) result.get("_id");
			    	String fName = (String) result.get("firstname");
					String lName = (String) result.get("lastname");
					String email = (String) result.get("email");
					String phone = (String) result.get("phoneNumber");
					String age = (String) result.get("age");
					String gend = (String) result.get("gender");
					String prefGender = (String) result.get("preferredGender");
					String goals = (String) result.get("goals");
					String frequency = (String) result.get("frequency");
					String timeOfDay = (String) result.get("timeOfDay");
					String style = (String) result.get("workoutStyle");
					String weight = (String) result.get("weight");
					String experience = (String) result.get("experience");
					
					List<Match> accepted = new ArrayList<Match>();
					List<Match> rejected = new ArrayList<Match>();
					List<Match> idle = new ArrayList<Match>();
					List<Match> waiting = new ArrayList<Match>();

					JSONArray accept = (JSONArray) result.get("acceptedMatches");
					JSONArray reject = (JSONArray) result.get("rejectedMatches");
					JSONArray idl = (JSONArray) result.get("idleMatches");
					JSONArray wait = (JSONArray) result.get("waitingMatches");
					
			    	User otherUser = new User(fName, lName, email, phone, age, gend, prefGender, goals, frequency,
			    			timeOfDay, style, weight, experience);
			    	otherUser.setId(id);
			    	
			    	for(int i = 0; i < accept.size(); i++) {
						String matchId = (String) accept.get(i);

						// Create the Match
						// TODO move function to MatchController
						Match m = MatchController.getMatchById(matchId);
						accepted.add(m);

						// System.out.println(matchId);
					}

					// Reject Matches
					for(int i = 0; i < reject.size(); i++) {
						String matchId = (String) reject.get(i);

						// Create the Match
						// TODO move function to MatchController
						Match m =  MatchController.getMatchById(matchId);
						rejected.add(m);

						//System.out.println(matchId);
					}

					// Idle Matches
					/*for(int i = 0; i < idl.size(); i++) {
						String matchId = (String) idl.get(i);

						// Create the Match
						// TODO move function to MatchController
						Match m =  MatchController.getMatchById(matchId);
						idle.add(m);

					}*/

					// Waiting Matches
					for(int i = 0; i < wait.size(); i++) {
						String matchId = (String) wait.get(i);

						// Create the Match
						// TODO move function to MatchController
						Match m =  MatchController.getMatchById(matchId);
						waiting.add(m);

					}

					// Add the Match Arrays to the User object
					otherUser.setAccepted(accepted);
					otherUser.setRejected(rejected);
					//otherUser.setIdle(idle);
					otherUser.setWaiting(waiting);

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


	/**
	 * Gets the user by id.
	 *
	 * @param id the id
	 * @return the user by id
	 */
	// Get user by Id
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
				Master.appLogger.info(":: Getting user by ID: " + fName + " " + lName + " from DB.");

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
					Match m = MatchController.getMatchById(matchId);
					accepted.add(m);

					// System.out.println(matchId);
				}

				// Reject Matches
				for(int i = 0; i < reject.size(); i++) {
					String matchId = (String) reject.get(i);

					// Create the Match
					// TODO move function to MatchController
					Match m =  MatchController.getMatchById(matchId);
					rejected.add(m);

					//System.out.println(matchId);
				}

				// Idle Matches
				/*for(int i = 0; i < idl.size(); i++) {
					String matchId = (String) idl.get(i);

					// Create the Match
					// TODO move function to MatchController
					Match m =  MatchController.getMatchById(matchId);
					idle.add(m);

					//System.out.println(matchId);
				}*/

				// Waiting Matches
				for(int i = 0; i < wait.size(); i++) {
					String matchId = (String) wait.get(i);

					// Create the Match
					// TODO move function to MatchController
					Match m =  MatchController.getMatchById(matchId);
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
	
	/**
	 * Update matched array state.
	 *
	 * @param u the u
	 */
	// TODO - updateMatchedArrayState
	@SuppressWarnings("unchecked")
	public void updateMatchedArrayState(User u) {
		JSONObject updateArrays = new JSONObject();
		
		// Create the Arrays
		JSONArray acceptedMatches = new JSONArray();
		JSONArray rejectedMatches = new JSONArray();
		JSONArray idleMatches = new JSONArray();
		JSONArray waitingMatches = new JSONArray();
		
		// Load the accept array
		List<Match> acceptM = u.getAccepted();
		for(int i = 0; i < acceptM.size(); i++) {
			String matchId = acceptM.get(i).getId();
			
			acceptedMatches.add(matchId);
		}
		
		// Load the reject array
		List<Match> rejectM = u.getRejected();
		for(int i = 0; i < rejectM.size(); i++) {
			String matchId = rejectM.get(i).getId();
			
			rejectedMatches.add(matchId);
		}
		
		// Load the idle array
		List<Match> idleM = u.getIdle();
		for(int i = 0; i < idleM.size(); i++) {
			String matchId = idleM.get(i).getId();
			
			idleMatches.add(matchId);
		}
		
		// Load the idle array
		List<Match> waitM = u.getWaiting();
		for(int i = 0; i < waitM.size(); i++) {
			String matchId = waitM.get(i).getId();
			
			waitingMatches.add(matchId);
		}
		
		// Add the Arrays to the JSON Object
		updateArrays.put("acceptedMatches", acceptedMatches);
		updateArrays.put("rejectedMatches", rejectedMatches);
		updateArrays.put("idleMatches", idleMatches);
		updateArrays.put("waitingMatches", waitingMatches);
		
		// Now that the JSON is done we want to open a Patch request to update the user
		
		// Open the Connection
		HttpPatch request = new HttpPatch(baseUrl + u.getId());
		
		try {
			// Set headers
			request.addHeader("content-type", "application/json");
			
			// Attach Body
			StringEntity params = new StringEntity(updateArrays.toJSONString());
			request.setEntity(params);
			
			// Execute the request
			HttpResponse response = httpClient.execute(request);
			
			// Get the Respose Back
			if(response.getStatusLine().getStatusCode() == 200) {
				// Success!
				System.out.println("User Arrays Updated!");
			} else {
				System.err.println("Failed to Update a User Arrays! Maybe invalid id?");
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			request.releaseConnection();
		}
		
		
	}
	
	/**
	 * Only get user by id.
	 *
	 * @param id the id
	 * @return the user
	 */
	// Get user by Id
		public User onlyGetUserById(String id) {
			User newUser = null;
			// Open the Connection
			HttpGet request = new HttpGet(baseUrl + id);

			try {
				// Add Headers
				request.addHeader("content-type", "application/json");

				// Execute the Request
				HttpResponse response = httpClient.execute(request);

				// Get the Response Back
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
					Master.appLogger.info(":: Getting user " + fName + " " + lName + " from DB WITHOUT their match info.");

					// Add its ID
					String idDB = (String) result.get("_id");
					newUser.setId(idDB);

			    } else {
			    	Master.appLogger.warning(":: Failed to get the user! Maybe invalid id?");
			    }


			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				request.releaseConnection();
			}

			return newUser;
		}
		
		/**
		 * Populate user matches array.
		 */
		public void populateUserMatchesArray() {
			//if we made some new matches, this flag will be set, and we will
			//re-populate our recent activity feed.
			if(changesToMatches) {
				Master.appLogger.info(":: Updating user matches array.");
				UserController.getInstance().getUser().setAcceptedUsers(new ArrayList<User>());
				UserController.getInstance().getUser().setWaitingUsers(new ArrayList<User>());
				UserController.getInstance().getUser().setPendingUsers(new ArrayList<User>());
				for (Match m : UserController.getUser().getAccepted()) {
					if (m.getStatus() == Status.Accepted) {
						User other = UserController.getInstance().onlyGetUserById(m.getOther());
						List<User> users = UserController.getInstance().getUser().getAcceptedUsers();
						users.add(other);
						UserController.getInstance().getUser().setAcceptedUsers(users);
					}
				}
				
				for (Match m : UserController.getUser().getWaiting()) {
					User other = UserController.getInstance().onlyGetUserById(m.getOther());
					List<User> users = UserController.getInstance().getUser().getWaitingUsers();
					users.add(other);
					UserController.getInstance().getUser().setWaitingUsers(users);
				}
				
				for (Match m : UserController.getUser().getAccepted()) {
					if (m.getStatus() == Status.Idle) {
						User other = UserController.getInstance().onlyGetUserById(m.getOther());
						List<User> users = UserController.getInstance().getUser().getPendingUsers();
						users.add(other);
						UserController.getInstance().getUser().setPendingUsers(users);
					}
				}
				
				changesToMatches = false;
			}
			
		}
		
		/**
		 * Gets the all users.
		 *
		 * @return the all users
		 */
		// Gets all users based on gender
		public List<User> getAllUsers() {
			List<User> userList = new ArrayList<User>();
			Master.appLogger.info(":: Retrieving all users from the DB.");

			// Open the post response
//			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(baseUrl);

			// Try to execute the request
			try {
				// Set headers
				request.addHeader("content-type", "application/json");

				// Execute the request
				HttpResponse response = httpClient.execute(request);

				// Get the Response Back
			    HttpEntity entity = response.getEntity();
			    String json = EntityUtils.toString(entity, StandardCharsets.UTF_8);

				// Get the Response Back
			    if(response.getStatusLine().getStatusCode() == 200) {
			    	// SUCCESS!

			    	// Get response object
//				    JSONObject o = (JSONObject) new JSONParser().parse(json);

				    // Get the Array of Users
				    JSONArray userArr = (JSONArray) new JSONParser().parse(json);

				    // Loop through each user
				    for(int i = 0; i < userArr.size(); i++) {
				    	JSONObject result = (JSONObject) userArr.get(i);
				    	// Getting the data of that user
				    	String id = (String) result.get("_id");
				    	
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
						
				    	User otherUser = new User(fName, lName, email, phone, age, gender, prefGender, goals, frequency,
				    			timeOfDay, style, weight, experience);

			    		userList.add(otherUser);
				    }

			    } else {
			    	// FAIL!
			    	System.err.println("failed yo");
			    }

			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				request.releaseConnection();
			}
			
			return userList;
		}

}
