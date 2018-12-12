package impl;

import dao.EmployeesDAO;
import dao.EmployeesDAOplus;
import data.Employee;

import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class EmployeesDAOimpl implements EmployeesDAOplus {
    private DataSource dataSource;
    private String JNDIName;

    public EmployeesDAOimpl(String JNDIName) {
        InitialContext ctx = null;
        this.JNDIName = JNDIName;
        try {
            ctx = new InitialContext();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        try {
            dataSource = (DataSource) ctx.lookup(this.JNDIName);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int insert(Employee employee) {
        String query = "INSERT INTO `employees` (`id`, `first_name`, " +
                "`second_name`, `birth_date`, `hire_date`, `salary`, `jobtitles_id`, `departments_id`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(query);
            statement.setInt(1, employee.getId());
            statement.setString(2, employee.getFirstName());
            statement.setString(3, employee.getSecondName());
            statement.setDate(4, Date.valueOf(employee.getBirthDate()));
            statement.setDate(5, Date.valueOf(employee.getHireDate()));
            statement.setDouble(6, employee.getSalary());
            statement.setInt(7, findJobtitleId(employee.getJobtitle()));
            statement.setInt(8, employee.getDepartment().getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee.getId();
    }

    private int findJobtitleId(String jobtitle) {
        String query = "SELECT id FROM jobtitles WHERE name = ?";
        int id = -1;
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(query);
            statement.setString(1, jobtitle);
            ResultSet rs = statement.executeQuery();
            rs.next();
            id = rs.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public boolean delete(Employee employee) {
        String query = "DELETE FROM `employees` WHERE `employees`.`id` = ?";
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(query);
            statement.setInt(1, employee.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Employee findByID(int id) {
        String query = "SELECT employees.first_name, employees.second_name, employees.birth_date, " +
                "employees.hire_date, employees.salary, jobtitles.name, employees.departments_id " +
                "FROM employees JOIN jobtitles ON employees.jobtitles_id = jobtitles.id " +
                "WHERE employees.id = ?";
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            DepartmentDAOimpl departmentDAOimpl = new DepartmentDAOimpl(JNDIName);
            return new Employee(rs.getString("first_name"), rs.getString("second_name"),
                    rs.getDate("birth_date").toLocalDate(), rs.getDate("hire_date").toLocalDate(),
                    rs.getString("name"),
                    rs.getDouble("salary"),
                    departmentDAOimpl.findByID(rs.getInt("departments_id")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Set<Employee> findByDepID(int id){
        String query = "SELECT employees.first_name, employees.second_name, employees.birth_date, " +
                "employees.hire_date, employees.salary, jobtitles.name " +
                "FROM employees JOIN jobtitles ON employees.jobtitles_id = jobtitles.id " +
                "WHERE employees.departments_id = ?";
        Set<Employee> employeeSet = new HashSet<>();
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                employeeSet.add(new Employee(rs.getString("first_name"), rs.getString("second_name"),
                        rs.getDate("birth_date").toLocalDate(), rs.getDate("hire_date").toLocalDate(),
                        rs.getString("name"),
                        rs.getDouble("salary"),
                        null));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeSet;
    }

    @Override
    public boolean update(Employee employee) {
        String query = "UPDATE `employees` SET `first_name` = ?, " +
                "`second_name` = ?, " +
                "`birth_date` = ?, " +
                "`hire_date` = ?, " +
                "`salary` = ?, " +
                "`jobtitles_id` = ?, " +
                "`departments_id` = ? " +
                "WHERE `employees`.`id` = ?";

        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(query);
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getSecondName());
            statement.setDate(3, Date.valueOf(employee.getBirthDate()));
            statement.setDate(4, Date.valueOf(employee.getHireDate()));
            statement.setDouble(5, employee.getSalary());
            statement.setInt(6, findJobtitleId(employee.getJobtitle()));
            statement.setInt(7, employee.getDepartment().getId());
            statement.setInt(8, employee.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean saveOrUpdate(Employee employee) {
        String query = "SELECT id FROM `employees` WHERE id=?";
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(query);
            statement.setInt(1, employee.getId());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                update(employee);
            } else {
                insert(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Collection<Employee> findByName(String firstName, String secondName) {
        String query = "SELECT id FROM `employees` WHERE employees.first_name = ? AND employees.second_name = ?";
        LinkedList<Employee> found = new LinkedList<>();
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(query);
            statement.setString(1, firstName);
            statement.setString(2, secondName);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                found.add(findByID(rs.getInt("id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return found;
    }

    @Override
    public Collection<Employee> findByJobtitle(String jobtitle) {
        String query = "SELECT employees.id FROM `employees` " +
                "JOIN jobtitles ON employees.jobtitles_id = jobtitles.id " +
                "WHERE jobtitles.name = ?";
        LinkedList<Employee> found = new LinkedList<>();
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(query);
            statement.setString(1, jobtitle);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                found.add(findByID(rs.getInt("id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return found;
    }
}
