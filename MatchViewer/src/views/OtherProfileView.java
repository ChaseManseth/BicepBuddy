///////////////////////
//Author: Bob Rein
//Date: 3/16/19
////////////////////////

package views;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class OtherProfileView {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					OtherProfileView window = new OtherProfileView();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public OtherProfileView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		String name,style,info;
		int age =72;
		name="Donald Trump";
		style= "Wall Building";
		info = "He has the best words. He is quite frankly a "
				+ "tremendous and fantastic person. Every thing he "
				+ "he does, he does it bigly";
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnInviteBuddy = new JButton("Invite Buddy");
		
		btnInviteBuddy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				frame.setVisible(true);
				frame.setTitle("Accepted");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setBounds(100, 100, 400, 200);
				
				JPanel panel = new JPanel();
				panel.setLayout(new FlowLayout());
				
				JTextField message = new JTextField();
				message.setText("You have invited "+ name +" to match.");
				message.setEditable(false);
				
				panel.add(message);
				frame.add(panel);

			}
		});
		
		
		btnInviteBuddy.setBounds(155, 101, 128, 25);
		frame.getContentPane().add(btnInviteBuddy);
		
		JButton btnBlockBuddy = new JButton("Block Buddy");
		
		btnBlockBuddy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				frame.setVisible(true);
				frame.setTitle("Accepted");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setBounds(100, 100, 400, 200);
				
				JPanel panel = new JPanel();
				panel.setLayout(new FlowLayout());
				
				JTextField message = new JTextField();
				message.setText("You have blocked "+ name +".");
				message.setEditable(false);
				
				panel.add(message);
				frame.add(panel);

			}
		});
		btnBlockBuddy.setBounds(155, 165, 128, 25);
		frame.getContentPane().add(btnBlockBuddy);
		
		JLabel lblPic = new JLabel("");
		lblPic.setBounds(24, 34, 104, 88);
		try {
		    BufferedImage myPicture = ImageIO.read(new File("thedonald.jpeg"));
		    Image scaled = myPicture.getScaledInstance(lblPic.getWidth(), lblPic.getHeight(),
		            Image.SCALE_SMOOTH);
			lblPic.setIcon(new ImageIcon(scaled));
        } catch (IOException e) {
    	    e.printStackTrace();
        }
	
		frame.getContentPane().add(lblPic);
		
		JLabel lblBasics = new JLabel("Basics");
		lblBasics.setBounds(50, 138, 66, 15);
		frame.getContentPane().add(lblBasics);
		
		JLabel lblMore = new JLabel("More Information");
		lblMore.setBounds(301, 34, 137, 25);
		frame.getContentPane().add(lblMore);
		
		JLabel lblName2 = new JLabel(name);
		lblName2.setBounds(182, 12, 97, 15);
		frame.getContentPane().add(lblName2);
		
		JLabel lblInfo = new JLabel("<html>"+info+"</html>");
		lblInfo.setBounds(301, 71, 137, 158);
		frame.getContentPane().add(lblInfo);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.GRAY));
		panel.setBounds(22, 170, 104, 88);
		frame.getContentPane().add(panel);
		
		JLabel lblName = new JLabel(name);
		panel.add(lblName);
		
		JLabel lblAge = new JLabel(Integer.toString(age));
		panel.add(lblAge);
		
		JLabel lblStyle = new JLabel(style);
		panel.add(lblStyle);
	}
}
