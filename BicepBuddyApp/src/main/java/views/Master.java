package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;

import mdlaf.MaterialLookAndFeel;

import java.awt.ComponentOrientation;
import javax.swing.JMenu;
import java.awt.Font;

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
		Master mFrameReference = this;
		
		frame = new JFrame();
		frame.setTitle("Bicep Buddy v0.1");
		frame.setResizable(false);
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu login = new JMenu("Login");
		login.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("Loading Login");
				updateFrame(new Login(mFrameReference));
			}
		});
		menuBar.add(login);
		
		JMenu signUp = new JMenu("Sign Up");
		signUp.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		signUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Loading signUp");
				updateFrame(new Signup(mFrameReference));
			}
		});
		menuBar.add(signUp);
		
		JMenu mnProfile = new JMenu("Profile");
		mnProfile.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnProfile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("Loading Profile");
				updateFrame(new ProfileView(mFrameReference));
			}
		});
		menuBar.add(mnProfile);
		
		JMenu mnOtherUserProfile = new JMenu("Other User Profile");
		mnOtherUserProfile.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnOtherUserProfile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("Loading OtherProfile");
				updateFrame(new OtherProfileView());
			}
		});
		menuBar.add(mnOtherUserProfile);
		
		JMenu settings = new JMenu("Settings");
		settings.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		settings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("Loading Settings");
				updateFrame(new SettingsView(mFrameReference));
			}
		});
		menuBar.add(settings);
		
		JMenu mnMatch = new JMenu("Match");
		mnMatch.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnMatch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("Loading match view");
				
				ArrayList<TestMatch> matches = new ArrayList<>();
				matches.add(new TestMatch("Cerny", 76.6));
				matches.add(new TestMatch("Cerniette", 65.23));
				matches.add(new TestMatch("Hillary Clinton", 96.83));
				
				updateFrame(new MatchGUI(matches, mFrameReference));
			}
		});
		menuBar.add(mnMatch);
		
		
		panel = new ProfileView(mFrameReference);
		frame.getContentPane().add(panel);

		frame.setVisible(true);
	}
	
	public void updateFrame(JPanel j) {
		panel.setVisible(false);
		panel = j;
		panel.setVisible(true);
		// remove the current JPanel so that our frame doesn't have a billion
		// panels in it when we switch views a bunch
		frame.remove(frame.getContentPane());
		
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}
}
