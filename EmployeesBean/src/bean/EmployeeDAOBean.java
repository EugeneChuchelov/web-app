package bean;

import entity.Department;
import entity.Employee;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;

@Stateless
public class EmployeeDAOBean implements EmployeeDAO {
    @Resource(lookup = "jdbc/Orcl")
    private DataSource dataSource;
    @Override
    public Collection<Employee> findByID(int id) {
        String query = "SELECT * " +
                "FROM emp " +
                "WHERE empno = ?";
        LinkedList<Employee> found = new LinkedList<>();
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            found.add(createEmp(rs));
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return found;
    }

    @Override
    public Collection<Employee> findByName(String name) {
        String query = "SELECT * FROM emp WHERE ename LIKE ?";
        LinkedList<Employee> found = new LinkedList<>();
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                found.add(createEmp(rs));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return found;
    }

    @Override
    public Collection<Employee> findAll() {
        String query = "SELECT * FROM emp";
        ResultSet rs;
        LinkedList<Employee> found = new LinkedList<>();
        try {
            Connection con = dataSource.getConnection();
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                found.add(createEmp(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return found;
    }

    private Employee createEmp(ResultSet rs) throws SQLException {
        return new Employee(rs.getInt("EMPNO"), rs.getString("ENAME"),
                rs.getString("JOB"), rs.getInt("MGR"),
                rs.getDate("HIREDATE").toLocalDate(), rs.getDouble("SAL"),
                rs.getDouble("COMM"),
                findDeptByID(rs.getInt("DEPTNO")));
    }

    private Department findDeptByID(int id) {
        String query = "SELECT * FROM dept WHERE deptno = ?";
        Department department = null;
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            department = new Department(rs.getInt("DEPTNO"),
                    rs.getString("DNAME"), rs.getString("LOC"));
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return department;
    }
}
