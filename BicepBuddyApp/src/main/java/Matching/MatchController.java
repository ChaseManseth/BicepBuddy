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
		return UserController.getInstance().getUserById(match.getOther());
	}
	
	public static Match findMatch(Match match) {
		Match result = null;
		if (UserController.getUser().getAccepted().contains(match)) {
			result = UserController.getUser().getAccepted().get(UserController.getUser().getAccepted().indexOf(match));
		}
		else if (UserController.getUser().getRejected().contains(match)) {
			result = UserController.getUser().getRejected().get(UserController.getUser().getRejected().indexOf(match));
		}
		else if (UserController.getUser().getWaiting().contains(match)) {
			result = UserController.getUser().getWaiting().get(UserController.getUser().getWaiting().indexOf(match));
		}
		return result;
	}
	
	public static void acceptMatchInitial(Match match) {
		//add to primary accepted
		List<Match> matches = UserController.getUser().getAccepted();
		matches.add(match);
		UserController.getUser().setAccepted(matches);
		//add to other waiting
		matches = UserController.getInstance().getUserById(match.getOther()).getWaiting();
		matches.add(match);
		UserController.getInstance().getUserById(match.getOther()).setWaiting(matches);
	}
	
	public static void acceptMatchOther(Match match) {
		//add to other accepted
		match.accept();
		List<Match> matches = UserController.getInstance().getUserById(match.getOther()).getWaiting();
		matches.remove(match);
		UserController.getInstance().getUserById(match.getOther()).setWaiting(matches);
		
		matches = UserController.getInstance().getUserById(match.getOther()).getAccepted();
		matches.add(match);
		UserController.getInstance().getUserById(match.getOther()).setAccepted(matches);
	}
	
	public static void rejectMatch(Match match) {
		//add to both rejected
		match.reject();
		List<Match> matches = UserController.getUser().getAccepted();
		if (matches.contains(match)) {
			matches.remove(match);
			UserController.getUser().setAccepted(matches);
		}
		matches = UserController.getUser().getRejected();
		matches.add(match);
		UserController.getUser().setRejected(matches);
		
		UserController.getInstance().getUserById(match.getOther()).getWaiting();
		if (matches.contains(match)) {
			matches.remove(match);
			UserController.getInstance().getUserById(match.getOther()).setWaiting(matches);
		}
		matches = UserController.getInstance().getUserById(match.getOther()).getRejected();
		matches.add(match);
		UserController.getInstance().getUserById(match.getOther()).setRejected(matches);
	}
	
}
