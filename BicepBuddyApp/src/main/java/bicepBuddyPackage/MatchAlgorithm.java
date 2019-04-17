package bicepBuddyPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.Math;

public class MatchAlgorithm {
	//Priority % value
	private static int criticalValue = 100; // Pass or fail
	private static int importantValue = 40;
	private static int prefValue = 30;
	private static int minorValue = 20;
	private static int qualityValue = 10;
	//Priority enum
	//Use .ordinal for rank 1-5
	enum Priority{
		Critical, //Pass or fail
		Important,
		Preference,
		Minor,
		Quality
	};
	//Used for calculating priorities
	private static Priority prefGenderPriority = Priority.Critical;
	private static Priority weightPriority = Priority.Preference;
	private static Priority stylePriority = Priority.Important;
	private static Priority timePriority = Priority.Important;
	private static Priority freqPriority = Priority.Minor;
	private static Priority goalPriority = Priority.Quality;
	private static Priority expPriority = Priority.Preference;
	private static Priority agePriority = Priority.Minor;
	//String arrays for preferences
	public static String GENDERS[] = {"Male","Female"};
	public static String WEIGHTCLASS[] = {"<100","100-125","125-150","150-175","175-200","200-225","225-250","250-275","275-300","300+"};
	public static String STYLES[] = {"Cardio","Weights","Power Lifting","Body Building"};
	public static String TIMES[] = {"Early Morning","Morning","Afternoon","Evening"};
	public static String FREQUENCIES[] = {"Once","3 Times","5 Times","Every Day","Multiple Times"};
	public static String GOALS[] = {"Lose Weight","Stay Healthy","Get Healthy","Gain Weight","Get Swole","I'm Addicted"};
	public static String EXPERIENCE[] = {"None","Little","Moderate","Regular","Experienced"};
	
	
	public MatchAlgorithm() {
		
	}
	
	public List<Match> matchUser(User user){
		List<User> users = possibleMatches(user);
		Map<User,List<Integer>> ratios = new HashMap<>();
		//Calculating ratios for all possible matches
		for (User u : users) {
			ratios.put(u, calculateRatios(user,u));
		}
		
		return new ArrayList<Match>();
	}
	
	//Get all possible users who fit the Priority 1 Category
	public List<User> possibleMatches(User user){
		
		return new ArrayList<User>();
	}
	
	public List<Integer> calculateRatios(User user, User other) {
		List<List<Integer>> ratios = new ArrayList<>(5);
		for (int i = 0; i < 5; i++) {
			ratios.add(new ArrayList<Integer>());
		}
		ratios.get(prefGenderPriority.ordinal()).add(new Integer(calculateRatio(
						GENDERS.length,
						Arrays.asList(GENDERS).indexOf(user.getPrefGender()),
						Arrays.asList(GENDERS).indexOf(other.getPrefGender()))));
		return new ArrayList<Integer>();
	}
	
	public Integer calculateRatio(Integer length,Integer userOptIdx,Integer otherOptIdx) {
		Integer data = userOptIdx - otherOptIdx;
		if (data < 0) {
			data *= 1;
		}
		return (length / length) - (data / length);
	}
}
