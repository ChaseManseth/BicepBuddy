package admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import Matching.Match;
import Matching.MatchController;
import User.User;
import User.UserController;
import Views.OtherProfileView;
import Views.ViewController;
import bicepBuddyPackage.Master;

// TODO: Auto-generated Javadoc
/**
 * The Class ButtonColumn.
 * @authors: Zachary Steudel, Hunter Long, Chase Manseth, Bob Rein, Reece Kemball-Cook
 */

/*
 * NOTE: PORTIONS OF THIS CODE WERE TAKEN AND ADAPTED FROM  Rob Camick AND HIS WEB PAGE http://www.camick.com/java/source/ButtonColumn.java 
 */
@SuppressWarnings("serial")
class ButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener{
	
	/** The table. */
	JTable table;
	
	/** The render button. */
	JButton renderButton;
	
	/** The edit button. */
	JButton editButton; 
	
	/** The text. */
	String text;
	
	/**
	 * Instantiates a new button column.
	 *
	 * @param table the table
	 * @param column the column
	 */
	public ButtonColumn(JTable table, int column){
	    super();
	    this.table = table;
	    renderButton = new JButton();
	
	    editButton = new JButton();
	    editButton.setFocusPainted( false );
	    editButton.addActionListener( this );
	
	    TableColumnModel columnModel = table.getColumnModel();
	    columnModel.getColumn(column).setCellRenderer( this );
	    columnModel.getColumn(column).setCellEditor( this );
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
	 */
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
	    renderButton.setText( value.toString() );
	    return renderButton;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableCellEditor#getTableCellEditorComponent(javax.swing.JTable, java.lang.Object, boolean, int, int)
	 */
	public Component getTableCellEditorComponent(
	    JTable table, Object value, boolean isSelected, int row, int column){
	    text = value.toString();
	    editButton.setText( text );
	    return editButton;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.CellEditor#getCellEditorValue()
	 */
	public Object getCellEditorValue(){
	    return text;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e){
	    fireEditingStopped();    
	    if(e.getActionCommand().equalsIgnoreCase("view")) {
	        Master.getInstance().updateFrame(new OtherProfileView((User)(table.getValueAt(table.getSelectedRow(), 0))));
	    }
	    if(e.getActionCommand().equalsIgnoreCase("delete")) {
	    	
					JFrame confirm = new JFrame();
					JPanel p = new JPanel();
					p.setLayout(new BorderLayout());
					confirm.getContentPane().add(p);
					
					JTextField uSure = new JTextField();
					uSure.setText("Delete: " + ((User)(table.getValueAt(table.getSelectedRow(), 0))) +"'s acount?");
					uSure.setEditable(false);
					
					JButton yesBtn = new JButton();
					yesBtn.setText("YES");
					yesBtn.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							UserController.getInstance().deleteUser((User)(table.getValueAt(table.getSelectedRow(), 0)));
							((AdminTableModel)table.getModel()).removeRow(table.convertRowIndexToModel(table.getSelectedRow()));
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
	}
}

