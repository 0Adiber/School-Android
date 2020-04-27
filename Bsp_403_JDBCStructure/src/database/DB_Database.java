package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB_Database {
    
    private String db_url;
    private String db_driver;
    private String db_username;
    private String db_password;
    private Connection connection;
    private DB_CachedConnection cachedConnection;

    public DB_Database() throws ClassNotFoundException, SQLException {
        loadProperties();
        Class.forName(db_driver);
        connect();
    }
    
    /**
     * establish connection to database
     * @throws SQLException 
     */
    public void connect() throws SQLException {
        if(connection != null) {
            connection.close();
        }
        connection = DriverManager.getConnection(db_url, db_username, db_password);
        cachedConnection = new DB_CachedConnection(connection);
    }
    
    public void disconnect() throws SQLException {
        if(connection != null)
            connection.close();
    }
    
    public Connection getConnection() {
        return connection;
    }
    
    public Statement getStatement() throws SQLException {
        if(connection != null && cachedConnection != null) {
            throw new RuntimeException("database connection error");
        }
        return cachedConnection.getStatement();
    }
    
    public void releaseStatement(Statement statement) {
        if(connection != null && cachedConnection != null) {
            throw new RuntimeException("database connection error");
        }
        cachedConnection.releaseStatement(statement);
    }
    
    public void loadProperties() {
        db_url = DB_Properties.getProp("url");
        db_driver = DB_Properties.getProp("driver");
        db_username = DB_Properties.getProp("username");
        db_password = DB_Properties.getProp("password");        
    }
    
    public static void main(String[] args) {
        try {
            new DB_Database();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.toString());
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
    
}
