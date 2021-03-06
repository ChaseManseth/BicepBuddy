package Matching;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import User.User;
import User.UserController;
import bicepBuddyPackage.Master;

/**
 * The Class MatchController.
 * @authors: Zachary Steudel, Hunter Long, Chase Manseth, Bob Rein, Reece Kemball-Cook
 */
public class MatchController {
	
	/** The http client. */
	private static HttpClient httpClient = HttpClientBuilder.create().build();
	// Testing
	//private static String baseUrl = "http://localhost:3000/match/";
	/** The base url. */
	// Production
	private static String baseUrl = "http://bb.manseth.com/match/";
	
	/**
	 * Generate frame.
	 */
	public static void generateFrame() {
		Master.updateFrame(new MatchGUI());
	}
	
	/**
	 * Generate matches.
	 */
	public static void generateMatches() {
		if(UserController.getInstance().getTimesMatchCalled() == 5) {
			UserController.getUser().setIdle(MatchAlgorithm.matchUser());
			Master.appLogger.info(":: Matches generated from algorithm and added to idle");
			// Updating Array State for User
			UserController.getInstance().updateMatchedArrayState(UserController.getInstance().getUser());
			
			UserController.getInstance().setTimesMatchCalled(0);
		}
		
		UserController.getInstance().setTimesMatchCalled(UserController.getInstance().getTimesMatchCalled() + 1);
	}
	
	/**
	 * Direct match.
	 *
	 * @param user the user
	 * @return the match
	 */
	public static Match directMatch(User user) {
		Master.appLogger.info(":: Direct Match");
		return MatchAlgorithm.directMatch(user);
	}
	
	/**
	 * Gets the matches.
	 *
	 * @param user the user
	 * @return the matches
	 */
	public static List<Match> getMatches(User user){
		return user.getIdle();
	}
	
	/**
	 * Gets the other.
	 *
	 * @param match the match
	 * @return the other
	 */
	public static User getOther(Match match) {
		return UserController.getInstance().onlyGetUserById(match.getOther());
	}
	
	/**
	 * Find match.
	 *
	 * @param match the match
	 * @return the match
	 */
	public static Match findMatch(Match match) {
		Match result = null;
		if (UserController.getUser().getWaiting().contains(match)) {
			result = UserController.getUser().getWaiting().get(UserController.getUser().getWaiting().indexOf(match));
		}
		else if (UserController.getUser().getAccepted().contains(match)) {
			result = UserController.getUser().getAccepted().get(UserController.getUser().getAccepted().indexOf(match));
		}
		else if (UserController.getUser().getRejected().contains(match)) {
			result = UserController.getUser().getRejected().get(UserController.getUser().getRejected().indexOf(match));
		}
		
		return result;
	}
	
	/**
	 * Accept match initial.
	 *
	 * @param match the match
	 */
	public static void acceptMatchInitial(Match match) {
		Master.appLogger.info(":: Match sent from " + 
				UserController.getUser().toString() + " to " + 
				UserController.getInstance().onlyGetUserById(match.getOther()).toString());
		
		UserController uc = UserController.getInstance();
		
		if(match.getId() == null) {
			String id = createMatch(match);
			match.setId(id);
		}
		
		//add to primary accepted
		List<Match> matches = UserController.getUser().getAccepted();
		matches.add(match);
		UserController.getUser().setAccepted(matches);
		
		// Updating Array State for User
		uc.updateMatchedArrayState(UserController.getUser());
		
		
		//add to other waiting
		User other = UserController.getInstance().getUserById(match.getOther());
		matches = other.getWaiting();
		matches.add(match);
		other.setWaiting(matches);
		
		// Updating Array State for Other User
		uc.updateMatchedArrayState(other);
	}
	
	/**
	 * Accept match other.
	 *
	 * @param match the match
	 */
	public static void acceptMatchOther(Match match) {
		Master.appLogger.info(":: Match accepted from " + 
				UserController.getInstance().onlyGetUserById(match.getOther()).toString() + " by " + 
				UserController.getUser().toString());
		
		//add to other accepted
		match.accept();
		
		// Save Status change to DB
		updateMatch(match);

		List<Match> matches = UserController.getUser().getWaiting();
		matches.remove(match);
		UserController.getUser().setWaiting(matches);
		
		matches = UserController.getUser().getAccepted();
		matches.add(match);
		UserController.getUser().setAccepted(matches);
		// Updating Array State for Other User
		UserController.getInstance().updateMatchedArrayState(UserController.getUser());
	}
	
	/**
	 * Reject match.
	 *
	 * @param match the match
	 */
	public static void rejectMatch(Match match) {
		if(match.getId() == null) {
			String id = createMatch(match);
			match.setId(id);
		}
		Master.appLogger.info(":: Match rejected by " + UserController.getUser().toString() + " with " + 
				UserController.getInstance().onlyGetUserById(match.getOther()).toString());
		
		UserController uc = UserController.getInstance();
		
		//add to both rejected
		match.reject();
		
		// Save Status change to DB
		updateMatch(match);
		
		// User
		List<Match> matches = UserController.getUser().getAccepted();
		if (matches.contains(match)) {
			matches.remove(match);
			UserController.getUser().setAccepted(matches);
		}
		matches = UserController.getUser().getWaiting();
		if (matches.contains(match)) {
			matches.remove(match);
			UserController.getUser().setWaiting(matches);
		}
		matches = UserController.getUser().getRejected();
		matches.add(match);
		UserController.getUser().setRejected(matches);
		
		// Updating Array State for User
		uc.updateMatchedArrayState(uc.getUser());
		
		
		
		// Other User
		User other = UserController.getInstance().getUserById(match.getOther());
		matches = other.getAccepted();
		if (matches.contains(match)) {
			matches.remove(match);
			other.setWaiting(matches);
		}
		matches = other.getWaiting();
		if (matches.contains(match)) {
			matches.remove(match);
			other.setWaiting(matches);
		}
		matches = other.getRejected();
		matches.add(match);
		other.setRejected(matches);
		
		// Updating Array State for Other User
		uc.updateMatchedArrayState(other);
	}
	
	/**
	 * Gets the match by id from the database.
	 *
	 * @param id the id
	 * @return the match by id
	 */
	public static Match getMatchById(String id) {
		Master.appLogger.info(":: Match fully fetched with id " + id);
		
		Match getMatch = null;
		
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
		    	// Success!
		    	JSONObject o = (JSONObject) new JSONParser().parse(json);
		    	
		    	// Match
		    	JSONObject match = (JSONObject) o.get("match");
		    	
		    	// Get Match Data to build Match Object
		    	//String id,String other,Integer strength, Integer status, String matchId
		    	
		    	String userId = (String) match.get("userId");
		    	String otherUserId = (String) match.get("matchedUserId");
		    	int strength = ((Long) match.get("strength")).intValue();
		    	int status = ((Long) match.get("status")).intValue();
		    	String matchId = (String) match.get("_id");
	
		    	getMatch = new Match(userId, otherUserId, strength, status, matchId);
		    	
		    } else {
		    	System.err.println("Failed to get Match Object");
		    }
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			request.releaseConnection();
		}
		
		
		return getMatch;
	}
	
	/**
	 * Creates the match.
	 * Create a Match in the DB
	 *
	 * @param m the m
	 * @return the string
	 */
	@SuppressWarnings("unchecked")
	public static String createMatch(Match m) {
		String id = "";
		
		// Create the Post JSON
		JSONObject createMatchJSON = new JSONObject();
		String userId = m.getUsers().get(0);
		String otherUserId = m.getUsers().get(1);
		createMatchJSON.put("userId", userId);
		createMatchJSON.put("matchedUserId", otherUserId);
		createMatchJSON.put("status", m.getStatus().ordinal());
		createMatchJSON.put("strength", m.getStrength());
		
		System.out.println(createMatchJSON.toJSONString());
		
		HttpPost request = new HttpPost(baseUrl);
		
		try {
			// Add JSON to the body and headers indicating type
			StringEntity params = new StringEntity(createMatchJSON.toJSONString());
		    request.addHeader("content-type", "application/json");
		    request.setEntity(params);
			
		    // Execute the request
		    HttpResponse response = httpClient.execute(request);
		    
		    // Get the body of the response
		    HttpEntity entity = response.getEntity();
		    String json = EntityUtils.toString(entity, StandardCharsets.UTF_8);

		    JSONParser parse = new JSONParser();
		    JSONObject o = (JSONObject) parse.parse(json);
		    
		    // Successful Match Creation
		    if(response.getStatusLine().getStatusCode() == 201) {
		    	// Get the Match Id from the DB
		    	JSONObject matchDB = (JSONObject) o.get("match");
		    	
		    	// Get the Id of the created match
		    	id = (String) matchDB.get("_id");
		    	
		    } else {
		    	System.err.println("Match Failed to create");
		    }
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			request.releaseConnection();
		}
		
		Master.appLogger.info(":: Match generated with id " + id);
		
		return id;
		
	}
	
	/**
	 * Update match.
	 * Update an Existing Match in the DB
	 *
	 * @param m the m
	 */
	@SuppressWarnings("unchecked")
	public static void updateMatch(Match m) {
		Master.appLogger.info(":: Match updated with id " + m.getId());
		// Create the Post JSON
		JSONObject patchMatchJSON = new JSONObject();
		patchMatchJSON.put("status", m.getStatus().ordinal());
		
		// Open the Connection
		HttpPatch request = new HttpPatch(baseUrl + m.getId());
		
		try {
			// Set headers
			request.addHeader("content-type", "application/json");
			
			// Attach Body
			StringEntity params = new StringEntity(patchMatchJSON.toJSONString());
			request.setEntity(params);
			
			// Execute the request
			HttpResponse response = httpClient.execute(request);
			
			// Get the Respose Back
			if(response.getStatusLine().getStatusCode() == 200) {
				// Success!
				System.out.println("Match Updated");
			} else {
				System.err.println("Failed to Update a Match! Maybe invalid id?");
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			request.releaseConnection();
		}
		
	}
	
}
