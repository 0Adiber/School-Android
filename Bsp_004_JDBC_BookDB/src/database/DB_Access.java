package database;

import beans.Author;
import beans.Book;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import beans.Genre;
import beans.Publisher;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class DB_Access {

    private static DB_Access theInstance = null;
    private DB_Database db;
    
    private List<Author> authors = new ArrayList<>();
    private List<Book> books = new ArrayList<>();
    private List<Publisher> publishers = new ArrayList<>();
    private Genre parent;
    
    public static DB_Access getInstance() throws ClassNotFoundException, SQLException {
        if(theInstance == null)
            theInstance = new DB_Access();
        return theInstance;
    }
    
    public void connect() throws SQLException {
        db.connect();
        getAllPublishers();
        getTopGenre();
        getAllAuthors();
    }
    
    public void disconnect() throws SQLException {
        db.disconnect();
    }
    
    public boolean isConnected() throws SQLException {
        return db.isConnected();
    }
    
    private DB_Access() throws ClassNotFoundException, SQLException {
        db = new DB_Database();
    }
    
    private void getAllPublishers() throws SQLException {
        publishers.clear();
        publishers.add(new Publisher(-1, "Alle"));
        String query = "SELECT * FROM publishers ORDER BY NAME";
        Statement stat = db.getStatement();
        ResultSet rs = stat.executeQuery(query);
        while(rs.next()) {
            publishers.add(new Publisher(rs));
        }
        db.releaseStatement(stat);
    }
    
    private void getAllAuthors() throws SQLException {
        authors.clear();
        String query = "SELECT * FROM authors;";
        Statement stat = db.getStatement();
        ResultSet rs = stat.executeQuery(query);
        while(rs.next()) {
            authors.add(new Author(rs));
        }
        db.releaseStatement(stat);
    }
    
    private void getTopGenre() throws SQLException {
        String query = "SELECT * FROM genres;";
        Statement stat = db.getStatement();
        ResultSet rs = stat.executeQuery(query);
        while(rs.next()) {
            Genre g = new Genre(rs);
            if(g.getParentID() == 0) {
                g.setGenre("Alle");
                parent = g;
            }
        }
    }
    
    public Set<Genre> getGenresBy(Publisher p) throws SQLException {
        String query = "SELECT g.genre_id, g.genre, g.parent_id FROM publishers p "
                  + "INNER JOIN books b ON b.publisher_id=p.publisher_id "
                  + "INNER JOIN book_genres bg ON bg.book_id = b.book_id "
                  + "INNER JOIN genres g ON g.genre_id = bg.genre_id "
                  + (p.getPublisherID()==-1?"":"WHERE p.publisher_id="+p.getPublisherID())+" ORDER BY genre;";
                
        Set<Genre> list = new TreeSet<>();
        list.add(parent);
        
        Statement stat = db.getStatement();
        ResultSet rs = stat.executeQuery(query);
        while(rs.next()) {
            list.add(new Genre(rs));
        }
        
        return list;
    }
    
    public List<Book> getBookBy(Genre g, Publisher p, String author, String title) throws SQLException {
        String query = "SELECT * FROM books b INNER JOIN book_authors ba ON ba.book_id = b.book_id INNER JOIN authors a ON a.author_id = ba.author_id";
        String end = " WHERE UPPER(b.title) LIKE UPPER('%" + title + "%') AND (UPPER(a.first_name) LIKE UPPER('%"+author+"%') OR UPPER(a.middle_name) LIKE UPPER('%"+author+"%') OR UPPER(a.last_name) LIKE UPPER('%"+author+"%'))";
        
        
        query+=" INNER JOIN book_genres bg ON bg.book_id = b.book_id "
                  + "INNER JOIN genres g on g.genre_id = bg.genre_id";
        if(g.getGenreID() != parent.getGenreID()) {
            end+=" AND g.genre_id = " + g.getGenreID();
        }
        
        
        query += " INNER JOIN publishers p ON p.publisher_id = b.publisher_id";
        if(p.getPublisherID() != -1) {
            end+=" AND p.publisher_id = " + p.getPublisherID();
        }
                
        query += end + " ORDER BY b.title,a.first_name, a.middle_name, a.last_name";
        
        Statement stat = db.getStatement();
        
        List<Book> tmp = parseBooks(stat.executeQuery(query));
        
        db.releaseStatement(stat);
        
        return tmp;
    }

    private List<Book> parseBooks(ResultSet rs) throws SQLException {
        List<Book> list = new ArrayList<>();

        Book currBook = null;
        List<Author> currAuthors = new ArrayList<>();
        List<Genre> currGenres = new ArrayList<>();

        Book book;
        while (rs.next()) {
            book = new Book(rs);
            if (currBook == null)
                currBook = book;

            if (!book.equals(currBook)) {
                currBook.setAuthors(new ArrayList<>(currAuthors));
                currGenres = currGenres.stream().sorted().collect(Collectors.toList());
                currBook.setGenres(new ArrayList<>(currGenres));
                list.add(currBook);
                currAuthors.clear();
                currGenres.clear();
                currBook = book;
            }
            if (!currAuthors.contains(new Author(rs)))
                currAuthors.add(new Author(rs));
            if (!currGenres.contains(new Genre(rs)))
                currGenres.add(new Genre(rs));
            
        }

        if (currBook != null) {
            currBook.setAuthors(new ArrayList<>(currAuthors));
            currBook.setGenres(new ArrayList<>(currGenres));
            list.add(currBook);
        }
                
        return list;
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Publisher> getPublishers() {
        return publishers;
    }

    public Genre getParent() {
        return parent;
    }
    
}
