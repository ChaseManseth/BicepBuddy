package bicepBuddyPackage;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;

import Matching.MatchController;
import Messaging.DMView;
import User.UserController;
import User.UserDB;
import Views.Login;
import Views.OtherProfileView;
import Views.ProfileView;
import Views.SettingsView;
import Views.Signup;
import admin.AdminController;
import admin.AdminGui;
import mdlaf.MaterialLookAndFeel;

import java.awt.ComponentOrientation;
import javax.swing.JMenu;
import java.awt.Font;

// TODO: Auto-generated Javadoc
/**
 * The Class Master.
 * @authors: Zachary Steudel, Hunter Long, Chase Manseth, Bob Rein, Reece Kemball-Cook
 */
public class Master {

	/** The Constant appLogger. */
	public final static Logger appLogger = Logger.getLogger(Master.class.getName());
	//master is an example of the COMPOSITE PATTERN
	/** The master. */
	//holds all of the components (views)
	private static Master master = null;

	//create static logger (SINGLETON PATTERN)
	static {
		try {
			InputStream configFile = new FileInputStream("logger.properties");
			LogManager.getLogManager().readConfiguration(configFile);
			configFile.close();

			appLogger.info(":: Beginning Swing App");

		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	/** The frame. */
	private static JFrame frame;
	
	/** The panel. */
	private static JPanel panel;
	
	/**
	 * Gets the single instance of Master.
	 *
	 * @return single instance of Master
	 */
	//SINGLETON PATTERN Master Frame
	public static Master getInstance() {
		if(master == null) {
			appLogger.info(":: Loading Master Frame");
			master = new Master();
		}
		
		return master;
	}

	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new MaterialLookAndFeel());
					Master.getInstance().frame.setVisible(true);
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
	 * FACADE: encapsulates the initialization process.
	 * COMMAND PATTERN: Every ActionListener represents an example of command.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setTitle("Bicep Buddy v0.3");
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
				updateFrame(new Login());
			}
		});
		menuBar.add(login);

		JMenu signUp = new JMenu("Sign Up");
		signUp.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		signUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Loading signUp");
				updateFrame(new Signup());
			}
		});
		menuBar.add(signUp);


		panel = new Login();
		frame.getContentPane().add(panel);

		frame.setVisible(true);
	}

	/**
	 * Update frame.
	 *
	 * @param j the j
	 */
	//FACADE: Handles switching out the panel every time we change views.
	public static void updateFrame(JPanel j) {
		frame.getContentPane().removeAll();
		panel = j;
		frame.getContentPane().add(panel);
		frame.setVisible(true);
		//panel.setVisible(false);
		//panel = j;
		//panel.setVisible(true);
		// remove the current JPanel so that our frame doesn't have a billion
		// panels in it when we switch views a bunch
		//frame.remove(frame.getContentPane());
		
		//frame.setVisible(true);
	}

	//FACADE PATTERN: function encapsulates the loading of the logged
	/**
	 * Logged in menu load.
	 */
	//in menu bar.
	public void loggedInMenuLoad() {
		//start the master frame with only the signup and login menu bars
		//available. When the user logs in, open up the other menu bar options.
		JMenuBar menuBar = new JMenuBar();
		this.frame.setJMenuBar(null);
		this.frame.setJMenuBar(menuBar);

		JMenu mnProfile = new JMenu("Profile");
		mnProfile.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnProfile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("Loading Profile");
				updateFrame(new ProfileView());
			}
		});
		menuBar.add(mnProfile);

		/*JMenu mnOtherUserProfile = new JMenu("Other User Profile");
		mnOtherUserProfile.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnOtherUserProfile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("Loading OtherProfile");
				UserDB udb = new UserDB();
				updateFrame(new OtherProfileView(udb.testerGetRandomUser()));
			}
		});
		menuBar.add(mnOtherUserProfile);*/

		JMenu settings = new JMenu("Settings");
		settings.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		settings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("Loading Settings");
				updateFrame(new SettingsView());
			}
		});
		menuBar.add(settings);

		JMenu mnMatch = new JMenu("Match");
		mnMatch.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnMatch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("Loading match view");
				//create match controller
				MatchController.generateFrame();
			}
		});
		menuBar.add(mnMatch);
		
		
		JMenu administrator = new JMenu("Administrator"); 
		administrator.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		administrator.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("Loading administrator view");
				//create user controller
				AdminController.generateFrame();
			}
		});
		menuBar.add(administrator);

		
		/*
		 * JMenu chat = new JMenu("Chat"); chat.setFont(new Font("Segoe UI", Font.PLAIN,
		 * 12)); chat.addMouseListener(new MouseAdapter() {
		 * 
		 * @Override public void mouseClicked(MouseEvent e) {
		 * Master.appLogger.info(":: Loading DM View..."); // Placeholder invocation for
		 * testing User.User user =
		 * UserController.getInstance().getUserById("5cbfc7992060cc3d409469e8");
		 * DMController.getInstance(user).updateView();
		 * frame.addComponentListener(DMController.getInstance());
		 * System.out.println(UserController.getUser().getId()); } });
		 * menuBar.add(chat);
		 */
		

		JMenu logout = new JMenu("Log Out");
		logout.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		logout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("Loading logout");
				UserController.setUser(null);
				UserController.getInstance().setChangesToMatches(true);

				Master.appLogger.info(":: User logged out.");
				loggedOutMenuLoad();
			}
		});
		menuBar.add(logout);
	}

	//FACADE PATTERN: encapsulates loading the logged
	/**
	 * Logged out menu load.
	 */
	//out menu bar
	public void loggedOutMenuLoad() {
		JMenuBar menuBar = new JMenuBar();
		this.frame.setJMenuBar(null);
		this.frame.setJMenuBar(menuBar);

		JMenu login = new JMenu("Login");
		login.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("Loading Login");
				updateFrame(new Login());
			}
		});
		menuBar.add(login);

		JMenu signUp = new JMenu("Sign Up");
		signUp.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		signUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Loading signUp");
				updateFrame(new Signup());
			}
		});
		menuBar.add(signUp);


		updateFrame(new Login());
	}
	
	/**
	 * Gets the frame.
	 *
	 * @return the frame
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Sets the frame.
	 *
	 * @param frame the new frame
	 */
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
