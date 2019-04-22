package Matching;

import java.util.List;

import User.User;
import User.UserController;
import bicepBuddyPackage.Master;

public class MatchController {
	
	public static void generateFrame(User user) {
		Master.updateFrame(new MatchGUI(user));
	}
	
	public static void generateMatches(User user) {
		user.setIdle(MatchAlgorithm.matchUser(user));
	}
	
	public static Match directMatch(User user) {
		return MatchAlgorithm.directMatch(UserController.getUser(),user);
	}
	
	public static List<Match> getMatches(User user){
		return user.getIdle();
	}
	
	public static void acceptMatchInitial(Match match) {
		//add to primary accepted
		List<Match> matches = match.getUser().getAccepted();
		matches.add(match);
		match.getUser().setAccepted(matches);
		//add to other waiting
		matches = match.getMatched().getWaiting();
		matches.add(match);
		match.getMatched().setWaiting(matches);
	}
	
	public static void acceptMatchOther(Match match) {
		//add to other accepted
		match.accept();
		List<Match> matches = match.getMatched().getWaiting();
		matches.remove(match);
		match.getMatched().setWaiting(matches);
		
		matches = match.getMatched().getAccepted();
		matches.add(match);
		match.getMatched().setAccepted(matches);
	}
	
	public static void rejectMatch(Match match) {
		//add to both rejected
		match.reject();
		List<Match> matches = match.getUser().getAccepted();
		if (matches.contains(match)) {
			matches.remove(match);
			match.getUser().setAccepted(matches);
		}
		matches = match.getUser().getRejected();
		matches.add(match);
		match.getUser().setRejected(matches);
		
		matches = match.getMatched().getWaiting();
		if (matches.contains(match)) {
			matches.remove(match);
			match.getMatched().setWaiting(matches);
		}
		matches = match.getMatched().getRejected();
		matches.add(match);
		match.getMatched().setRejected(matches);
	}
	
}
