package views;
public class TestMatch {
	private String matchName;
	private double matchStr;
	
	public TestMatch(String matchName, double matchStr) {
		super();
		this.matchName = matchName;
		this.matchStr = matchStr;
	}
	public String getMatchName() {
		return matchName;
	}
	public void setMatchName(String matchName) {
		this.matchName = matchName;
	}
	public double getMatchStr() {
		return matchStr;
	}
	public void setMatchStr(double matchStr) {
		this.matchStr = matchStr;
	}
	
	
}
