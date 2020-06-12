package bl;

import beans.Employee;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class EmployeeModel extends AbstractTableModel {

    List<Employee> emps;
    List<String> cols = Arrays.asList("Name","Gender","Birthdate","Hiredate");
    
    boolean[] order = new boolean[]{false, false, false, false};
    
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
        order = new boolean[]{false, false, false, false};
    }
    
    public void sort(String column) {
        switch(column) {
            case "name":
                if(order[0] == false) {
                    this.emps.sort(new SortByName());
                    order[0] = true;
                } else {
                    this.emps.sort(new SortByName().reversed());
                    order[0] = false;
                }
                break;
            case "gender":
                if(order[1] == false) {
                    this.emps.sort(new SortByGender());
                    order[1] = true;
                } else {
                    this.emps.sort(new SortByGender().reversed());
                    order[1] = false;
                }
                break;
            case "birthdate":
                if(order[2] == false) {
                    this.emps.sort(new SortByBirth());
                    order[2] = true;
                } else {
                    this.emps.sort(new SortByBirth().reversed());
                    order[2] = false;
                }
                break;
            case "hiredate":
                if(order[3] == false) {
                    this.emps.sort(new SortByHire());
                    order[3] = true;
                } else {
                    this.emps.sort(new SortByHire().reversed());
                    order[3] = false;
                }
                break;
        }
        
        fireTableDataChanged();
    }
    
}
