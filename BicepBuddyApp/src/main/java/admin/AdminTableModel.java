package admin;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import User.User;


// TODO: Auto-generated Javadoc
/**
 * The Class AdminTableModel.
 * @authors: Zachary Steudel, Hunter Long, Chase Manseth, Bob Rein, Reece Kemball-Cook
 */
public class AdminTableModel extends AbstractTableModel {

    /** The column names. */
    private String[] columnNames = {"Email","age","style"};
  
  /** The users. */
  //TO-DO: make columns email, button, button
    private ArrayList<User> users = new ArrayList<User>();

     
     /**
      * Instantiates a new admin table model.
      *
      * @param myList the my list
      */
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
			
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	public int getColumnCount() {
		return columnNames.length;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	public int getRowCount() {
		return users.size();
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
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

	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
	 */
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#isCellEditable(int, int)
	 */
	public boolean isCellEditable(int row, int column)
    {
      return column == 3 || column == 4;
    }
	
	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#setValueAt(java.lang.Object, int, int)
	 */
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
