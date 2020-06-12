package bl;

import beans.Employee;
import java.util.Comparator;

public class SortByBirth implements Comparator<Employee>{

    @Override
    public int compare(Employee a, Employee b) {
        return a.getBirthdate().compareTo(b.getBirthdate());
    }
    
    

}
