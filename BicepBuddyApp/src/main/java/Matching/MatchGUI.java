package Matching;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import User.User;
import User.UserController;
import User.UserDB;
import Views.OtherProfileView;
import Views.ProfileView;
import Views.ViewController;
import bicepBuddyPackage.Master;
import javax.swing.JLabel;

// TODO: Auto-generated Javadoc
/**
 * The Class MatchGUI.
 * @authors: Zachary Steudel, Hunter Long, Chase Manseth, Bob Rein, Reece Kemball-Cook
 */
public class MatchGUI extends JPanel{

	//if you want to show MatchGUI in the master frame, add g.this to
	/** The match name. */
	//the frame.
	private JTextField matchName;
	
	/** The match strength. */
	private JTextField matchStrength;
	
	/** The cur match shown. */
	private int curMatchShown = 0;
	
	/** The matches. */
	private List<Match> matches;
	
	private JLabel lblAcceptingMatch;

	private JLabel rejLabel;	/**
	 * Instantiates a new match GUI.
	 */
	public MatchGUI() {		
		
		//create JPanel for the frame.
		this.setLayout(null);
		this.setBounds(100, 100, 900, 550);
		
		//Matches generated here
		Master.appLogger.info(":: Match generation called by MatchGUI");
		MatchController.generateMatches();
		matches = MatchController.getMatches(UserController.getUser());
		
		if (matches.size() == 0) {
			Master.updateFrame(new ProfileView());
			noMatches();
		}
		
		//text field to handle match's name
		matchName = new JTextField();
		matchName.setFont(new Font("Tahoma", Font.PLAIN, 26));
		matchName.setHorizontalAlignment(SwingConstants.CENTER);
		matchName.setEditable(false);
		matchName.setBounds(109, 13, 682, 70);
		this.add(matchName);
		matchName.setColumns(10);
		
		//text field to handle the strength of the match
		matchStrength = new JTextField();
		matchStrength.setFont(new Font("Tahoma", Font.PLAIN, 26));
		matchStrength.setHorizontalAlignment(SwingConstants.CENTER);
		matchStrength.setEditable(false);
		matchStrength.setBounds(109, 96, 682, 70);
		this.add(matchStrength);
		matchStrength.setColumns(10);
		setCurrentMatch();
		
		//button to handle going to previous match in the list.
		JButton prevMatchBtn = new JButton("<");
		prevMatchBtn.setBackground(new Color(108,117,125));
		prevMatchBtn.setForeground(new Color(255,255,255));
		
		prevMatchBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				prevMatchBtn.setBackground(new Color(80,87,94));
				prevMatchBtn.setForeground(new Color(255,255,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				prevMatchBtn.setBackground(new Color(108,117,125));
				prevMatchBtn.setForeground(new Color(255,255,255));
			}
		});
		prevMatchBtn.setFont(new Font("Tahoma", Font.PLAIN, 30));
		prevMatchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Master.appLogger.info(":: MatchGUI left button action");
				if(curMatchShown == 0) {
					curMatchShown = matches.size() - 1;
				}
				else {
					curMatchShown--;
				}
				//curMatchShown--;
				setCurrentMatch();
			}
		});
		prevMatchBtn.setBounds(0, 0, 97, 518);
		this.add(prevMatchBtn);
		
		//button to handle going to next match in the list.
		JButton nextMatchBtn = new JButton(">");
		nextMatchBtn.setFont(new Font("Tahoma", Font.PLAIN, 30));
		nextMatchBtn.setBackground(new Color(108,117,125));
		nextMatchBtn.setForeground(new Color(255,255,255));
		
		nextMatchBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				nextMatchBtn.setBackground(new Color(80,87,94));
				nextMatchBtn.setForeground(new Color(255,255,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				nextMatchBtn.setBackground(new Color(108,117,125));
				nextMatchBtn.setForeground(new Color(255,255,255));
			}
		});
		nextMatchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Master.appLogger.info(":: MatchGUI right button action");
				curMatchShown++;
				setCurrentMatch();
			}
		});
		nextMatchBtn.setBounds(803, 0, 97, 518);
		this.add(nextMatchBtn);
		
		//button for accepting match
		JButton acceptBtn = new JButton("Accept Match");
		acceptBtn.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		acceptBtn.setBackground(new Color(34, 139, 34));
		acceptBtn.setForeground(new Color(255,255,255));
		
		acceptBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				acceptBtn.setBackground(new Color(26,86,8));
				acceptBtn.setForeground(new Color(255,255,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				acceptBtn.setBackground(new Color(34, 139, 34));
				acceptBtn.setForeground(new Color(255,255,255));
			}
		});
		
		acceptBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Master.appLogger.info(":: Accept button pressed");
//				JFrame frame = new JFrame();
//				frame.setVisible(true);
//				frame.setTitle("Accepted");
//				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//				frame.setBounds(100, 100, 400, 200);
//				
//				JPanel panel = new JPanel();
//				panel.setLayout(new FlowLayout());
//				
//				JTextField acc = new JTextField();
//				acc.setText("You have accepted this match!");
//				acc.setFont(new Font("Tahoma", Font.PLAIN, 20));
//				acc.setEditable(false);
//				
//				panel.add(acc);
//				frame.getContentPane().add(panel);
//				Master.getInstance().addLoading();
				lblAcceptingMatch.setVisible(true);
				lblAcceptingMatch.revalidate();
				lblAcceptingMatch.repaint();
				
				new SwingWorker<Void, Void>(){

					@Override
					protected Void doInBackground() throws Exception {
						//Accepts match here
						UserController.getInstance().setChangesToMatches(true);
						MatchController.acceptMatchInitial(matches.get(curMatchShown));
						UserController.getInstance().setTimesMatchCalled(5);
						
						matches.remove(matches.get(curMatchShown));
						if(curMatchShown != 0) {
							curMatchShown--;
						}
						
						if(matches.size() == 0) {
							Master.getInstance().updateFrame(new ProfileView());
							noMatches();
						}
						else {
							setCurrentMatch();
						}
						return null;
					}
					@Override
					protected void done() {
						lblAcceptingMatch.setVisible(false);
						lblAcceptingMatch.revalidate();
						lblAcceptingMatch.repaint();
					}
				}.execute();
				
			}
		});
		acceptBtn.setBounds(109, 400, 271, 70);
		this.add(acceptBtn);
		
		//button for rejecting match
		JButton rejectBtn = new JButton("Reject Match");
		rejectBtn.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		rejectBtn.setBackground(new Color(255, 0, 0));
		rejectBtn.setForeground(new Color(255,255,255));
		
		rejectBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				rejectBtn.setBackground(new Color(204,8,8));
				rejectBtn.setForeground(new Color(255,255,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rejectBtn.setBackground(new Color(255, 0, 0));
				rejectBtn.setForeground(new Color(255,255,255));
			}
		});
		
		rejectBtn.addActionListener(new ActionListener() {
			
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				Master.appLogger.info(":: Reject button pressed");
				/*JFrame frame = new JFrame();
				frame.setVisible(true);
				frame.setTitle("Rejection");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setBounds(100, 100, 400, 200);
				
				JPanel panel = new JPanel();
				panel.setLayout(new FlowLayout());
				
				JTextField rej = new JTextField();
				rej.setText("You have rejected this match!");
				rej.setFont(new Font("Tahoma", Font.PLAIN, 20));
				rej.setEditable(false);*/
				//Rejects match here
				rejLabel.setVisible(true);
				rejLabel.revalidate();
				rejLabel.repaint();
				
				new SwingWorker<Void, Void>(){

					@Override
					protected Void doInBackground() throws Exception {
						MatchController.rejectMatch(matches.get(curMatchShown));
						UserController.getInstance().setTimesMatchCalled(5);
						
//						panel.add(rej);
//						frame.getContentPane().add(panel);
						matches.remove(matches.get(curMatchShown));
						if(curMatchShown != 0) {
							curMatchShown--;
						}
						
						if(matches.size() == 0) {
							Master.getInstance().updateFrame(new ProfileView());
						}
						else {
							setCurrentMatch();
						}
						return null;
					}
					@Override
					protected void done() {
						rejLabel.setVisible(false);
						rejLabel.revalidate();
						rejLabel.repaint();
					}
				}.execute();
				
			}
		});
		rejectBtn.setBounds(520, 400, 271, 70);
		this.add(rejectBtn);
		
		//button to view other user profile
		JButton userProfileButton = new JButton("View User Profile");
		userProfileButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		userProfileButton.setBounds(109, 199, 682, 56);
		
		userProfileButton.setBackground(new Color(108,117,125));
		userProfileButton.setForeground(new Color(255,255,255));
		//show profile of user
		userProfileButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				userProfileButton.setBackground(new Color(80,87,94));
				userProfileButton.setForeground(new Color(255,255,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				userProfileButton.setBackground(new Color(108,117,125));
				userProfileButton.setForeground(new Color(255,255,255));
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Master.appLogger.info(":: Button to " + matches.get(curMatchShown).getOther().toString() + " profile pressed");
				//Goes to other profile here
				Master.getInstance().addLoading();
				
				new SwingWorker<Void, Void>(){

					@Override
					protected Void doInBackground() throws Exception {
						Master.getInstance().updateFrame(new OtherProfileView(UserController.getInstance()
								.onlyGetUserById(matches.get(curMatchShown).getOther())));
						return null;
					}
					@Override
					protected void done() {
						Master.getInstance().unLoad();
					}
				}.execute();
			}
		});
		
		this.add(userProfileButton);
		
		rejLabel = new JLabel("Rejecting Match . . .");
		rejLabel.setForeground(new Color(255, 0, 0));
		rejLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		rejLabel.setBounds(520, 336, 235, 51);
		rejLabel.setVisible(false);
		add(rejLabel);
		
		lblAcceptingMatch = new JLabel("Accepting Match . . .");
		lblAcceptingMatch.setForeground(new Color(50, 205, 50));
		lblAcceptingMatch.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAcceptingMatch.setBounds(109, 336, 235, 51);
		lblAcceptingMatch.setVisible(false);
		add(lblAcceptingMatch);
	}
	
	/**
	 * No matches.
	 */
	public void noMatches() {
		Master.appLogger.info(":: No matches found");
		
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setTitle("No Matches");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(200, 200, 800, 400);
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		JTextField noMatchField = new JTextField();
		noMatchField.setText("You have exhausted your matches from this list.");
		noMatchField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		noMatchField.setEditable(false);
		
		panel.add(noMatchField);
		frame.getContentPane().add(panel);
		
		Master.updateFrame(new ProfileView());
		
	}
	
	/**
	 * Sets the current match.
	 */
	//Updates match shown by index
	public void setCurrentMatch() {
		Master.appLogger.info(":: Currently shown match updated");
		Master.getInstance().addLoading();
		
		new SwingWorker<Void, Void>(){

			@Override
			protected Void doInBackground() throws Exception {
				if(matches.size() != 0) {
					curMatchShown = curMatchShown % matches.size();
					User other = UserController.getInstance().onlyGetUserById(matches.get(curMatchShown).getOther());
					matchName.setText(other.getfName() + " " + other.getlName());
					matchStrength.setText(matches.get(curMatchShown).getStrength() + "% Match");
				}
				return null;
			}
			@Override
			protected void done() {
				Master.getInstance().unLoad();
			}
		}.execute();
		
		
	}
	
}
