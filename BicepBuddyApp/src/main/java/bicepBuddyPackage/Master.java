package bicepBuddyPackage;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.UIManager;

import Matching.MatchController;
import Matching.MatchGUI;
import Messaging.DMController;
import User.UserController;
import Views.FriendsList;
import Views.Login;
import Views.ProfileView;
import Views.SettingsView;
import Views.Signup;
import admin.AdminController;
import mdlaf.MaterialLookAndFeel;

/**
 * The Class Master. Master is the central hub of the program. It has the Master frame 
 * where the panels with the Bicep Buddy Application live. Example of the MEDIATOR PATTERN,
 * where Master will act as a midway between panels as the program transitions views. 
 * @authors: Zachary Steudel, Hunter Long, Chase Manseth, Bob Rein, Reece Kemball-Cook
 */
public class Master {

	/** The Logger for the program. */
	public final static Logger appLogger = Logger.getLogger(Master.class.getName());
	//master is an example of the COMPOSITE PATTERN
	/** The master frame. */
	//holds all of the components (views)
	private static Master master = null;
	
	/** This boolean will act as a flag for the DM semaphore. */
	public boolean stop = true;

	//create static logger (SINGLETON PATTERN)
	static {
		try {
			InputStream configFile = new FileInputStream("logger.properties");
			LogManager.getLogManager().readConfiguration(configFile);
			configFile.close();

			appLogger.info(":: Beginning Swing App");

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	

	/** The frame of the program. */
	private static JFrame frame;
	
	/** The panel that is currently being shown. */
	private static JPanel panel;
	
	/** The loading label for when a new action is loading. */
	private JLabel loadingLabel = new JLabel("Loading . . .");
	
	/** The menu bar that holds the main travel dock for going between views.. */
	private JMenuBar menuBar;
	
	/**
	 * Gets the menu bar.
	 *
	 * @return the menu bar
	 */
	public JMenuBar getMenuBar() {
		return menuBar;
	}

	/**
	 * Sets the menu bar.
	 *
	 * @param menuBar the new menu bar
	 */
	public void setMenuBar(JMenuBar menuBar) {
		this.menuBar = menuBar;
	}
	
	

	/**
	 * Gets the loading label.
	 *
	 * @return the loading label
	 */
	public JLabel getLoadingLabel() {
		return loadingLabel;
	}

	/**
	 * Sets the loading label.
	 *
	 * @param loadingLabel: the new loading label
	 */
	public void setLoadingLabel(JLabel loadingLabel) {
		this.loadingLabel = loadingLabel;
	}

	/**
	 * Gets the single instance of Master Frame.
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
	 * Launch the application, instantiate the singleton Master Frame.
	 *
	 * @param args: command-line args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new MaterialLookAndFeel());
					Master.getInstance();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Launch the application.
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

		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		loadingLabel.setForeground(Color.BLUE);

		JMenu login = new JMenu("Login");
		login.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				menuBar.add(loadingLabel);
				menuBar.revalidate();
				menuBar.repaint();
				
				new SwingWorker<Void, Void>(){

					@Override
					protected Void doInBackground() throws Exception {
						updateFrame(new Login());
						return null;
					}
					
					protected void done() {
						menuBar.remove(loadingLabel);
						
						menuBar.revalidate();
						menuBar.repaint();
					}
				}.execute();
				
			}
		});
		menuBar.add(login);

		JMenu signUp = new JMenu("Sign Up");
		signUp.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		signUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuBar.add(loadingLabel);
				menuBar.revalidate();
				menuBar.repaint();
				
				new SwingWorker<Void, Void>(){

					@Override
					protected Void doInBackground() throws Exception {
						updateFrame(new Signup());
						return null;
					}
					
					protected void done() {
						menuBar.remove(loadingLabel);
						
						menuBar.revalidate();
						menuBar.repaint();
					}
				}.execute();
			}
		});
		menuBar.add(signUp);


		panel = new Login();
		frame.getContentPane().add(panel);

		frame.setVisible(true);
	}

	/**
	 * Update frame. Switches out the panel and destroys the memory 
	 * associated with the previous panels.
	 *
	 * @param j: new panel to be switched in to the frame.
	 */
	//FACADE: Handles switching out the panel every time we change views.
	public static void updateFrame(JPanel j) {
		frame.getContentPane().removeAll();
		panel = j;
		frame.getContentPane().add(panel);
		frame.setVisible(true);
		frame.revalidate();
		frame.repaint();
		
		// make sure to stop the thread that is running the DM's.
		if(DMController.getInstance() != null) {
			Master.getInstance().stop = true;
		}
		if(DMController.getInstance() != null) {
			DMController.getInstance().setNumMessages(0);
		}
	}

	/**FACADE PATTERN: function encapsulates the loading of the logged
	/*
	 * Logged in menu load. Function instantiates the menu bar for the program
	 * after logging in, allowing the user to traverse to the correct views.
	 */
	public void loggedInMenuLoad() {
		//start the master frame with only the signup and login menu bars
		//available. When the user logs in, open up the other menu bar options.
		menuBar = new JMenuBar();
		
		this.frame.setJMenuBar(null);
		this.frame.setJMenuBar(menuBar);

		JMenu mnProfile = new JMenu("Profile");
		mnProfile.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnProfile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				menuBar.add(loadingLabel);
				menuBar.revalidate();
				menuBar.repaint();
				
				new SwingWorker<Void, Void>(){

					@Override
					protected Void doInBackground() throws Exception {
						updateFrame(new ProfileView());
						return null;
					}
					
					protected void done() {
						menuBar.remove(loadingLabel);
						
						menuBar.revalidate();
						menuBar.repaint();
					}
				}.execute();
			}
		});
		menuBar.add(mnProfile);

		JMenu settings = new JMenu("Settings");
		settings.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		settings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				menuBar.add(loadingLabel);
				menuBar.revalidate();
				menuBar.repaint();
				
				new SwingWorker<Void, Void>(){

					@Override
					protected Void doInBackground() throws Exception {
						updateFrame(new SettingsView());
						return null;
					}
					
					protected void done() {
						menuBar.remove(loadingLabel);
						
						menuBar.revalidate();
						menuBar.repaint();
					}
				}.execute();
			}
		});
		menuBar.add(settings);
		
		JMenu mnLists = new JMenu("Buddy Lists");
		mnLists.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnLists.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				menuBar.add(loadingLabel);
				menuBar.revalidate();
				menuBar.repaint();
				
				new SwingWorker<Void, Void>(){

					@Override
					protected Void doInBackground() throws Exception {
						updateFrame(new FriendsList());
						return null;
					}
					
					protected void done() {
						menuBar.remove(loadingLabel);
						
						menuBar.revalidate();
						menuBar.repaint();
					}
				}.execute();
			}
		});
		menuBar.add(mnLists);

		JMenu mnMatch = new JMenu("Match");
		mnMatch.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnMatch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				menuBar.add(loadingLabel);
				menuBar.revalidate();
				menuBar.repaint();
				
				new SwingWorker<Void, Void>(){

					@Override
					protected Void doInBackground() throws Exception {
						MatchController.generateMatches();
						if(!UserController.getUser().getIdle().isEmpty()) {
							MatchController.generateFrame();
						}
						else {
							MatchGUI.noMatches();
						}
						return null;
					}
					
					protected void done() {
						menuBar.remove(loadingLabel);
						
						menuBar.revalidate();
						menuBar.repaint();
					}
				}.execute();
			}
		});
		menuBar.add(mnMatch);
		
		if(UserController.getUser()!=null && (UserController.getUser().getEmail().equalsIgnoreCase("chasemanseth@gmail.com")||
				UserController.getUser().getEmail().equalsIgnoreCase("bob@rein.com")||
				UserController.getUser().getEmail().equalsIgnoreCase("zacharysteudel@gmail.com"))){
			JMenu administrator = new JMenu("Administrator"); 
			administrator.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			administrator.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					menuBar.add(loadingLabel);
					menuBar.revalidate();
					menuBar.repaint();
					
					new SwingWorker<Void, Void>(){
	
						@Override
						protected Void doInBackground() throws Exception {
							AdminController.generateFrame();
							return null;
						}
						
						protected void done() {
							menuBar.remove(loadingLabel);
							
							menuBar.revalidate();
							menuBar.repaint();
						}
					}.execute();
				}
			});
			menuBar.add(administrator);	
		}
		JMenu logout = new JMenu("Log Out");
		logout.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		logout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("Loading logout");
				UserController.setUser(null);
				UserController.getInstance().setTimesMatchCalled(5);
				UserController.getInstance().setChangesToMatches(true);

				Master.appLogger.info(":: User logged out.");
				loggedOutMenuLoad();
			}
		});
		menuBar.add(logout);
	}

	//FACADE PATTERN: encapsulates loading the logged out menu loading.
	/**
	 * Logged out menu load. Gets rid of the travel bay so the user can't access
	 * the UI when logged out.
	 */
	public void loggedOutMenuLoad() {
		menuBar = new JMenuBar();
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
	 * @param frame: the new frame
	 */
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	/**
	 * Adds the loading label to the menu bar.
	 */
	public void addLoading() {
		this.menuBar.add(loadingLabel);
		this.menuBar.revalidate();
		this.menuBar.repaint();
	}
	
	/**
	 * get rid of the loading label.
	 */
	public void unLoad() {
		this.menuBar.remove(loadingLabel);
		this.menuBar.revalidate();
		this.menuBar.repaint();
	}
}
