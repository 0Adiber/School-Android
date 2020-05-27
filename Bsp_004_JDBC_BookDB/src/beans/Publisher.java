package beans;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Publisher implements Comparable<Publisher>{

    private int publisherID;
    private String name;

    public Publisher(int publisherID, String name) {
        this.publisherID = publisherID;
        this.name = name;
    }

    public Publisher(ResultSet rs) throws SQLException {
        publisherID = rs.getInt("publisher_id");
        name = rs.getString("name");
    }

    public String getName() {
        return name;
    }

    public int getPublisherID() {
        return publisherID;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.publisherID;
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
        final Publisher other = (Publisher) obj;
        if (this.publisherID != other.publisherID)
            return false;
        return true;
    }

    @Override
    public int compareTo(Publisher p) {
        // if its minus one, its the "alle" one
        if (publisherID == -1) {
            return -1;
        } else if (p.getPublisherID() == -1) {
            return 1;
        }
        return name.compareTo(p.getName());
    }
    
}
