package beans;

import db.DB_Access;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Objects;

public class Student {
    private String className;
    private int catalogNo;
    private String firstname;
    private String surname;
    private String gender;
    private LocalDate birthdate;
        
    private static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Student(String className, int catalogNo, String firstname, String surname, String gender, LocalDate dateofbirth) {
        this.className = className;
        this.catalogNo = catalogNo;
        this.firstname = firstname;
        this.surname = surname;
        this.gender = gender;
        this.birthdate = dateofbirth;
    }
    
    public Student(ResultSet rs) throws SQLException {
        this.className = DB_Access.getInstance().getGradeName(rs.getInt("classid"));
        this.catalogNo = rs.getInt("catno");
        this.firstname = rs.getString("firstname");
        this.surname = rs.getString("surname");
        this.gender = rs.getString("gender");
        this.birthdate = rs.getDate("dateofbirth").toLocalDate();
    }

    public Student(String line) {
        String[] split = line.split(";");
        if (split.length != 5) throw new RuntimeException("line does not have enough parts");
        //Klasse;Familienname;Vorname;Geschlecht;Geburtsdatum
        this.className = split[0];
        this.surname = split[1];
        this.firstname = split[2];
        this.gender = split[3];
        this.birthdate = LocalDate.parse(split[4], DTF);
        this.catalogNo = -1;
    }

    public String getClassName() {
        return className;
    }

    public int getCatalogNo() {
        return catalogNo;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }

    public String getGender() {
        return gender;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setCatalogNo(int catalogNo) {
        this.catalogNo = catalogNo;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.className);
        hash = 43 * hash + Objects.hashCode(this.firstname);
        hash = 43 * hash + Objects.hashCode(this.surname);
        hash = 43 * hash + Objects.hashCode(this.gender);
        hash = 43 * hash + Objects.hashCode(this.birthdate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Student other = (Student) obj;
        if (!Objects.equals(this.className, other.className))
            return false;
        if (!Objects.equals(this.firstname, other.firstname))
            return false;
        if (!Objects.equals(this.surname, other.surname))
            return false;
        if (!Objects.equals(this.gender, other.gender))
            return false;
        if (!Objects.equals(this.birthdate, other.birthdate))
            return false;
        return true;
    }
}

