package bicepBuddyPackage;

import java.util.Calendar;
import java.util.Date;

public class Match {
	public static Integer KILLTIMEALLOTTED = 2;
	
	private User user;
	private User matched;
	private Boolean accepted;
	private Boolean rejected;
	private Boolean idle;
	private Date dateCreated;
	private Date killDate;
	private double strength;
	
	public Match(User user,User matched,double strength){
		this.user = user;
		this.matched = matched;
		this.accepted = false;
		this.rejected = false;
		this.idle = true;
		this.dateCreated = new Date();
		this.strength = strength;
		//Adding hours to kill date
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.HOUR_OF_DAY,KILLTIMEALLOTTED);
		this.killDate = c.getTime();
	}
	//Used to update a match to the accepted status
	public void accepted() {
		accepted = true;
		rejected = false;
		idle = false;
	}
	public Boolean isAccepted() {
		return accepted;
	}
	//Used to update a match to the rejected status
	public void rejected() {
		accepted = false;
		rejected = true;
		idle = false;
	}
	public Boolean isRejected() {
		return rejected;
	}
	//Used to update a match to the idle status
	public void idle() {
		accepted = false;
		rejected = false;
		idle = true;
	}
	public Boolean isIdle() {
		return idle;
	}
	
	//Getters and setters
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getMatched() {
		return matched;
	}
	public void setMatched(User matched) {
		this.matched = matched;
	}
	public Boolean getAccepted() {
		return accepted;
	}
	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}
	public Boolean getRejected() {
		return rejected;
	}
	public void setRejected(Boolean rejected) {
		this.rejected = rejected;
	}
	public Boolean getIdle() {
		return idle;
	}
	public void setIdle(Boolean idle) {
		this.idle = idle;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getKillDate() {
		return killDate;
	}
	public void setKillDate(Date killDate) {
		this.killDate = killDate;
	}
	public double getStrength() {
		return strength;
	}
	public void setStrength(double strength) {
		this.strength = strength;
	}
	
	@Override
	public String toString() {
		return "Match [user=" + user + ", matched=" + matched + ", accepted=" + accepted + ", rejected=" + rejected
				+ ", idle=" + idle + ", dateCreated=" + dateCreated + ", killDate=" + killDate + ", strength="
				+ strength + "]";
	}
	
}
