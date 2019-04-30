package bicepBuddyPackage;

import java.util.List;

import org.json.simple.JSONArray;

import Matching.Match;
import Matching.MatchController;

public class Threader implements Runnable{

	private List<Match> matches;
	private JSONArray ar;
	
	public Threader(List<Match> m, JSONArray a) {
		matches = m;
		ar = a;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < ar.size(); i++) {
			String matchId = (String) ar.get(i);

			// Create the Match
			// TODO move function to MatchController
			Match m = MatchController.getMatchById(matchId);
			matches.add(m);

			// System.out.println(matchId);
		}
	}

}
