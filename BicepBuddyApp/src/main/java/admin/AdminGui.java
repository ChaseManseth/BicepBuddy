package admin;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import User.User;
import User.UserController;
import Views.OtherProfileView;
import Views.SettingsView;
import bicepBuddyPackage.Master;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;
import java.util.List;
import javax.swing.JScrollPane;
// TODO: Auto-generated Javadoc

/**
 * The Class AdminGui.
 * @authors: Zachary Steudel, Hunter Long, Chase Manseth, Bob Rein, Reece Kemball-Cook
 */
public class AdminGui extends JPanel {



	/** The table. */
	private JTable table;

	/**
	 * Instantiates a new admin gui.
	 */
	public AdminGui() {
		List<User> everybody =UserController.getInstance().getAllUsers();
		Integer userCount= everybody.size();
		setLayout(null);
		JLabel lblAdministrator = new JLabel("Administrator Dashboard");
		lblAdministrator.setFont(new Font("Dialog", Font.BOLD, 16));
		lblAdministrator.setBounds(290, 12, 299, 36);
		add(lblAdministrator);

		JLabel lblTotalUsers = new JLabel("Total users:");
		lblTotalUsers.setBounds(33, 72, 90, 15);
		add(lblTotalUsers); 

		JButton btnRefresh = new JButton("Refresh ");
		MaterialUIMovement.add(btnRefresh, MaterialColors.GRAY_600);
		btnRefresh.setBounds(752, 23, 114, 25);
		btnRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Master.getInstance().updateFrame(new AdminGui()); 
			}
		});
		add(btnRefresh);

		JLabel lblNewLabel = new JLabel(userCount.toString());
		lblNewLabel.setBounds(122, 72, 66, 15);
		add(lblNewLabel);

        JLabel lblAllUsers = new JLabel("All users");
        lblAllUsers.setBounds(597, 102, 66, 15);
        add(lblAllUsers);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(381, 129, 485, 343);
		add(scrollPane);
		
		AdminTableModel model = new AdminTableModel(everybody);
		table = new JTable(model);
		table.setAutoCreateRowSorter(true);
		ButtonColumn buttonColumn1 = new ButtonColumn(table, 1);
		ButtonColumn buttonColumn2 = new ButtonColumn(table, 2);
		scrollPane.setViewportView(table);
		
        ChartPanel myPanel= new ChartPanel(AdminController.getInstance().getChart());
        myPanel.setBounds(33, 122, 326, 350);
        add(myPanel);
       

	}

}
