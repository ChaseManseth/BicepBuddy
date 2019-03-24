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

import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;
import javax.swing.JPasswordField;

public class Login extends JPanel {
	private JTextField emailTextField;
	private JPasswordField passwordField;

	/**
	 * Create the panel.
	 */
	public Login(Master mFrame) {
		
		setBounds(new Rectangle(0, 0, 900, 500));
		setLayout(null);
		
		JLabel loginLabel = new JLabel("Login to Bicep Buddy");
		loginLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		loginLabel.setBounds(348, 61, 239, 25);
		add(loginLabel);
		
		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setBounds(322, 187, 48, 14);
		add(emailLabel);
		
		emailTextField = new JTextField();
		emailTextField.setBackground(Color.LIGHT_GRAY);
		emailTextField.setBounds(431, 184, 192, 20);
		add(emailTextField);
		emailTextField.setColumns(10);
		
		
		// Password Field
		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(41,182,246));
		passwordField.setBounds(433, 234, 185, 20);
		add(passwordField);
		passwordField.setColumns(10);
		
		// Password Background color
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(431, 234, 192, 20);
		add(panel);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(322, 237, 81, 14);
		add(passwordLabel);
		
		JButton loginBtn = new JButton("Login");
		loginBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		loginBtn.setBackground(MaterialColors.GRAY_400);
		loginBtn.setForeground(Color.black);
		
		MaterialUIMovement.add(loginBtn, MaterialColors.GRAY_600);
		
		loginBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mFrame.updateFrame(new ProfileView(mFrame));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				loginBtn.setForeground(Color.white);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				loginBtn.setForeground(Color.black);
			}
		});
		
		loginBtn.setBounds(348, 303, 89, 23);
		add(loginBtn);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSignUp.setBounds(498, 303, 89, 23);
		btnSignUp.setBackground(MaterialColors.GRAY_400);
		btnSignUp.setForeground(new Color(0,0,0));
		
		
		MaterialUIMovement.add(btnSignUp, MaterialColors.GRAY_600);
		
		btnSignUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mFrame.updateFrame(new Signup(mFrame));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSignUp.setForeground(Color.white);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnSignUp.setForeground(Color.black);
			}
		});
		
		add(btnSignUp);

	}
}
