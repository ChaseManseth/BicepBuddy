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

public class DMView extends JPanel {
	
	public DMView(User otherUser) {
		DMController.getInstance(otherUser, this);
		
		// Contents of JFrame
		JPanel mainPanel = new JPanel();
		mainPanel.setMaximumSize(new Dimension(getSize().width, Integer.MAX_VALUE));
		mainPanel.setPreferredSize(new Dimension(getSize().width, getSize().height));
		mainPanel.setLayout(new BorderLayout());
		
		// Panel to hold messages and scrollbar
		JPanel messagePanel = new JPanel();
		messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
		//messagePanel.setBackground(Color.GRAY);
		JScrollPane messageScroll = new JScrollPane(messagePanel);
		
		messageScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		messageScroll.getVerticalScrollBar().setUnitIncrement(16);
		
		mainPanel.add(messageScroll, BorderLayout.CENTER);
		
		// JTextField for entering messages
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		
		JTextField messageField = new JTextField("Enter message here...");
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
		
		bottomPanel.add(messageField);
		bottomPanel.add(sendButton);
		
		mainPanel.add(bottomPanel, BorderLayout.PAGE_END);
		
		//mainPanel.setBackground(Color.GRAY);
				
	}

}
