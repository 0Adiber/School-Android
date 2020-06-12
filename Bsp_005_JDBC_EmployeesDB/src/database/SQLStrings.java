package database;

public class SQLStrings {
    
    public static String GETALLDEPT = "SELECT * FROM departments";
    
    public static String GETALLEMP = "SELECT * FROM employees";
    
    public static String GETALLEMPBY = "SELECT * FROM employees e "
                                     + "INNER JOIN dept_emp de ON de.emp_no=e.emp_no "
                                     + "INNER JOIN departments d ON de.dept_no=d.dept_no "
                                     + "WHERE d.dept_no=(department) AND (e.gender=(gender1) OR e.gender=(gender2)) AND birth_date <= (birth)";

}
