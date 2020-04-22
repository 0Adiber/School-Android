package at.adiber.beans;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Employee {
    
    private int pers_nr;
    private String name;
    private String vorname;
    private LocalDate geb_datum;
    private double gehalt;
    private int abt_nr;
    private String geschlecht;

    public Employee(int pers_nr, String name, String vorname, LocalDate geb_datum, double gehalt, int abt_nr, String geschlecht) {
        this.pers_nr = pers_nr;
        this.name = name;
        this.vorname = vorname;
        this.geb_datum = geb_datum;
        this.gehalt = gehalt;
        this.abt_nr = abt_nr;
        this.geschlecht = geschlecht;
    }

    //when tretrieving from db
    public Employee(ResultSet rs) throws SQLException {
        this.pers_nr = rs.getInt("pers_nr");
        this.name = rs.getString("name");
        this.vorname = rs.getString("vorname");
        this.geb_datum = rs.getDate("geb_datum").toLocalDate();
        this.gehalt = rs.getBigDecimal("gehalt").doubleValue();
        this.abt_nr = rs.getInt("abt_nr");
        this.geschlecht = rs.getString("geschlecht");
    }
    
    public Employee(Object[] data) {
        this.pers_nr = (int)data[0];
        this.name = (String)data[1];
        this.vorname = (String)data[2];
        this.geb_datum = (LocalDate)data[3];
        this.gehalt = (double)data[4];
        this.abt_nr = (int)data[5];
        this.geschlecht = (String)data[6];
    }
    
    public int getPers_nr() {
        return pers_nr;
    }

    public void setPers_nr(int pers_nr) {
        this.pers_nr = pers_nr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public LocalDate getGeb_datum() {
        return geb_datum;
    }

    public void setGeb_datum(LocalDate geb_datum) {
        this.geb_datum = geb_datum;
    }

    public double getGehalt() {
        return gehalt;
    }

    public void setGehalt(double gehalt) {
        this.gehalt = gehalt;
    }

    public int getAbt_nr() {
        return abt_nr;
    }

    public void setAbt_nr(int abt_nr) {
        this.abt_nr = abt_nr;
    }

    public String getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }
    
    public Object[] convertToTableData() {
        Object[] data = new Object[7];
        data[0] = pers_nr;
        data[1] = name;
        data[2] = vorname;
        data[3] = geb_datum;
        data[4] = gehalt;
        data[5] = abt_nr;
        data[6] = geschlecht;
        return data;
    }
    
}
