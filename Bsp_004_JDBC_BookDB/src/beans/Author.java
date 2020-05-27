package beans;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Author implements Comparable<Author>{
 private int authorID;
    private String firstname, middlename, lastname;
    
    public Author(int authorID, String firstname, String middlename, String lastname) {
        this.authorID = authorID;
        this.firstname = firstname;
        if (middlename == null) {
            middlename = "";
        }
        this.middlename = middlename;
        if (lastname == null) {
            lastname = "";
        }
        this.lastname = lastname;
    }

    /**
     * the constructor for a SQL row
     *
     * @param rs
     * @throws SQLException
     */
    public Author(ResultSet rs) throws SQLException {
        authorID = rs.getInt("author_id");
        firstname = rs.getString("first_name");

        // check if null
        middlename = rs.getString("middle_name");
        if (middlename == null) {
            middlename = "";
        }
        
        lastname = rs.getString("last_name");
        if (lastname == null) {
            lastname = "";
        }
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.authorID;
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Author other = (Author) obj;
        if (this.authorID != other.authorID) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        System.out.println(firstname+lastname+middlename);
        if(middlename.isEmpty()) {
            return String.format("%s, %s", firstname, lastname);
        }
        return String.format("%s, %s, %s", firstname, middlename, lastname);
    }

    @Override
    public int compareTo(Author t) {
        return toString().compareTo(t.toString());
    }
}
