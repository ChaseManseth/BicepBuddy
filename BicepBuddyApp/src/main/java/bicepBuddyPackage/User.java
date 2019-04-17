package bicepBuddyPackage;

public class User {
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
	}
	public User() {
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
	
	
}
