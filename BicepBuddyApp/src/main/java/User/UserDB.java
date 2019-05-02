package User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;

import bicepBuddyPackage.ErrorGUI;
import bicepBuddyPackage.Master;

/**
 * The Class UserDB (Deprecated).
 * USERDB IS NOT USED ANYMORE, AS WE USE AN ACTUAL DATABASE (MONGODB)
 * 
 * THESE FUNCTIONS ARE DEPRECATED NOW.
 * 
 * @authors: Zachary Steudel, Hunter Long, Chase Manseth, Bob Rein, Reece Kemball-Cook
 */
/*
 * class will interact with the database.
 */
public class UserDB {
	
	/** The reader. */
	private BufferedReader reader = null;
	
	/** The filename. */
	private String filename = "buddyDB.csv";
	
	/**
	 * Gets the filename.
	 *
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * Sets the filename.
	 *
	 * @param filename the new filename
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * Gets the reader.
	 *
	 * @return the reader
	 */
	public BufferedReader getReader() {
		return reader;
	}

	/**
	 * Sets the reader.
	 *
	 * @param reader the new reader
	 */
	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}
	
	/**
	 * Gets the number of users in the local CSV DB.
	 *
	 * @return the number of users in local CSV DB
	 */
	public int getNumUsers() {
		int numUsers = 0;
		try {
			reader = new BufferedReader(new FileReader(new File(filename)));
			String line;
			Master.appLogger.info(":: Opening CSV DB file to count number of users.");
			while ((line = reader.readLine()) != null) {
				numUsers++;
			}
			
			reader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return numUsers;
	}
	
	/**
	 * Tester get random user.
	 *
	 * @return a random user
	 */
	public User testerGetRandomUser() {
		Random r = new Random();
		int userToGet = r.nextInt(getNumUsers() + 1);
		
		try {
			reader = new BufferedReader(new FileReader(new File(filename)));
			String line;
			Master.appLogger.info(":: Opening CSV DB file to find random user.");
			int curUser = 0;
			while ((line = reader.readLine()) != null) {
				String[] split = line.split(",");
				
				//if this user in the db is the one that
				//random selection gave us, return it.
				if(curUser == userToGet) {
					return new User(split);
				}
				curUser++;
			}
			
			reader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Not exists.
	 *
	 * @param email email to verify with local CSV DB
	 * @return true, if email does not yet exist in the local CSV DB
	 */
	public boolean notExists(String email) {
		try {
			reader = new BufferedReader(new FileReader(new File(filename)));
			String line;
			Master.appLogger.info(":: Opening CSV DB file to check that user doesn't exist already.");
			//read the emails from the UserDB CSV and return true if the
			//user is not found, else return false
			while ((line = reader.readLine()) != null) {
				String[] split = line.split(",");
				
				//check first field: email
				if(email.contentEquals(split[0])) {
					Master.appLogger.info(":: email " + email + " exists in the DB already.");
					reader.close();
					return false;
				}
			}
			
			reader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	/**
	 * Adds the user.
	 *
	 * @param u the User to be added to the local CSV DB
	 * @param password the password to be assigned to u
	 */
	//TODO: STORE HASHES OF PASSWORD, NOT PLAINTEXT
	public void addUser(User u, String password) {
		try {
			BufferedWriter buf = new BufferedWriter(new FileWriter(filename, true));
			
			Master.appLogger.info(":: Adding new user " + u.getfName() + " " + u.getlName() + " to DB.");
			String lineContent = "";

			lineContent += u.getEmail(); //0
			lineContent += ",";
			lineContent += password; //1
			lineContent += ",";
			lineContent += u.getfName(); // 2
			lineContent += ",";
			lineContent += u.getlName(); // 3
			lineContent += ",";
			lineContent += u.getStyle(); // 4
			lineContent += ",";
			lineContent += u.getTimeOfDay(); // 5
			lineContent += ",";
			
			lineContent += u.getGender(); // 6
			lineContent += ",";
			lineContent += u.getPrefGender(); // 7
			lineContent += ",";
			lineContent += u.getFrequency(); // 8
			lineContent += ",";
			lineContent += u.getWeight(); // 9
			lineContent += ",";
			lineContent += u.getPhone(); // 10
			lineContent += ",";
			lineContent += u.getAge(); // 11
			lineContent += ",";
			lineContent += u.getGoals(); // 12
			lineContent += ",";
			lineContent += u.getExperience(); // 13
			
			buf.write(lineContent);
			buf.newLine();
			
			buf.close();
		}
		catch(Exception e) {
			Master.appLogger.warning(":: File could not be opened.");
		}
	}

	/**
	 * Login.
	 *
	 * @param email the email of the User logging in
	 * @param pass the password of the User logging in
	 * @return true, if login was successful
	 */
	public boolean login(String email, String pass) {
		try {
			BufferedReader buf = new BufferedReader(new FileReader(new File(filename)));
			String lineContent;
			
			while((lineContent = buf.readLine()) != null) {
				String[] split = lineContent.split(",");
				
				if(split[0].contentEquals(email)) {
					if(split[1].contentEquals(pass)) {
						UserController.setUserFromDB(split);
						buf.close();
						return true;
					}
					Master.appLogger.info(":: User's password was invalid.");
				}
			}
			
			buf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		new ErrorGUI("Please enter valid username and password.");
		return false;
	}

	// this method of rewriting the old file seems super inefficient but
	/**
	 * Delete user.
	 *
	 * @param u the User to be deleted from the local CSV DB
	 */
	// seems to be the accepted method online lel
	public void deleteUser(User u) {
		BufferedWriter writer = null;
		try {
			File dbCSV = new File(filename);
			File tempCSV = new File("buddyTemp.csv");
			
			reader = new BufferedReader(new FileReader(dbCSV));
			writer = new BufferedWriter(new FileWriter(tempCSV, true));
			String line;
			Master.appLogger.info(":: Opening CSV DB file to delete user " + u.getEmail());
			//read each line, write it to new DB if it doesn't match deleted user.
			while ((line = reader.readLine()) != null) {
				String[] split = line.split(",");
				
				//check first field: email
				if(!u.getEmail().contentEquals(split[0])) {
					writer.write(line);
					writer.newLine();
				}
			}
			
			reader.close();
			writer.close();
			
			//delete old csv and rename new db csv
			if(dbCSV.delete()) {
				Master.appLogger.info(":: Deleted User: " + u.getEmail());
			}
			tempCSV.renameTo(dbCSV);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Edits the user.
	 *
	 * @param u the User to edit
	 * @param email the new email
	 * @param fName the new first name
	 * @param lName the new last name
	 * @param style the new workout style
	 * @param timeOfDay the new time of day
	 * @param gender the new gender
	 * @param prefGender the new preferred gender
	 * @param freq the new workout frequency
	 * @param goal the new workout goal
	 * @param weight the new current weight
	 * @param exp the new level of experience
	 * @param age the new age
	 * @param phone the new phone number
	 * @return true, if successfully added to local CSV DB
	 */
	public boolean editUser(User u, String email, String fName, String lName, String style, String timeOfDay,
			String gender, String prefGender, String freq, String goal, String weight, String exp, String age, String phone) {
		BufferedWriter writer = null;
		
		Master.appLogger.info(":: Opening CSV DB file to edit user: " + u.getEmail());
		if(!email.contentEquals(u.getEmail()) && !notExists(email)) {
			new ErrorGUI("Email already exists. Can't change.");
			Master.appLogger.info(":: Couldn't edit user settings.");
			return false;
		}
		
		try {
			File dbCSV = new File(filename);
			File tempCSV = new File("buddyTemp.csv");
			
			reader = new BufferedReader(new FileReader(dbCSV));
			writer = new BufferedWriter(new FileWriter(tempCSV, true));
			String line;
			//read each line, write it to new DB if it doesn't match deleted user.
			while ((line = reader.readLine()) != null) {
				String[] split = line.split(",");
				
				//check first field: email
				if(!u.getEmail().contentEquals(split[0])) {
					writer.write(line);
				}
				else {
					//edit this user
					String lineContent = "";

					lineContent += email; //0
					lineContent += ",";
					lineContent += split[1]; //1
					lineContent += ",";
					lineContent += fName; // 2
					lineContent += ",";
					lineContent += lName; // 3
					lineContent += ",";
					lineContent += style; // 4
					lineContent += ",";
					lineContent += timeOfDay; // 5
					lineContent += ",";
					
					lineContent += gender; // 6
					lineContent += ",";
					lineContent += prefGender; // 7
					lineContent += ",";
					lineContent += freq; // 8
					lineContent += ",";
					lineContent += weight; // 9
					lineContent += ",";
					lineContent += phone; // 10
					lineContent += ",";
					lineContent += age; // 11
					lineContent += ",";
					lineContent += goal; // 12
					lineContent += ",";
					lineContent += exp; // 13
					
					writer.write(lineContent);
					
					u.setEmail(email);
					u.setfName(fName);
					u.setlName(lName);
					u.setStyle(style);
					u.setGender(gender);
					u.setPrefGender(prefGender);
					u.setFrequency(freq);
				}
				writer.newLine();
			}
			
			reader.close();
			writer.close();
			
			//delete old csv and rename new db csv
			dbCSV.delete();
			tempCSV.renameTo(dbCSV);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}
}
