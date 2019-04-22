package Messaging;

import javax.swing.JFrame;

public class DMController {

	private DMView dmView;
	private DM dm;
	
	public DMController() {
		dmView = new DMView();
		dm = new DM();
		
		dmView.setVisible(true);
	}
	
	public void updateView() {
		dmView.updateMessages(dm.getSorted());
		dmView.setVisible(true);
	}
	
}
