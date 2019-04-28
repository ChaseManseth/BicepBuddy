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

 
    private String[] columnNames = {"Email","Delete","View"};

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
		  val=data.get(row).getMyUser().getEmail();
		  break;
	  case 1:
		  val=data.get(row).getDeleteString();
		  break;
	  case 2:
		  val=data.get(row).getViewString();
		  break;
	  default:
		  val=data.get(row).getMyUser().getEmail();
	} 
	return val;
}

/*
* JTable uses this method to determine the default renderer/
* editor for each cell.  If we didn't implement this method,
* then the last column would contain text ("true"/"false"),
* rather than a check box.
*/
public Class getColumnClass(int c) {
return getValueAt(0, c).getClass();
}


public boolean isCellEditable(int row, int col) {
	return col>0;
}

/*
* Don't need to implement this method unless your table's
* data can change.
*/
public void setValueAt(Object value, int row, int col) {
		if(col == 0) {
			  data.get(row).setMyUser((User)value);
			  fireTableCellUpdated(row, col);
		} 
}



}
