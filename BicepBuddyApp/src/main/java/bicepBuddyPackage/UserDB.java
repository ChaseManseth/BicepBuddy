package bicepBuddyPackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

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
					return false;
				}
			}
			
			reader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	//TO-DO: STORE HASHES OF PASSWORD, NOT PLAINTEXT
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
						return true;
					}
					Master.appLogger.info(":: User's password was invalid.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		ErrorGUI eg = new ErrorGUI("Please enter valid username and password.");
		return false;
	}
}
