/*
 * @authors: Zachary Steudel, Hunter Long, Chase Manseth, Bob Rein, Reece Kemball-Cook
 */
package User;

import java.util.ArrayList;
import java.util.List;

import Matching.Match;
import Messaging.Message;

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 * @authors: Zachary Steudel, Hunter Long, Chase Manseth, Bob Rein, Reece Kemball-Cook
 */
public class User {
	
	/** The id. */
	private String id;
	
	/** The jwt. */
	private String jwt;
	
	/** The name. */
	private String fName;
	
	/** The l name. */
	private String lName;
	
	/** The email. */
	private String email;
	
	/** The phone. */
	private String phone;
	
	/** The age. */
	private String age;
	
	/** The gender. */
	private String gender;
	
	/** The pref gender. */
	private String prefGender;
	
	/** The goals. */
	private String goals;
	
	/** The frequency. */
	private String frequency;
	
	/** The time of day. */
	private String timeOfDay;
	
	/** The style. */
	private String style;
	
	/** The weight. */
	private String weight;
	
	/** The experience. */
	private String experience;
	
	/** The profilePic. */
	private String profilePic;
	
	/** The accepted. */
	private List<Match> accepted;
	
	/** The rejected. */
	private List<Match> rejected;
	
	/** The idle. */
	private List<Match> idle;
	
	/** The waiting. */
	private List<Match> waiting;
	
	/** The accepted users. */
	// users in memory to speed up profile view loading
	private List<User> acceptedUsers;
	
	/** The rejected users. */
	private List<User> rejectedUsers;
	
	/** The idle users. */
	private List<User> idleUsers;
	
	/** The waiting users. */
	private List<User> waitingUsers;
	
	/** The pending users. */
	private List<User> pendingUsers;
	
	/** The notifications. */
	private List<Message> notifications;


	/**
	 * Gets the pending users.
	 *
	 * @return the pending users
	 */
	public List<User> getPendingUsers() {
		return pendingUsers;
	}
	
	/**
	 * Gets the notifications.
	 *
	 * @return the notifications
	 */
	public List<Message> getNotifications() {
		return notifications;
	}

	/**
	 * Sets the notifications.
	 *
	 * @param notifications the new notifications
	 */
	public void setNotifications(List<Message> notifications) {
		this.notifications = notifications;
	}

	/**
	 * Sets the pending users.
	 *
	 * @param pendingUsers the new pending users
	 */
	public void setPendingUsers(List<User> pendingUsers) {
		this.pendingUsers = pendingUsers;
	}


	/**
	 * Gets the accepted users.
	 *
	 * @return the accepted users
	 */
	public List<User> getAcceptedUsers() {
		return acceptedUsers;
	}


	/**
	 * Sets the accepted users.
	 *
	 * @param acceptedUsers the new accepted users
	 */
	public void setAcceptedUsers(List<User> acceptedUsers) {
		this.acceptedUsers = acceptedUsers;
	}


	/**
	 * Gets the rejected users.
	 *
	 * @return the rejected users
	 */
	public List<User> getRejectedUsers() {
		return rejectedUsers;
	}


	/**
	 * Sets the rejected users.
	 *
	 * @param rejectedUsers the new rejected users
	 */
	public void setRejectedUsers(List<User> rejectedUsers) {
		this.rejectedUsers = rejectedUsers;
	}


	/**
	 * Gets the idle users.
	 *
	 * @return the idle users
	 */
	public List<User> getIdleUsers() {
		return idleUsers;
	}


	/**
	 * Sets the idle users.
	 *
	 * @param idleUsers the new idle users
	 */
	public void setIdleUsers(List<User> idleUsers) {
		this.idleUsers = idleUsers;
	}


	/**
	 * Gets the waiting users.
	 *
	 * @return the waiting users
	 */
	public List<User> getWaitingUsers() {
		return waitingUsers;
	}


	/**
	 * Sets the waiting users.
	 *
	 * @param waitingUsers the new waiting users
	 */
	public void setWaitingUsers(List<User> waitingUsers) {
		this.waitingUsers = waitingUsers;
	}


	/**
	 * Instantiates a new user.
	 *
	 * @param uDetails the u details
	 */
	public User(String[] uDetails) {		
		setEmail(uDetails[0]);
		setfName(uDetails[2]);
		setlName(uDetails[3]);
		setStyle(uDetails[4]);
		setTimeOfDay(uDetails[5]);
		setGender(uDetails[6]);
		setPrefGender(uDetails[7]);
		setFrequency(uDetails[8]);;
		setWeight(uDetails[9]);
		setPhone(uDetails[10]);
		setAge(uDetails[11]);
		setGoals(uDetails[12]);
		setExperience(uDetails[13]);
	}
	
	
	/**
	 * Instantiates a new user.
	 *
	 * @param fName the f name
	 * @param lName the l name
	 * @param email the email
	 * @param phone the phone
	 * @param age the age
	 * @param gender the gender
	 * @param prefGender the pref gender
	 * @param goals the goals
	 * @param frequency the frequency
	 * @param timeOfDay the time of day
	 * @param style the style
	 * @param weight the weight
	 * @param experience the experience
	 */
	public User(String fName, String lName, String email, String phone, String age, String gender, String prefGender,
			String goals, String frequency, String timeOfDay, String style, String weight, String experience) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.phone = phone;
		this.age = age;
		this.gender = gender;
		this.prefGender = prefGender;
		this.goals = goals;
		this.frequency = frequency;
		this.timeOfDay = timeOfDay;
		this.style = style;
		this.weight = weight;
		this.experience = experience;
		
		this.accepted = new ArrayList<>();
		this.rejected = new ArrayList<>();
		this.idle = new ArrayList<>();
		this.waiting = new ArrayList<>();
		
		this.acceptedUsers = new ArrayList<>();
		this.rejectedUsers = new ArrayList<>();
		this.idleUsers = new ArrayList<>();
		this.waitingUsers = new ArrayList<>();
		this.pendingUsers = new ArrayList<>();
	}
	
	/**
	 * Instantiates a new user.
	 *
	 * @param id the id
	 * @param firstname the firstname
	 * @param lastname the lastname
	 * @param gender the gender
	 * @param profilePic the profile pic
	 * @param workoutStyle the workout style
	 * @param goals the goals
	 * @param experience the experience
	 * @param timeOfDay the time of day
	 * @param frequency the frequency
	 * @param preferredGender the preferred gender
	 * @param age the age
	 */
	// Modified Constructor for getting some data but not all from other users 
	public User(String id, String firstname, String lastname, String gender, String profilePic, String workoutStyle,
			String goals, String experience, String timeOfDay, String frequency, String preferredGender, String age) {
		this.id = id;
		this.fName = firstname;
		this.lName = lastname;
		this.gender = gender;
		this.style = workoutStyle;
		this.goals = goals;
		this.experience = experience;
		this.timeOfDay = timeOfDay;
		this.frequency = frequency;
		this.prefGender = preferredGender;
		this.age = age;
		
		// Create list
		this.accepted = new ArrayList<>();
		this.rejected = new ArrayList<>();
		this.idle = new ArrayList<>();
		this.waiting = new ArrayList<>();
		
		this.acceptedUsers = new ArrayList<>();
		this.rejectedUsers = new ArrayList<>();
		this.idleUsers = new ArrayList<>();
		this.waitingUsers = new ArrayList<>();
		this.pendingUsers = new ArrayList<>();
	}
	
	/**
	 * Update user.
	 *
	 * @param email the email
	 * @param fName the f name
	 * @param lName the l name
	 * @param style the style
	 * @param timeOfDay the time of day
	 * @param gender the gender
	 * @param prefGender the pref gender
	 * @param freq the freq
	 * @param goal the goal
	 * @param weight the weight
	 * @param exp the exp
	 * @param age the age
	 * @param phone the phone
	 */
	public void updateUser(String email, String fName, String lName, Object style, Object timeOfDay,
			Object gender, Object prefGender, Object freq, Object goal, Object weight, Object exp, 
			String age, String phone) {
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.phone = phone;
		this.age = age;
		this.gender = (String) gender;
		this.prefGender = (String) prefGender;
		this.goals = (String) goal;
		this.frequency = (String) freq;
		this.timeOfDay = (String) timeOfDay;
		this.style = (String) style;
		this.weight = (String) weight;
		this.experience = (String) exp;
		/*this.accepted = new ArrayList<>();
		this.rejected = new ArrayList<>();
		this.idle = new ArrayList<>();
		this.waiting = new ArrayList<>();*/
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return fName + " " + lName;
	}
	
	/**
	 * Instantiates a new user.
	 */
	public User() {
	}
	
	/**
	 * Gets the accepted.
	 *
	 * @return the accepted
	 */
	public List<Match> getAccepted() {
		return accepted;
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
	 * Gets the profilePic.
	 *
	 * @return the profilePic
	 */
	public String getProfilePic() {
		return profilePic;
	}
	
	/**
	 * Sets the profilePic.
	 *
	 * @param pic the new profile pic
	 */
	public void setProfilePic(String pic) {
		this.profilePic = pic;
	}
	
	/**
	 * Gets the jwt.
	 *
	 * @return the jwt
	 */
	public String getJwt() {
		return jwt;
	}
	
	/**
	 * Sets the jwt.
	 *
	 * @param jwt the new jwt
	 */
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	
	/**
	 * Sets the accepted.
	 *
	 * @param accepted the new accepted
	 */
	public void setAccepted(List<Match> accepted) {
		this.accepted = accepted;
	}
	
	/**
	 * Gets the rejected.
	 *
	 * @return the rejected
	 */
	public List<Match> getRejected() {
		return rejected;
	}
	
	/**
	 * Sets the rejected.
	 *
	 * @param rejected the new rejected
	 */
	public void setRejected(List<Match> rejected) {
		this.rejected = rejected;
	}
	
	/**
	 * Gets the idle.
	 *
	 * @return the idle
	 */
	public List<Match> getIdle() {
		return idle;
	}
	
	/**
	 * Sets the idle.
	 *
	 * @param idle the new idle
	 */
	public void setIdle(List<Match> idle) {
		this.idle = idle;
	}
	
	/**
	 * Gets the waiting.
	 *
	 * @return the waiting
	 */
	public List<Match> getWaiting() {
		return waiting;
	}
	
	/**
	 * Sets the waiting.
	 *
	 * @param waiting the new waiting
	 */
	public void setWaiting(List<Match> waiting) {
		this.waiting = waiting;
	}
	
	/**
	 * Gets the f name.
	 *
	 * @return the f name
	 */
	public String getfName() {
		return fName;
	}
	
	/**
	 * Sets the f name.
	 *
	 * @param fName the new f name
	 */
	public void setfName(String fName) {
		this.fName = fName;
	}
	
	/**
	 * Gets the l name.
	 *
	 * @return the l name
	 */
	public String getlName() {
		return lName;
	}
	
	/**
	 * Sets the l name.
	 *
	 * @param lName the new l name
	 */
	public void setlName(String lName) {
		this.lName = lName;
	}
	
	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Gets the phone.
	 *
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * Sets the phone.
	 *
	 * @param phone the new phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * Gets the age.
	 *
	 * @return the age
	 */
	public String getAge() {
		return age;
	}
	
	/**
	 * Sets the age.
	 *
	 * @param age the new age
	 */
	public void setAge(String age) {
		this.age = age;
	}
	
	/**
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	
	/**
	 * Sets the gender.
	 *
	 * @param gender the new gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	/**
	 * Gets the pref gender.
	 *
	 * @return the pref gender
	 */
	public String getPrefGender() {
		return prefGender;
	}
	
	/**
	 * Sets the pref gender.
	 *
	 * @param prefGender the new pref gender
	 */
	public void setPrefGender(String prefGender) {
		this.prefGender = prefGender;
	}
	
	/**
	 * Gets the goals.
	 *
	 * @return the goals
	 */
	public String getGoals() {
		return goals;
	}
	
	/**
	 * Sets the goals.
	 *
	 * @param goals the new goals
	 */
	public void setGoals(String goals) {
		this.goals = goals;
	}
	
	/**
	 * Gets the frequency.
	 *
	 * @return the frequency
	 */
	public String getFrequency() {
		return frequency;
	}
	
	/**
	 * Sets the frequency.
	 *
	 * @param frequency the new frequency
	 */
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	
	/**
	 * Gets the time of day.
	 *
	 * @return the time of day
	 */
	public String getTimeOfDay() {
		return timeOfDay;
	}
	
	/**
	 * Sets the time of day.
	 *
	 * @param timeOfDay the new time of day
	 */
	public void setTimeOfDay(String timeOfDay) {
		this.timeOfDay = timeOfDay;
	}
	
	/**
	 * Gets the style.
	 *
	 * @return the style
	 */
	public String getStyle() {
		return style;
	}
	
	/**
	 * Sets the style.
	 *
	 * @param style the new style
	 */
	public void setStyle(String style) {
		this.style = style;
	}
	
	/**
	 * Gets the weight.
	 *
	 * @return the weight
	 */
	public String getWeight() {
		return weight;
	}
	
	/**
	 * Sets the weight.
	 *
	 * @param weight the new weight
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}
	
	/**
	 * Gets the experience.
	 *
	 * @return the experience
	 */
	public String getExperience() {
		return experience;
	}
	
	/**
	 * Sets the experience.
	 *
	 * @param experience the new experience
	 */
	public void setExperience(String experience) {
		this.experience = experience;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accepted == null) ? 0 : accepted.hashCode());
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((experience == null) ? 0 : experience.hashCode());
		result = prime * result + ((fName == null) ? 0 : fName.hashCode());
		result = prime * result + ((frequency == null) ? 0 : frequency.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((goals == null) ? 0 : goals.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idle == null) ? 0 : idle.hashCode());
		result = prime * result + ((jwt == null) ? 0 : jwt.hashCode());
		result = prime * result + ((lName == null) ? 0 : lName.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((prefGender == null) ? 0 : prefGender.hashCode());
		result = prime * result + ((rejected == null) ? 0 : rejected.hashCode());
		result = prime * result + ((style == null) ? 0 : style.hashCode());
		result = prime * result + ((timeOfDay == null) ? 0 : timeOfDay.hashCode());
		result = prime * result + ((waiting == null) ? 0 : waiting.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (accepted == null) {
			if (other.accepted != null)
				return false;
		} else if (!accepted.equals(other.accepted))
			return false;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (experience == null) {
			if (other.experience != null)
				return false;
		} else if (!experience.equals(other.experience))
			return false;
		if (fName == null) {
			if (other.fName != null)
				return false;
		} else if (!fName.equals(other.fName))
			return false;
		if (frequency == null) {
			if (other.frequency != null)
				return false;
		} else if (!frequency.equals(other.frequency))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (goals == null) {
			if (other.goals != null)
				return false;
		} else if (!goals.equals(other.goals))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idle == null) {
			if (other.idle != null)
				return false;
		} else if (!idle.equals(other.idle))
			return false;
		if (jwt == null) {
			if (other.jwt != null)
				return false;
		} else if (!jwt.equals(other.jwt))
			return false;
		if (lName == null) {
			if (other.lName != null)
				return false;
		} else if (!lName.equals(other.lName))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (prefGender == null) {
			if (other.prefGender != null)
				return false;
		} else if (!prefGender.equals(other.prefGender))
			return false;
		if (rejected == null) {
			if (other.rejected != null)
				return false;
		} else if (!rejected.equals(other.rejected))
			return false;
		if (style == null) {
			if (other.style != null)
				return false;
		} else if (!style.equals(other.style))
			return false;
		if (timeOfDay == null) {
			if (other.timeOfDay != null)
				return false;
		} else if (!timeOfDay.equals(other.timeOfDay))
			return false;
		if (waiting == null) {
			if (other.waiting != null)
				return false;
		} else if (!waiting.equals(other.waiting))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}
	
}
