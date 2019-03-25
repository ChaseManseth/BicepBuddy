package views;

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

public class OtherProfileView extends JPanel {

	//private JFrame frame;

	/**
	 * Create the application.
	 */
	public OtherProfileView() {
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
				
				JLabel lblName2 = new JLabel(fname +" "+lname);
				lblName2.setBounds(365, 12, 135, 15);
				add(lblName2);
				
				JButton btnStartMatching = new JButton("Invite Buddy");
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
						matches.add(new TestMatch("Donald Trump", 96.83));
						
//						mFrame.updateFrame(new MatchGUI(matches, mFrame));
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
				btnStartMatching.setBounds(346, 138, 188, 47);
				add(btnStartMatching);
				
				JPanel infoPanel = new JPanel();
				infoPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
				infoPanel.setBounds(34, 26, 208, 440);
				add(infoPanel);
				infoPanel.setLayout(null);
				
				JLabel lblFirstname = new JLabel("Donald Trump");
				lblFirstname.setBounds(10, 224, 165, 28);
				infoPanel.add(lblFirstname);
				
				JLabel lblAge = new JLabel(Integer.toString(age));
				lblAge.setBounds(10, 264, 165, 15);
				infoPanel.add(lblAge);
				
				JButton button = new JButton("Block Buddy");
				button.setForeground(Color.BLACK);
				button.setFont(new Font("Dialog", Font.PLAIN, 12));
				button.setBackground(new Color(189, 189, 189));
				button.setBounds(346, 288, 188, 47);
				add(button);
	}
}
