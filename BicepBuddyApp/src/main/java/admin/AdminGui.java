package admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import User.User;
import User.UserController;
import Views.OtherProfileView;
import bicepBuddyPackage.Master;
import javax.swing.JScrollPane;
public class AdminGui extends JPanel {
	private JTable table_1;
	private JTable table;

	public AdminGui() {
		// TO-DO: Make these loaded and not hard coded
		Integer userCount= 12, numActions=2;
		setLayout(null);
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
        lblFancyChartOr.setBounds(33, 340, 192, 15);
        add(lblFancyChartOr);
        
    	JLabel label = new JLabel("All users");
    	label.setBounds(313, 191, 66, 15);
		add(label);
		
	          
	//table wont scroll
		//table_1 = new JTable(new AdminTableModel(UserController.getInstance().getAllUsers()));
		//table_1.setBounds(475, 218, 356, 212);
		//add(table_1);
		
		
		//copied from reece i think

		JPanel panel = new JPanel();
		panel.setBounds(258, 218, 205, 212);
		add(panel);
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		
		JList list = new JList();
		list.setBounds(12, 12, 181, 162);
		list.setListData(UserController.getInstance().getAllUsers().toArray());
		list.setFont(new Font("Tahoma", Font.BOLD, 12));
		MouseListener mouseListener = new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		    	Master.getInstance().updateFrame(new OtherProfileView((User)list.getSelectedValue()));
		    }
		};
		list.addMouseListener(mouseListener);
		panel.add(list);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(522, 218, 344, 212);
		add(scrollPane);
		
		table = new JTable(new AdminTableModel(UserController.getInstance().getAllUsers()));
		scrollPane.setViewportView(table);
		
		
		



		

		
		
		
		
       
	}
}
