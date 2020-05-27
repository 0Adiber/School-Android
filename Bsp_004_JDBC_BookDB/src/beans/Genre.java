package beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Genre implements Comparable<Genre>{
    
    private int genreID;
    private String genre;
    private int parentID;

    public Genre(int genre_id, String genre, int parent_id) {
        this.genreID = genre_id;
        this.genre = genre;
        this.parentID = parent_id;
    }
    
    public Genre(ResultSet rs) throws SQLException {
        genreID = rs.getInt("genre_id");
        genre = rs.getString("genre");
        if (genre == null) genre = "";
        parentID = rs.getInt("parent_id");
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.genreID;
        hash = 53 * hash + Objects.hashCode(this.genre);
        hash = 53 * hash + this.parentID;
        return hash;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getGenreID() {
        return genreID;
    }

    public String getGenre() {
        return genre;
    }

    public int getParentID() {
        return parentID;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Genre other = (Genre) obj;
        if (this.genreID != other.genreID)
            return false;
        if (this.parentID != other.parentID)
            return false;
        if (!Objects.equals(this.genre, other.genre))
            return false;
        return true;
    }

    @Override
    public int compareTo(Genre t) {
        // if its zero, its the parentgenre
        if (parentID == 0) {
            return -1;
        } else if (t.getParentID() == 0) {
            return 1;
        }
        return genre.compareTo(t.getGenre());
    }

    @Override
    public String toString() {
        return genre;
    }

}
