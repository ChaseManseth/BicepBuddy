package admin;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import admin.AdminGui;
import bicepBuddyPackage.Master;

// TODO: Auto-generated Javadoc
/**
 * The Class AdminController.
 * @authors: Zachary Steudel, Hunter Long, Chase Manseth, Bob Rein, Reece Kemball-Cook
 */
public class AdminController {
	
	/** The http client. */
	private static HttpClient httpClient = HttpClientBuilder.create().build();
	// Testing
	//private static String baseUrl = "http://localhost:3000/match/";
	/** The base url. */
	// Production
	private static String baseUrl = "http://bb.manseth.com/match/";
	
	/** The myadmin. */
	private static AdminController myadmin = null;
	
	/**
	 * Generate frame.
	 */
	public static void generateFrame() {
		Master.updateFrame(new AdminGui());
	}
	
	/**
	 * Gets the single instance of AdminController.
	 *
	 * @return single instance of AdminController
	 */
	// Singleton getInstance
	public static AdminController getInstance() {
		if(myadmin == null) {
			myadmin = new AdminController();
		}
		return myadmin;
	}


	
	
}
