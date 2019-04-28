package Views;

import User.User;
import bicepBuddyPackage.Master;

// TODO: Auto-generated Javadoc
/**
 * The Class ViewController.
 *
 * @authors: Zachary Steudel, Hunter Long, Chase Manseth, Bob Rein, Reece Kemball-Cook
 */
public class ViewController {
	
	/**
	 * Sign up view.
	 */
	public void signUpView() {
		Master.getInstance().updateFrame(new Signup());
	}
	
	/**
	 * Login view.
	 */
	public void loginView() {
		Master.getInstance().updateFrame(new Login());
	}

	/**
	 * Invite user change.
	 *
	 * @param u the u
	 */
	public void inviteUserChange(User u) {
		OtherProfileView.inviteBuddyFrame(u);
	}

	/**
	 * Block buddy change.
	 *
	 * @param u the u
	 */
	public void blockBuddyChange(User u) {
		OtherProfileView.blockBuddyFrame(u);
	}

	/**
	 * Settings view.
	 */
	public void settingsView() {
		Master.getInstance().updateFrame(new SettingsView());
	}

	/**
	 * Change profile image frame.
	 */
	public void changeProfileImageFrame() {
		SettingsView.changeImageFrame();
	}
}
