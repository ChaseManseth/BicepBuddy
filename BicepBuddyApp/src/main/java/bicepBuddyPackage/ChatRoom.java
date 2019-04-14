package bicepBuddyPackage;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatRoom extends JFrame implements ComponentListener {
	
	private JSplitPane split;
	private JScrollPane leftScroll;
	private JScrollPane rightScroll;
	private JPanel leftPanel;
	private JPanel rightPanel;
	// private List<Jabel> matchIcons;
	private JTextField messageField;
	
	public ChatRoom() {
		setSize(new Dimension(450, 600));
		setTitle("ChatRoom");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.setMaximumSize(new Dimension(60, Integer.MAX_VALUE));
		leftPanel.setPreferredSize(new Dimension(60, getSize().height));
		leftPanel.setBackground(Color.DARK_GRAY);
		
		for(int i = 1; i <= 21; i++) {
			ImageIcon ii;
			if(i % 3 == 0) {
				ii = new ImageIcon(getClass().getClassLoader().getResource("jediOrder.png"));
			} else if(i % 2 == 0) {
				ii = new ImageIcon(getClass().getClassLoader().getResource("rebel.png"));
			} else {
				ii = new ImageIcon(getClass().getClassLoader().getResource("avengers.png"));
			}
			JLabel label = new JLabel(ii);
			label.setAlignmentX(Component.LEFT_ALIGNMENT);
			leftPanel.add(label);
			leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		}
		
		rightPanel = new JPanel();
		rightPanel.setMaximumSize(new Dimension(getSize().width - 50, Integer.MAX_VALUE));
		rightPanel.setPreferredSize(new Dimension(getSize().width - 50, getSize().height));
		rightPanel.setLayout(new BorderLayout());
		
		messageField = new JTextField("Enter a message...");
		messageField.setColumns(25);
		rightPanel.add(messageField, BorderLayout.PAGE_END);
		
		rightPanel.setBackground(Color.GRAY);
		
		leftScroll = new JScrollPane(leftPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		JScrollBar scrollBar = new JScrollBar(JScrollBar.VERTICAL) {
			@Override
			public boolean isVisible() {
				return true;
			}
		};
		
		leftScroll.setVerticalScrollBar(scrollBar);
		leftScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		
		getContentPane().add(leftScroll);
		getContentPane().add(rightPanel);

	}
	
	class Conversation extends JPanel {
		
		List<Message> messages;
		
		public Conversation() {
			messages = new ArrayList<>();
			
			// Populate messages
			
		}
		
	}
	
	class Message {
		private String text;
		private User sender;
		private User receiver;
		private Date sendDate;
		
		
		public String getText() {
			return text;
		}
		
		public User getSender() {
			return sender;
		}
		
		public User getReceiver() {
			return receiver;
		}
		
		public Date getSendDate() {
			return sendDate;
		}
		
		public void setText(String text) {
			this.text = text;
		}
		
		public void setSender(User sender) {
			this.sender = sender;
		}
		
		public void setReceiver(User receiver) {
			this.receiver = receiver;
		}
		
		public void setSendDate(Date sendDate) {
			this.sendDate = sendDate;
		}
	}

	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		int x = e.getComponent().getLocation().x + e.getComponent().getSize().width;
		int y = e.getComponent().getLocation().y;
		setLocation(x, y);
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

}
