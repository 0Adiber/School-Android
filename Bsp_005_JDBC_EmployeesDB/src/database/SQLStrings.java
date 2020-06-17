package database;

public class SQLStrings {
    
    public static String GETALLDEPT = "SELECT * FROM departments";
        
    public static String GETALLEMPBY = "SELECT * FROM employees e "
                                     + "INNER JOIN dept_emp de ON de.emp_no=e.emp_no "
                                     + "INNER JOIN departments d ON de.dept_no=d.dept_no "
                                     + "WHERE d.dept_no=(department) AND (e.gender=(gender1) OR e.gender=(gender2)) AND birth_date <= (birth)";
    
    public static String GETDEPTMAN = "SELECT * FROM dept_manager dm "
                                    + "INNER JOIN departments d ON dm.dept_no=d.dept_no "
                                    + "INNER JOIN employees e ON dm.emp_no=e.emp_no "
                                    + "WHERE dm.dept_no=(department)";
    
    public static String GETSAlFOREMP = "SELECT * FROM salaries WHERE emp_no=(employee)";

}
