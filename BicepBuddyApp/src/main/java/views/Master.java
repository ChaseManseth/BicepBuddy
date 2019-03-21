package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;

import mdlaf.MaterialLookAndFeel;

import java.awt.ComponentOrientation;

public class Master {

	private JFrame frame;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new MaterialLookAndFeel());
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
		
		JMenuItem login = new JMenuItem("Login");
		login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				login.setBackground(new Color(48,48,48));
				login.setForeground(new Color(255,255,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				login.setBackground(new Color(240,240,240));
				login.setForeground(new Color(0,0,0));
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("Loading Login");
				updateFrame(new Login());
			}
		});
		menuBar.add(login);
		
		JMenuItem signUp = new JMenuItem("Sign Up");
		signUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Loading signUp");
				updateFrame(new Signup());
			}
		});
		menuBar.add(signUp);
		
		JMenuItem profile = new JMenuItem("Profile");
		menuBar.add(profile);
		
		JMenuItem otherUserProfile = new JMenuItem("Other User Profile");
		menuBar.add(otherUserProfile);
		
		JMenuItem match = new JMenuItem("Match");
		menuBar.add(match);
		
		
		
		panel = new Login();
		frame.getContentPane().add(panel);

		frame.setVisible(true);
		
		System.out.println(menuBar.getMenuCount());
	}
	
	public void updateFrame(JPanel j) {
		panel.setVisible(false);
		panel = j;
		panel.setVisible(true);
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}
}
