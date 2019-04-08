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

			lineContent += u.getEmail();
			lineContent += ",";
			lineContent += password;
			lineContent += ",";
			lineContent += u.getfName();
			lineContent += ",";
			lineContent += u.getlName();
			lineContent += ",";
			lineContent += u.getStyle();
			lineContent += ",";
			lineContent += u.getTimeOfDay();
			lineContent += ",";
			
			lineContent += u.getGender();
			lineContent += ",";
			lineContent += u.getPrefGender();
			lineContent += ",";
			lineContent += u.getFrequency();
			lineContent += ",";
			lineContent += u.getWeight();
			lineContent += ",";
			lineContent += u.getPhone();
			lineContent += ",";
			lineContent += u.getAge();
			lineContent += ",";
			lineContent += u.getGoals();
			
			buf.write(lineContent);
			buf.newLine();
			
			buf.close();
		}
		catch(Exception e) {
			Master.appLogger.warning(":: File could not be opened.");
		}
	}
}
