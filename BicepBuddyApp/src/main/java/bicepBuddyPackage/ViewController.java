package bicepBuddyPackage;

public class ViewController {
	public void signUpView() {
		Master.getInstance().updateFrame(new Signup());
	}
	
	public void loginView() {
		Master.getInstance().updateFrame(new Login());
	}

	public void inviteUserChange(User u) {
		OtherProfileView.inviteBuddyFrame(u);
	}

	public void blockBuddyChange(User u) {
		OtherProfileView.blockBuddyFrame(u);
	}

	public void settingsView() {
		Master.getInstance().updateFrame(new SettingsView());
	}

	public void changeProfileImageFrame() {
		SettingsView.changeImageFrame();
	}
}
