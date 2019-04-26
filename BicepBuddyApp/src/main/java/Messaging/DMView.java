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

import User.UserController;
import bicepBuddyPackage.Master;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.List;

public class DMView extends JFrame {
	
	private JScrollPane messageScroll;
	private JPanel mainPanel;
	private JPanel messagePanel;
	// private List<Jabel> matchIcons;
	private JPanel bottomPanel;
	protected JTextField messageField;
	private JButton sendButton;
	
	public DMView(ActionListener actionListener, WindowListener windowListener) {
		// JFrame setup
		setSize(new Dimension(450, 600));
		setTitle("Direct Messaging");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		addWindowListener(windowListener);
		
		// Contents of JFrame
		mainPanel = new JPanel();
		mainPanel.setMaximumSize(new Dimension(getSize().width, Integer.MAX_VALUE));
		mainPanel.setPreferredSize(new Dimension(getSize().width, getSize().height));
		mainPanel.setLayout(new BorderLayout());
		
		// Panel to hold messages and scrollbar
		messagePanel = new JPanel();
		messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
		//messagePanel.setBackground(Color.GRAY);
		messageScroll = new JScrollPane(messagePanel);
		messageScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		messageScroll.getVerticalScrollBar().setUnitIncrement(16);
		
		mainPanel.add(messageScroll, BorderLayout.CENTER);
		
		// JTextField for entering messages
		bottomPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		
		messageField = new JTextField("Enter a message...");
		messageField.setColumns(25);
		messageField.addActionListener(actionListener);
		messageField.setActionCommand("send");
		
		sendButton = new JButton("Send");
		sendButton.addActionListener(actionListener);
		sendButton.setActionCommand("send");
		
		bottomPanel.add(messageField);
		bottomPanel.add(sendButton);
		
		mainPanel.add(bottomPanel, BorderLayout.PAGE_END);
		
		//mainPanel.setBackground(Color.GRAY);
		
		getContentPane().add(mainPanel);
		
	}
	
	public void update(List<Message> messages) {
		Master.appLogger.info(":: Updating all messages in view");
		messagePanel.removeAll();
		messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
		
		/*
		 * Filler glue = (Filler) Box.createVerticalGlue();
		 * glue.changeShape(glue.minimumSize(), getSize(), messagePanel.getSize());
		 * messagePanel.add(glue);
		 */
		
		for(int i = 0; i < messages.size(); i++) {
			JPanel p = new JPanel();
			JTextArea mArea = new JTextArea(messages.get(i).getText());
			
			mArea.setLineWrap(true);
			mArea.setWrapStyleWord(true);
			mArea.setEditable(false);
			mArea.setColumns(30);
			
			// Message alignment
			if(messages.get(i).getSender() != null && messages.get(i).getSender().equals(UserController.getUser())) {
				p.setLayout(new FlowLayout(FlowLayout.TRAILING));
				//p.add(Box.createRigidArea(new Dimension(35, 0)));
				mArea.setBackground(Color.GREEN);
				p.add(mArea);
			} else {
				p.setLayout(new FlowLayout(FlowLayout.LEADING));
				mArea.setBackground(Color.GRAY);
				p.add(mArea);
				//p.add(Box.createRigidArea(new Dimension(35, 0)));
			}
			
			//p.setMaximumSize(mArea.getSize());
			
			messagePanel.add(p);
			messagePanel.add(Box.createRigidArea(new Dimension(0, 10)));
		}
		
	}

}
