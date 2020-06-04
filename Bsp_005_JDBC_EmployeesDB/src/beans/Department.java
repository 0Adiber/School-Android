package beans;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Department {
    
    private String deptno;
    private String deptname;

    public Department(String deptno, String deptname) {
        this.deptno = deptno;
        this.deptname = deptname;
    }
    
    public Department(ResultSet rs) throws SQLException {
        deptno = rs.getString("dept_no");
        deptname = rs.getString("dept_name");
    }

    public String getDeptno() {
        return deptno;
    }

    public String getDeptname() {
        return deptname;
    }

    @Override
    public String toString() {
        return deptname;
    }
    
    

}
