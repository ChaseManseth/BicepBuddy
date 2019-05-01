package bicepBuddyPackage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;

/**
 * The Class ErrorGUI. Class will pop up an error frame for the user.
 * @authors: Zachary Steudel, Hunter Long, Chase Manseth, Bob Rein, Reece Kemball-Cook
 */
public class ErrorGUI {
	
	/** The frame. */
	private JFrame frame;
	
	/** The error message field. */
	private JTextField errorMsg;
	
	/**
	 * Instantiates a new error GUI.
	 *
	 * @param message: Message to be printed on the Error frame.
	 */
	public ErrorGUI(String message) {
		frame = new JFrame("ERROR");
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		errorMsg = new JTextField();
		errorMsg.setEditable(false);
		frame.getContentPane().add(errorMsg, BorderLayout.CENTER);
		errorMsg.setColumns(10);
		
		errorMsg.setText(message);
		
		frame.setBounds(100, 100, 500, 200);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}
	
}
