package beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.util.converter.LocalDateStringConverter;

public class DepartmentManager {
    
    private String firstname,lastname;
    private LocalDate from, to;
    private static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static LocalDate now = LocalDate.of(9999, 1, 1);

    public DepartmentManager(String firstname, String lastname, LocalDate from, LocalDate to) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.from = from;
        this.to = to;
    }
    
    public DepartmentManager(ResultSet rs) throws SQLException {
        firstname = rs.getString("first_name");
        lastname = rs.getString("last_name");
        from = rs.getDate("from_date").toLocalDate();
        to = rs.getDate("to_date") == null ? null : rs.getDate("to_date").toLocalDate();
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    @Override
    public String toString() {
        return String.format("<p><b>%s, %s</b>: from <span class=\"red\">%s</span> to <span class=\"red\">%s</span></p>", lastname, firstname, DTF.format(from), to.compareTo(now)==0?"now":DTF.format(to));
    }
    
}
