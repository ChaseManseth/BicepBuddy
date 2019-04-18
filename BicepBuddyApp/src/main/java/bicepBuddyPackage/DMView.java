package bicepBuddyPackage;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.List;

public class DMView extends JFrame implements ComponentListener {
	
	private JScrollPane leftScroll;
	private JScrollPane messageScroll;
	private JPanel profileBar;
	private JPanel rightPanel;
	private JPanel messagePanel;
	// private List<Jabel> matchIcons;
	private JTextField messageField;
	private static DMView view = null;
	
	public DMView() {
		setSize(new Dimension(450, 600));
		setTitle("Direct Messaging");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		profileBar = new JPanel();
		profileBar.setLayout(new BoxLayout(profileBar, BoxLayout.Y_AXIS));
		profileBar.setMaximumSize(new Dimension(60, Integer.MAX_VALUE));
		profileBar.setPreferredSize(new Dimension(60, getSize().height));
		profileBar.setBackground(Color.DARK_GRAY);
		
		
		// Placeholder for generating user profile pictures
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
			profileBar.add(label);
			profileBar.add(Box.createRigidArea(new Dimension(0, 10)));
		}
		
		rightPanel = new JPanel();
		rightPanel.setMaximumSize(new Dimension(getSize().width - 50, Integer.MAX_VALUE));
		rightPanel.setPreferredSize(new Dimension(getSize().width - 50, getSize().height));
		rightPanel.setLayout(new BorderLayout());
		
		messagePanel = new JPanel();
		messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
		messagePanel.setBackground(Color.CYAN);
		messageScroll = new JScrollPane(messagePanel);
		messageScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		messageScroll.getVerticalScrollBar().setUnitIncrement(16);
		
		rightPanel.add(messageScroll, BorderLayout.CENTER);
		
		messageField = new JTextField("Enter a message...");
		messageField.setColumns(25);
		rightPanel.add(messageField, BorderLayout.PAGE_END);
		
		rightPanel.setBackground(Color.GRAY);
		
		leftScroll = new JScrollPane(profileBar, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		JScrollBar scrollBar = new JScrollBar(JScrollBar.VERTICAL) {
			@Override
			public boolean isVisible() {
				return true;
			}
		};
		
		scrollBar.setUnitIncrement(16);
		leftScroll.setVerticalScrollBar(scrollBar);
		leftScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		
		getContentPane().add(leftScroll);
		getContentPane().add(rightPanel);
		
		view = this;
		
		DM dm = new DM();
		updateMessages(dm.getSorted());

	}
	
	public static DMView getInstance() {
		if(view == null) {
			view = new DMView();
		}
		return view;
	}
	
	public void updateMessages(List<Message> messages) {
		Master.appLogger.info(":: Updating all messages in view");
		messagePanel.removeAll();
		for(Message message : messages) {
			JPanel p = new JPanel();
			JTextArea mArea = new JTextArea(message.getText());
			mArea.setLineWrap(true);
			mArea.setWrapStyleWord(true);
			mArea.setEditable(false);
			mArea.setColumns(30);
			if(message.getSender() != null && message.getSender().equals(UserController.getUser())) {
				p.setLayout(new FlowLayout(FlowLayout.TRAILING));
				
				p.add(Box.createRigidArea(new Dimension(35, 0)));
				p.add(mArea);
			} else {
				p.setLayout(new FlowLayout(FlowLayout.LEADING));
				p.add(mArea);
				p.add(Box.createRigidArea(new Dimension(35, 0)));
			}
			
			
			messagePanel.add(p);
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
