package beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Book {
    
    private String title;
    private String isbnNr;
    private List<Author> authors;
    private Publisher publisher;
    private int pages, bookId;
    private float rating;
    private List<Genre> genres;
    private LocalDate publishedDate;
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public Book(String title, String isbnNr, List<Author> authors, Publisher publisher, int pages, int bookId, float rating, List<Genre> genres, LocalDate publishedDate) {
        this.title = title;
        this.isbnNr = isbnNr;
        this.authors = authors;
        this.publisher = publisher;
        this.pages = pages;
        this.bookId = bookId;
        this.rating = rating;
        this.genres = genres;
        this.publishedDate = publishedDate;
    }
    
    public Book(ResultSet rs) throws SQLException {
        title = rs.getString("title");
        bookId = rs.getInt("book_id");

        // check if returned values are null
        isbnNr = rs.getString("isbn");
        if (isbnNr == null)
            isbnNr = "";

        pages = rs.getInt("total_pages");

        rating = rs.getFloat("rating");

        java.sql.Date date = rs.getDate("published_date");
        if (date != null)
            publishedDate = date.toLocalDate();

        // carry the resultset on because the names are the same
        publisher = new Publisher(rs);
    }

    @Override
    public String toString() {
        return title;
    }    
    
    public String toHTML() {
        String html = "<html><body>";
        html += String.format("<h1>%s</h1>", title);
        
        for(Author a : authors)
            html+=String.format("<h2>%s</h2>", a.toString());
        
        html+="<hr/>";
        
        html += "<ul>";

        if (!isbnNr.isEmpty())
            html += addListItem("<span class=\"fancy\">ISBN: </span>", isbnNr);
        html += addListItem("<span class=\"fancy\">Genres: </span>", genres.toString().replace("[", "").replace("]",""));
        if (pages != 0)
            html += addListItem("<span class=\"fancy\">Seitenzahl: </span>", pages + "");
        if (rating != 0)
            html += addListItem("<span class=\"fancy\">Bewertung: </span>", String.format("%.2f", rating));
        if (publishedDate != null)
            html += addListItem("<span class=\"fancy\">Erscheinungsdatum: </span>", DTF.format(publishedDate));
        html += addListItem("<span class=\"fancy\">Verlag: </span>", publisher+"");
        
        html += "</ul></body></html>";
        return html;
    }

    private String addListItem(String name, String val) {
        return String.format("<li><b>%s</b>%s</li>", name, val);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbnNr() {
        return isbnNr;
    }

    public void setIsbnNr(String isbnNr) {
        this.isbnNr = isbnNr;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Book other = (Book) obj;
        if (this.bookId != other.bookId) {
            return false;
        }
        return true;
    }
    
    
    
}
