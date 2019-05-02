package Views;
import java.awt.BorderLayout;
/**
 * @authors: Zachary Steudel, Hunter Long, Chase Manseth, Bob Rein, Reece Kemball-Cook
 */
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.SwingWorker;
import javax.swing.border.LineBorder;

import Matching.MatchAlgorithm;
import User.User;
import User.UserController;
import bicepBuddyPackage.Master;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;

import javax.swing.JMenu;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.Font;

/**
 * The Class SettingsView. Will allow the users to see and change their settings.
 * @authors: Zachary Steudel, Hunter Long, Chase Manseth, Bob Rein, Reece Kemball-Cook
 */
public class SettingsView extends JPanel {

	/** The field for user's first name. */
	private JTextField txtFirstName;
	
	/** The email field. */
	private JTextField txtEmail;
	
	/** The last name field. */
	private JTextField txtLastName;
	
	/** The age field. */
	private JTextField ageField;
	
	/** The phone field. */
	private JTextField phoneField;



	/**
	 * Create the settings view.
	 */
	public SettingsView() {
		User u = UserController.getUser();
		setBounds(100, 100, 900, 560);
		setLayout(null);
		
		JLabel lblName2 = new JLabel("Account Settings");
		lblName2.setFont(new Font("Dialog", Font.BOLD, 18));
		lblName2.setBounds(356, 12, 203, 15);
		add(lblName2);
		
		txtFirstName = new JTextField();
		txtFirstName.setBounds(64, 66, 169, 25);
		txtFirstName.setBackground(Color.LIGHT_GRAY);
		add(txtFirstName);
		txtFirstName.setText(u.getfName());
		txtFirstName.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(64, 209, 169, 25);
		txtEmail.setBackground(Color.LIGHT_GRAY);
		add(txtEmail);
		txtEmail.setText(u.getEmail());
		txtEmail.setColumns(10);
		
		txtLastName = new JTextField();
		txtLastName.setText(u.getlName());
		txtLastName.setBounds(64, 145, 169, 25);
		txtLastName.setBackground(Color.LIGHT_GRAY);
		add(txtLastName);
		txtLastName.setColumns(10);

		JComboBox styleBox = new JComboBox(MatchAlgorithm.STYLES);
		styleBox.setBounds(418, 62, 161, 32);
		styleBox.setBackground(Color.LIGHT_GRAY);
		styleBox.setSelectedItem(u.getStyle());
		add(styleBox);
		
		JLabel lblWorkoutStyle = new JLabel("Workout Style");
		lblWorkoutStyle.setBounds(282, 71, 107, 15);
		add(lblWorkoutStyle);
		
		JComboBox timeOfDay = new JComboBox(MatchAlgorithm.TIMES);
		timeOfDay.setBounds(416, 141, 161, 32);
		timeOfDay.setBackground(Color.LIGHT_GRAY);
		timeOfDay.setSelectedItem(u.getTimeOfDay());
		add(timeOfDay);
		
		JLabel label = new JLabel("Preferred Time");
		label.setBounds(282, 150, 107, 15);
		add(label);
		
		JComboBox gender = new JComboBox(MatchAlgorithm.GENDERS);
		gender.setBounds(418, 205, 161, 32);
		gender.setBackground(Color.LIGHT_GRAY);
		gender.setSelectedItem(u.getGender());
		add(gender);
		
		JLabel label_1 = new JLabel("Gender");
		label_1.setBounds(282, 222, 107, 15);
		add(label_1);
		
		JComboBox prefGender = new JComboBox(MatchAlgorithm.GENDERS);
		prefGender.setBounds(418, 277, 161, 32);
		prefGender.setBackground(Color.LIGHT_GRAY);
		prefGender.setSelectedItem(u.getPrefGender());
		add(prefGender);
		
		JLabel label_2 = new JLabel("Buddy Gender");
		label_2.setBounds(282, 286, 107, 15);
		add(label_2);
		
		JComboBox freqBox = new JComboBox(MatchAlgorithm.FREQUENCIES);
		freqBox.setBounds(418, 350, 161, 32);
		freqBox.setBackground(Color.LIGHT_GRAY);
		freqBox.setSelectedItem(u.getFrequency());
		add(freqBox);
		
		JLabel label_3 = new JLabel("Weekly Frequency");
		label_3.setBounds(282, 359, 137, 15);
		add(label_3);
		
		JButton deleter = new JButton("Delete My Profile");
		deleter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame confirm = new JFrame();
				JPanel p = new JPanel();
				p.setLayout(new BorderLayout());
				confirm.getContentPane().add(p);
				
				JTextField uSure = new JTextField();
				uSure.setText("Are you sure you want to delete your account?");
				uSure.setEditable(false);
				
				JButton yesBtn = new JButton();
				yesBtn.setText("YES");
				yesBtn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						UserController uc = new UserController();
						uc.deleteAccount(UserController.getUser());
						confirm.dispose();
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						yesBtn.setBackground(Color.RED);
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						yesBtn.setBackground(new JButton().getBackground());
					}
				});
				
				JButton noBtn = new JButton();
				noBtn.setText("NO ");
				noBtn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						confirm.dispose();
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						noBtn.setBackground(Color.GREEN);
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						noBtn.setBackground(new JButton().getBackground());
					}
				});
				
				
				p.add(uSure, BorderLayout.PAGE_START);
				p.add(yesBtn, BorderLayout.LINE_START);
				p.add(noBtn, BorderLayout.LINE_END);
				
				confirm.setBounds(100, 100, 500, 200);
				confirm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				confirm.setVisible(true);
			}
		});
		deleter.setBounds(64, 479, 203, 25);
		add(deleter);
		MaterialUIMovement.add(deleter, MaterialColors.GRAY_600);
		deleter.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				deleter.setForeground(Color.white);
			}
			
			public void mouseExited(MouseEvent e) {
				deleter.setForeground(Color.black);
			}
		});
		
		JLabel lblGoal = new JLabel("Goal");
		lblGoal.setBounds(591, 70, 56, 16);
		add(lblGoal);
		
		JComboBox goalsBox = new JComboBox(MatchAlgorithm.GOALS);
		goalsBox.setBounds(693, 62, 169, 32);
		goalsBox.setBackground(Color.LIGHT_GRAY);
		goalsBox.setSelectedItem(u.getGoals());
		add(goalsBox);
		
		JLabel lblWeight = new JLabel("Weight");
		lblWeight.setBounds(589, 149, 56, 16);
		add(lblWeight);
		
		JComboBox weightBox = new JComboBox(MatchAlgorithm.WEIGHTCLASS);
		weightBox.setBounds(693, 141, 169, 32);
		weightBox.setSelectedItem(u.getWeight());
		weightBox.setBackground(Color.LIGHT_GRAY);
		add(weightBox);
		
		JLabel lblExperience = new JLabel("Experience");
		lblExperience.setBounds(591, 213, 84, 16);
		add(lblExperience);
		
		JComboBox expBox = new JComboBox(MatchAlgorithm.EXPERIENCE);
		expBox.setBounds(693, 205, 169, 32);
		expBox.setBackground(Color.LIGHT_GRAY);
		expBox.setSelectedItem(u.getExperience());
		add(expBox);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(591, 285, 56, 16);
		add(lblAge);
		
		ageField = new JTextField();
		ageField.setBounds(694, 281, 156, 25);
		ageField.setText(u.getAge());
		ageField.setBackground(Color.LIGHT_GRAY);
		add(ageField);
		ageField.setColumns(10);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(591, 358, 56, 16);
		add(lblPhone);
		
		phoneField = new JTextField();
		phoneField.setColumns(10);
		phoneField.setBackground(Color.LIGHT_GRAY);
		phoneField.setText(u.getPhone());
		phoneField.setBounds(694, 355, 156, 25);
		add(phoneField);
		
		JButton saver = new JButton("Save changes");
		MaterialUIMovement.add(saver, MaterialColors.GRAY_600);
		
		saver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Master.getInstance().addLoading();
				
				new SwingWorker<Void, Void>(){

					protected Void doInBackground() throws Exception {
						UserController.getInstance().editUser(txtEmail.getText(), txtFirstName.getText(), txtLastName.getText(),
							    styleBox.getSelectedItem(), timeOfDay.getSelectedItem(), gender.getSelectedItem(),
							    prefGender.getSelectedItem(), freqBox.getSelectedItem(), goalsBox.getSelectedItem(),
							    weightBox.getSelectedItem(), expBox.getSelectedItem(), ageField.getText(), phoneField.getText());
						UserController.getInstance().setTimesMatchCalled(5);
						return null;
					}
					
					protected void done() {
						Master.getInstance().unLoad();
					}
				}.execute();
				
			}
		});
		saver.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				saver.setForeground(Color.white);
			}
			
			public void mouseExited(MouseEvent e) {
				saver.setForeground(Color.black);
			}
		});
		saver.setBounds(584, 479, 203, 25);
		add(saver);
	}
	
	/**
	 * Pops up a frame that indicates that we saved our changes.
	 */
	public static void saved() {
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
	
	/**
	 * Deprecated and unused.
	 */
	public static void changeImageFrame() {
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
}
