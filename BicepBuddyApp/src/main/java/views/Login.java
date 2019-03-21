package views;

import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.MatteBorder;

public class Login extends JPanel {
	private JTextField emailTextField;
	private JTextField passwordTextField;

	/**
	 * Create the panel.
	 */
	public Login() {
		
		setBounds(new Rectangle(0, 0, 900, 500));
		setLayout(null);
		
		JLabel loginLabel = new JLabel("Login to Bicep Buddy");
		loginLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		loginLabel.setBounds(348, 61, 239, 25);
		add(loginLabel);
		
		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setBounds(348, 188, 48, 14);
		add(emailLabel);
		
		emailTextField = new JTextField();
		emailTextField.setBackground(new Color(222,222,222));
		emailTextField.setBounds(457, 185, 152, 20);
		add(emailTextField);
		emailTextField.setColumns(10);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(348, 238, 81, 14);
		add(passwordLabel);
		
		passwordTextField = new JTextField();
		passwordTextField.setBackground(new Color(222,222,222));
		passwordTextField.setBounds(457, 235, 152, 20);
		add(passwordTextField);
		passwordTextField.setColumns(10);
		
		JButton loginBtn = new JButton("Login");
		loginBtn.setBackground(new Color(108,117,125));
		loginBtn.setForeground(new Color(255,255,255));
		
		loginBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				loginBtn.setBackground(new Color(80,87,94));
				loginBtn.setForeground(new Color(255,255,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				loginBtn.setBackground(new Color(108,117,125));
				loginBtn.setForeground(new Color(255,255,255));
			}
		});
		
		loginBtn.setBounds(385, 303, 89, 23);
		add(loginBtn);
		

	}
}
