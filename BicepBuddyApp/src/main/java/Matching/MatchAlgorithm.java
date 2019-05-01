package Matching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import User.User;
import User.UserController;

/**
 * The Class MatchAlgorithm.
 * @authors: Zachary Steudel, Hunter Long, Chase Manseth, Bob Rein, Reece Kemball-Cook
 */
public class MatchAlgorithm {

	/** The critical value. */
	//Priority % value
	private static int criticalValue = 100; // Pass or fail

	/** The important value. */
	private static int importantValue = 40;

	/** The pref value. */
	private static int prefValue = 30;

	/** The minor value. */
	private static int minorValue = 20;

	/** The quality value. */
	private static int qualityValue = 10;

	/** The prioritycount. */
	//Number of priorities
	public static int PRIORITYCOUNT = 5;

	/** The matchesreturned. */
	//Number of matches returned
	public static int MATCHESRETURNED = 10;
	//Priority enum
	/**
	 * The Enum Priority.
	 */
	//Use .ordinal for rank 1-5
	enum Priority{

		/** The Critical. */
		Critical,
		/** The Important. */
		//Pass or fail
		Important,

		/** The Preference. */
		Preference,

		/** The Minor. */
		Minor,

		/** The Quality. */
		Quality
	};

	/** The pref gender priority. */
	//Used for calculating priorities
	private static Priority prefGenderPriority = Priority.Critical;

	/** The weight priority. */
	private static Priority weightPriority = Priority.Preference;

	/** The style priority. */
	private static Priority stylePriority = Priority.Important;

	/** The time priority. */
	private static Priority timePriority = Priority.Important;

	/** The freq priority. */
	private static Priority freqPriority = Priority.Minor;

	/** The goal priority. */
	private static Priority goalPriority = Priority.Quality;

	/** The exp priority. */
	private static Priority expPriority = Priority.Preference;

	/** The age priority. */
	private static Priority agePriority = Priority.Minor;

	/** The genders. */
	//String arrays for preferences
	public static String GENDERS[] = {"N/A", "Male","Female"};

	/** The weightclass. */
	public static String WEIGHTCLASS[] = {"N/A", "<100","100-125","125-150","150-175","175-200","200-225","225-250","250-275","275-300","300+"};

	/** The styles. */
	public static String STYLES[] = {"N/A", "Cardio","Weights","Power Lifting","Body Building"};

	/** The times. */
	public static String TIMES[] = {"N/A", "Early Morning","Morning","Afternoon","Evening"};

	/** The frequencies. */
	public static String FREQUENCIES[] = {"N/A", "Once","3 Times","5 Times","Every Day","Multiple Times"};

	/** The goals. */
	public static String GOALS[] = {"N/A", "Lose Weight","Stay Healthy","Get Healthy","Gain Weight","Get Swole","I'm Addicted"};

	/** The experience. */
	public static String EXPERIENCE[] = {"N/A", "None","Little","Moderate","Regular","Experienced"};

	/** The ages. */
	public static String AGES[] = {"<18","19-20","21-22","23-24","25-26","27+"};


	/**
	 * Instantiates a new match algorithm.
	 */
	public MatchAlgorithm() {}

	/**
	 * Direct match.
	 * GET MATCH BY ID to get Strength
	 *
	 * @param other the other
	 * @return the match
	 */
	public static Match directMatch(User other) {
		Match m = new Match(UserController.getUser(),other,calculateRatios(other));

		// Create the match in the DB
		m.setId(MatchController.createMatch(m));

		return m;
	}

	/**
	 * Match user.
	 *
	 * @return the list
	 */
	public static List<Match> matchUser(){
		List<User> users = possibleMatches();
		Map<User,Integer> ratios = new HashMap<>();
		//Calculating ratios for all possible matches
		for (User u : users) {
			ratios.put(u, calculateRatios(u));
		}

		//Sort by ratios
		//Placing in list to sort
		List<Map.Entry<User, Integer> > list = new ArrayList<Map.Entry<User, Integer> >(ratios.entrySet());
        //Sort the list
        Collections.sort(list, new Comparator<Map.Entry<User, Integer> >() {
            public int compare(Map.Entry<User, Integer> o1, Map.Entry<User, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        //Replace data in ratios
        ratios.clear();
        for (Map.Entry<User, Integer> m : list) {
        	ratios.put(m.getKey(), m.getValue());
        }

		//Check if match already exists
        List<User> toRemove = new ArrayList<>();
        for (Map.Entry<User, Integer> m : ratios.entrySet()) {
        	Match temp = new Match(UserController.getUser().getId(),m.getKey().getId(),0);
        	//Checking accepted, rejected, and idle arrays
        	if (UserController.getUser().getAccepted().contains(temp) ||
        			UserController.getUser().getRejected().contains(temp) ||
        			UserController.getUser().getWaiting().contains(temp) ||
        			UserController.getUser().getId().equals(m.getKey().getId())) {
        		toRemove.add(m.getKey());
        	}
        }
        for (User u : toRemove) {
        	ratios.remove(u);
        }

		//Take top MATCHESRETURNED
        List<Match> matches = new ArrayList<>();
        Integer counter = 0;
        for (Map.Entry<User, Integer> m : ratios.entrySet()) {
        	Match match = new Match(UserController.getUser(),m.getKey(),m.getValue());

        	// Create the match in the DB
    		String id = MatchController.createMatch(match);
    		// Set the id of the Match
    		match.setId(id);

        	matches.add(match);
        	counter++;
        	if (counter > MATCHESRETURNED) {
        		break;
        	}
        }

        Collections.sort(matches, new Comparator<Match>() {
			public int compare(Match o1, Match o2) {
				return -1 * (o1.getStrength()).compareTo(o2.getStrength());
			}
        });


		return matches;
	}

	/**
	 * Possible matches.
	 * Get all possible users who fit the Priority 1 Category
	 *
	 * @return the list
	 */
	public static List<User> possibleMatches(){
		// Gets a list of users based on the current users prefered gender
		List<User> users1 = UserController.getInstance().getUsersByGender(UserController.getUser().getPrefGender());

		return users1;
	}

	/**
	 * Calculate ratios all matches.
	 *
	 * @param other the other
	 * @return the integer
	 */
	@SuppressWarnings("deprecation")
	public static Integer calculateRatios(User other) {
		List<List<Integer>> ratios = new ArrayList<>(5);
		for (int i = 0; i < PRIORITYCOUNT; i++) {
			ratios.add(new ArrayList<Integer>());
		}
		//Pref Gender ratio
		ratios.get(prefGenderPriority.ordinal()).add(new Integer(calculateRatio(
				GENDERS.length,
				Arrays.asList(GENDERS).indexOf(UserController.getUser().getPrefGender()),
				Arrays.asList(GENDERS).indexOf(other.getPrefGender()))));
		//Weight ratio
		ratios.get(weightPriority.ordinal()).add(new Integer(calculateRatio(
				WEIGHTCLASS.length,
				Arrays.asList(WEIGHTCLASS).indexOf(UserController.getUser().getWeight()),
				Arrays.asList(WEIGHTCLASS).indexOf(other.getWeight()))));
		//Style ratio
		ratios.get(stylePriority.ordinal()).add(new Integer(calculateRatio(
				STYLES.length,
				Arrays.asList(STYLES).indexOf(UserController.getUser().getStyle()),
				Arrays.asList(STYLES).indexOf(other.getStyle()))));
		//Times ratio
		ratios.get(timePriority.ordinal()).add(new Integer(calculateRatio(
				TIMES.length,
				Arrays.asList(TIMES).indexOf(UserController.getUser().getTimeOfDay()),
				Arrays.asList(TIMES).indexOf(other.getTimeOfDay()))));
		//Frequency ratio
		ratios.get(freqPriority.ordinal()).add(new Integer(calculateRatio(
				FREQUENCIES.length,
				Arrays.asList(FREQUENCIES).indexOf(UserController.getUser().getFrequency()),
				Arrays.asList(FREQUENCIES).indexOf(other.getFrequency()))));
		//Goals ratio
		ratios.get(goalPriority.ordinal()).add(new Integer(calculateRatio(
				GOALS.length,
				Arrays.asList(GOALS).indexOf(UserController.getUser().getGoals()),
				Arrays.asList(GOALS).indexOf(other.getGoals()))));
		//Experience ratio
		ratios.get(expPriority.ordinal()).add(new Integer(calculateRatio(
				EXPERIENCE.length,
				Arrays.asList(EXPERIENCE).indexOf(UserController.getUser().getExperience()),
				Arrays.asList(EXPERIENCE).indexOf(other.getExperience()))));

		List<Integer> finals = new ArrayList<>();
		for (int i = 0; i < PRIORITYCOUNT; i++) {
			int sum = 0;
			for (Integer c : ratios.get(i)) {
				sum += c;
			}
			finals.add(sum / ratios.get(i).size());
		}

		Integer result = 0;
		for (Integer i : finals) {
			result += i;
		}

		result /= PRIORITYCOUNT;
		return result;
	}

	/**
	 * Calculate ratio.
	 * Calculates ratio between 2 users
	 *
	 * @param length the length
	 * @param userOptIdx the user opt idx
	 * @param otherOptIdx the other opt idx
	 * @return the integer
	 */
	public static Integer calculateRatio(Integer length,Integer userOptIdx,Integer otherOptIdx) {
		Integer data = userOptIdx - otherOptIdx;
		if (data < 0) {
			data *= -1;
		}
		return (int)((1 - ((double)data / (double)length)) * 100);
	}
}
