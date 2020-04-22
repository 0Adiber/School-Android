package at.adiber.db;

import at.adiber.beans.Employee;
import at.adiber.sql.SQLLoader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DB_Access {
    
    private Connection con;
    
    private PreparedStatement insEmp;
    private PreparedStatement remEmp;
    private PreparedStatement empFromDepartment;
    private PreparedStatement getEmps;
    private PreparedStatement avgSal;
    private PreparedStatement avgSalDept;
    private String testData;
    
    private static final String HOST = "localhost";
    private static final String PORT = "5432";
    private static final String DB_NAME = "mitarbeiterdb".toLowerCase();
    private static final String TABLE_NAME = "mitarbeiter".toLowerCase();
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";
    
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("HH:mm:ss");
    
    public DB_Access() throws ClassNotFoundException {
        //load pg
        Class.forName("org.postgresql.Driver");
        
        //load sql statements
        testData = SQLLoader.loadSql("testdata.sql");
    }
    
    //connect to db
    public void init() throws SQLException {
        logger("Starting initialization of DB");
        connect("postgres");
        logger("Connected to postgres DB");
        
        logger("Creating DB: " + DB_NAME);
        createDB();
        logger("Created " + DB_NAME);
        
        logger("Connecting to DB \"" + DB_NAME + "\"");
        connect(DB_NAME);
        logger("Connected to " + DB_NAME);
        
        logger("Creating Table: " + TABLE_NAME);
        createTable();
        logger("Created " + TABLE_NAME);
        
        logger("Inserting TestData");
        initEmployees();
        logger("Inserted TestData");
    }
    
    public void connect(String dbName) throws SQLException {
        String url = "jdbc:postgresql://" + HOST + ":" + PORT + "/" + dbName;
        
        con = DriverManager.getConnection(url, USERNAME, PASSWORD);
        logger("Connected to DB \"" + DB_NAME + "\"");
        
        //load the prepared statements
        insEmp = con.prepareStatement("INSERT INTO " + TABLE_NAME + " (PERS_NR, NAME, VORNAME, GEB_DATUM, GEHALT, ABT_NR, GESCHLECHT) VALUES\n" +
                                     "(?, ?, ?, ?, ?, ?, ?);");
        remEmp = con.prepareStatement("DELETE FROM " +TABLE_NAME+ " WHERE pers_nr = ?;");
        empFromDepartment = con.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE abt_nr=? ORDER BY name,vorname");
        avgSal = con.prepareStatement("SELECT AVG(gehalt) FROM " + TABLE_NAME + " WHERE geschlecht=?");
        avgSalDept = con.prepareStatement("SELECT AVG(gehalt) FROM " + TABLE_NAME + " WHERE geschlecht=? AND abt_nr=?");
        getEmps = con.prepareStatement("SELECT * FROM " + TABLE_NAME + " ORDER BY pers_nr");
        
        logger("Loaded all Prepared Statements");
    }
    
    public void disconnect() throws SQLException {
        if(con != null && !con.isClosed()) {
            con.close();
            logger("Disconnected from DB \"" + DB_NAME + "\"");
        }
    }
    
    public boolean createDB() {
        String query = "CREATE DATABASE " + DB_NAME;
        
        try(Statement s = con.createStatement()) {
            return s.execute(query);
        } catch(SQLException e) {
            return false;
        }
    }
    
    public boolean createTable() {
        String query = "CREATE TABLE " + TABLE_NAME + " (\n" +
                                    "	pers_nr INTEGER PRIMARY KEY,\n" +
                                    "	name VARCHAR(40) NOT NULL,\n" +
                                    "	vorname VARCHAR(40) NOT NULL,\n" +
                                    "	geb_datum DATE,\n" +
                                    "	gehalt NUMERIC(7,2),\n" +
                                    "	abt_nr INTEGER NOT NULL,\n" +
                                    "	geschlecht CHAR(1) NOT NULL\n" +
                                    ");";
        
        try(Statement s = con.createStatement()) {
            return s.execute(query);
        } catch(SQLException e) {
            return false;
        }
    }
    
    public List<Employee> getEmployeesFromDepartment(int department) throws SQLException {
        List<Employee> emps = new ArrayList<>();
        
        empFromDepartment.setInt(1, department);
        ResultSet rs = empFromDepartment.executeQuery();
        
        while(rs.next()) {
            emps.add(new Employee(rs));
        }
        
        logger("Got Employees by Department: " + department);
        
        return emps;
    }
    
    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> emps = new ArrayList<>();
        
        ResultSet rs = getEmps.executeQuery();
        
        while(rs.next()) {
            emps.add(new Employee(rs));
        }
        
        logger("Got all Employees");
        
        return emps;
    }
    
    public double getAverageSalery(char gender) throws SQLException {
        avgSal.setString(1, ""+gender);
        ResultSet rs = avgSal.executeQuery();
        logger("Got AVG Sal for " + gender);
        
        if(rs.next()) return rs.getBigDecimal(1).doubleValue();
        return 0;
    }
    
    public double getAverageSalery(char gender, int dept) throws SQLException {
        avgSalDept.setString(1, ""+gender);
        avgSalDept.setInt(2, dept);
        ResultSet rs = avgSalDept.executeQuery();
        logger("Got AVG Sal of Dept " + dept + " for " + gender);
        
        if(rs.next()) return rs.getBigDecimal(1).doubleValue();
        return 0;
    }
    
    public void initEmployees() throws SQLException {
        try(Statement s = con.createStatement()) {
            try {
                s.execute("DELETE FROM " + TABLE_NAME);
            } catch(SQLException e) {
                createTable();
            }
            s.execute(testData);
        }
    }
    
    public boolean insertEmployee(Employee employee) throws SQLException {
        insEmp.setInt(1, employee.getPers_nr());
        insEmp.setString(2, employee.getName());
        insEmp.setString(3, employee.getVorname());
        insEmp.setDate(4, java.sql.Date.valueOf(employee.getGeb_datum()));
        insEmp.setBigDecimal(5, BigDecimal.valueOf(employee.getGehalt()));
        insEmp.setInt(6, employee.getAbt_nr());
        insEmp.setString(7, ""+employee.getGeschlecht().charAt(0));
        
        logger("Inserting Employee: " + employee.getVorname() + employee.getName());
        
        return insEmp.execute();
    }
    
    public boolean removeEmployee(Employee employee) throws SQLException {
        remEmp.setInt(1, employee.getPers_nr());
        logger("Removing Employee: " + employee.getVorname() + employee.getName());
        return remEmp.execute();
    }
    
    private void logger(String msg) {
        LocalTime now = LocalTime.now();
        System.out.format("(%s): %s\n", DTF.format(now), msg);
    }
    
}
