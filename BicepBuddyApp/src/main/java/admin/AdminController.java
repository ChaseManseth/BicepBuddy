package admin;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import User.User;
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
    public static JFreeChart getChart(List<User> everybody, String option) { 
        DefaultPieDataset data = new DefaultPieDataset();
        String title ="Gender Demographics";
        double mcount=0.0,fcount=0.0,ncount=0.0;
        for (int i =0; i<everybody.size();i++) {
        	if(everybody.get(i).getGender().equalsIgnoreCase("male")) {
        		mcount+=1.0;
        	}
        	if(everybody.get(i).getGender().equalsIgnoreCase("female")) {
        		fcount+=1.0;
        	}
        	if(everybody.get(i).getGender().equalsIgnoreCase("N/A")) {
        		ncount+=1.0;
        	}
        }
//        everybody.forEach(a -> {
//        	data.setValue(a.getfName(), 1.0);
//        });
        data.setValue("Male", mcount);
        data.setValue("Female", fcount);
        data.setValue("N/A", ncount);

        return ChartFactory.createPieChart(title,data,false,true,false);
    }

	
	
}
