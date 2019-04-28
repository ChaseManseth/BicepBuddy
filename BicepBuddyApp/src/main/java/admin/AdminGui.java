package admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Matching.Match;
import User.User;
import User.UserController;
import User.UserDB;
import Views.OtherProfileView;
import Views.ProfileView;
import bicepBuddyPackage.Master;

import javax.swing.JLabel;
import javax.swing.JTable;
public class AdminGui extends JPanel {
	private JTable table_1;

	public AdminGui() {
		setLayout(null);		
		Integer userCount= 12, numActions=2;
		JLabel lblAdministrator = new JLabel("Administrator");
		lblAdministrator.setBounds(381, 24, 135, 15);
		add(lblAdministrator);
		
		JLabel lblTotalUsers = new JLabel("Total users:");
		lblTotalUsers.setBounds(33, 119, 90, 15);
		add(lblTotalUsers);
		
		JButton btnRefresh = new JButton("Refresh ");
		btnRefresh.setBounds(608, 114, 114, 25);
		add(btnRefresh);
		
		JLabel lblNewLabel = new JLabel(userCount.toString());
		lblNewLabel.setBounds(129, 119, 66, 15);
		add(lblNewLabel);
		
        JLabel lblAllUsers = new JLabel("All users");
        lblAllUsers.setBounds(643, 191, 66, 15);
        add(lblAllUsers);
        
        JLabel lblFancyChartOr = new JLabel("Fancy chart or something");
        lblFancyChartOr.setBounds(65, 341, 230, 15);
        add(lblFancyChartOr);
	          
		table_1 = new JTable(new AdminTableModel());
		table_1.setBounds(475, 218, 369, 323);
		add(table_1);

       
	}
}
