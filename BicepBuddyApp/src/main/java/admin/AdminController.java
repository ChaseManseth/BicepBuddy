package admin;

import java.util.ArrayList;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import admin.AdminGui;
import User.User;
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
	//TO-DO:Return all user objects from db
	public static ArrayList<User> getAllUsers() {
		return new ArrayList<User>();
	}

	
	
}
