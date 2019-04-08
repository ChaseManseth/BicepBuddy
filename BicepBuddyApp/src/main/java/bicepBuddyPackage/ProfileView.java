package bicepBuddyPackage;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;

import java.awt.Color;

public class ProfileView extends JPanel {

	public ProfileView(Master mFrame) {
		
		// TEMP DATA
		String fname,lname,style,info,email;
		int age =72;
		fname="Donald";
		lname="Trump";
		style= "Wall Building";
		email = "donald@trump.com";
		// END TEMP DATA

		//frame = new JFrame();
		setBounds(0, 0, 900, 500);

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
		lblMore.setBounds(269, 183, 247, 25);
		add(lblMore);
		
		JLabel lblName2 = new JLabel("Your Profile");
		lblName2.setBounds(96, 11, 135, 15);
		add(lblName2);

		JButton editProfileBtn = new JButton("Edit Profile");
		editProfileBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		editProfileBtn.setBackground(MaterialColors.GRAY_400);
		editProfileBtn.setForeground(Color.black);
		
		MaterialUIMovement.add(editProfileBtn, MaterialColors.GRAY_600);
		
		editProfileBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mFrame.updateFrame(new SettingsView(mFrame));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				editProfileBtn.setForeground(Color.white);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				editProfileBtn.setForeground(Color.black);
			}
		});
		
		editProfileBtn.setBounds(54, 420, 165, 25);
		add(editProfileBtn);
		
		JButton btnStartMatching = new JButton("Start Matching");
		btnStartMatching.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnStartMatching.setBackground(MaterialColors.GRAY_400);
		btnStartMatching.setForeground(Color.black);
		
		MaterialUIMovement.add(btnStartMatching, MaterialColors.GRAY_600);
		
		btnStartMatching.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ArrayList<TestMatch> matches = new ArrayList<>();
				matches.add(new TestMatch("Cerny", 76.6));
				matches.add(new TestMatch("Cerniette", 65.23));
				matches.add(new TestMatch("Hillary Clinton", 96.83));
				
				mFrame.updateFrame(new MatchGUI(matches, mFrame));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnStartMatching.setForeground(Color.white);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnStartMatching.setForeground(Color.black);
			}
		});
		btnStartMatching.setBounds(459, 40, 188, 47);
		add(btnStartMatching);
		
		JPanel activityPanel = new JPanel();
		activityPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		activityPanel.setBounds(269, 219, 587, 281);
		add(activityPanel);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		infoPanel.setBounds(34, 26, 208, 440);
		add(infoPanel);
		infoPanel.setLayout(null);
		
		JLabel lblEmail = new JLabel(email);
		lblEmail.setBounds(10, 264, 165, 15);
		infoPanel.add(lblEmail);
		
		JLabel lblFirstname = new JLabel("Donald Trump");
		lblFirstname.setBounds(10, 224, 165, 28);
		infoPanel.add(lblFirstname);
		
		JLabel lblAge = new JLabel(Integer.toString(age));
		lblAge.setBounds(10, 300, 165, 15);
		infoPanel.add(lblAge);
	}
}
