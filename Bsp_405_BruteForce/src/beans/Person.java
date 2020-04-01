package beans;

public class Person {

    private String firstname;
    private String lastname;
    private String pw; // --> ist in unserem usecase eigentlich unnötig, da wir die passwörter ja auf die konsole ausgeben
    private String hash;

    public Person(String line) {
        String[] parts = line.split(",");
        this.firstname = parts[0];
        this.lastname = parts[1];
        this.pw = parts[2];
        this.hash = parts[3];
    }
    
    public Person(String firstname, String lastname, String pw, String hash) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.pw = pw;
        this.hash = hash;
    }
 
    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPw() {
        return pw;
    }

    public String getHash() {
        return hash;
    }
    
    
    
}
