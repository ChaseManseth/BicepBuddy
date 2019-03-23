package views;

import javax.swing.JPanel;
import java.awt.Rectangle;
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
import javax.swing.JTextField;
import java.awt.Color;

public class Signup extends JPanel {
	private JTextField firstTextField;
	private JTextField lastTextField;
	private JTextField emailTextField;
	private JTextField passwordTextField;
	private JTextField passwordConfTextField;
	private JTextField phoneTextField;
	private JTextField ageTextField;
	
	private String genders[]= {"","Male", "Female"};
	private String prefGenders[] = {"Any","Male", "Female"};
	private String goals[] = {"","Lost Weight","Gain Weight","Get Stronger","Coast"};
	private String frequencies[]= {"","Once a week", "3 times a week", "Everyday"};
	private String preferedTimes[]= {"","Early Morning", "Morning", "Lunch","Afternoon","Early Evening", "Night"};
	private String workoutStyles[] = {"","General Fitness", "Cardio", "Powerlifting","Crossfit", "Bodybuilding", "Weight Loss"};
	private String weight[] = {"","0-100","100-125","125-150","150-175","175-200","200+"};
	private String experience[] = {"","None","Little","Average","Experienced"};

	/**
	 * Create the panel.
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
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSubmit.setBounds(30, 430, 115, 32);
		add(btnSubmit);
		
		JComboBox genderCombo = new JComboBox(genders);
		genderCombo.setBackground(Color.LIGHT_GRAY);
		genderCombo.setBounds(455, 240, 150, 25);
		add(genderCombo);
		
		JComboBox preferedGenderCombo = new JComboBox(prefGenders);
		preferedGenderCombo.setBackground(Color.LIGHT_GRAY);
		preferedGenderCombo.setBounds(455, 320, 150, 25);
		add(preferedGenderCombo);
		
		JComboBox goalsCombo = new JComboBox(goals);
		goalsCombo.setBackground(Color.LIGHT_GRAY);
		goalsCombo.setBounds(455, 400, 150, 25);
		add(goalsCombo);
		
		JComboBox frequencyCombo = new JComboBox(frequencies);
		frequencyCombo.setBackground(Color.LIGHT_GRAY);
		frequencyCombo.setBounds(700, 80, 150, 25);
		add(frequencyCombo);
		
		JComboBox timeOfDayCombo = new JComboBox(preferedTimes);
		timeOfDayCombo.setBackground(Color.LIGHT_GRAY);
		timeOfDayCombo.setBounds(700, 160, 150, 25);
		add(timeOfDayCombo);
		
		JComboBox styleCombo = new JComboBox(workoutStyles);
		styleCombo.setBackground(Color.LIGHT_GRAY);
		styleCombo.setBounds(700, 240, 150, 25);
		add(styleCombo);
		
		JComboBox weightCombo = new JComboBox(weight);
		weightCombo.setBackground(Color.LIGHT_GRAY);
		weightCombo.setBounds(700, 320, 150, 25);
		add(weightCombo);
		
		JComboBox experienceCombo = new JComboBox(experience);
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
		lastTextField.setBounds(200, 160, 150, 25);
		add(lastTextField);
		lastTextField.setColumns(10);
		
		emailTextField = new JTextField();
		emailTextField.setBackground(Color.LIGHT_GRAY);
		emailTextField.setBounds(200, 240, 150, 25);
		add(emailTextField);
		emailTextField.setColumns(10);
		
		passwordTextField = new JTextField();
		passwordTextField.setBackground(Color.LIGHT_GRAY);
		passwordTextField.setBounds(200, 320, 150, 25);
		add(passwordTextField);
		passwordTextField.setColumns(10);
		
		passwordConfTextField = new JTextField();
		passwordConfTextField.setBackground(Color.LIGHT_GRAY);
		passwordConfTextField.setBounds(200, 400, 150, 25);
		add(passwordConfTextField);
		passwordConfTextField.setColumns(10);
		
		phoneTextField = new JTextField();
		phoneTextField.setBackground(Color.LIGHT_GRAY);
		phoneTextField.setBounds(455, 80, 150, 25);
		add(phoneTextField);
		phoneTextField.setColumns(10);
		
		ageTextField = new JTextField();
		ageTextField.setBackground(Color.LIGHT_GRAY);
		ageTextField.setBounds(455, 160, 150, 25);
		add(ageTextField);
		ageTextField.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(200, 65, 91, 14);
		add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(200, 145, 107, 14);
		add(lblLastName);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(200, 225, 48, 14);
		add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(200, 305, 115, 14);
		add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setBounds(200, 385, 150, 14);
		add(lblConfirmPassword);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setBounds(455, 65, 127, 14);
		add(lblPhoneNumber);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(455, 145, 48, 14);
		add(lblAge);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(455, 225, 99, 14);
		add(lblGender);
		
		JLabel lblPreferedGender = new JLabel("Preferred Gender");
		lblPreferedGender.setBounds(455, 305, 150, 14);
		add(lblPreferedGender);
		
		JLabel lblGoals = new JLabel("Goals");
		lblGoals.setBounds(455, 385, 48, 14);
		add(lblGoals);
		
		JLabel lblFrequency = new JLabel("Frequency");
		lblFrequency.setBounds(700, 65, 121, 14);
		add(lblFrequency);
		
		JLabel lblTimeOfDay = new JLabel("Time of Day");
		lblTimeOfDay.setBounds(700, 145, 121, 14);
		add(lblTimeOfDay);
		
		JLabel lblStyle = new JLabel("Style");
		lblStyle.setBounds(700, 225, 48, 14);
		add(lblStyle);
		
		JLabel lblWeight = new JLabel("Weight");
		lblWeight.setBounds(700, 305, 99, 14);
		add(lblWeight);
		
		JLabel lblExperience = new JLabel("Experience");
		lblExperience.setBounds(700, 385, 121, 14);
		add(lblExperience);

	}
}
