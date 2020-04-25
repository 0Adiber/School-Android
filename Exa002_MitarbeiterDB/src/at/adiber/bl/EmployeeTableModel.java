package at.adiber.bl;

import at.adiber.beans.Employee;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

public class EmployeeTableModel extends AbstractTableModel{
    
    private String[] columns = {"Personal Nr.", "Nachname", "Vorname", "Geburtsdatum", "Gehalt", "Abteilungs Nr.", "Geschlecht"};
    
    private List<Object[]> rowData;
    
    public static int lastKey = 0;

    public EmployeeTableModel(List<Employee> employees) {
        this.rowData = employees.stream().map(Employee::convertToTableData).collect(Collectors.toList());
        lastKey = employees.get(employees.size()-1).getPers_nr();
    }

    @Override
    public String getColumnName(int col) {
        return columns[col];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    public void changeData(List<Employee> employees) {
        rowData = employees.stream().map(Employee::convertToTableData).collect(Collectors.toList());
        this.fireTableDataChanged();
    }
    
    public Object[] getRow(int row) {
        return rowData.get(row);
    }
    
    @Override
    public int getRowCount() {
        return rowData.size();
    }
    
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        return rowData.get(row)[col];
    }

    
}
