package admin;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import admin.AdminGui;
import User.User;
import User.UserController;
import bicepBuddyPackage.Master;

public class AdminController {
	private static HttpClient httpClient = HttpClientBuilder.create().build();
	// Testing
	//private static String baseUrl = "http://localhost:3000/match/";
	// Production
	private static String baseUrl = "http://bb.manseth.com/match/";
	private static AdminController myadmin = null;
	public static void generateFrame() {
		Master.updateFrame(new AdminGui());
	}
	// Singleton getInstance
	public static AdminController getInstance() {
		if(myadmin == null) {
			myadmin = new AdminController();
		}
		return myadmin;
	}

	public List<User> getAllUsers() {
		List<User> userList = new ArrayList<User>();

		// Open the post response
//		HttpClient httpClient = HttpClientBuilder.create().build();
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
//			    JSONObject o = (JSONObject) new JSONParser().parse(json);

			    // Get the Array of Users
			    JSONArray userArr = (JSONArray) new JSONParser().parse(json);

			    // Loop through each user
			    for(int i = 0; i < userArr.size(); i++) {
			    	JSONObject user = (JSONObject) userArr.get(i);

			    	// Getting the data of that user
			    	String id = (String) user.get("_id");
			    	User otherUser = UserController.getInstance().onlyGetUserById(id);

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
