package Views;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import bicepBuddyPackage.Master;

/**
 * The Class MatchGUI.
 * @authors: Zachary Steudel, Hunter Long, Chase Manseth, Bob Rein, Reece Kemball-Cook
 */

/**
 * The Class LoadingView.
 */
public class LoadingView extends JPanel{
	
	/** The loading field. */
	private JTextField loadingField;
	
	/**
	 * Instantiates a new loading view.
	 */
	public LoadingView() {
		setBounds(0, 0, 900, 500);
		setLayout(null);
		
		Master.appLogger.info(":: LOADING SCREEN");
		
		loadingField = new JTextField();
		loadingField.setForeground(new Color(253, 245, 230));
		loadingField.setBackground(new Color(0, 0, 255));
		loadingField.setEditable(false);
		loadingField.setFont(new Font("Tahoma", Font.BOLD, 25));
		loadingField.setText("Loading Your Page . . .");
		loadingField.setHorizontalAlignment(SwingConstants.CENTER);
		loadingField.setBounds(12, 13, 876, 85);
		add(loadingField);
		loadingField.setColumns(10);
		
		
	}
}
