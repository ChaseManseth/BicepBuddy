package Views;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.border.LineBorder;

import Matching.Match;
import Matching.Match.Status;
import Matching.MatchController;
import Messaging.DMView;
import User.User;
import User.UserController;
import bicepBuddyPackage.Master;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;
/**
 * @authors: Zachary Steudel, Hunter Long, Chase Manseth, Bob Rein, Reece Kemball-Cook
 */
public class OtherProfileView extends JPanel {

	//private JFrame frame;
	
	private JLabel lblAcceptingMatch;

	private JLabel rejLabel;

	/**
	 * Create the application.
	 *
	 * @param u the u
	 */
	public OtherProfileView(User u) {
		// user data
		String fname,lname,email,style,gender,preferedGender,frequency;
		String age=  u.getAge();
	    
		fname = u.getfName();
		lname = u.getlName();
		style = u.getStyle();
		//email = "redacted";
		gender = u.getGender();
		preferedGender = u.getPrefGender();
		frequency = u.getFrequency();
		Integer percent = 0;
		//*********************************************************************************************
		Match tempMatch = MatchController.findMatch(new Match(u,UserController.getUser()));
		if (tempMatch == null) {
			tempMatch = MatchController.directMatch(u);
		}
		percent = tempMatch.getStrength();
		//*********************************************************************************************
		// END USER DATA

		//frame = new JFrame();
		setBounds(0, 0, 900, 500);

		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		JLabel lblPic = new JLabel("");
		lblPic.setBounds(54, 40, 165, 184);
		try {
		    BufferedImage myPicture = ImageIO.read(new File(u.getProfilePic()));
		    Image scaled = myPicture.getScaledInstance(lblPic.getWidth(), lblPic.getHeight(),
		            Image.SCALE_SMOOTH);
			lblPic.setIcon(new ImageIcon(scaled));
        } catch (IOException e) {
    	    e.printStackTrace();
        }
	
		add(lblPic);
		
		JLabel lblName2 = new JLabel(u.getfName() + " " + u.getlName() + "'s Profile");
		lblName2.setFont(new Font("Dialog", Font.BOLD, 16));
		lblName2.setBounds(415, 26, 343, 19);
		add(lblName2);
		
		int ind = UserController.getUser().getAccepted().indexOf(new Match(u,UserController.getUser()));
		if(ind != -1 && UserController.getUser().getAccepted().get(ind).getStatus() == Status.Accepted) {
			JButton btnChat = new JButton("Chat with " + u.getfName());
			btnChat.setBackground(MaterialColors.GRAY_400);
			btnChat.setForeground(Color.black);
			btnChat.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Master.getInstance().addLoading();
					
					new SwingWorker<Void, Void>(){

						@Override
						protected Void doInBackground() throws Exception {
							Master.updateFrame(new DMView(u));
							Master.getInstance().stop = false;
							return null;
						}
						
						protected void done() {
							Master.getInstance().unLoad();
						}
					}.execute();
					
					//DMController.getInstance(u);
				}
			});
			btnChat.setBounds(277, 174, 263, 71);
			
			MaterialUIMovement.add(btnChat, MaterialColors.GRAY_600);
			btnChat.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					btnChat.setForeground(Color.white);
				}
				
				public void mouseExited(MouseEvent e) {
					btnChat.setForeground(Color.black);
				}
			});
			
			add(btnChat);
			
			JButton notifyBtn = new JButton("It's gym time " + u.getfName() + "!");
			notifyBtn.setBackground(MaterialColors.GRAY_400);
			notifyBtn.setForeground(Color.black);
			
			notifyBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					UserController.getInstance().notifyUser(u);
				}
				
			});
			
			MaterialUIMovement.add(notifyBtn, MaterialColors.GRAY_600);
			notifyBtn.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					notifyBtn.setForeground(Color.white);
				}
				
				public void mouseExited(MouseEvent e) {
					notifyBtn.setForeground(Color.black);
				}
			});
			
			notifyBtn.setBounds(605, 174, 263, 71);
			add(notifyBtn);
		}
		
		//*********************************************************************************************
		//If not accepted or rejected yet display
		else if(!UserController.getUser().getAccepted().contains(new Match(u,UserController.getUser())) && 
				!UserController.getUser().getRejected().contains(new Match(u,UserController.getUser()))) {
			JButton btnInvite = new JButton("Invite Buddy");
			btnInvite.setFont(new Font("Dialog", Font.BOLD, 14));
			btnInvite.setBackground(new Color(34, 139, 34));
			btnInvite.setForeground(Color.white);
			btnInvite.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
//					ViewController vc = new ViewController();
//					vc.inviteUserChange(u);
					lblAcceptingMatch.setVisible(true);
					lblAcceptingMatch.revalidate();
					lblAcceptingMatch.repaint();
					new SwingWorker<Void, Void>(){

						@Override
						protected Void doInBackground() throws Exception {
							//Accept invite or send invite
							if (UserController.getUser().getWaiting().contains(new Match(u,UserController.getUser()))) {
								MatchController.acceptMatchOther(MatchController.findMatch(new Match(u,UserController.getUser())));
							}
							else {
								MatchController.acceptMatchInitial(MatchController.directMatch(u));
							}
							UserController.getInstance().setChangesToMatches(true);
							return null;
						}

						protected void done() {
							lblAcceptingMatch.setVisible(false);
							lblAcceptingMatch.revalidate();
							lblAcceptingMatch.repaint();
						}
					}.execute();
					
					
				}
			});
			btnInvite.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					btnInvite.setBackground(new Color(26,86,8));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					btnInvite.setBackground(new Color(34, 139, 34));
				}
			});
			btnInvite.setBounds(331, 103, 188, 47);
			add(btnInvite);
			
			JButton btnBlock = new JButton("Block Buddy");
			btnBlock.setForeground(Color.white);
			btnBlock.setFont(new Font("Dialog", Font.BOLD, 14));
			btnBlock.setBackground(new Color(255, 0, 0));
			btnBlock.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
//					ViewController vc = new ViewController();
//					vc.blockBuddyChange(u);
					rejLabel.setVisible(true);
					rejLabel.revalidate();
					rejLabel.repaint();
					new SwingWorker<Void, Void>(){

						@Override
						protected Void doInBackground() throws Exception {
							//Accept invite or send invite
							if (UserController.getUser().getWaiting().contains(new Match(u,UserController.getUser()))) {
								MatchController.rejectMatch(MatchController.findMatch(new Match(u,UserController.getUser())));
							}
							else {
								MatchController.rejectMatch(MatchController.directMatch(u));
							}
							return null;
						}

						protected void done() {
							rejLabel.setVisible(false);
							rejLabel.revalidate();
							rejLabel.repaint();
						}
					}.execute();
					
				}
			});
			btnBlock.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					btnBlock.setBackground(new Color(204,8,8));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					btnBlock.setBackground(new Color(255, 0, 0));
				}
			});
			btnBlock.setBounds(599, 103, 188, 47);
			add(btnBlock);
		}
		//*********************************************************************************************
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		infoPanel.setBounds(34, 26, 208, 440);
		add(infoPanel);
		infoPanel.setLayout(null);
		
		JLabel usersName = new JLabel(u.getfName() + " " + u.getlName());
		usersName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		usersName.setBounds(10, 224, 165, 28);
		infoPanel.add(usersName);
		
		JLabel lblAge = new JLabel(age);
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAge.setBounds(48, 263, 127, 15);
		infoPanel.add(lblAge);
		
		JLabel lblAge_1 = new JLabel("Age");
		lblAge_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAge_1.setBounds(10, 263, 48, 14);
		infoPanel.add(lblAge_1);
		
		JLabel lblMatchPercent = new JLabel("Match Strength:");
		lblMatchPercent.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMatchPercent.setBounds(10, 289, 120, 14);
		infoPanel.add(lblMatchPercent);
		
		JLabel lblLblpercent = new JLabel(Double.toString(percent));
		lblLblpercent.setBounds(129, 289, 67, 14);
		infoPanel.add(lblLblpercent);
		
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(277, 258, 591, 208);
		add(panel);
		
		JLabel lblWorkoutStyle = new JLabel("Workout Style:");
		lblWorkoutStyle.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblWorkoutStyle.setBounds(12, 28, 128, 49);
		panel.add(lblWorkoutStyle);
		
		JLabel lblFrequency = new JLabel("Frequency:");
		lblFrequency.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFrequency.setBounds(12, 101, 95, 49);
		panel.add(lblFrequency);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGender.setBounds(267, 28, 78, 49);
		panel.add(lblGender);
		
		JLabel lblPreferedBuddyGender = new JLabel("Preferred Buddy Gender:");
		lblPreferedBuddyGender.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPreferedBuddyGender.setBounds(267, 101, 200, 49);
		panel.add(lblPreferedBuddyGender);
		
		JLabel lblNewLabel = new JLabel(style);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(139, 45, 109, 15);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(frequency);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(139, 118, 109, 15);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(gender);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(488, 45, 91, 15);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(preferedGender);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(488, 118, 91, 15);
		panel.add(lblNewLabel_3);
		
		rejLabel = new JLabel("Rejecting Match . . .");
		rejLabel.setForeground(new Color(255, 0, 0));
		rejLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		rejLabel.setBounds(633, 173, 235, 51);
		rejLabel.setVisible(false);
		add(rejLabel);
		
		lblAcceptingMatch = new JLabel("Accepting Match . . .");
		lblAcceptingMatch.setForeground(new Color(50, 205, 50));
		lblAcceptingMatch.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAcceptingMatch.setBounds(277, 173, 235, 51);
		lblAcceptingMatch.setVisible(false);
		add(lblAcceptingMatch);
		
	}
	
	public static void inviteBuddyFrame(User u) {
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setTitle("Invite");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 400, 200);
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		JTextField message = new JTextField();
		message.setText("You invited " + u.getfName() + " " + u.getlName() + " to be your buddy!");
		message.setEditable(false);
		
		panel.add(message);
		frame.getContentPane().add(panel);
	}
	
	public static void blockBuddyFrame(User u){
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setTitle("Block");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 400, 200);
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		JTextField message = new JTextField();
		message.setText("You blocked " + u.getfName() + " " + u.getlName());
		message.setEditable(false);
		
		panel.add(message);
		frame.getContentPane().add(panel);
	}
}
