package bl;

import beans.Employee;
import java.util.Comparator;

public class SortByGender implements Comparator<Employee>{

    @Override
    public int compare(Employee a, Employee b) {
        return a.getGender().compareTo(b.getGender());
    }  

}
