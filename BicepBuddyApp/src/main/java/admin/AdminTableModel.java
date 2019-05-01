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

 
    /** The column names. */
    private String[] columnNames = {"User","Delete User"," View Profile"};

    /** The data. */
    private ArrayList<AdminTableModelRow> data = new ArrayList<AdminTableModelRow>();

     /**
      * Instantiates a new admin table model.
      *
      * @param myList the my list
      */
     public AdminTableModel (List<User> myList) {    	 
    	 myList.forEach(a  -> {
    		    data.add( new AdminTableModelRow(a));
    		    });
     }

	/**
	 * Gets the current column count.
	 *
	 * @return the column count
	 */
	public int getColumnCount() {
		return columnNames.length;
	}
	
	/**
	 * Gets the current column count.
	 *
	 * @return the row count
	 */
	public int getRowCount() {
		return data.size();
	}
	
	/**
	 * Gets the name of the column at col.
	 *
	 * @param col the column whose name will be retrieved
	 * @return the column name
	 */
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	/**
	 * Gets the value at the position of row and col.
	 *
	 * @param row the row of the value to retrieve
	 * @param col the col of the value to retrieve
	 * @return the value at
	 */
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
	
	/**
	 * Gets the Class of the column at c.
	 *
	 * @param c the target column
	 * @return the column class
	 */
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
	
	/**
	 * Returns based on whether or not the specified cell can be changed.
	 *
	 * @param row the target row
	 * @param col the target column
	 * @return true, if cell at row, col can be changed
	 */
	public boolean isCellEditable(int row, int col) {
		return col>0;
	}
	
	/**
	 * Sets the value of cell at row, col to value.
	 *
	 * @param value new value to be stored
	 * @param row the target row
	 * @param col the target column
	 */
	public void setValueAt(Object value, int row, int col) {
			if(col == 0) {
				  data.get(row).setMyUser((User)value);
				  fireTableCellUpdated(row, col);
			} 
	}
	
	/**
	 * Removes the target row from the model.
	 *
	 * @param row the target row to remove
	 */
	public void removeRow(int row) {
	   	data.remove(row);
	    fireTableRowsDeleted(row, row);
	}


}
