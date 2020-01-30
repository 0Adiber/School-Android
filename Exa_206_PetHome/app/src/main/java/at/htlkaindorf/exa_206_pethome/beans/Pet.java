package at.htlkaindorf.exa_206_pethome.beans;

import java.io.Serializable;
import java.time.LocalDate;

public class Pet implements Serializable {

    private String name;
    private LocalDate dateOfBirth;
    private Gender gender;

    public Pet(String name, LocalDate dateOfBirth, Gender gender) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }
}
