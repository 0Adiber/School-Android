package bl;

import beans.Employee;
import java.util.Comparator;

public class SortByName implements Comparator<Employee>{

    public int compare(Employee a, Employee b) {
        if(a.getLastname().toUpperCase().compareTo(b.getLastname().toUpperCase()) == 0) {
            return a.getFirstname().toUpperCase().compareTo(b.getFirstname().toUpperCase());
        } else {
            return a.getLastname().toUpperCase().compareTo(b.getLastname().toUpperCase());
        }
    }
    
}
