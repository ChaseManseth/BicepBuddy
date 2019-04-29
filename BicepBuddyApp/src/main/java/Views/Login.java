
package Views;

import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.border.MatteBorder;

import User.UserController;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;
import javax.swing.JPasswordField;

// TODO: Auto-generated Javadoc
/**
 * The Class Login.
 * @authors: Zachary Steudel, Hunter Long, Chase Manseth, Bob Rein, Reece Kemball-Cook
 */
public class Login extends JPanel {
	
	/** The email text field. */
	private JTextField emailTextField;
	
	/** The password field. */
	private JPasswordField passwordField;

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
		emailLabel.setBounds(322, 180, 48, 14);
		add(emailLabel);
		
		emailTextField = new JTextField();
		emailTextField.setBackground(Color.LIGHT_GRAY);
		emailTextField.setBounds(431, 171, 192, 33);
		add(emailTextField);
		emailTextField.setColumns(10);
		
		
		// Password Field
		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(41,182,246));
		passwordField.setBounds(433, 221, 185, 33);
		add(passwordField);
		passwordField.setColumns(10);
		
		passwordField.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// user can press enter to log in.
				UserController uc = new UserController();
				uc.validateLogin(emailTextField.getText(), passwordField.getText());
			}
			
		});
		
		// Password Background color
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(431, 221, 192, 33);
		add(panel);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(322, 228, 81, 14);
		add(passwordLabel);
		
		JButton loginBtn = new JButton("Login");
		loginBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		loginBtn.setBackground(MaterialColors.GRAY_400);
		loginBtn.setForeground(Color.black);
		
		MaterialUIMovement.add(loginBtn, MaterialColors.GRAY_600);
		
		loginBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserController uc = new UserController();
				uc.validateLogin(emailTextField.getText(), passwordField.getText());
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
				ViewController vc = new ViewController();
				vc.signUpView();
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
		
		//ADD THE BICEP PICTURE
		JLabel logoLabel = new JLabel("");
		logoLabel.setBounds(322, 339, 301, 148);
		add(logoLabel);
		try {
		    BufferedImage myPicture = ImageIO.read(new File("bicep.jpg"));
		    Image scaled = myPicture.getScaledInstance(logoLabel.getWidth(), logoLabel.getHeight(),
		            Image.SCALE_SMOOTH);
			logoLabel.setIcon(new ImageIcon(scaled));
	    } catch (IOException e) {
		    e.printStackTrace();
	    }

	}
}
