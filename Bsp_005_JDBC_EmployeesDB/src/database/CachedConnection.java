package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Queue;

public class CachedConnection {
  
  private Queue<Statement> statementQueue = new LinkedList<>();                  
  private Connection connection;

  public CachedConnection(Connection connection) {
    this.connection = connection;
  }
  
  public Statement getStatement() throws SQLException {
    if (connection == null) {
      throw new RuntimeException("not connected to DB");
    }
    if (!statementQueue.isEmpty()) {
      return statementQueue.poll();
    }
    return connection.createStatement();
  }
  
  /**
   * returns a statement to the queue
   * @param statement 
   */
  public void releaseStatement(Statement statement) {
    if (connection == null) {
      throw new RuntimeException("not connected to DB");
    }
    statementQueue.offer(statement);
  }

  private Exception RuntimeException(String not_connected_to_DB) {
    throw new UnsupportedOperationException("Not supported yet."); 
  }
}
