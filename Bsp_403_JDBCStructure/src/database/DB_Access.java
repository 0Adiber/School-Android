package database;

import beans.Student;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB_Access {
    /**
     * implement class as singleton
     */
    private static DB_Access theInstance = null;
    private DB_Database db;
    
    public static DB_Access getInstance() {
        if(theInstance == null) {
            theInstance = new DB_Access();
        }
        return theInstance;
    }
    
    private DB_Access() {
        try {
            db = new DB_Database();
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Databse problem - possible cause: DB-Driver not found");
        } catch (SQLException ex) {
            throw new RuntimeException("Databse problem - cause: " + ex.toString());
        }
    }
    
    /**
     * get all studets of one class from database
     */
    public List<Student> getAllStudents(String classname) throws SQLException {
        String sqlString = String.format("SELECT * FROM student WHERE class_name='%s'", classname);
        Statement stat = db.getStatement();
        ResultSet rs = stat.executeQuery(sqlString);
        List<Student> studentList = new ArrayList<>();
        while(rs.next()) {
            //TODO: get Values from ResultSet
            studentList.add(new Student());
        }
        db.releaseStatement(stat);
        return studentList;
    }
    
    /**
     * insert a new student into the database
     * @param student
     * @return true if instert was successfull - otherwise false
     */
    public boolean insertStudent(Student student) {
        String sqlString = String.format("INSERT INTO ....");
        return true;
    }
    
}
