package bl;

import beans.Employee;
import java.util.Comparator;

public class SortByHire implements Comparator<Employee>{

    @Override
    public int compare(Employee a, Employee b) {
        return a.getHiredate().compareTo(b.getHiredate());
    }
    
    

}
