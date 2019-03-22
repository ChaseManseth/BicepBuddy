package views;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class ProfileView extends JPanel {

	//private JFrame frame;

	/**
	 * Create the application.
	 */
	public ProfileView(Master mFrame) {
		String fname,lname,style,info,email;
		int age =72;
		fname="Donald";
		lname="Trump";
		style= "Wall Building";
		email = "donald@trump.com";

		//frame = new JFrame();
		setBounds(100, 100, 900, 560);

		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		JLabel lblPic = new JLabel("");
		lblPic.setBounds(54, 40, 165, 184);
		try {
		    BufferedImage myPicture = ImageIO.read(new File("thedonald.jpeg"));
		    Image scaled = myPicture.getScaledInstance(lblPic.getWidth(), lblPic.getHeight(),
		            Image.SCALE_SMOOTH);
			lblPic.setIcon(new ImageIcon(scaled));
        } catch (IOException e) {
    	    e.printStackTrace();
        }
	
		add(lblPic);
		
		JLabel lblMore = new JLabel("Recent Matches and Activity Feed");
		lblMore.setBounds(442, 176, 247, 25);
		add(lblMore);
		
		JLabel lblName2 = new JLabel("Your Profile");
		lblName2.setBounds(396, 12, 175, 15);
		add(lblName2);
		
		JLabel lblFirstname = new JLabel(fname);
		lblFirstname.setBounds(54, 250, 165, 28);
		add(lblFirstname);
		
		JLabel lblLastname = new JLabel(lname);
		lblLastname.setBounds(54, 290, 165, 15);
		add(lblLastname);
		
		JLabel lblEmail = new JLabel(email);
		lblEmail.setBounds(54, 330, 165, 15);
		add(lblEmail);
		
		JLabel lblAge = new JLabel(Integer.toString(age));
		lblAge.setBounds(54, 370, 165, 15);
		add(lblAge);
		
		JButton btnNewButton = new JButton("Edit Profile");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(54, 420, 165, 25);
		add(btnNewButton);
		
		JButton btnStartMatching = new JButton("Start Matching");
		btnStartMatching.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<TestMatch> matches = new ArrayList<>();
				matches.add(new TestMatch("Cerny", 76.6));
				matches.add(new TestMatch("Cerniette", 65.23));
				matches.add(new TestMatch("Donald Trump", 96.83));
				
				mFrame.updateFrame(new MatchGUI(matches, mFrame));
			}
		});
		btnStartMatching.setBounds(463, 84, 188, 47);
		add(btnStartMatching);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBounds(269, 219, 587, 300);
		add(panel);
	}
}
