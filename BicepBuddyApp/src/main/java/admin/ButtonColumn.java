package admin;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
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

class ButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener{
	JTable table;
	JButton renderButton;
	JButton editButton;
	String text;
	
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
	
	public Component getTableCellRendererComponent(    JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
	    renderButton.setText( value.toString() );
	    return renderButton;
	}
	
	public Component getTableCellEditorComponent(
	    JTable table, Object value, boolean isSelected, int row, int column){
	    text = value.toString();
	    editButton.setText( text );
	    return editButton;
	}
	
	public Object getCellEditorValue(){
	    return text;
	}
	
	public void actionPerformed(ActionEvent e){
	    fireEditingStopped();    
	    if(e.getActionCommand().equalsIgnoreCase("view")) {
	        Master.getInstance().updateFrame(new OtherProfileView((User)(table.getValueAt(table.getSelectedRow(), 0))));
	    }
	    if(e.getActionCommand().equalsIgnoreCase("delete")) {
	        System.out.println( "I am deleting : " + table.getValueAt(table.getSelectedRow(), 0));//need to actually delete him
	        JFrame frame = new JFrame();
			frame.setVisible(true);
			frame.setTitle("Delete User?");
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setBounds(100, 100, 400, 200);
			
			JPanel panel = new JPanel();
			panel.setLayout(new FlowLayout());
			
			JTextField message = new JTextField();
			message.setText("Delete User: " + table.getValueAt(table.getSelectedRow(), 0)+"?");
			message.setFont(new Font("Tahoma", Font.PLAIN, 20));
			message.setEditable(false);
			JButton yes = new JButton("Yes");
			yes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Deletion was confirmed");
					//UserController.getInstance().deleteAccount((User)(table.getValueAt(table.getSelectedRow(), 0)));
					frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				}
			});
			panel.add(message);
			panel.add(yes);
			frame.getContentPane().add(panel);
	    }	    
	}
}

