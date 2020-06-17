package beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class GehaltHistory {
    
    private int gehalt;
    private LocalDate from,to;
    private static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static LocalDate now = LocalDate.of(9999, 1, 1);

    public GehaltHistory(int gehalt, LocalDate from, LocalDate to) {
        this.gehalt = gehalt;
        this.from = from;
        this.to = to;
    }
    
    public GehaltHistory(ResultSet rs) throws SQLException {
        this.gehalt = rs.getInt("salary");
        this.from = rs.getDate("from_date").toLocalDate();
        this.to = rs.getDate("to_date").toLocalDate();
    }

    public int getGehalt() {
        return gehalt;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }
    
    @Override
    public String toString() {
        return String.format("<p>From <span class=\"green\">%s</span> To <span class=\"gray\">%s</span> : <b>%s</b></p>", DTF.format(from), to.compareTo(now)==0?"now":DTF.format(to), NumberFormat.getCurrencyInstance(Locale.GERMANY).format(gehalt));
    }

}
