package bicepBuddyPackage;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;



import javax.swing.JMenu;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
public class SettingsView extends JPanel {

	private JFrame frame;
	private JTextField txtFirstName;
	private JTextField txtEmail;
	private JTextField txtLastName;



	/**
	 * Create the application.
	 */
	public SettingsView(Master mFrame) {
		setBounds(100, 100, 900, 560);
		setLayout(null);
		
		JLabel lblPic = new JLabel("");
		lblPic.setBounds(64, 62, 169, 175);
		try {
		    BufferedImage myPicture = ImageIO.read(new File("thedonald.jpeg"));
		    Image scaled = myPicture.getScaledInstance(lblPic.getWidth(), lblPic.getHeight(),
		            Image.SCALE_SMOOTH);
			lblPic.setIcon(new ImageIcon(scaled));
        } catch (IOException e) {
    	    e.printStackTrace();
        }
	
		add(lblPic);
		
		JLabel lblName2 = new JLabel("Account Settings");
		lblName2.setBounds(376, 12, 137, 15);
		add(lblName2);
		
		JButton btnNewButton = new JButton("Change Image");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				frame.setVisible(true);
				frame.setTitle("Change Image");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setBounds(100, 100, 400, 200);
				
				JPanel panel = new JPanel();
				panel.setLayout(new FlowLayout());
				
				JTextField message = new JTextField();
				message.setText("We need to build a file selector that only shows image files.");
				message.setEditable(false);
				
				panel.add(message);
				frame.getContentPane().add(panel);

			}
		});
		btnNewButton.setBounds(64, 254, 169, 25);
		add(btnNewButton);
		
		txtFirstName = new JTextField();
		txtFirstName.setBounds(64, 307, 169, 19);
		add(txtFirstName);
		txtFirstName.setText("Donald");
		txtFirstName.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(64, 405, 169, 19);
		add(txtEmail);
		txtEmail.setText("donald@trump.com");
		txtEmail.setColumns(10);
		
		txtLastName = new JTextField();
		txtLastName.setText("Trump");
		txtLastName.setBounds(64, 356, 169, 19);
		add(txtLastName);
		txtLastName.setColumns(10);

		JComboBox comboBox = new JComboBox(MatchAlgorithm.STYLES);
		comboBox.setBounds(584, 62, 203, 32);
		add(comboBox);
		
		JLabel lblWorkoutStyle = new JLabel("Workout Style");
		lblWorkoutStyle.setBounds(406, 71, 107, 15);
		add(lblWorkoutStyle);
		
		JComboBox comboBox_1 = new JComboBox(MatchAlgorithm.TIMES);
		comboBox_1.setBounds(584, 141, 201, 32);
		add(comboBox_1);
		
		JLabel label = new JLabel("Prefered Time");
		label.setBounds(406, 150, 107, 15);
		add(label);
		
		JComboBox comboBox_2 = new JComboBox(MatchAlgorithm.GENDERS);
		comboBox_2.setBounds(584, 213, 203, 32);
		add(comboBox_2);
		
		JLabel label_1 = new JLabel("Gender");
		label_1.setBounds(406, 222, 107, 15);
		add(label_1);
		
		JComboBox comboBox_3 = new JComboBox(MatchAlgorithm.GENDERS);
		comboBox_3.setBounds(584, 277, 203, 32);
		add(comboBox_3);
		
		JLabel label_2 = new JLabel("Buddy Gender");
		label_2.setBounds(406, 286, 107, 15);
		add(label_2);
		
		JComboBox comboBox_4 = new JComboBox(MatchAlgorithm.FREQUENCIES);
		comboBox_4.setBounds(584, 349, 203, 32);
		add(comboBox_4);
		
		JLabel label_3 = new JLabel("Frequency");
		label_3.setBounds(406, 358, 107, 15);
		add(label_3);
		
		JButton btnNewButton_1 = new JButton("Save changes");
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				frame.setVisible(true);
				frame.setTitle("Save changes");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setBounds(100, 100, 400, 200);
				
				JPanel panel = new JPanel();
				panel.setLayout(new FlowLayout());
				
				JTextField message = new JTextField();
				message.setText("All changes have been successfully changed.");
				message.setEditable(false);
				
				panel.add(message);
				frame.getContentPane().add(panel);
			}
		});
		btnNewButton_1.setBounds(584, 479, 203, 25);
		add(btnNewButton_1);
	}

}
