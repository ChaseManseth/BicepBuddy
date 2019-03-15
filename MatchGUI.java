import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;

public class MatchGUI extends JFrame {

	private JPanel contentPane;
	private JTextField matchName;
	private JTextField matchStrength;
	private int curMatchShown = 0;

	public static void main(String[] args) {
		ArrayList<TestMatch> matches = new ArrayList<TestMatch>();
		matches.add(new TestMatch("Cerny", 99.9));
		matches.add(new TestMatch("Joe", 98.8));
		matches.add(new TestMatch("Cerny-ette", 73.4));
		
		MatchGUI g = new MatchGUI(matches);
		g.setVisible(true);
	}

	public MatchGUI(ArrayList<TestMatch> matches) {		
		//create empty frame
		setTitle("Matches");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 793, 400);
		
		//create JPanel for the frame.
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//text field to handle match's name
		matchName = new JTextField();
		matchName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		matchName.setHorizontalAlignment(SwingConstants.CENTER);
		matchName.setEditable(false);
		matchName.setText(matches.get(curMatchShown).getMatchName());
		matchName.setBounds(109, 0, 564, 44);
		contentPane.add(matchName);
		matchName.setColumns(10);
		
		//text field to handle the strength of the match
		matchStrength = new JTextField();
		matchStrength.setFont(new Font("Tahoma", Font.PLAIN, 20));
		matchStrength.setText(matches.get(curMatchShown).getMatchStr() + "% Match");
		matchStrength.setHorizontalAlignment(SwingConstants.CENTER);
		matchStrength.setEditable(false);
		matchStrength.setBounds(109, 57, 564, 44);
		contentPane.add(matchStrength);
		matchStrength.setColumns(10);
		
		//button to handle going to previous match in the list.
		JButton prevMatchBtn = new JButton("<");
		prevMatchBtn.setFont(new Font("Tahoma", Font.PLAIN, 30));
		prevMatchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(curMatchShown > 0) {
					//we can go back in the list, otherwise, if we are at first
					//match, we can't go back in the list.
					curMatchShown--;
					matchName.setText(matches.get(curMatchShown).getMatchName());
					matchStrength.setText(matches.get(curMatchShown).getMatchStr() + "% Match");
				}
			}
		});
		prevMatchBtn.setBounds(0, 0, 97, 363);
		contentPane.add(prevMatchBtn);
		
		//button to handle going to next match in the list.
		JButton nextMatchBtn = new JButton(">");
		nextMatchBtn.setFont(new Font("Tahoma", Font.PLAIN, 30));
		nextMatchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(curMatchShown < matches.size() - 1) {
					//we can go forward in the list, otherwise, if we are at last
					//item, we can't show any more matches
					curMatchShown++;
					matchName.setText(matches.get(curMatchShown).getMatchName());
					matchStrength.setText(matches.get(curMatchShown).getMatchStr() + "% Match");
				}
			}
		});
		nextMatchBtn.setBounds(685, 0, 97, 363);
		contentPane.add(nextMatchBtn);
		
		//button for accepting match
		JButton acceptBtn = new JButton("Accept Match");
		acceptBtn.setFont(new Font("Tahoma", Font.PLAIN, 30));
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
				frame.add(panel);
				
				matches.remove(matches.get(curMatchShown));
				if(curMatchShown != 0) {
					curMatchShown--;
				}
				
				if(matches.size() == 0) {
					noMatches();
					dispose();
				}
				else {
					matchName.setText(matches.get(curMatchShown).getMatchName());
					matchStrength.setText(matches.get(curMatchShown).getMatchStr() + "% Match");
				}
			}
		});
		acceptBtn.setBounds(109, 270, 271, 70);
		contentPane.add(acceptBtn);
		
		//button for rejecting match
		JButton rejectBtn = new JButton("Reject Match");
		rejectBtn.setFont(new Font("Tahoma", Font.PLAIN, 30));
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
				
				panel.add(rej);
				frame.add(panel);
				
				matches.remove(matches.get(curMatchShown));
				if(curMatchShown != 0) {
					curMatchShown--;
				}
				
				if(matches.size() == 0) {
					noMatches();
					dispose();
				}
				else {
					matchName.setText(matches.get(curMatchShown).getMatchName());
					matchStrength.setText(matches.get(curMatchShown).getMatchStr() + "% Match");
				}
			}
		});
		rejectBtn.setBounds(402, 270, 271, 70);
		contentPane.add(rejectBtn);
		
		//button to view other user profile
		JButton userProfileButton = new JButton("View User Profile");
		userProfileButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		userProfileButton.setBounds(109, 114, 564, 56);
		contentPane.add(userProfileButton);
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
		noMatchField.setText("You have no matches!");
		noMatchField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		noMatchField.setEditable(false);
		
		panel.add(noMatchField);
		frame.add(panel);
		
	}
	
}
