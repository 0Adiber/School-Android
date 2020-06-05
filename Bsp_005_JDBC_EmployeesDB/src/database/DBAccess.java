package database;

import beans.Department;
import beans.Employee;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBAccess {
    
    private static DBAccess instance = null;
    private Database db;
        
    public static DBAccess getInstance() throws ClassNotFoundException, SQLException {
        if(instance == null)
            instance = new DBAccess();
        return instance;
    }
    
    public void connect() throws SQLException {
        db.connect();
    }
    
    public void disconnect() throws SQLException {
        db.disconnect();
    }
    
    public boolean isConnected() throws SQLException {
        return db.isConnected();
    }
    
    private DBAccess() throws ClassNotFoundException, SQLException {
        db = new Database();
        connect();
    }

    public List<Department> getAllDepartments() throws SQLException {      
        List<Department> d = new ArrayList<>();
        String query = SQLStrings.GETALLDEPT;
        Statement stat = db.getStatement();
        ResultSet rs = stat.executeQuery(query);
        while(rs.next()) {
            d.add(new Department(rs));
        }
        db.releaseStatement(stat);
        
        return d;
    }
    
    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> e = new ArrayList<>();
        String query = SQLStrings.GETALLEMP;
        Statement stat = db.getStatement();
        ResultSet rs = stat.executeQuery(query);
        while(rs.next()) {
            e.add(new Employee(rs));
        }
        db.releaseStatement(stat);
        
        return e;
    }
    
    public List<Employee> getAllEmployeesBy(String deptNo, String ...gender) throws SQLException {
        List<Employee> emps = new ArrayList<>();
        
        String query = SQLStrings.GETALLEMP.replace("(department)", "'"+deptNo+"'").replace("(gender1)", "'"+gender[0]+"'").replace("(gender2)", "'"+gender[1]+"'");
        
        Statement stat = db.getStatement();
        ResultSet rs = stat.executeQuery(query);
        while(rs.next()) {
            emps.add(new Employee(rs));
        }
        db.releaseStatement(stat);
        return emps;        
    }
    
}
