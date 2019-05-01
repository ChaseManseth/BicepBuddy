package admin;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.jfree.chart.ChartPanel;

import User.User;
import User.UserController;
import bicepBuddyPackage.Master;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;

/**
 * The Class AdminGui.
 * @authors: Zachary Steudel, Hunter Long, Chase Manseth, Bob Rein, Reece Kemball-Cook
 */

public class AdminGui extends JPanel {

	/** The table. */
	private JTable table;
	private ChartPanel chartPanel;

	/**
	 * Instantiates a new admin gui.
	 */
	public AdminGui() {
		 String options[] = {"Gender", "Weight","Preferred","Goals","Frequency", "Time","Style","Experience"};
		List<User> everybody =UserController.getInstance().getAllUsers();
		Integer userCount= everybody.size();
		setLayout(null);
		JLabel lblAdministrator = new JLabel("Administrator Dashboard");
		lblAdministrator.setFont(new Font("Dialog", Font.BOLD, 16));
		lblAdministrator.setBounds(313, 16, 299, 36);
		add(lblAdministrator);
		
        JLabel lblAllUsers = new JLabel("All " + userCount.toString() +" users");
        lblAllUsers.setBounds(640, 151, 106, 15);
        add(lblAllUsers);
        
		JLabel lblChoose = new JLabel("Choose:");
		lblChoose.setFont(new Font("Dialog", Font.BOLD, 14));
		lblChoose.setBounds(33, 120, 66, 15);
		add(lblChoose);

		JButton btnRefresh = new JButton("Refresh ");
		MaterialUIMovement.add(btnRefresh, MaterialColors.GRAY_600);
		btnRefresh.setBounds(752, 27, 114, 25);
		btnRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Master.getInstance().updateFrame(new AdminGui()); 
			}
		});
		add(btnRefresh);
		
		chartPanel = new ChartPanel(AdminController.getInstance().getChart(everybody,(String)("Gender")));
        chartPanel.setBounds(33, 151, 450, 350);
        add(chartPanel);

        JComboBox comboBox = new JComboBox(options);
        comboBox.setBounds(109, 115, 150, 25);
        comboBox.setSelectedItem("Gender");
        add(comboBox);
        comboBox.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
            	remove(chartPanel);
            	chartPanel = new ChartPanel(AdminController.getInstance().getChart(everybody,(String)(comboBox.getSelectedItem())));
                chartPanel.setBounds(33, 151, 450, 350);
                add(chartPanel);
                chartPanel.repaint();
            }
        });

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(500, 180, 366, 321);
		add(scrollPane);
		
		AdminTableModel model = new AdminTableModel(everybody);
		table = new JTable(model);
		table.setAutoCreateRowSorter(true);
		new ButtonColumn(table, 1);
		new ButtonColumn(table, 2);
		scrollPane.setViewportView(table);
	}
}
