package admin;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import User.User;


public class AdminTableModel extends AbstractTableModel {

    private String[] columnNames = {"Email","age","style"};
  //TO-DO: make columns email, button, button
    private ArrayList<User> users = new ArrayList<User>();

     
     public AdminTableModel (List<User> myList) {
//TO-DO: Get info from db not hard coding
    	 users= (ArrayList<User>) myList;
//    	    User a = new User();
//    	    a.setEmail("test@bob.com");
//    	    a.setAge("25");
//    	    a.setStyle("power");
//    	    users.add(a);
//    	    User b = new User();
//    	    b.setEmail("blah@bob.com");
//    	    b.setAge("15");
//    	    b.setStyle("cardio");
//    	    users.add(b);
//    	    User c = new User();
//    	    c.setEmail("hi@bob.com");
//    	    c.setAge("20");
//    	    c.setStyle("casual");
//    	    users.add(c);
//    	    User d = new User();
//    	    d.setEmail("hey@bob.com");
//    	    d.setAge("40");
//    	    d.setStyle("cardio");
//    	    users.add(d);
     }
			
	public int getColumnCount() {
		return columnNames.length;
	}
	
	public int getRowCount() {
		return users.size();
	}
	
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	public Object getValueAt(int row, int col) {

		Object val;
		switch(col) {
		  case 0:
			  val=users.get(row).getEmail();
			  break;
		  case 1:
			  val=users.get(row).getAge();
			  break;
		  case 2:
			  val=users.get(row).getStyle();
			  break;
		  default:
			  val=users.get(row).getEmail();
		} 
		return val;
	}

	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
	
	public boolean isCellEditable(int row, int column)
    {
      return column == 3 || column == 4;
    }
	
	public void setValueAt(Object value, int row, int col) {
		switch(col) {
		  case 0:
			  users.get(row).setEmail((String)(value));
			  break;
		  case 1:
			  users.get(row).setAge((String)(value));
			  break;
		  case 2:
			  users.get(row).setStyle((String)(value));
			  break;
		  default:
			  users.get(row).setEmail((String)(value));
		} 
		fireTableCellUpdated(row, col);
	}
	

}
