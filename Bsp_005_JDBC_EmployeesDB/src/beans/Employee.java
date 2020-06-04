package beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Employee {
    
    private int empno;
    private String firstname;
    private String lastname;
    private Gender gender;
    private LocalDate hiredate;
    private LocalDate birthdate;
    
    private static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public Employee(int empno, String firstname, String lastname, Gender gender, LocalDate hiredate, LocalDate birthdate) {
        this.empno = empno;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.hiredate = hiredate;
        this.birthdate = birthdate;
    }
    
    public Employee(ResultSet rs) throws SQLException {
        empno = rs.getInt("emp_no");
        firstname = rs.getString("first_name");
        lastname = rs.getString("last_name");
        gender = Gender.valueOf(rs.getString("gender"));
        birthdate = rs.getDate("birth_date").toLocalDate();
        hiredate = rs.getDate("hire_date").toLocalDate();
    }

    public int getEmpno() {
        return empno;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Gender getGender() {
        return gender;
    }

    public LocalDate getHiredate() {
        return hiredate;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String[] toArray() {
        return new String[] {firstname+", "+lastname, gender.toString(), DTF.format(birthdate), DTF.format(hiredate)};
    }
    
}
