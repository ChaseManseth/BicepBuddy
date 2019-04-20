package bicepBuddyPackage;

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

public class MatchGUI extends JPanel{

	//if you want to show MatchGUI in the master frame, add g.this to
	//the frame.
	private JTextField matchName;
	private JTextField matchStrength;
	private int curMatchShown = 0;
	private List<Match> matches;

	public MatchGUI(User user) {		
		
		//create JPanel for the frame.
		this.setLayout(null);
		this.setBounds(100, 100, 900, 550);
		
		MatchController.generateMatches(user);
		matches = MatchController.getMatches(user);
		
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
				
				curMatchShown--;
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
				JFrame frame = new JFrame();
				frame.setVisible(true);
				frame.setTitle("Accepted");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setBounds(100, 100, 400, 200);
				
				JPanel panel = new JPanel();
				panel.setLayout(new FlowLayout());
				
				JTextField acc = new JTextField();
				acc.setText("You have accepted this match!");
				acc.setFont(new Font("Tahoma", Font.PLAIN, 20));
				acc.setEditable(false);
				
				panel.add(acc);
				frame.getContentPane().add(panel);
				
				MatchController.acceptMatchInitial(matches.get(curMatchShown));
				
				matches.remove(matches.get(curMatchShown));
				if(curMatchShown != 0) {
					curMatchShown--;
				}
				
				if(matches.size() == 0) {
					//noMatches();
					//dispose();
					Master.getInstance().updateFrame(new ProfileView());
				}
				else {
					setCurrentMatch();
				}
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
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				frame.setVisible(true);
				frame.setTitle("Rejection");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setBounds(100, 100, 400, 200);
				
				JPanel panel = new JPanel();
				panel.setLayout(new FlowLayout());
				
				JTextField rej = new JTextField();
				rej.setText("You have rejected this match!");
				rej.setFont(new Font("Tahoma", Font.PLAIN, 20));
				rej.setEditable(false);
				
				MatchController.rejectMatch(matches.get(curMatchShown));
				
				panel.add(rej);
				frame.getContentPane().add(panel);
				matches.remove(matches.get(curMatchShown));
				if(curMatchShown != 0) {
					curMatchShown--;
				}
				
				if(matches.size() == 0) {
					//noMatches();
					Master.getInstance().updateFrame(new ProfileView());
				}
				else {
					setCurrentMatch();
				}
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
				UserDB udb = new UserDB();
				//*****************************
				Master.getInstance().updateFrame(new OtherProfileView(matches.get(curMatchShown).getMatched()));
			}
		});
		
		this.add(userProfileButton);
	}
	
	public void noMatches() {
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setTitle("No Matches");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(200, 200, 400, 400);
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		JTextField noMatchField = new JTextField();
		noMatchField.setText("You have exhausted your matches from this list."
				+ "Generate more matches by matching from your profile!");
		noMatchField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		noMatchField.setEditable(false);
		
		panel.add(noMatchField);
		frame.getContentPane().add(panel);
		
	}
	
	public void setCurrentMatch() {
		curMatchShown = curMatchShown % matches.size();
		matchName.setText(matches.get(curMatchShown).getMatched().getfName() + " " + matches.get(curMatchShown).getMatched().getlName());
		matchStrength.setText(matches.get(curMatchShown).getStrength() + "% Match");
	}
	
}
