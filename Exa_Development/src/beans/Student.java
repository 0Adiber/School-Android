package beans;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Student implements Serializable{ 
        //marker interface --> fügt klasse dort hinzu, aber keine methoden implementieren (statische variablen werden nicht serialisiert)
        // die darin enhaltenen variablen werden auch serialisiert (falls diese serializable implementieren)

    private static final long serialVersionUID = 123456L;
    
    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;
    private Gender gender;
    private transient URI uri; //URI ist serialisierbar; "Uri" -> in Android nicht //transient --> variable ist von serialisierung ausgenommen //transient hier nur zum testen
    
    public Student() {
        
    }

    public Student(String firstname, String lastname, LocalDate dateOfBirth, Gender gender) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        try {
            this.uri = new URI("www.123");
        } catch (URISyntaxException ex) {
            System.out.println(ex.toString());
        }
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
        return "Student{" + "firstname=" + firstname + ", lastname=" + lastname + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", uri=" + uri + '}';
    }
       
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();  //mach, was serializeable macht (alles bis auf static & transient)
        oos.writeUTF(uri.toString());
    }
    
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();  //same as above
        String strUri = ois.readUTF();
        try {
            uri = new URI(strUri);
        } catch (URISyntaxException ex) {
            System.out.println(ex.toString());
        }
    }
    
}
