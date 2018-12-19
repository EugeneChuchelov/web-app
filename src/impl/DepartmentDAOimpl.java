package impl;

import com.mysql.cj.jdbc.MysqlDataSource;
import dao.DepartmentsDAO;
import dao.EmployeesDAOplus;
import data.Department;
import data.Employee;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class DepartmentDAOimpl implements DepartmentsDAO {
    private static final String JNDI_NAME = "jdbc/Res";
    private MysqlDataSource dataSource;

    public DepartmentDAOimpl() {
        InitialContext ctx = null;
        try {
            ctx = new InitialContext();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        try {
            DataSource ds = (DataSource) ctx.lookup(JNDI_NAME);
            dataSource = ds.unwrap(MysqlDataSource.class);

        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            dataSource.setAutoReconnect(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int insert(Department department) {
        String query = "INSERT INTO `departments` (`id`, `name`, `Description`) VALUES (?, ?, ?)";
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, department.getId());
            statement.setString(2, department.getName());
            statement.setString(3, department.getDescription());
            statement.executeUpdate();
            con.close();
            EmployeesDAOimpl employeesDAOimpl = new EmployeesDAOimpl();
            for (Employee employee : department.getEmployees()) {
                employeesDAOimpl.saveOrUpdate(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return department.getId();
    }

    @Override
    public boolean delete(Department department) {
        String query = "DELETE FROM `departments` WHERE `departments`.`id` = ?";
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, department.getId());
            statement.executeUpdate();
            con.close();
            EmployeesDAOimpl employeesDAOimpl = new EmployeesDAOimpl();
            for (Employee employee : department.getEmployees()) {
                employeesDAOimpl.delete(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Department findByID(int id) {
        String query = "SELECT * FROM `departments` WHERE id = ?";
        LinkedList<Employee> employees = new LinkedList<>();
        Department department = null;
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            EmployeesDAOplus ds = new EmployeesDAOimpl();
            department = new Department(rs.getString("name"), rs.getString("description"),
                    ds.findByDepID(id));
            con.close();
            for (Employee employee : department.getEmployees()) {
                employee.setDepartment(department);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return department;
    }

    @Override
    public boolean update(Department department) {
        String query = "UPDATE `departments` SET `name` = ?, `Description` = ? WHERE `departments`.`id` = ?";
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, department.getName());
            statement.setString(2, department.getDescription());
            statement.setInt(3, department.getId());
            statement.executeUpdate();
            con.close();
            EmployeesDAOimpl employeesDAOimpl = new EmployeesDAOimpl();
            for (Employee employee : department.getEmployees()) {
                employeesDAOimpl.saveOrUpdate(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean saveOrUpdate(Department department) {
        String query = "SELECT id FROM `departments` WHERE id=?";
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, department.getId());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                update(department);
            } else {
                insert(department);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Collection<Department> findByName(String name) {
        String query = "SELECT id FROM `departments` WHERE departments.name = ?";
        LinkedList<Department> found = new LinkedList<>();
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                found.add(findByID(rs.getInt("id")));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return found;
    }
}
