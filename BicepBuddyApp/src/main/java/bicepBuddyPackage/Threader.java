package bicepBuddyPackage;

import java.util.List;

import org.json.simple.JSONArray;

import Matching.Match;
import Matching.MatchController;


/**
 * The Class Threader. Threader will be used for matching to speed up and delegate
 * the process of matching users.
 * 
 *  @authors: Zachary Steudel, Hunter Long, Chase Manseth, Bob Rein, Reece Kemball-Cook
 */
public class Threader implements Runnable{

	/** The matches to be delegated. */
	private List<Match> matches;
	
	/** The JSONArray to grab the matches from. */
	private JSONArray ar;
	
	/**
	 * Instantiates a new threader.
	 *
	 * @param m: matching array
	 * @param a: jsonarray
	 */
	public Threader(List<Match> m, JSONArray a) {
		matches = m;
		ar = a;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		for(int i = 0; i < ar.size(); i++) {
			String matchId = (String) ar.get(i);
			// Create the Match
			Match m = MatchController.getMatchById(matchId);
			matches.add(m);
		}
	}

}
