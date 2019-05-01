package Views;

import User.User;
import bicepBuddyPackage.Master;

/**
 * The Class ViewController. Will control changing between views.
 *
 * @authors: Zachary Steudel, Hunter Long, Chase Manseth, Bob Rein, Reece Kemball-Cook
 */
public class ViewController {
	
	/**
	 * Sign up view.
	 */
	public void signUpView() {
		Master.getInstance();
		Master.updateFrame(new Signup());
	}
	
	/**
	 * Login view.
	 */
	public void loginView() {
		Master.getInstance();
		Master.updateFrame(new Login());
	}

	/**
	 * Invite user change.
	 *
	 * @param u: User sent in.
	 */
	public void inviteUserChange(User u) {
		OtherProfileView.inviteBuddyFrame(u);
	}

	/**
	 * Block buddy change.
	 *
	 * @param u: User sent in.
	 */
	public void blockBuddyChange(User u) {
		OtherProfileView.blockBuddyFrame(u);
	}

	/**
	 * Settings view.
	 */
	public void settingsView() {
		Master.getInstance();
		Master.updateFrame(new SettingsView());
	}

	/**
	 * Change profile image frame. DEPRECATED
	 */
	public void changeProfileImageFrame() {
		SettingsView.changeImageFrame();
	}
}
