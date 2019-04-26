package Messaging;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.swing.JTextField;

import User.User;
import bicepBuddyPackage.Master;
import User.UserController;

public class DMController implements ActionListener, WindowListener, ComponentListener {

	private DMView dmView = null;
	private DM dm = null;
	
	// DMController singleton
	private static DMController controller = null;
	
	// Various constants
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss::MM:dd::yyyy");
	private static final String MESSAGE_FILE = "messageDB.csv";
	
	public DMController(User partner) {
		dm = new DM();
		dm.setPartner(partner);
		dmView = new DMView(this, this);
		dmView.setTitle("Chatting with -- " + partner.getfName() + " " + partner.getlName());
		updateMessages();
		dmView.setVisible(true);
	}
	
	/*
	 * Updates the DMView
	 */
	public void updateView() {
		dmView.update(dm.getSorted());
		dmView.setVisible(true);
	}
	
	/*
	 * Reads all Messages from csv file that were sent by the logged in user and
	 * received by the specified User
	 */
	public static Set<Message> loadMessages(User partner) {
		Set<Message> messages = new HashSet<>();
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(MESSAGE_FILE));
			
			String line = new String();
			while((line = reader.readLine()) != null) {
				String[] split = line.split(",");
				
				if(split.length == 4) {
					User sender = null, receiver = null;
					Date sendDate = null;
					Message message = null;
					UserController uc = UserController.getInstance();
					
					sender = uc.getUserById(split[0]);
					receiver = uc.getUserById(split[1]);
					
					// Filter out messages not applicable
					if((sender.getId().equals(UserController.getUser().getId()) && receiver.getId().equals(partner.getId()))
							|| (sender.getId().equals(partner.getId()) && receiver.getId().equals(UserController.getUser().getId()))) {
					
						try {
							sendDate = DATE_FORMAT.parse(split[2]);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							Master.appLogger.log(Level.WARNING, e.getMessage());
							sendDate = new Date();
						}
						String text = split[3];
						message = new Message(sender, receiver, sendDate, text);
						messages.add(message);
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Master.appLogger.log(Level.WARNING, e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Master.appLogger.log(Level.WARNING, e.getMessage());
		} finally {
			if(reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Master.appLogger.log(Level.WARNING, e.getMessage());
				}
			}
		}
		
		return messages;
	}
	
	/*
	 * Updates all messages and the DMView
	 */
	public void updateMessages() {
		Master.appLogger.info(":: Updating messages...");
		this.dm.setMessages(loadMessages(dm.getPartner()));
		updateView();
	}
	
	/*
	 * Creates message to send, adds it to DM instance, updates the DMView accordingly
	 */
	public void sendMessage(String text) {
		Message message = new Message(UserController.getUser(), dm.getPartner(), Calendar.getInstance().getTime(), text);
		dm.add(message);
		updateView();
		saveMessage(message);
	}
	
	/*
	 * Writes all Messages from DM instance to csv file
	 */
	public static void saveMessage(Message message) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(MESSAGE_FILE, true));
			
			String line = message.getSender().getId()
					+ "," + message.getReceiver().getId()
					+ "," + DATE_FORMAT.format(message.getSendDate())
					+ "," + message.getText().replaceAll("[^\\p{L}\\p{Z}]","");
			
			writer.write(line);
			writer.newLine();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Master.appLogger.log(Level.WARNING, e.getMessage());
		} finally {
			if(writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Master.appLogger.log(Level.WARNING, e.getMessage());
				}
			}
		}
	}
	
	/*
	 * Creates instance if it does not exist or the partner changes
	 */
	public static DMController getInstance(User partner) {
		// If not made, make one; if different User, refresh
		if(controller == null) {
			controller = new DMController(partner);
		}
		else if(!controller.dm.getPartner().getId().equals(partner.getId())) {
			controller = new DMController(partner);
		}
		
		return controller;
	}
	
	/*
	 * Should only be used to get an already existing DMController instance
	 */
	public static DMController getInstance() {
		return controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("send")) {
			DMController controller = DMController.getInstance();
			Master.appLogger.info(":: Message Field triggered!");
			
			controller.sendMessage(controller.dmView.messageField.getText());
			controller.dmView.messageField.setText("");
		}
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void componentMoved(ComponentEvent e) {
		int x = e.getComponent().getLocation().x + e.getComponent().getSize().width;
		int y = e.getComponent().getLocation().y;
		dmView.setLocation(x, y);
		
	}
	

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
