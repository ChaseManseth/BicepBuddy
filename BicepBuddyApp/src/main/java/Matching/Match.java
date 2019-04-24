package Matching;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import User.User;
import User.UserController;

public class Match {
	public static Integer KILLTIMEALLOTTED = 2;
	
	public static enum Status{
		Accepted,
		Rejected,
		Idle
	}
	
	private List<String> users;
	private Status status;
	private Date dateCreated;
	private Date killDate;
	private Integer strength;
	
	public Match(User user,User other,Integer strength){
		this.users.add(user.getId());
		this.users.add(other.getId());
		this.status = Status.Idle;
		this.dateCreated = new Date();
		this.strength = strength;
		//Adding hours to kill date
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.HOUR_OF_DAY,KILLTIMEALLOTTED);
		this.killDate = c.getTime();
	}
	public Match(String user,String other,Integer strength){
		this.users.add(user);
		this.users.add(other);
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
		this.users.add(user.getId());
		this.users.add(other.getId());
		status = Status.Idle;
		dateCreated = null;
		killDate = null;
		strength = 0;
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
	//Used to get user not currently logged in
	public String getOther() {
		if (UserController.getUser().getId() == users.get(0)) {
			return users.get(0);
		}
		return users.get(1);
	}
	
	public Status getStatus() {
		return status;
	}
	public List<String> getUsers() {
		return users;
	}
	public void setUsers(List<String> users) {
		this.users = users;
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
	public void setStatus(Status status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = prime * result + ((killDate == null) ? 0 : killDate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((strength == null) ? 0 : strength.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		Match other = (Match) obj;
		if (users.contains(other.getUsers().get(0)) && users.contains(other.getUsers().get(1))) {
			return true;
		}
		return false;
	}
	
}
