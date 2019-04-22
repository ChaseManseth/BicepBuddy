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
	private Integer strength;
	
	public Match(User user,User matched,Integer strength){
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
	//Used for creating temporary matches for checking existance
	public Match(User user,User other) {
		this.user = user;
		this.matched = other;
		status = Status.Idle;
		dateCreated = null;
		killDate = null;
		strength = 0;
	}
	//Used for comparing to see if matches exist in user arrays
	public Boolean checkExist(Match match) {
		if (match.user == this.user && match.matched == this.matched) {
			return true;
		}
		return false;
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
	public Integer getStrength() {
		return strength;
	}
	public void setStrength(Integer strength) {
		this.strength = strength;
	}
	
	@Override
	public String toString() {
		return "Match [user=" + user + ", matched=" + matched + ", status=" + status + ", dateCreated=" + dateCreated + ", killDate=" + killDate + ", strength="
				+ strength + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = prime * result + ((killDate == null) ? 0 : killDate.hashCode());
		result = prime * result + ((matched == null) ? 0 : matched.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		long temp;
		temp = Double.doubleToLongBits(strength);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Match other = (Match) obj;
		if (dateCreated == null) {
			if (other.dateCreated != null)
				return false;
		} else if (!dateCreated.equals(other.dateCreated))
			return false;
		if (killDate == null) {
			if (other.killDate != null)
				return false;
		} else if (!killDate.equals(other.killDate))
			return false;
		if (matched == null) {
			if (other.matched != null)
				return false;
		} else if (!matched.equals(other.matched))
			return false;
		if (status != other.status)
			return false;
		if (Double.doubleToLongBits(strength) != Double.doubleToLongBits(other.strength))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	
	
}
