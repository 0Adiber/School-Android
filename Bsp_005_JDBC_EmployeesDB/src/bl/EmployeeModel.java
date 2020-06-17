package bl;

import beans.Employee;
import database.DBAccess;
import gui.EmployeesGUI;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
    
    public Employee getEmp(int row) {
        return emps.get(row);
    }
    
    public void addEmp(Employee emp) {
        this.emps.add(emp);
        this.fireTableRowsInserted(emps.size()-1, emps.size()-1);
    }
    
    public void updateEmp(int index, Employee emp) {
        emps.remove(index);
        emps.add(index, emp);
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

    @Override
    public boolean isCellEditable(int row, int col) {
        return (getColumnName(col).equals(cols.get(0)) || getColumnName(col).equals(cols.get(3)));
    }

    @Override
    public void setValueAt(Object obj, int row, int col) {
        
        Employee emp = emps.get(row);
        
        //Name
        if(getColumnName(col).equals(cols.get(0))) {
            String name = (String)obj;
            String[] parts = name.split(",");
            
            emp.setLastname(parts[0]);
            emp.setFirstname(parts.length == 1?"":parts[1]);
        }
        //Hiredate
        else if(getColumnName(col).equals(cols.get(3))) {
            try {
                String ds = (String)obj;
                LocalDate date = LocalDate.parse(ds, Employee.DTF);
                emp.setHiredate(date);
            } catch(DateTimeParseException ex) {
                JOptionPane.showMessageDialog(EmployeesGUI.gui, "Wrong date format! (dd.MM.yyyy)");
            }
        }
        
        try {
            if(!DBAccess.getInstance().updateEmployee(row, emp)) {
                JOptionPane.showMessageDialog(EmployeesGUI.gui, "Could not change Data!");
            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(EmployeesGUI.gui, "Please add the Postgres Library");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(EmployeesGUI.gui, "Database error");
        }
        
    }
    
    
    
}
