package bicepBuddyPackage;

import java.util.Calendar;
import java.util.Date;

public class Match {
	public static Integer KILLTIMEALLOTTED = 2;
	
	public static enum Status{
		Accepted,
		Rejected,
		Idle
	}
	
	private User user;
	private User matched;
	private Status status;
	private Date dateCreated;
	private Date killDate;
	private double strength;
	
	public Match(User user,User matched,double strength){
		this.user = user;
		this.matched = matched;
		this.status = Status.Idle;
		this.dateCreated = new Date();
		this.strength = strength;
		//Adding hours to kill date
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.HOUR_OF_DAY,KILLTIMEALLOTTED);
		this.killDate = c.getTime();
	}
	//Used to determine status
	public void accept() {
		status = Status.Accepted;
	}
	public void reject() {
		status = Status.Rejected;
	}
	public void idle() {
		status = Status.Idle;
	}
	public Status getStatus() {
		return status;
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
		return "Match [user=" + user + ", matched=" + matched + ", status=" + status + ", dateCreated=" + dateCreated + ", killDate=" + killDate + ", strength="
				+ strength + "]";
	}
	
}
