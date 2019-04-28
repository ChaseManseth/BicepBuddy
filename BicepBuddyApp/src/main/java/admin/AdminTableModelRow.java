package admin;

import User.User;

public class AdminTableModelRow {
	User myUser;
	String deleteString = "delete";
	String viewString = "view";
	public User getMyUser() {
		return myUser;
	}
	
	public AdminTableModelRow() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminTableModelRow(User myUser) {
		super();
		this.myUser = myUser;
	}

	public void setMyUser(User myUser) {
		this.myUser = myUser;
	}
	public String getDeleteString() {
		return deleteString;
	}
	public void setDeleteString(String deleteString) {
		this.deleteString = deleteString;
	}
	public String getViewString() {
		return viewString;
	}
	public void setViewString(String viewString) {
		this.viewString = viewString;
	}
	
	
}
