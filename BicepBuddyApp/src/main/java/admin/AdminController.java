package admin;

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

import Matching.Match;
import Matching.MatchAlgorithm;
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
	
	public static void generateFrame() {
		Master.updateFrame(new AdminGui());
	}
	
	
}
