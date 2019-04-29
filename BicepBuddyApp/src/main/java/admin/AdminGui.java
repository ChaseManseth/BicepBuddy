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
// TODO: Auto-generated Javadoc

/**
 * The Class AdminGui.
 * @authors: Zachary Steudel, Hunter Long, Chase Manseth, Bob Rein, Reece Kemball-Cook
 */
public class AdminGui extends JPanel {

	/** The table 1. */
	private JTable table_1;

	/** The table. */
	private JTable table;

	/**
	 * Instantiates a new admin gui.
	 */
	public AdminGui() {
		Integer userCount= UserController.getInstance().getAllUsers().size();
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
        lblAllUsers.setBounds(591, 191, 66, 15);
        add(lblAllUsers);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(381, 218, 485, 212);
		add(scrollPane);
		AdminTableModel model = new AdminTableModel(UserController.getInstance().getAllUsers());
		table = new JTable(model);
		table.setAutoCreateRowSorter(true);
		ButtonColumn buttonColumn1 = new ButtonColumn(table, 1);
		ButtonColumn buttonColumn2 = new ButtonColumn(table, 2);
		scrollPane.setViewportView(table);

	}
}
