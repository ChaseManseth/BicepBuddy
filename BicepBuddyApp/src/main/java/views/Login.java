package views;

import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;

public class Login extends JPanel {
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public Login() {
		setBounds(new Rectangle(0, 0, 900, 500));
		setLayout(null);
		
		JLabel lblLogin = new JLabel("Login to Bicep Buddy");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLogin.setBounds(348, 105, 239, 25);
		add(lblLogin);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(348, 188, 48, 14);
		add(lblEmail);
		
		textField = new JTextField();
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(457, 185, 96, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(348, 238, 81, 14);
		add(lblPassword);
		
		textField_1 = new JTextField();
		textField_1.setBackground(Color.LIGHT_GRAY);
		textField_1.setBounds(457, 235, 96, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBackground(Color.LIGHT_GRAY);
		btnLogin.setBounds(385, 303, 89, 23);
		add(btnLogin);
		

	}
}
