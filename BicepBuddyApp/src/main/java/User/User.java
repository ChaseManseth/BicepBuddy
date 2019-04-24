package User;

import java.util.ArrayList;
import java.util.List;

import Matching.Match;

public class User {
	private String id;
	private String jwt;
	private String fName;
	private String lName;
	private String email;
	private String phone;
	private String age;
	private String gender;
	private String prefGender;
	private String goals;
	private String frequency;
	private String timeOfDay;
	private String style;
	private String weight;
	private String experience;
	private List<Match> accepted;
	private List<Match> rejected;
	private List<Match> idle;
	private List<Match> waiting;
	
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
	}
	
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
	}
	
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
		this.accepted = new ArrayList<>();
		this.rejected = new ArrayList<>();
		this.idle = new ArrayList<>();
		this.waiting = new ArrayList<>();
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", jwt=" + jwt + ", fName=" + fName + ", lName=" + lName + ", email=" + email
				+ ", phone=" + phone + ", age=" + age + ", gender=" + gender + ", prefGender=" + prefGender + ", goals="
				+ goals + ", frequency=" + frequency + ", timeOfDay=" + timeOfDay + ", style=" + style + ", weight="
				+ weight + ", experience=" + experience + ", accepted=" + accepted + ", rejected=" + rejected
				+ ", idle=" + idle + ", waiting=" + waiting + "]";
	}
	public User() {
	}
	public List<Match> getAccepted() {
		return accepted;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	public void setAccepted(List<Match> accepted) {
		this.accepted = accepted;
	}
	public List<Match> getRejected() {
		return rejected;
	}
	public void setRejected(List<Match> rejected) {
		this.rejected = rejected;
	}
	public List<Match> getIdle() {
		return idle;
	}
	public void setIdle(List<Match> idle) {
		this.idle = idle;
	}
	public List<Match> getWaiting() {
		return waiting;
	}
	public void setWaiting(List<Match> waiting) {
		this.waiting = waiting;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPrefGender() {
		return prefGender;
	}
	public void setPrefGender(String prefGender) {
		this.prefGender = prefGender;
	}
	public String getGoals() {
		return goals;
	}
	public void setGoals(String goals) {
		this.goals = goals;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getTimeOfDay() {
		return timeOfDay;
	}
	public void setTimeOfDay(String timeOfDay) {
		this.timeOfDay = timeOfDay;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
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
