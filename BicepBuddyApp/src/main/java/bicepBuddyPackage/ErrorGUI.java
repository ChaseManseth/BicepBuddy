package bicepBuddyPackage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;

public class ErrorGUI {
	private JFrame frame;
	private JTextField errorMsg;
	
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
