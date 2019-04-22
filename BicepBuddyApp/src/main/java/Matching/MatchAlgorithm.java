package Matching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import User.User;

public class MatchAlgorithm {
	//Priority % value
	private static int criticalValue = 100; // Pass or fail
	private static int importantValue = 40;
	private static int prefValue = 30;
	private static int minorValue = 20;
	private static int qualityValue = 10;
	//Number of priorities
	public static int PRIORITYCOUNT = 5;
	//Number of matches returned
	public static int MATCHESRETURNED = 10;
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
	public static String GENDERS[] = {"N/A", "Male","Female"};
	public static String WEIGHTCLASS[] = {"N/A", "<100","100-125","125-150","150-175","175-200","200-225","225-250","250-275","275-300","300+"};
	public static String STYLES[] = {"N/A", "Cardio","Weights","Power Lifting","Body Building"};
	public static String TIMES[] = {"N/A", "Early Morning","Morning","Afternoon","Evening"};
	public static String FREQUENCIES[] = {"N/A", "Once","3 Times","5 Times","Every Day","Multiple Times"};
	public static String GOALS[] = {"N/A", "Lose Weight","Stay Healthy","Get Healthy","Gain Weight","Get Swole","I'm Addicted"};
	public static String EXPERIENCE[] = {"N/A", "None","Little","Moderate","Regular","Experienced"};
	//public static String AGES[] = {"<18","19-20","21-22","23-24","25-26","27+"};


	public MatchAlgorithm() {}
	
	public static Match directMatch(User user, User other) {
		return new Match(user,other,calculateRatios(user,other));
	}

	public static List<Match> matchUser(User user){
		List<User> users = possibleMatches(user);
		Map<User,Integer> ratios = new HashMap<>();
		//Calculating ratios for all possible matches
		for (User u : users) {
			ratios.put(u, calculateRatios(user,u));
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
        	Match temp = new Match(user,m.getKey());
        	//Checking accepted, rejected, and idle arrays
        	if (has(user.getAccepted(),temp) || has(user.getRejected(),temp) || has(user.getWaiting(),temp)) {
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
        	matches.add(new Match(user,m.getKey(),m.getValue()));
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

	//Get all possible users who fit the Priority 1 Category
	public static List<User> possibleMatches(User user){
		//TO-DO
		
		User user1 = new User("Mark","Zucc","markzucc@gmail.com","123-456-7890","25","Male","Male","Get Swole","Multiple Times","Early Morning","Cardio","150-175","Regular");
		User user2 = new User("Other","Dude","otherdude@gmail.com","098-765-4321","26","Male","Male","Stay Healthy","Every Day","Morning","Cardio","125-150","Moderate");
		User user3 = new User("Lady","Dudette","ladydudette@gmail.com","111-111-1111","32","Male","Male","Stay Healthy","Every Day","Morning","Cardio","125-150","Moderate");
		User user4 = new User("Lady","Dudette2","ladydudette2@gmail.com","222-222-2222","35","Male","Female","Stay Healthy","Every Day","Morning","Cardio","125-150","Moderate");
		List<User> users = new ArrayList<>();
		users.add(user1);
		users.add(user2);
		users.add(user3);
		users.add(user4);

		return users;
	}

	@SuppressWarnings("deprecation")
	public static Integer calculateRatios(User user, User other) {
		List<List<Integer>> ratios = new ArrayList<>(5);
		for (int i = 0; i < PRIORITYCOUNT; i++) {
			ratios.add(new ArrayList<Integer>());
		}
		//Pref Gender ratio
		ratios.get(prefGenderPriority.ordinal()).add(new Integer(calculateRatio(
				GENDERS.length,
				Arrays.asList(GENDERS).indexOf(user.getPrefGender()),
				Arrays.asList(GENDERS).indexOf(other.getPrefGender()))));
		//Weight ratio
		ratios.get(weightPriority.ordinal()).add(new Integer(calculateRatio(
				WEIGHTCLASS.length,
				Arrays.asList(WEIGHTCLASS).indexOf(user.getWeight()),
				Arrays.asList(WEIGHTCLASS).indexOf(other.getWeight()))));
		//Style ratio
		ratios.get(stylePriority.ordinal()).add(new Integer(calculateRatio(
				STYLES.length,
				Arrays.asList(STYLES).indexOf(user.getStyle()),
				Arrays.asList(STYLES).indexOf(other.getStyle()))));
		//Times ratio
		ratios.get(timePriority.ordinal()).add(new Integer(calculateRatio(
				TIMES.length,
				Arrays.asList(TIMES).indexOf(user.getTimeOfDay()),
				Arrays.asList(TIMES).indexOf(other.getTimeOfDay()))));
		//Frequency ratio
		ratios.get(freqPriority.ordinal()).add(new Integer(calculateRatio(
				FREQUENCIES.length,
				Arrays.asList(FREQUENCIES).indexOf(user.getFrequency()),
				Arrays.asList(FREQUENCIES).indexOf(other.getFrequency()))));
		//Goals ratio
		ratios.get(goalPriority.ordinal()).add(new Integer(calculateRatio(
				GOALS.length,
				Arrays.asList(GOALS).indexOf(user.getGoals()),
				Arrays.asList(GOALS).indexOf(other.getGoals()))));
		//Experience ratio
		ratios.get(expPriority.ordinal()).add(new Integer(calculateRatio(
				EXPERIENCE.length,
				Arrays.asList(EXPERIENCE).indexOf(user.getExperience()),
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
	//Calculates ratio between 2 users
	public static Integer calculateRatio(Integer length,Integer userOptIdx,Integer otherOptIdx) {
		Integer data = userOptIdx - otherOptIdx;
		if (data < 0) {
			data *= -1;
		}
		return (int)((1 - ((double)data / (double)length)) * 100);
	}

	public static Boolean has(List<Match> matches, Match match) {
		for (Match m : matches) {
			if ((m.getUser().getfName().equals(match.getUser().getfName()) &&			//Checking user to user
					m.getUser().getlName().equals(match.getUser().getlName()) &&
					m.getMatched().getfName().equals(match.getMatched().getfName()) &&	//Checking matched to matched
					m.getMatched().getlName().equals(match.getMatched().getlName())) ||
					(m.getUser().getfName().equals(match.getMatched().getfName()) &&	//Checking user to matched
					m.getUser().getlName().equals(match.getMatched().getlName()) &&
					m.getMatched().getfName().equals(match.getUser().getfName()) &&		//Checking matched to user
					m.getMatched().getlName().equals(match.getUser().getlName()))) {
				return true;
			}
		}
		return false;
	}
}
