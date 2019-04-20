package bicepBuddyPackage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
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
import javax.swing.border.LineBorder;



import javax.swing.JMenu;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
public class SettingsView extends JPanel {

	private JTextField txtFirstName;
	private JTextField txtEmail;
	private JTextField txtLastName;



	/**
	 * Create the application.
	 */
	public SettingsView() {
		User u = UserController.getUser();
		setBounds(100, 100, 900, 560);
		setLayout(null);
		
		JLabel lblPic = new JLabel("");
		lblPic.setBounds(64, 62, 169, 175);
		/*try {
		    BufferedImage myPicture = ImageIO.read(new File("thedonald.jpeg"));
		    Image scaled = myPicture.getScaledInstance(lblPic.getWidth(), lblPic.getHeight(),
		            Image.SCALE_SMOOTH);
			lblPic.setIcon(new ImageIcon(scaled));
        } catch (IOException e) {
    	    e.printStackTrace();
        }*/
	
		add(lblPic);
		
		JLabel lblName2 = new JLabel("Account Settings");
		lblName2.setBounds(376, 12, 137, 15);
		add(lblName2);
		
		JButton btnNewButton = new JButton("Change Image");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				frame.setVisible(true);
				frame.setTitle("Change Image");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setBounds(100, 100, 400, 200);
				
				JPanel panel = new JPanel();
				panel.setLayout(new FlowLayout());
				
				JTextField message = new JTextField();
				message.setText("We need to build a file selector that only shows image files.");
				message.setEditable(false);
				
				panel.add(message);
				frame.getContentPane().add(panel);

			}
		});
		btnNewButton.setBounds(64, 254, 169, 25);
		add(btnNewButton);
		
		txtFirstName = new JTextField();
		txtFirstName.setBounds(64, 307, 169, 19);
		add(txtFirstName);
		txtFirstName.setText(u.getfName());
		txtFirstName.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(64, 405, 169, 19);
		add(txtEmail);
		txtEmail.setText(u.getEmail());
		txtEmail.setColumns(10);
		
		txtLastName = new JTextField();
		txtLastName.setText(u.getlName());
		txtLastName.setBounds(64, 356, 169, 19);
		add(txtLastName);
		txtLastName.setColumns(10);

		JComboBox comboBox = new JComboBox(MatchAlgorithm.STYLES);
		comboBox.setBounds(584, 62, 203, 32);
		comboBox.setSelectedItem(u.getStyle());
		add(comboBox);
		
		JLabel lblWorkoutStyle = new JLabel("Workout Style");
		lblWorkoutStyle.setBounds(406, 71, 107, 15);
		add(lblWorkoutStyle);
		
		JComboBox comboBox_1 = new JComboBox(MatchAlgorithm.TIMES);
		comboBox_1.setBounds(584, 141, 201, 32);
		comboBox_1.setSelectedItem(u.getTimeOfDay());
		add(comboBox_1);
		
		JLabel label = new JLabel("Preferred Time");
		label.setBounds(406, 150, 107, 15);
		add(label);
		
		JComboBox gender = new JComboBox(MatchAlgorithm.GENDERS);
		gender.setBounds(584, 213, 203, 32);
		gender.setSelectedItem(u.getGender());
		add(gender);
		
		JLabel label_1 = new JLabel("Gender");
		label_1.setBounds(406, 222, 107, 15);
		add(label_1);
		
		JComboBox comboBox_3 = new JComboBox(MatchAlgorithm.GENDERS);
		comboBox_3.setBounds(584, 277, 203, 32);
		comboBox_3.setSelectedItem(u.getPrefGender());
		add(comboBox_3);
		
		JLabel label_2 = new JLabel("Buddy Gender");
		label_2.setBounds(406, 286, 107, 15);
		add(label_2);
		
		JComboBox comboBox_4 = new JComboBox(MatchAlgorithm.FREQUENCIES);
		comboBox_4.setBounds(584, 349, 203, 32);
		comboBox_4.setSelectedItem(u.getFrequency());
		add(comboBox_4);
		
		JLabel label_3 = new JLabel("Frequency");
		label_3.setBounds(406, 358, 107, 15);
		add(label_3);
		
		JButton btnNewButton_1 = new JButton("Save changes");
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				frame.setVisible(true);
				frame.setTitle("Save changes");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setBounds(100, 100, 400, 200);
				
				JPanel panel = new JPanel();
				panel.setLayout(new FlowLayout());
				
				JTextField message = new JTextField();
				message.setText("All changes have been successfully changed.");
				message.setEditable(false);
				
				panel.add(message);
				frame.getContentPane().add(panel);
			}
		});
		btnNewButton_1.setBounds(584, 479, 203, 25);
		add(btnNewButton_1);
		
		JButton deleter = new JButton("Delete My Profile");
		deleter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame confirm = new JFrame();
				JPanel p = new JPanel();
				p.setLayout(new BorderLayout());
				confirm.add(p);
				
				JTextField uSure = new JTextField();
				uSure.setText("Are you sure you want to delete your account?");
				uSure.setEditable(false);
				
				JButton yesBtn = new JButton();
				yesBtn.setText("YES");
				yesBtn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						UserController uc = new UserController();
						uc.deleteAccount(UserController.getUser());
						confirm.dispose();
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						yesBtn.setBackground(Color.RED);
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						yesBtn.setBackground(new JButton().getBackground());
					}
				});
				
				JButton noBtn = new JButton();
				noBtn.setText("NO ");
				noBtn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						confirm.dispose();
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						noBtn.setBackground(Color.GREEN);
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						noBtn.setBackground(new JButton().getBackground());
					}
				});
				
				
				p.add(uSure, BorderLayout.PAGE_START);
				p.add(yesBtn, BorderLayout.LINE_START);
				p.add(noBtn, BorderLayout.LINE_END);
				
				confirm.setBounds(100, 100, 500, 200);
				confirm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				confirm.setVisible(true);
			}
		});
		deleter.setBounds(64, 479, 203, 25);
		add(deleter);
	}
}
