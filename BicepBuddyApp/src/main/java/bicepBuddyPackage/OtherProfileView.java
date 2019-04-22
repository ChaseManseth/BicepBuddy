package bicepBuddyPackage;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class OtherProfileView extends JPanel {

	//private JFrame frame;

	/**
	 * Create the application.
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
		// END USER DATA

		//frame = new JFrame();
		setBounds(0, 0, 900, 500);

		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		JLabel lblPic = new JLabel("");
		lblPic.setBounds(54, 40, 165, 184);
		/*try {
		    BufferedImage myPicture = ImageIO.read(new File("hillary.jpg"));
		    Image scaled = myPicture.getScaledInstance(lblPic.getWidth(), lblPic.getHeight(),
		            Image.SCALE_SMOOTH);
			lblPic.setIcon(new ImageIcon(scaled));
        } catch (IOException e) {
    	    e.printStackTrace();
        }*/
	
		add(lblPic);
		
		JLabel lblName2 = new JLabel(u.getfName() + " " + u.getlName() + "'s Profile");
		lblName2.setFont(new Font("Dialog", Font.BOLD, 16));
		lblName2.setBounds(480, 26, 235, 19);
		add(lblName2);
		
		JButton btnInvite = new JButton("Invite Buddy");
		btnInvite.setFont(new Font("Dialog", Font.BOLD, 14));
		btnInvite.setBackground(new Color(34, 139, 34));
		btnInvite.setForeground(Color.white);
		btnInvite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewController vc = new ViewController();
				vc.inviteUserChange(u);
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
		lblAge.setBounds(10, 264, 165, 15);
		infoPanel.add(lblAge);
		
		JButton button = new JButton("Block Buddy");
		button.setForeground(Color.white);
		button.setFont(new Font("Dialog", Font.BOLD, 14));
		button.setBackground(new Color(255, 0, 0));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewController vc = new ViewController();
				vc.blockBuddyChange(u);
			}
		});
		
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				button.setBackground(new Color(204,8,8));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				button.setBackground(new Color(255, 0, 0));
			}
		});
	
		button.setBounds(599, 103, 188, 47);
		add(button);
		
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
		lblGender.setBounds(301, 28, 78, 49);
		panel.add(lblGender);
		
		JLabel lblPreferedBuddyGender = new JLabel("Preferred Buddy Gender:");
		lblPreferedBuddyGender.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPreferedBuddyGender.setBounds(301, 101, 200, 49);
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
		lblNewLabel_2.setBounds(513, 45, 66, 15);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(preferedGender);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(513, 118, 66, 15);
		panel.add(lblNewLabel_3);
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
