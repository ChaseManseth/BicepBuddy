package User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;

import bicepBuddyPackage.ErrorGUI;
import bicepBuddyPackage.Master;

/*
 * class will interact with the database.
 */
public class UserDB {
	private BufferedReader reader = null;
	private String filename = "buddyDB.csv";
	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public BufferedReader getReader() {
		return reader;
	}

	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}
	
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
		
		
		ErrorGUI eg = new ErrorGUI("Please enter valid username and password.");
		return false;
	}

	// this method of rewriting the old file seems super inefficient but
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

	public boolean editUser(User u, String email, String fName, String lName, String style, String timeOfDay,
			String gender, String prefGender, String freq, String goal, String weight, String exp, String age, String phone) {
		BufferedWriter writer = null;
		
		Master.appLogger.info(":: Opening CSV DB file to edit user: " + u.getEmail());
		if(!email.contentEquals(u.getEmail()) && !notExists(email)) {
			ErrorGUI eg = new ErrorGUI("Email already exists. Can't change.");
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
