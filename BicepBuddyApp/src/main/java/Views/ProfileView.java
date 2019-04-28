package Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Matching.Match;
import Matching.Match.Status;
import Matching.MatchController;
import User.User;
import User.UserController;
import bicepBuddyPackage.Master;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;
/**
 * @authors: Zachary Steudel, Hunter Long, Chase Manseth, Bob Rein, Reece Kemball-Cook
 */
public class ProfileView extends JPanel {

	/**
	 * Instantiates a new profile view.
	 */
	public ProfileView() {

		// USER DATA
		String fname,lname,style,info,email, age;
		age = UserController.getUser().getAge();
		fname = UserController.getUser().getfName();
		lname = UserController.getUser().getlName();
		style = UserController.getUser().getStyle();
		email = UserController.getUser().getEmail();
		// END USER DATA

		//frame = new JFrame();
		setBounds(0, 0, 900, 500);

		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		JLabel lblPic = new JLabel("");
		lblPic.setBounds(54, 40, 165, 184);
		/*try {
		    BufferedImage myPicture = ImageIO.read(new File("thedonald.jpeg"));
		    Image scaled = myPicture.getScaledInstance(lblPic.getWidth(), lblPic.getHeight(),
		            Image.SCALE_SMOOTH);
			lblPic.setIcon(new ImageIcon(scaled));
        } catch (IOException e) {
    	    e.printStackTrace();
        }*/

		add(lblPic);

		JLabel lblName2 = new JLabel("Your Profile");
		lblName2.setBounds(96, 11, 135, 15);
		add(lblName2);

		JButton editProfileBtn = new JButton("Edit Profile");
		editProfileBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		editProfileBtn.setBackground(MaterialColors.GRAY_400);
		editProfileBtn.setForeground(Color.black);

		MaterialUIMovement.add(editProfileBtn, MaterialColors.GRAY_600);

		editProfileBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ViewController vc = new ViewController();
				vc.settingsView();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				editProfileBtn.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				editProfileBtn.setForeground(Color.black);
			}
		});

		editProfileBtn.setBounds(54, 420, 165, 25);
		add(editProfileBtn);

		JButton btnStartMatching = new JButton("Start Matching");
		btnStartMatching.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnStartMatching.setBackground(MaterialColors.GRAY_400);
		btnStartMatching.setForeground(Color.black);

		MaterialUIMovement.add(btnStartMatching, MaterialColors.GRAY_600);

		btnStartMatching.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MatchController.generateFrame();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnStartMatching.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnStartMatching.setForeground(Color.black);
			}
		});
		btnStartMatching.setBounds(459, 40, 188, 47);
		add(btnStartMatching);
		
		//*************************************************************************************************************************
		//In user accepted and other accepted
		UserController.getInstance().populateUserMatchesArray();
		
		JLabel lblFriends = new JLabel("Buddies");
		lblFriends.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFriends.setBounds(269, 183, 150, 25);
		add(lblFriends);
		
		JPanel friendsPanel = new JPanel();
		friendsPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		friendsPanel.setBounds(269, 219, 150, 281);
		
		JList friendsList = new JList();
		friendsList.setListData(UserController.getInstance().getUser().getAcceptedUsers().toArray());
		friendsList.setFont(new Font("Tahoma", Font.BOLD, 12));
		MouseListener mouseListener = new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		    	Master.getInstance().updateFrame(new OtherProfileView((User)friendsList.getSelectedValue()));
		    }
		};
		friendsList.addMouseListener(mouseListener);
		friendsPanel.add(friendsList);
		add(friendsPanel);
		
		//*************************************************************************************************************************
		JLabel lblIncomingBuddies = new JLabel("Incoming Buddies");
		lblIncomingBuddies.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIncomingBuddies.setBounds(589, 188, 150, 14);
		add(lblIncomingBuddies);
		
		JPanel incomingPanel = new JPanel();
		incomingPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		incomingPanel.setBounds(589, 219, 150, 281);
		
		JList incomingList = new JList();
		incomingList.setListData(UserController.getInstance().getUser().getWaitingUsers().toArray());
		incomingList.setFont(new Font("Tahoma", Font.BOLD, 12));
		MouseListener mouseListener1 = new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		    	Master.getInstance().updateFrame(new OtherProfileView((User)incomingList.getSelectedValue()));
		    }
		};
		incomingList.addMouseListener(mouseListener1);
		incomingPanel.add(incomingList);
		add(incomingPanel);
		
		//**********************************************************************************************************************************
		JLabel lblPendingBuddies = new JLabel("Pending Buddies");
		lblPendingBuddies.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPendingBuddies.setBounds(429, 189, 150, 14);
		add(lblPendingBuddies);
		
		JPanel pendingPanel = new JPanel();
		pendingPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		pendingPanel.setBounds(429, 219, 150, 281);
		
		JList pendingList = new JList();
		pendingList.setListData(UserController.getInstance().getUser().getPendingUsers().toArray());
		pendingList.setFont(new Font("Tahoma", Font.BOLD, 12));
		MouseListener mouseListener2 = new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		    	Master.getInstance().updateFrame(new OtherProfileView((User)pendingList.getSelectedValue()));
		    }
		};
		pendingList.addMouseListener(mouseListener2);
		pendingPanel.add(pendingList);
		add(pendingPanel);

		JPanel infoPanel = new JPanel();
		infoPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		infoPanel.setBounds(34, 26, 208, 440);
		add(infoPanel);
		infoPanel.setLayout(null);

		JLabel lblEmail = new JLabel(email);
		lblEmail.setBounds(10, 264, 186, 15);
		infoPanel.add(lblEmail);

		JLabel lblFirstname = new JLabel(fname);
		lblFirstname.setBounds(10, 224, 186, 28);
		infoPanel.add(lblFirstname);

		JLabel lblAge = new JLabel(age);
		lblAge.setBounds(10, 300, 186, 15);
		infoPanel.add(lblAge);
		
	}
}
