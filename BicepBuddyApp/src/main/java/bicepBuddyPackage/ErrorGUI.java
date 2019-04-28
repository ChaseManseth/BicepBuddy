package bicepBuddyPackage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;

// TODO: Auto-generated Javadoc
/**
 * The Class ErrorGUI.
 * @authors: Zachary Steudel, Hunter Long, Chase Manseth, Bob Rein, Reece Kemball-Cook
 */
public class ErrorGUI {
	
	/** The frame. */
	private JFrame frame;
	
	/** The error msg. */
	private JTextField errorMsg;
	
	/**
	 * Instantiates a new error GUI.
	 *
	 * @param message the message
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
