package views;

import javax.swing.JPanel;
import java.awt.Rectangle;
import javax.swing.JLabel;

public class Signup extends JPanel {

	/**
	 * Create the panel.
	 */
	public Signup() {
		setBounds(new Rectangle(0, 0, 900, 500));
		setLayout(null);
		
		JLabel lblSignup = new JLabel("Signup");
		lblSignup.setBounds(374, 105, 46, 14);
		add(lblSignup);

	}
}
