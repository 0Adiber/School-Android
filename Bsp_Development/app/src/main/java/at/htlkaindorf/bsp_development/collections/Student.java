package at.htlkaindorf.bsp_development.collections;

import java.util.Objects;
import java.util.function.Predicate;

import androidx.annotation.NonNull;

public class Student {
    String firstname;
    String lastname;

    public Student(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
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

    @NonNull
    @Override
    public String toString() {
        return String.format("%s %s", firstname, lastname);
    }

    //removeIf

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(firstname, student.firstname) &&
                Objects.equals(lastname, student.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname);
    }



}
