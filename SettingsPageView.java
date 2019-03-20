package settingsPage;

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

public class SettingsPageView {

	private JFrame frame;
	private JTextField txtFirstName;
	private JTextField txtEmail;
	private JTextField txtLastName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SettingsPageView window = new SettingsPageView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SettingsPageView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPic = new JLabel("");
		lblPic.setBounds(22, 12, 126, 109);
		try {
		    BufferedImage myPicture = ImageIO.read(new File("thedonald.jpeg"));
		    Image scaled = myPicture.getScaledInstance(lblPic.getWidth(), lblPic.getHeight(),
		            Image.SCALE_SMOOTH);
			lblPic.setIcon(new ImageIcon(scaled));
        } catch (IOException e) {
    	    e.printStackTrace();
        }
	
		frame.getContentPane().add(lblPic);
		
		JLabel lblName2 = new JLabel("Account Settings");
		lblName2.setBounds(160, 12, 137, 15);
		frame.getContentPane().add(lblName2);
		
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
				frame.add(panel);

			}
		});
		btnNewButton.setBounds(22, 127, 169, 25);
		frame.getContentPane().add(btnNewButton);
		
		txtFirstName = new JTextField();
		txtFirstName.setBounds(24, 164, 169, 19);
		frame.getContentPane().add(txtFirstName);
		txtFirstName.setText("Donald");
		txtFirstName.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(24, 228, 169, 19);
		frame.getContentPane().add(txtEmail);
		txtEmail.setText("donald@trump.com");
		txtEmail.setColumns(10);
		
		txtLastName = new JTextField();
		txtLastName.setText("Trump");
		txtLastName.setBounds(24, 197, 169, 19);
		frame.getContentPane().add(txtLastName);
		txtLastName.setColumns(10);
		String workoutStyles[] = {"General Fitness", "Cardio", "Powerlifting","Crossfit", "Bodybuilding", "Weight Loss"};
		String preferedTimes[]= {"Early Morning", "Morning", "Lunch","Afternoon","Early Evening", "Night"};
		String genders[]= {"Male", "Female"};
		String frequencies[]= {"Once a week", "3 times a week", "Everyday"};
		JComboBox comboBox = new JComboBox(workoutStyles);
		comboBox.setBounds(310, 53, 113, 32);
		frame.getContentPane().add(comboBox);
		
		JLabel lblWorkoutStyle = new JLabel("Workout Style");
		lblWorkoutStyle.setBounds(200, 62, 107, 15);
		frame.getContentPane().add(lblWorkoutStyle);
		
		JComboBox comboBox_1 = new JComboBox(preferedTimes);
		comboBox_1.setBounds(310, 97, 113, 32);
		frame.getContentPane().add(comboBox_1);
		
		JLabel label = new JLabel("Prefered Time");
		label.setBounds(200, 106, 107, 15);
		frame.getContentPane().add(label);
		
		JComboBox comboBox_2 = new JComboBox(genders);
		comboBox_2.setBounds(310, 141, 113, 32);
		frame.getContentPane().add(comboBox_2);
		
		JLabel label_1 = new JLabel("Gender");
		label_1.setBounds(200, 150, 107, 15);
		frame.getContentPane().add(label_1);
		
		JComboBox comboBox_3 = new JComboBox(genders);
		comboBox_3.setBounds(310, 184, 113, 32);
		frame.getContentPane().add(comboBox_3);
		
		JLabel label_2 = new JLabel("Buddy Gender");
		label_2.setBounds(200, 193, 107, 15);
		frame.getContentPane().add(label_2);
		
		JComboBox comboBox_4 = new JComboBox(frequencies);
		comboBox_4.setBounds(310, 226, 113, 32);
		frame.getContentPane().add(comboBox_4);
		
		JLabel label_3 = new JLabel("Frequency");
		label_3.setBounds(200, 230, 107, 15);
		frame.getContentPane().add(label_3);
		
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
				frame.add(panel);
			}
		});
		btnNewButton_1.setBounds(286, 12, 137, 25);
		frame.getContentPane().add(btnNewButton_1);
	}
}

