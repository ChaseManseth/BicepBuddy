package views;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Master {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Master window = new Master();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Master() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		final JMenuItem mntmLogin = new JMenuItem("Login");
		mntmLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mntmLogin.setBackground(new Color(48,48,48));
				mntmLogin.setForeground(new Color(255,255,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mntmLogin.setBackground(new Color(240,240,240));
				mntmLogin.setForeground(new Color(0,0,0));
			}
		});
		mntmLogin.setMargin(new Insets(0, 0, 0, 0));
		mntmLogin.setVerifyInputWhenFocusTarget(false);
		mntmLogin.setBounds(new Rectangle(0, 0, 50, 0));
		menuBar.add(mntmLogin);
		
		JMenuItem mntmSignUp = new JMenuItem("Sign Up");
		menuBar.add(mntmSignUp);
		
		JMenuItem mntmProfile = new JMenuItem("Profile");
		menuBar.add(mntmProfile);
		
		JMenuItem mntmOtherUserProfile = new JMenuItem("Other User Profile");
		menuBar.add(mntmOtherUserProfile);
		
		JMenuItem mntmMatch = new JMenuItem("Match");
		menuBar.add(mntmMatch);
		
		
		
		JPanel panel = new Login();
		frame.getContentPane().add(panel);

		frame.setVisible(true);
	}
}
