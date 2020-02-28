package beans;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Company {
    //company,no_employees,founded,turnover,country
    //Photobug,29,11/16/1982,51507.19,Philippines
          
    private String name;
    private int noEmplayees;
    private LocalDate founded;
    private double turnover;
    private String country;
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public Company(String name, int noEmplayees, LocalDate founded, double turnover, String country) {
        this.name = name;
        this.noEmplayees = noEmplayees;
        this.founded = founded;
        this.turnover = turnover;
        this.country = country;
    }
    
    public Company(String line) {
        String[] tokens = line.split(",");
        name = tokens[0];
        noEmplayees = Integer.parseInt(tokens[1]);
        founded = LocalDate.parse(tokens[2], DTF);
        turnover = Double.parseDouble(tokens[3]);
        country = tokens[4];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNoEmplayees() {
        return noEmplayees;
    }

    public void setNoEmplayees(int noEmplayees) {
        this.noEmplayees = noEmplayees;
    }

    public LocalDate getFounded() {
        return founded;
    }

    public void setFounded(LocalDate founded) {
        this.founded = founded;
    }

    public double getTurnover() {
        return turnover;
    }

    public void setTurnover(double turnover) {
        this.turnover = turnover;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Company{" + "name=" + name + ", noEmplayees=" + noEmplayees + ", founded=" + founded + ", turnover=" + turnover + ", country=" + country + '}';
    }
    
    
    
}
