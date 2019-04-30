package admin;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

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
    public static JFreeChart getChart() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("One", new Double(43.2));
        dataset.setValue("Two", new Double(10.0));
        dataset.setValue("Three", new Double(27.5));
        dataset.setValue("Four", new Double(17.5));
        dataset.setValue("Five", new Double(11.0));
        dataset.setValue("Six", new Double(19.4));
        return ChartFactory.createPieChart("Test1",dataset,false,true,false);
    }

	
	
}
