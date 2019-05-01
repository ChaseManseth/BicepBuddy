package Matching;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import User.User;
import User.UserController;

/**
 * The Class Match.
 * @authors: Zachary Steudel, Hunter Long, Chase Manseth, Bob Rein, Reece Kemball-Cook
 */
public class Match {
	
	/** The killtimeallotted. */
	public static Integer KILLTIMEALLOTTED = 2;
	
	/**
	 * The Enum Status.
	 */
	public static enum Status{
		
		/** The Accepted. */
		Accepted,
		
		/** The Rejected. */
		Rejected,
		
		/** The Idle. */
		Idle;
	}
	
	/** The users. */
	private List<String> users;
	
	/** The status. */
	private Status status;
	
	/** The date created. */
	private Date dateCreated;
	
	/** The kill date. */
	private Date killDate;
	
	/** The strength. */
	private Integer strength;
	
	/** The id. */
	private String id;
	
	/**
	 * Instantiates a new match.
	 *
	 * @param user the user
	 * @param other the other
	 * @param strength the strength
	 */
	public Match(User user,User other,Integer strength){
		this.users = new ArrayList<String>();
		
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
	
	/**
	 * Instantiates a new match.
	 *
	 * @param id the id
	 * @param other the other
	 * @param strength the strength
	 */
	public Match(String id,String other,Integer strength){
		this.users = new ArrayList<String>();
		this.users.add(id);
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
	
	/**
	 * Instantiates a new match.
	 *
	 * @param id the id
	 * @param other the other
	 * @param strength the strength
	 * @param status the status
	 * @param matchId the match id
	 */
	// Constructor for MatchController getMatchById
	public Match(String id,String other,Integer strength, Integer status, String matchId){
		this.users = new ArrayList<String>();
		this.users.add(id);
		this.users.add(other);
		this.strength = strength;
		this.id = matchId;
		
		// Setting Status
		if(status == 0) {
			this.status = Status.Accepted;
		} else if(status == 1) {
			this.status = Status.Rejected;
		} else if(status == 2) {
			this.status = Status.Idle;
		}
		
		
		this.dateCreated = new Date();
		//Adding hours to kill date
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.HOUR_OF_DAY,KILLTIMEALLOTTED);
		this.killDate = c.getTime();
	}
	
	/**
	 * Instantiates a new match.
	 *
	 * @param user the user
	 * @param other the other
	 */
	//Used for creating temporary matches for checking existance
	public Match(User user,User other) {
		this.users = new ArrayList<String>();
		this.users.add(user.getId());
		this.users.add(other.getId());
		status = Status.Idle;
		dateCreated = null;
		killDate = null;
		strength = 0;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Accept a match.
	 * Used to determine status
	 */
	public void accept() {
		status = Status.Accepted;
	}
	
	/**
	 * Reject a match.
	 */
	public void reject() {
		status = Status.Rejected;
	}
	
	/**
	 * Idle a match.
	 */
	public void idle() {
		status = Status.Idle;
	}
	
	/**
	 * Gets the other.
	 * Used to get user not currently logged in
	 *
	 * @return the other
	 */
	public String getOther() {
		if (UserController.getUser().getId().contentEquals(users.get(0))) {
			return users.get(1);
		}
		return users.get(0);
	}
	
	/**
	 * Gets the status of match.
	 *
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}
	
	/**
	 * Gets the users.
	 *
	 * @return the users
	 */
	public List<String> getUsers() {
		return users;
	}
	
	/**
	 * Sets the users.
	 *
	 * @param users the new users
	 */
	public void setUsers(List<String> users) {
		this.users = users;
	}
	
	/**
	 * Gets the date created.
	 *
	 * @return the date created
	 */
	public Date getDateCreated() {
		return dateCreated;
	}
	
	/**
	 * Sets the date created.
	 *
	 * @param dateCreated the new date created
	 */
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	/**
	 * Gets the kill date.
	 *
	 * @return the kill date
	 */
	public Date getKillDate() {
		return killDate;
	}
	
	/**
	 * Sets the kill date.
	 *
	 * @param killDate the new kill date
	 */
	public void setKillDate(Date killDate) {
		this.killDate = killDate;
	}
	
	/**
	 * Gets the strength.
	 *
	 * @return the strength
	 */
	public Integer getStrength() {
		return strength;
	}
	
	/**
	 * Sets the strength.
	 *
	 * @param strength the new strength
	 */
	public void setStrength(Integer strength) {
		this.strength = strength;
	}
	
	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	
	@Override
	public String toString() {
		return "Match [users=" + users + ", status=" + status + ", strength=" + strength + ", id=" + id + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		Match other = (Match) obj;
		if (users.contains(other.getUsers().get(0)) && users.contains(other.getUsers().get(1))) {
			return true;
		}
		return false;
	}
	
}
