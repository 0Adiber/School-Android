/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import beans.Student;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class DB_Tester {
    
    private Connection connection;

    public DB_Tester() throws ClassNotFoundException {
        //load postgres database driver
        Class.forName("org.postgresql.Driver");
    }
    
    /**
     * connect to postgres database dbName
     * @param dbName 
     */
    public void connect(String dbName) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/"+dbName; //port optional, außer wenn nicht default port nummer
        String username = "postgres";
        String password = "postgres";
        connection = DriverManager.getConnection(url, username, password);
    }
    
    public void disconnect() throws SQLException {
        if(connection != null)
            connection.close();
    }
    
    public void createDatabase(String databasename) throws SQLException {
        String sqlString = "CREATE DATABASE " + databasename.toLowerCase();
        //create statement from connection
        Statement statement = connection.createStatement();
        statement.execute(sqlString);
        statement.close();
    }
    
    public void createTable() throws SQLException {
        String sqlString = "CREATE TABLE student\n" +
                "(student_id SERIAL PRIMARY KEY,\n" +
                " catalognr INTEGER NOT NULL,\n" +
                " firstname VARCHAR(100) NOT NULL,\n" +
                " lastname VARCHAR(100) NOT NULL,\n" +
                " dateofbirth DATE NOT NULL\n" +
                ")";
        
        Statement statement = connection.createStatement();
        statement.executeUpdate(sqlString);
        statement.close();        
    }
    
    public void insertStudent(Student student) throws SQLException {
        Statement statement = connection.createStatement();
        java.sql.Date sqlDate = java.sql.Date.valueOf(student.getDateOfBirth());
        String sqlString = "INSERT INTO student (catalognr, firstname, lastname, dateofbirth)\n" +
                            "VALUES (" + student.getCatalogNr() 
                            + ", '"+ student.getFirstname() + "', '" 
                            + student.getLastname() + "', '" 
                            + sqlDate.toString() + "');";
        
        statement.executeUpdate(sqlString);
        statement.close();   
    }
    
    public List<Student> getTableContent() throws SQLException {
        List<Student> students = new ArrayList<>();
        Statement statement = connection.createStatement();
        String sqlQuery = "SELECT * FROM student;";
        ResultSet rs = statement.executeQuery(sqlQuery);
        
        while(rs.next()) {
            int id = rs.getInt(1);
            int catnr = rs.getInt("catalognr");
            String firstname = rs.getString("firstname");
            String lastname = rs.getString("lastname");
            java.sql.Date dateOfBirth = rs.getDate("dateofbirth");
            Student student = new Student(id, catnr, firstname, lastname, dateOfBirth.toLocalDate());
            students.add(student);
        }
        
        statement.close();
        
        return students;
    }
    
    public static void main(String[] args) {
        try {
            DB_Tester dbt = new DB_Tester();
            dbt.connect("postgres");
            
            try {
                dbt.createDatabase("studentdb");
            } catch(SQLException ex) {
                System.out.println("Database already exists!");
            }
            dbt.disconnect();
            dbt.connect("studentdb");
            
            try {
                dbt.createTable();
            }catch(SQLException ex) {
                System.out.println("relation student already exists");
            }
            
//            dbt.insertStudent(new Student(0, 1, "Leon", "Anghel", LocalDate.of(2002, 10, 24)));
//            dbt.insertStudent(new Student(0, 2, "Nico", "Baumann", LocalDate.of(2002, 7, 31)));
//            dbt.insertStudent(new Student(0, 1, "Adrian", "Berner", LocalDate.of(2003, 6, 12)));

            dbt.getTableContent().stream()
                                 .forEach(System.out::println);
            
            dbt.disconnect();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.toString());
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        
    }
    
}
