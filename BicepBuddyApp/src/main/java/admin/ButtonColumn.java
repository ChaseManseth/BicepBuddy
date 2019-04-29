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
							UserController uc = new UserController();
							//uc.deleteAccount(UserController.getUser());
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

