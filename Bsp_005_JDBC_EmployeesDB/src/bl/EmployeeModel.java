package bl;

import beans.Employee;
import database.DBAccess;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class EmployeeModel extends AbstractTableModel {

    List<Employee> emps;
    List<String> cols = Arrays.asList("Name","Gender","Birthdate","Hiredate");
    
    public EmployeeModel() throws ClassNotFoundException, SQLException {
        emps = DBAccess.getInstance().getAllEmployees();
    }
    
    @Override
    public int getRowCount() {
        return emps.size();
    }

    @Override
    public int getColumnCount() {
        return cols.size();
    }

    @Override
    public Object getValueAt(int row, int col) {
        return emps.get(row).toArray()[col];
    }

    @Override
    public String getColumnName(int col) {
        return cols.get(col);
    }

    public List<Employee> getEmps() {
        return emps;
    }

    public void setEmps(List<Employee> emps) {
        this.emps = emps;
        this.fireTableDataChanged();
    }
    
    
    
}
