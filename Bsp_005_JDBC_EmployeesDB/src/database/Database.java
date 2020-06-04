package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

  private String db_url;
  private String db_driver;
  private String db_username;
  private String db_password;
  private Connection connection;
  private CachedConnection cachedConnection;

  public Database() throws ClassNotFoundException, SQLException {
    loadProperties();
    Class.forName(db_driver);
  }

  /**
   * establish connection to database
   *
   * @throws SQLException
   */
  public void connect() throws SQLException {
    if (connection != null) {
      connection.close();
    }
    connection = DriverManager.getConnection(db_url, db_username, db_password);
    cachedConnection = new CachedConnection(connection);
  }

  public void disconnect() throws SQLException {
    if (connection != null) {
      connection.close();
      cachedConnection = null;
    }
  }

  public void loadProperties() {
    db_url = DBProperties.getPropertyValue("url");
    db_driver = DBProperties.getPropertyValue("driver");
    db_username = DBProperties.getPropertyValue("username");
    db_password = DBProperties.getPropertyValue("password");
  }

  public Connection getConnection() {
    return connection;
  }

  public Statement getStatement() throws SQLException {
    if (connection == null || cachedConnection == null) {
      throw new RuntimeException("database connection error");
    }
    return cachedConnection.getStatement();
  }

  public void releaseStatement(Statement statement) {
    if (connection == null || cachedConnection == null) {
      throw new RuntimeException("database connection error");
    }
    cachedConnection.releaseStatement(statement);
  }
  
  public boolean isConnected() throws SQLException {
      return !(connection == null || connection.isClosed());
  }

}