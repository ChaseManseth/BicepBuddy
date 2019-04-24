package Matching;

import java.util.ArrayList;
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
	
	public static User getOther(Match match) {
		return UserController.getUserById(match.getOther());
	}
	
	public static Match findMatch(Match match) {
		Match result = null;
		if (UserController.getUserById(UserController.getUser()).getAccepted().contains(match)) {
			result = UserController.getUserById(UserController.getUser()).getAccepted().get(UserController.getUserById(UserController.getUser()).getAccepted().indexOf(match));
		}
		else if (UserController.getUserById(UserController.getUser()).getRejected().contains(match)) {
			result = UserController.getUserById(UserController.getUser()).getRejected().get(UserController.getUserById(UserController.getUser()).getRejected().indexOf(match));
		}
		else if (UserController.getUserById(UserController.getUser()).getWaiting().contains(match)) {
			result = UserController.getUserById(UserController.getUser()).getWaiting().get(UserController.getUserById(UserController.getUser()).getWaiting().indexOf(match));
		}
		return result;
	}
	
	public static void acceptMatchInitial(Match match) {
		//add to primary accepted
		List<Match> matches = UserController.getUserById(UserController.getUser()).getAccepted();
		matches.add(match);
		UserController.getUserById(UserController.getUser()).setAccepted(matches);
		//add to other waiting
		matches = UserController.getUserById(match.getOther()).getWaiting();
		matches.add(match);
		UserController.getUserById(match.getOther()).setWaiting(matches);
	}
	
	public static void acceptMatchOther(Match match) {
		//add to other accepted
		match.accept();
		List<Match> matches = UserController.getUserById(match.getOther()).getWaiting();
		matches.remove(match);
		UserController.getUserById(match.getOther()).setWaiting(matches);
		
		matches = UserController.getUserById(match.getOther()).getAccepted();
		matches.add(match);
		UserController.getUserById(match.getOther()).setAccepted(matches);
	}
	
	public static void rejectMatch(Match match) {
		//add to both rejected
		match.reject();
		List<Match> matches = UserController.getUserById(UserController.getUser()).getAccepted();
		if (matches.contains(match)) {
			matches.remove(match);
			UserController.getUserById(UserController.getUser()).setAccepted(matches);
		}
		matches = UserController.getUserById(UserController.getUser()).getRejected();
		matches.add(match);
		UserController.getUserById(UserController.getUser()).setRejected(matches);
		
		UserController.getUserById(match.getOther()).getWaiting();
		if (matches.contains(match)) {
			matches.remove(match);
			UserController.getUserById(match.getOther()).setWaiting(matches);
		}
		matches = UserController.getUserById(match.getOther()).getRejected();
		matches.add(match);
		UserController.getUserById(match.getOther()).setRejected(matches);
	}
	
}
