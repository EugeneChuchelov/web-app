package impl;

import dao.DepartmentsDAO;
import dao.EmployeesDAO;
import data.Employee;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;

public class EmployeesDAOimpl implements EmployeesDAO {
    private static final String JNDI_NAME = "jdbc/Orcl";
    private DataSource dataSource;

    public EmployeesDAOimpl() {
        InitialContext ctx = null;
        try {
            ctx = new InitialContext();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        try {
            dataSource = (DataSource) ctx.lookup(JNDI_NAME);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
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
        DepartmentsDAO departmentDAOimpl = new DepartmentDAOimpl();
        return new Employee(rs.getInt("EMPNO"), rs.getString("ENAME"),
                rs.getString("JOB"), rs.getInt("MGR"),
                rs.getDate("HIREDATE").toLocalDate(), rs.getDouble("SAL"),
                rs.getDouble("COMM"),
                departmentDAOimpl.findByID(rs.getInt("DEPTNO")));
    }
}
