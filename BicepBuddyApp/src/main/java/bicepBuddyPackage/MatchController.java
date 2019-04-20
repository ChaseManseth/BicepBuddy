package bicepBuddyPackage;

import java.util.ArrayList;
import java.util.List;

public class MatchController {
	
	public static void generateFrame(User user) {
		Master.updateFrame(new MatchGUI(user));
	}
	
	public static void generateMatches(User user) {
		user.setIdle(MatchAlgorithm.matchUser(user));
	}
	
	public static List<Match> getMatches(User user){
		return user.getIdle();
	}
	
	public static void acceptMatchInitial(Match match) {
		//add to primary accepted
		List<Match> matches = match.getUser().getAccepted();
		if (matches == null) {
			matches = new ArrayList<Match>();
		}
		matches.add(match);
		match.getUser().setAccepted(matches);
		//add to other waiting
		matches = match.getMatched().getWaiting();
		if (matches == null) {
			matches = new ArrayList<Match>();
		}
		matches.add(match);
		match.getMatched().setWaiting(matches);
	}
	
	public void acceptMatchOther(Match match) {
		//add to other accepted
		match.accept();
		List<Match> matches = match.getMatched().getWaiting();
		matches.remove(match);
		match.getMatched().setWaiting(matches);
		
		matches = match.getMatched().getAccepted();
		if (matches == null) {
			matches = new ArrayList<Match>();
		}
		matches.add(match);
		match.getMatched().setAccepted(matches);
	}
	
	public static void rejectMatch(Match match) {
		//add to both rejected
		match.reject();
		List<Match> matches = match.getUser().getAccepted();
		if (matches != null) {
			if (matches.contains(match)) {
				matches.remove(match);
				match.getUser().setAccepted(matches);
			}
		}
		matches = match.getUser().getRejected();
		if (matches == null) {
			matches = new ArrayList<Match>();
		}
		matches.add(match);
		match.getUser().setRejected(matches);
		
		matches = match.getMatched().getWaiting();
		if (matches != null) {
			if (matches.contains(match)) {
				matches.remove(match);
				match.getMatched().setWaiting(matches);
			}
		}
		matches = match.getMatched().getRejected();
		if (matches == null) {
			matches = new ArrayList<Match>();
		}
		matches.add(match);
		match.getMatched().setRejected(matches);
	}
	
}
