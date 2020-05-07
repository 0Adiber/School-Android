package db;

import beans.Student;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DB_Access {

  private static DB_Access theInstance = null;
  private DB_Database db;
  
  private static int lastStudentId = 0, lastClassId = 0;
  
  private final String insertStudentString = "INSERT INTO student (studentid, classid, catno, firstname, surname, gender, dateofbirth) VALUES ( ? , ? , ? , ?, ?, ?, ?);";
  private final String insertClassString = "INSERT INTO grade (classid,classname) VALUES (?,?);";
  private PreparedStatement insertStudentpStat = null, insertClasspStat = null;
  
  public static DB_Access getInstance() {
    if (theInstance == null) {
      theInstance = new DB_Access();
    }
    return theInstance;
  }
   
  public void connect() throws SQLException {
      db.connect();
  }
  
  public boolean isConnected() throws SQLException {
      return db.isConnected();
  }
  
  public void disconnect() throws SQLException {
      insertClasspStat = null;
      insertStudentpStat = null;
      db.disconnect();
  }

  private DB_Access() {
    try {
      db = new DB_Database();
    } catch (ClassNotFoundException ex) {
      throw new RuntimeException("Database problem - possible cause: DB-Driver not found");
    } catch (SQLException ex) {
      throw new RuntimeException("Database problem - possible cause: " + ex.toString());
    }
  }

  public List<Student> getAllStudentsFor(String grade) throws SQLException {
    String query = "SELECT * FROM student WHERE classid='" + getGradeId(grade) + "';";
    Statement stat = db.getStatement();
    ResultSet rs = stat.executeQuery(query);
    List<Student> studentList = new ArrayList<>();
    while (rs.next()) {
      studentList.add(new Student(rs));
    }
    db.releaseStatement(stat);
    return studentList;
  }
  
   public List<Student> getAllStudents() throws SQLException {
    String query = "SELECT * FROM student;";
    Statement stat = db.getStatement();
    ResultSet rs = stat.executeQuery(query);
    List<Student> studentList = new ArrayList<>();
    while (rs.next()) {
      studentList.add(new Student(rs));
    }
    db.releaseStatement(stat);
    return studentList;
  }

  public boolean insertStudent(Student student) throws SQLException {
    if (insertStudentpStat == null) {
      insertStudentpStat = db.getConnection().prepareStatement(insertStudentString);
    }
    insertStudentpStat.setInt(1, lastStudentId++);
    insertStudentpStat.setInt(2, getGradeId(student.getClassName()));
    insertStudentpStat.setInt(3, student.getCatalogNo());
    insertStudentpStat.setString(4, student.getFirstname());
    insertStudentpStat.setString(5, student.getSurname());
    insertStudentpStat.setString(6, student.getGender());
    insertStudentpStat.setDate(7, Date.valueOf(student.getBirthdate()));
    
    int numDataSets = insertStudentpStat.executeUpdate();
    return numDataSets > 0;
  }
  
  public boolean insertClass(String classname) throws SQLException {
      if(insertClasspStat == null)
          insertClasspStat = db.getConnection().prepareStatement(insertClassString);
      insertClasspStat.setInt(1, lastClassId++);
      insertClasspStat.setString(2, classname);
      
      return insertClasspStat.executeUpdate() > 0;
  }
  
  public int getGradeId(String grade) throws SQLException {
      String query = "SELECT classid FROM grade WHERE classname='"+grade+"';";
      Statement stat = db.getStatement();
        ResultSet rs = stat.executeQuery(query);
        int result = -1;
        if (rs.next()) {
          result = rs.getInt("classid");
        }
        db.releaseStatement(stat);
        return result;
  }

  public List<String> getAllGrades() throws SQLException {
    String query = "SELECT * FROM grade;";
    Statement stat = db.getStatement();
    ResultSet rs = stat.executeQuery(query);
    List<String> classList = new ArrayList<>();
    while (rs.next()) {
      classList.add(rs.getString("classname"));
    }
    db.releaseStatement(stat);
    return classList;
  }
  
  public void deleteAll() throws SQLException {
    String query = "DELETE FROM student;";
    Statement stat = db.getStatement();
    stat.execute(query);
    query = "DELETE FROM grade;";
    stat.execute(query);
    db.releaseStatement(stat);
  }
  
  public void deleteFor(String classname) throws SQLException {
    String query = "DELETE FROM student WHERE classid=" + getGradeId(classname) + ";";
    Statement stat = db.getStatement();
    stat.execute(query);
    db.releaseStatement(stat);
  }
  
  public String getGradeName(int grade) throws SQLException {
    String query = "SELECT classname FROM grade WHERE classid="+grade+";";
    Statement stat = db.getStatement();
    ResultSet rs = stat.executeQuery(query);
    String res = null;
    if(rs.next()) {
        res = rs.getString("classname");
    }
    db.releaseStatement(stat);
    return res;
  }
  
}
