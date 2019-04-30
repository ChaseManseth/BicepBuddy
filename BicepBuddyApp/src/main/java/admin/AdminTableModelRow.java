package admin;

import User.User;

public class AdminTableModelRow {
	
	/** User whose data is used for this row. */
	User myUser;
	
	/**  */
	String deleteString = "delete";
	
	/**  */
	String viewString = "view";
	
	/** Gets the user for this row. */
	public User getMyUser() {
		return myUser;
	}
	
	public AdminTableModelRow() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates this row with data from myUser
	 * 
	 * @param myUser the User whose data will populate this row
	 */
	public AdminTableModelRow(User myUser) {
		super();
		this.myUser = myUser;
	}

	/**
	 * Update value of this row's myUser
	 * 
	 * @param myUser new User
	 */
	public void setMyUser(User myUser) {
		this.myUser = myUser;
	}
	
	/**
	 * Gets the deleteString
	 * 
	 * @return deleteString
	 */
	public String getDeleteString() {
		return deleteString;
	}
	
	/**
	 * Updates the value of deleteString
	 * 
	 * @param deleteString new value
	 */
	public void setDeleteString(String deleteString) {
		this.deleteString = deleteString;
	}
	
	/**
	 * Gets the viewString
	 * 
	 * @return viewString
	 */
	public String getViewString() {
		return viewString;
	}
	
	/**
	 * Updates the viewString
	 * 
	 * @param viewString new value
	 */
	public void setViewString(String viewString) {
		this.viewString = viewString;
	}
	
	
}
