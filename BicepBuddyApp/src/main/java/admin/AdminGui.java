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
import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;

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
		Integer userCount= UserController.getInstance().getAllUsers().size();
		setLayout(null);
		JLabel lblAdministrator = new JLabel("Administrator");
		lblAdministrator.setFont(new Font("Dialog", Font.BOLD, 30));
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
				//update table here  
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
		AdminTableModel model = new AdminTableModel(UserController.getInstance().getAllUsers());
		table = new JTable(model);
		table.setAutoCreateRowSorter(true);
		ButtonColumn buttonColumn1 = new ButtonColumn(table, 1);
		ButtonColumn buttonColumn2 = new ButtonColumn(table, 2);
		scrollPane.setViewportView(table);
		

        ChartPanel myPanel= new ChartPanel(getChart());
        myPanel.setBounds(33, 122, 326, 350);
        add(myPanel);
       

	}
    private static JFreeChart getChart() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("One", new Double(43.2));
        dataset.setValue("Two", new Double(10.0));
        dataset.setValue("Three", new Double(27.5));
        dataset.setValue("Four", new Double(17.5));
        dataset.setValue("Five", new Double(11.0));
        dataset.setValue("Six", new Double(19.4));
        return ChartFactory.createPieChart("Test1",dataset,false,true,false);
    }
}
