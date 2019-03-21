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
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class OtherProfileView extends JPanel {

	//private JFrame frame;

	/**
	 * Create the application.
	 */
	public OtherProfileView() {
		String name,style,info;
		int age =72;
		name="Donald Trump";
		style= "Wall Building";
		info = "He has the best words. He is quite frankly a "
				+ "tremendous and fantastic person. Every thing he "
				+ "he does, he does it bigly";
		//frame = new JFrame();
		setBounds(100, 100, 900, 560);

		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
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
				frame.getContentPane().add(panel);

			}
		});
		
		
		btnInviteBuddy.setBounds(349, 164, 194, 25);
		add(btnInviteBuddy);
		
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
				frame.getContentPane().add(panel);

			}
		});
		btnBlockBuddy.setBounds(349, 280, 194, 25);
		add(btnBlockBuddy);
		
		JLabel lblPic = new JLabel("");
		lblPic.setBounds(54, 70, 104, 88);
		try {
		    BufferedImage myPicture = ImageIO.read(new File("thedonald.jpeg"));
		    Image scaled = myPicture.getScaledInstance(lblPic.getWidth(), lblPic.getHeight(),
		            Image.SCALE_SMOOTH);
			lblPic.setIcon(new ImageIcon(scaled));
        } catch (IOException e) {
    	    e.printStackTrace();
        }
	
		add(lblPic);
		
		JLabel lblBasics = new JLabel("Basics");
		lblBasics.setBounds(102, 285, 66, 15);
		add(lblBasics);
		
		JLabel lblMore = new JLabel("More Information");
		lblMore.setBounds(708, 133, 137, 25);
		add(lblMore);
		
		JLabel lblName2 = new JLabel(name);
		lblName2.setBounds(396, 12, 175, 15);
		add(lblName2);
		
		JLabel lblInfo = new JLabel("<html>"+info+"</html>");
		lblInfo.setBounds(708, 220, 137, 158);
		add(lblInfo);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.GRAY));
		panel.setBounds(74, 326, 104, 88);
		add(panel);
		
		JLabel lblName = new JLabel(name);
		panel.add(lblName);
		
		JLabel lblAge = new JLabel(Integer.toString(age));
		panel.add(lblAge);
		
		JLabel lblStyle = new JLabel(style);
		panel.add(lblStyle);
	}


}
