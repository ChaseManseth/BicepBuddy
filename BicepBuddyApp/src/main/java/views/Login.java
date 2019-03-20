package views;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

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
		lblLogin.setBounds(348, 105, 196, 25);
		add(lblLogin);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(348, 188, 48, 14);
		add(lblEmail);
		
		textField = new JTextField();
		textField.setBounds(457, 185, 96, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(348, 238, 57, 14);
		add(lblPassword);
		
		textField_1 = new JTextField();
		textField_1.setBounds(457, 235, 96, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(385, 303, 89, 23);
		add(btnLogin);

	}
}
