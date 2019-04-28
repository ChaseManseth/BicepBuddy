/*
 * @authors: Zachary Steudel, Hunter Long, Chase Manseth, Bob Rein, Reece Kemball-Cook
 */
package Messaging;

import javax.swing.Box;
import javax.swing.Box.Filler;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import User.User;
import User.UserController;
import bicepBuddyPackage.Master;
import mdlaf.utils.MaterialColors;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.Calendar;
import java.util.List;
import javax.swing.JLabel;

/**
 * The Class DMView.
 * @authors: Zachary Steudel, Hunter Long, Chase Manseth, Bob Rein, Reece Kemball-Cook
 */
public class DMView extends JPanel {
	
	/** The message sender. */
	private JTextField messageSender;
	
	/**
	 * Instantiates a new DM view.
	 *
	 * @param otherUser the other user
	 */
	public DMView(User otherUser) {
		DMController.getInstance(otherUser, this);
		
		this.setLayout(null);
		this.setBounds(100, 100, 900, 550);
		
		JTextArea messageField = new JTextArea();
		messageField.setEditable(false);
		messageField.setBounds(12, 13, 876, 441);
		
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
					Master.appLogger.info(":: Message Field triggered!");
					
					Message message = new Message(UserController.getUser().getId(), otherUser.getId(), 
							Calendar.getInstance().getTime(), messageSender.getText());
					controller.sendMessage(message);
					messageSender.setText("");
					
					controller.addMessage(messageField, message);
				}
				
			}
		});
		messageSender.setActionCommand("send");
		
		DMController.getInstance().populateMessages(messageField);
		
		JScrollPane messagePane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		messagePane.setBounds(12, 13, 876, 452);
		messagePane.setViewportView(messageField);
		messagePane.setVisible(true);
		add(messagePane);
		
		
				
		/*JTextField messageField = new JTextField("Enter message here...");
		messageField.setBounds(0, 356, 830, -356);
		messageField.setColumns(25);
		messageField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("send")) {
					DMController controller = DMController.getInstance();
					Master.appLogger.info(":: Message Field triggered!");
					
					Message message = new Message(UserController.getUser().getId(), otherUser.getId(), 
							Calendar.getInstance().getTime(), messageField.getText());
					controller.sendMessage(message);
					messageField.setText("");
				}
				
			}
		});
		messageField.setActionCommand("send");
		
		JButton sendButton = new JButton("Send");
		sendButton.setBounds(0, 149, 194, -149);
		sendButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("send")) {
					DMController controller = DMController.getInstance();
					Master.appLogger.info(":: Message Field triggered!");
					
					Message message = new Message(UserController.getUser().getId(), otherUser.getId(), 
							Calendar.getInstance().getTime(), messageField.getText());
					controller.sendMessage(message);
					messageField.setText("");
				}
				
			}
		});
		sendButton.setActionCommand("send");
		
		this.add(messageField);
		this.add(sendButton);*/
				
		//mainPanel.setBackground(Color.GRAY);
				
	}
}
