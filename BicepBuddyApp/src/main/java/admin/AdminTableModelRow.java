package admin;

import User.User;

// TODO: Auto-generated Javadoc
/**
 * The Class AdminTableModelRow.
 * @authors: Zachary Steudel, Hunter Long, Chase Manseth, Bob Rein, Reece Kemball-Cook
 */
public class AdminTableModelRow {
	
	/** User whose data is used for this row. */
	User myUser;
	
	/** The delete string. */
	String deleteString = "delete";
	
	/** The view string. */
	String viewString = "view";
	
	/**
	 *  Gets the user for this row.
	 *
	 * @return the my user
	 */
	public User getMyUser() {
		return myUser;
	}
	
	/**
	 * Instantiates a new admin table model row.
	 */
	public AdminTableModelRow() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates this row with data from myUser.
	 *
	 * @param myUser the User whose data will populate this row
	 */
	public AdminTableModelRow(User myUser) {
		super();
		this.myUser = myUser;
	}

	/**
	 * Update value of this row's myUser.
	 *
	 * @param myUser new User
	 */
	public void setMyUser(User myUser) {
		this.myUser = myUser;
	}
	
	/**
	 * Gets the deleteString.
	 *
	 * @return deleteString
	 */
	public String getDeleteString() {
		return deleteString;
	}
	
	/**
	 * Updates the value of deleteString.
	 *
	 * @param deleteString new value
	 */
	public void setDeleteString(String deleteString) {
		this.deleteString = deleteString;
	}
	
	/**
	 * Gets the viewString.
	 *
	 * @return viewString
	 */
	public String getViewString() {
		return viewString;
	}
	
	/**
	 * Updates the viewString.
	 *
	 * @param viewString new value
	 */
	public void setViewString(String viewString) {
		this.viewString = viewString;
	}
	
	
}
