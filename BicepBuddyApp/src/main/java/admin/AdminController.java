package admin;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import Matching.MatchAlgorithm;
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
        String title =option + " Demographics";
        String [] attributes = new String [everybody.size()];
        String [] possibilities = new String [0]; 
        int [] counts = new int [0];
        for (int i =0; i<everybody.size();i++) {
        	switch (option) {
				case "Gender":
					if (i==0) {
						possibilities =MatchAlgorithm.GENDERS; 
						counts = new int [MatchAlgorithm.GENDERS.length];
					}
					attributes[i] = everybody.get(i).getGender();
					break;
				case "Weight":
					if (i==0) {
						possibilities =MatchAlgorithm.WEIGHTCLASS; 
						counts = new int [MatchAlgorithm.WEIGHTCLASS.length];
					}
					attributes[i] = everybody.get(i).getWeight();
					break;
				case "Preferred":
					if (i==0) {
						possibilities =MatchAlgorithm.GENDERS; 
						counts = new int [MatchAlgorithm.GENDERS.length];
					}
					attributes[i] = everybody.get(i).getPrefGender();
					break;
				case "Goals":
					if (i==0) {
						possibilities =MatchAlgorithm.GOALS; 
						counts = new int [MatchAlgorithm.GOALS.length];
					}
					attributes[i] = everybody.get(i).getGoals();
					break;
				case "Frequency":
					if (i==0) {
						possibilities =MatchAlgorithm.FREQUENCIES; 
						counts = new int [MatchAlgorithm.FREQUENCIES.length];
					}
					attributes[i] = everybody.get(i).getFrequency();
					break;
				case "Time":
					if (i==0) {
						possibilities =MatchAlgorithm.TIMES; 
						counts = new int [MatchAlgorithm.TIMES.length];
					}
					attributes[i] = everybody.get(i).getTimeOfDay();
					break;
				case "Style":
					if (i==0) {
						possibilities =MatchAlgorithm.STYLES; 
						counts = new int [MatchAlgorithm.STYLES.length];
					}
					attributes[i] = everybody.get(i).getStyle();
					break;
				case "Experience":
					if (i==0) {
						possibilities =MatchAlgorithm.EXPERIENCE; 
						counts = new int [MatchAlgorithm.EXPERIENCE.length];
					}
					attributes[i] = everybody.get(i).getExperience();
					break;
			}
        }
        for (int i =0; i<everybody.size();i++) {
            for (int j = 0; j< possibilities.length; j++) {
            	if(possibilities[j].equalsIgnoreCase(attributes[i])) {
            		counts [j]++;
            	}
            }
        }


        for (int j = 0; j< possibilities.length; j++) {
        	if(counts[j]>0) {
	            data.setValue(possibilities[j],counts[j]);
        	}
        }
        return ChartFactory.createPieChart(title,data,false,true,false);
    }	
	
}
