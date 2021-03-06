package Views;

import javax.swing.JPanel;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import Matching.MatchAlgorithm;
import User.UserController;
import bicepBuddyPackage.Master;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JPasswordField;

/**
 * The Class Signup. This is the view where the user can create an account
 * and put in the fields that they would like their account to have. User is required
 * to enter name, last name, email, password.
 *
 * @authors: Zachary Steudel, Hunter Long, Chase Manseth, Bob Rein, Reece Kemball-Cook
 */
public class Signup extends JPanel {
	
	/** The first name text field. */
	private JTextField firstTextField;
	
	/** The last name text field. */
	private JTextField lastTextField;
	
	/** The email text field. */
	private JTextField emailTextField;
	
	/** The phone text field. */
	private JTextField phoneTextField;
	
	/** The age text field. */
	private JTextField ageTextField;
	
	/** The password field. */
	private JPasswordField password;
	
	/** The confirm PW field. */
	private JPasswordField confirmPW;

	/**
	 * Create the panel for Signup.
	 */
	public Signup() {
		setBounds(new Rectangle(0, 0, 900, 500));
		setLayout(null);
		
		JLabel lblSignup_1 = new JLabel("Signup");
		lblSignup_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignup_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSignup_1.setBounds(30, 30, 115, 32);
		add(lblSignup_1);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBackground(Color.LIGHT_GRAY);
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSubmit.setBounds(30, 430, 115, 32);
		MaterialUIMovement.add(btnSubmit, MaterialColors.GRAY_600);
		
		add(btnSubmit);
		
		JComboBox genderCombo = new JComboBox(MatchAlgorithm.GENDERS);
		genderCombo.setBackground(Color.LIGHT_GRAY);
		genderCombo.setBounds(455, 240, 150, 25);
		add(genderCombo);
		
		JComboBox preferedGenderCombo = new JComboBox(MatchAlgorithm.GENDERS);
		preferedGenderCombo.setBackground(Color.LIGHT_GRAY);
		preferedGenderCombo.setBounds(697, 240, 150, 25);
		add(preferedGenderCombo);
		
		JComboBox goalsCombo = new JComboBox(MatchAlgorithm.GOALS);
		goalsCombo.setBackground(Color.LIGHT_GRAY);
		goalsCombo.setBounds(200, 320, 150, 25);
		add(goalsCombo);
		
		JComboBox frequencyCombo = new JComboBox(MatchAlgorithm.FREQUENCIES);
		frequencyCombo.setBackground(Color.LIGHT_GRAY);
		frequencyCombo.setBounds(455, 320, 150, 25);
		add(frequencyCombo);
		
		JComboBox timeOfDayCombo = new JComboBox(MatchAlgorithm.TIMES);
		timeOfDayCombo.setBackground(Color.LIGHT_GRAY);
		timeOfDayCombo.setBounds(697, 320, 150, 25);
		add(timeOfDayCombo);
		
		JComboBox styleCombo = new JComboBox(MatchAlgorithm.STYLES);
		styleCombo.setBackground(Color.LIGHT_GRAY);
		styleCombo.setBounds(200, 400, 150, 25);
		add(styleCombo);
		
		JComboBox weightCombo = new JComboBox(MatchAlgorithm.WEIGHTCLASS);
		weightCombo.setBackground(Color.LIGHT_GRAY);
		weightCombo.setBounds(455, 400, 150, 25);
		add(weightCombo);
		
		JComboBox experienceCombo = new JComboBox(MatchAlgorithm.EXPERIENCE);
		experienceCombo.setBackground(Color.LIGHT_GRAY);
		experienceCombo.setBounds(700, 400, 150, 25);
		add(experienceCombo);
		
		
		firstTextField = new JTextField();
		firstTextField.setBackground(Color.LIGHT_GRAY);
		firstTextField.setBounds(200, 80, 150, 25);
		add(firstTextField);
		firstTextField.setColumns(10);
		
		lastTextField = new JTextField();
		lastTextField.setBackground(Color.LIGHT_GRAY);
		lastTextField.setBounds(455, 80, 150, 25);
		add(lastTextField);
		lastTextField.setColumns(10);
		
		emailTextField = new JTextField();
		emailTextField.setBackground(Color.LIGHT_GRAY);
		emailTextField.setBounds(700, 80, 150, 25);
		add(emailTextField);
		emailTextField.setColumns(10);
		
		phoneTextField = new JTextField();
		phoneTextField.setBackground(Color.LIGHT_GRAY);
		phoneTextField.setBounds(700, 160, 150, 25);
		add(phoneTextField);
		phoneTextField.setColumns(10);
		
		ageTextField = new JTextField();
		ageTextField.setBackground(Color.LIGHT_GRAY);
		ageTextField.setBounds(200, 240, 150, 25);
		add(ageTextField);
		ageTextField.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(200, 65, 91, 14);
		add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(455, 65, 107, 14);
		add(lblLastName);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(700, 65, 48, 14);
		add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(200, 145, 115, 14);
		add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setBounds(455, 145, 150, 14);
		add(lblConfirmPassword);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setBounds(700, 145, 127, 14);
		add(lblPhoneNumber);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(200, 225, 48, 14);
		add(lblAge);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(455, 225, 99, 14);
		add(lblGender);
		
		JLabel lblPreferedGender = new JLabel("Preferred Buddy Gender");
		lblPreferedGender.setBounds(697, 225, 193, 14);
		add(lblPreferedGender);
		
		JLabel lblGoals = new JLabel("Goals");
		lblGoals.setBounds(200, 305, 48, 14);
		add(lblGoals);
		
		JLabel lblFrequency = new JLabel("Weekly Frequency");
		lblFrequency.setBounds(455, 305, 150, 14);
		add(lblFrequency);
		
		JLabel lblTimeOfDay = new JLabel("Time of Day");
		lblTimeOfDay.setBounds(697, 305, 121, 14);
		add(lblTimeOfDay);
		
		JLabel lblStyle = new JLabel("Style");
		lblStyle.setBounds(200, 385, 48, 14);
		add(lblStyle);
		
		JLabel lblWeight = new JLabel("Weight");
		lblWeight.setBounds(455, 385, 99, 14);
		add(lblWeight);
		
		JLabel lblExperience = new JLabel("Experience");
		lblExperience.setBounds(700, 385, 121, 14);
		add(lblExperience);
		
		password = new JPasswordField();
		password.setBackground(new Color(41,182,246));
		password.setBounds(202, 162, 142, 23);
		add(password);
		
		// Password Background color
		JPanel pwBkg = new JPanel();
		pwBkg.setBackground(Color.LIGHT_GRAY);
		pwBkg.setBounds(200, 162, 150, 23);
		add(pwBkg);
		
		confirmPW = new JPasswordField();
		confirmPW.setBackground(new Color(41,182,246));
		confirmPW.setBounds(457, 165, 142, 23);
		add(confirmPW);
		
		// Password Background color
		JPanel confirmPwBkg = new JPanel();
		confirmPwBkg.setBackground(Color.LIGHT_GRAY);
		confirmPwBkg.setBounds(455, 165, 150, 23);
		add(confirmPwBkg);
		
		//submission of user data. Data will be submitted to the controller,
		//which will submit to the CSV (or DB)
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Master.getInstance().addLoading();
				
				new SwingWorker<Void, Void>(){

					@Override
					protected Void doInBackground() throws Exception {
						UserController.getInstance().validateSignup(firstTextField.getText(), lastTextField.getText(), 
								 emailTextField.getText(), 
								 phoneTextField.getText(), ageTextField.getText(), 
								 (String)genderCombo.getSelectedItem(), (String)preferedGenderCombo.getSelectedItem(), 
								 (String)goalsCombo.getSelectedItem(), (String)frequencyCombo.getSelectedItem(), 
								 (String)timeOfDayCombo.getSelectedItem(), (String)styleCombo.getSelectedItem(), 
								 (String)weightCombo.getSelectedItem(), 
								 (String)experienceCombo.getSelectedItem(), password.getText(),
								 confirmPW.getText());
						return null;
					}
					
					protected void done() {
						Master.getInstance().unLoad();
					}
				}.execute();
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSubmit.setForeground(Color.white);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnSubmit.setForeground(Color.black);
			}
		});

	}
}
