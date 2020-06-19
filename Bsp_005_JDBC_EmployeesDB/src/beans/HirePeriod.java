package beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class HirePeriod {
    
    private LocalDate from, to;

    public HirePeriod(LocalDate from, LocalDate to) {
        this.from = from;
        this.to = to;
    }
    
    public HirePeriod(ResultSet rs) throws SQLException {
        from = rs.getDate("from_date").toLocalDate();
        to = rs.getDate("to_date").toLocalDate();
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

}
