package Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Matching.MatchController;
import User.User;
import User.UserController;
import bicepBuddyPackage.Master;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

// TODO: Auto-generated Javadoc
/**
 * The Class FriendsList.
 */
public class FriendsList extends JPanel{
	
	/** The buddies label. */
	private JTextField buddiesLabel;
	
	/**
	 * Instantiates a new friends list.
	 */
	public FriendsList() {
		setBounds(0, 0, 900, 500);
		
		//*************************************************************************************************************************
		//In user accepted and other accepted
		UserController.getInstance().populateUserMatchesArray();
		setLayout(null);
		
		JLabel lblFriends = new JLabel("Buddies");
		lblFriends.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFriends.setBounds(363, 126, 203, 15);
		add(lblFriends);
		
		JPanel friendsPanel = new JPanel();
		friendsPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		friendsPanel.setBounds(363, 147, 164, 340);
		
		JList friendsList = new JList();
		friendsList.setListData(UserController.getInstance().getUser().getAcceptedUsers().toArray());
		friendsList.setFont(new Font("Tahoma", Font.BOLD, 12));
		MouseListener mouseListener = new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		    	Master.appLogger.info(":: Accessed friends list for user " + ((User)friendsList.getSelectedValue()).toString());
		    	Master.getInstance().addLoading();
				
				new SwingWorker<Void, Void>(){

					@Override
					protected Void doInBackground() throws Exception {
						Master.getInstance().updateFrame(new OtherProfileView((User)friendsList.getSelectedValue()));
						return null;
					}
					
					protected void done() {
						Master.getInstance().unLoad();
					}
				}.execute();
		    	
		    }
		};
		friendsList.addMouseListener(mouseListener);
		friendsPanel.add(friendsList);
		add(friendsPanel);
		
		//*************************************************************************************************************************
		JLabel lblIncomingBuddies = new JLabel("Incoming Buddies");
		lblIncomingBuddies.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIncomingBuddies.setBounds(709, 126, 179, 15);
		add(lblIncomingBuddies);
		
		JPanel incomingPanel = new JPanel();
		incomingPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		incomingPanel.setBounds(709, 147, 164, 340);
		
		JList incomingList = new JList();
		incomingList.setListData(UserController.getInstance().getUser().getWaitingUsers().toArray());
		incomingList.setFont(new Font("Tahoma", Font.BOLD, 12));
		MouseListener mouseListener1 = new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		    	Master.appLogger.info(":: Accessed incoming list for user " + ((User)incomingList.getSelectedValue()).toString());
		    	Master.getInstance().addLoading();
				
				new SwingWorker<Void, Void>(){

					@Override
					protected Void doInBackground() throws Exception {
						Master.getInstance().updateFrame(new OtherProfileView((User)incomingList.getSelectedValue()));
						return null;
					}
					
					protected void done() {
						Master.getInstance().unLoad();
					}
				}.execute();
		    	
		    }
		};
		incomingList.addMouseListener(mouseListener1);
		incomingPanel.add(incomingList);
		add(incomingPanel);
		
		//**********************************************************************************************************************************
		JLabel lblPendingBuddies = new JLabel("Pending Buddies");
		lblPendingBuddies.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPendingBuddies.setBounds(22, 126, 203, 15);
		add(lblPendingBuddies);
		
		JPanel pendingPanel = new JPanel();
		pendingPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		pendingPanel.setBounds(22, 147, 164, 340);
		
		JList pendingList = new JList();
		pendingList.setListData(UserController.getInstance().getUser().getPendingUsers().toArray());
		pendingList.setFont(new Font("Tahoma", Font.BOLD, 12));
		MouseListener mouseListener2 = new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		    	Master.appLogger.info(":: Accessed pending list for user " + ((User)pendingList.getSelectedValue()).toString());
		    	Master.getInstance().addLoading();
				
				new SwingWorker<Void, Void>(){

					@Override
					protected Void doInBackground() throws Exception {
				    	Master.getInstance().updateFrame(new OtherProfileView((User)pendingList.getSelectedValue()));
						return null;
					}
					
					protected void done() {
						Master.getInstance().unLoad();
					}
				}.execute();
		    }
		};
		pendingList.addMouseListener(mouseListener2);
		pendingPanel.add(pendingList);
		add(pendingPanel);
		
		buddiesLabel = new JTextField();
		buddiesLabel.setEditable(false);
		buddiesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		buddiesLabel.setText("Your Buddy Lists");
		buddiesLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		buddiesLabel.setBounds(293, 13, 310, 39);
		add(buddiesLabel);
		buddiesLabel.setColumns(10);
	}
}
