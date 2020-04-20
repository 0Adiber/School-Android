package beans;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Student {
    
    private int studentId;
    private int catalogNr;
    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;
    
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public Student() {
    }

    public Student(int studentid, int catalogNr, String firstname, String lastname, LocalDate dateOfBirth) {
        this.studentId = studentid;
        this.catalogNr = catalogNr;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCatalogNr() {
        return catalogNr;
    }

    public void setCatalogNr(int catalogNr) {
        this.catalogNr = catalogNr;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    
    
    @Override
    public String toString() {
        return String.format("%02d %s %s %s", catalogNr, lastname, firstname, DTF.format(dateOfBirth));
    }
    
    
    

}
