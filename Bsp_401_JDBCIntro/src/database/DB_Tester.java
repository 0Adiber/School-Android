/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
    
    public static void main(String[] args) {
        try {
            DB_Tester dbt = new DB_Tester();
            dbt.connect("postgres");
            dbt.disconnect();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DB_Tester.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DB_Tester.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
