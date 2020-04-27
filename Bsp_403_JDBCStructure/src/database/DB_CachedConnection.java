package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Queue;

/**
 * class to manage statement pool for database access
 * @author Admin
 */
public class DB_CachedConnection {
    
    private Queue<Statement> statementQueue = new LinkedList<>();
    private Connection connection;

    public DB_CachedConnection(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * returns a valid statement from the database
     * @return
     * @throws SQLException 
     */
    public Statement getStatement() throws SQLException {
        if(connection == null) {
            throw new RuntimeException("not connected to DB");
        }
        if(!statementQueue.isEmpty()) {
            return statementQueue.poll();
        }
        return connection.createStatement();
    }
    
    /**
     * returns statement to the queue
     * @param statement 
     */
    public void releaseStatement(Statement statement) {
        if(connection == null) {
            throw new RuntimeException("not connected to DB");
        }
        statementQueue.offer(statement);
    }
    
}
