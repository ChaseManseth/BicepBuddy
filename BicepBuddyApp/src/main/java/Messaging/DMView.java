/*
 * @authors: Zachary Steudel, Hunter Long, Chase Manseth, Bob Rein, Reece Kemball-Cook
 */
package Messaging;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.concurrent.Semaphore;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import User.User;
import User.UserController;
import bicepBuddyPackage.Master;
import mdlaf.utils.MaterialColors;

/**
 * The Class DMView.
 * @authors: Zachary Steudel, Hunter Long, Chase Manseth, Bob Rein, Reece Kemball-Cook
 */
public class DMView extends JPanel {
	
	/** The message sender. */
	private JTextField messageSender;
	
	/** The text update semaphore. */
	public Semaphore textUpdateSem = new Semaphore(1);
	
	/**
	 * Instantiates a new DM view with asynchronous messaging.
	 *
	 * @param otherUser the other user
	 */
	public DMView(User otherUser) {
		DMController.getInstance(otherUser, this);
		
		this.setLayout(null);
		this.setBounds(100, 100, 900, 550);
		
		JTextArea messageField = new JTextArea();
		messageField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		messageField.setEditable(false);
		messageField.setBounds(12, 13, 876, 441);
		messageField.setWrapStyleWord(true);
		messageField.setLineWrap(true);
		
		JLabel messageLbl = new JLabel("Enter message to " + otherUser.getfName() + ":");
		messageLbl.setBounds(12, 478, 226, 16);
		add(messageLbl);
		
		messageSender = new JTextField();
		messageSender.setBounds(283, 475, 605, 22);
		add(messageSender);
		messageSender.setColumns(10);
		messageSender.setBackground(MaterialColors.GRAY_400);
		
		messageSender.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("send")) {
					DMController controller = DMController.getInstance();
					Master.appLogger.info(":: Message " + messageSender.getText() + " to be sent.");
					
					Message message = new Message(UserController.getUser().getId(), otherUser.getId(), 
							Calendar.getInstance().getTime(), messageSender.getText());
					controller.sendMessage(message);
					messageSender.setText("");
					try {
						textUpdateSem.acquire();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					controller.addMessage(messageField, message);
					textUpdateSem.release();
				}
				
			}
		});
		messageSender.setActionCommand("send");
		
		JScrollPane messagePane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		messagePane.setBounds(12, 13, 876, 452);
		messagePane.setViewportView(messageField);
		messagePane.setVisible(true);
		add(messagePane);
		
		DMController.getInstance().populateMessages(messageField);
		
		//SWING WORKER THAT CONTINUALLY POPULATES MESSAGES
		new SwingWorker<Void, Void>(){
			@Override
			protected Void doInBackground() throws Exception {
				//Accept invite or send invite
				Master.getInstance().stop = false;
				//acquire permit
				while(Master.getInstance().stop == false) {
					textUpdateSem.acquire();
					DMController.getInstance().populateMessages(messageField);
					textUpdateSem.release();
					
					Thread.sleep(3000);
				}
				return null;
			}
		}.execute();
				
	}
}
