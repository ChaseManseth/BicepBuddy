package admin;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.xml.transform.Templates;

import User.User;


// TODO: Auto-generated Javadoc
/**
 * The Class AdminTableModel.
 * @authors: Zachary Steudel, Hunter Long, Chase Manseth, Bob Rein, Reece Kemball-Cook
 */
public class AdminTableModel extends AbstractTableModel {

 
    private String[] columnNames = {"User","Delete User"," View Profile"};

    private ArrayList<AdminTableModelRow> data = new ArrayList<AdminTableModelRow>();

     public AdminTableModel (List<User> myList) {    	 
    	 myList.forEach(a  -> {
    		    data.add( new AdminTableModelRow(a));
    		    });
     }


public int getColumnCount() {
return columnNames.length;
}

public int getRowCount() {
return data.size();
}

public String getColumnName(int col) {
return columnNames[col];
}

public Object getValueAt(int row, int col) {
	Object val;
	switch(col) {
	  case 0:
		  val=data.get(row).getMyUser();
		  break;
	  case 1:
		  val=data.get(row).getDeleteString();
		  break;
	  case 2:
		  val=data.get(row).getViewString();
		  break;
	  default:
		  val=data.get(row).getMyUser();
	} 
	return val;
}

public Class getColumnClass(int c) {
	return getValueAt(0, c).getClass();
}


public boolean isCellEditable(int row, int col) {
	return col>0;
}

public void setValueAt(Object value, int row, int col) {
		if(col == 0) {
			  data.get(row).setMyUser((User)value);
			  fireTableCellUpdated(row, col);
		} 
}
public void removeRow(int row) {
   	data.remove(row);
    fireTableRowsDeleted(row, row);
}


}
