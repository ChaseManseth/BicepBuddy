package bicepBuddyPackage;

import java.util.ArrayList;
import java.util.List;



public class MatchAlgorithm {
	//Priority enum
	enum Priority{
		Critical, //Pass or fail
		Important,
		Preference,
		Minor,
		Quality
	};
	//Priority % value
	private static int criticalValue = 100; // Pass or fail
	private static int importantValue = 40;
	private static int prefValue = 30;
	private static int minorValue = 20;
	private static int qualityValue = 10;
	//Used for calculating priorities
	private static Priority prefGenderPriority = Priority.Critical;
	private static Priority weightPriority = Priority.Preference;
	private static Priority stylePriority = Priority.Important;
	private static Priority timePriority = Priority.Important;
	private static Priority freqPriority = Priority.Minor;
	private static Priority goalPriority = Priority.Quality;
	private static Priority expPriority = Priority.Preference;
	private static Priority agePriority = Priority.Minor;
	
	
	public MatchAlgorithm() {
		
	}
	
	public List<Match> matchUser(User user){
		List<User> users = possibleMatches(user);
		
		return new ArrayList<Match>();
	}
	
	//Get all possible users who fit the Priority 1 Category
	public List<User> possibleMatches(User user){
		
		return new ArrayList<User>();
	}
}
