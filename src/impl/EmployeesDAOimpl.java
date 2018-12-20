package impl;

import com.mysql.cj.jdbc.MysqlDataSource;
import dao.DepartmentsDAO;
import dao.EmployeesDAOplus;
import data.Employee;

import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class EmployeesDAOimpl implements EmployeesDAOplus {
    private static final String JNDI_NAME = "jdbc/Res";
    @Resource(mappedName = JNDI_NAME)
    private MysqlDataSource dataSource;

    public EmployeesDAOimpl() {
        /*
        InitialContext ctx = null;
        try {
            ctx = new InitialContext();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        try {
            //DataSource ds = (DataSource) ctx.lookup(JNDI_NAME);
            //dataSource = ds.unwrap(MysqlDataSource.class);

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
        */
    }

    @Override
    public int insert(Employee employee) {
        String query = "INSERT INTO `employees` (`id`, `first_name`, " +
                "`second_name`, `birth_date`, `hire_date`, `salary`, `jobtitles_id`, `departments_id`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, employee.getId());
            statement.setString(2, employee.getFirstName());
            statement.setString(3, employee.getSecondName());
            statement.setDate(4, Date.valueOf(employee.getBirthDate()));
            statement.setDate(5, Date.valueOf(employee.getHireDate()));
            statement.setDouble(6, employee.getSalary());
            statement.setInt(7, findJobtitleId(employee.getJobtitle()));
            statement.setInt(8, employee.getDepartment().getId());

            statement.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee.getId();
    }

    private int findJobtitleId(String jobtitle) {
        String query = "SELECT id FROM jobtitles WHERE name = ?";
        int id = -1;
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, jobtitle);
            ResultSet rs = statement.executeQuery();
            rs.next();
            id = rs.getInt("id");
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public boolean delete(Employee employee) {
        String query = "DELETE FROM `employees` WHERE `employees`.`id` = ?";
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, employee.getId());
            statement.executeUpdate();
            con.close();
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
            Connection con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            DepartmentsDAO departmentDAOimpl = new DepartmentDAOimpl();
            Employee employee = new Employee(rs.getString("first_name"), rs.getString("second_name"),
                    rs.getDate("birth_date").toLocalDate(), rs.getDate("hire_date").toLocalDate(),
                    rs.getString("name"),
                    rs.getDouble("salary"),
                    departmentDAOimpl.findByID(rs.getInt("departments_id")));
            con.close();
            return employee;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Set<Employee> findByDepID(int id) {
        String query = "SELECT employees.first_name, employees.second_name, employees.birth_date, " +
                "employees.hire_date, employees.salary, jobtitles.name " +
                "FROM employees JOIN jobtitles ON employees.jobtitles_id = jobtitles.id " +
                "WHERE employees.departments_id = ?";
        Set<Employee> employeeSet = new HashSet<>();
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                employeeSet.add(new Employee(rs.getString("first_name"), rs.getString("second_name"),
                        rs.getDate("birth_date").toLocalDate(), rs.getDate("hire_date").toLocalDate(),
                        rs.getString("name"),
                        rs.getDouble("salary"),
                        null));
            }
            con.close();
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
            Connection con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getSecondName());
            statement.setDate(3, Date.valueOf(employee.getBirthDate()));
            statement.setDate(4, Date.valueOf(employee.getHireDate()));
            statement.setDouble(5, employee.getSalary());
            statement.setInt(6, findJobtitleId(employee.getJobtitle()));
            statement.setInt(7, employee.getDepartment().getId());
            statement.setInt(8, employee.getId());
            statement.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean saveOrUpdate(Employee employee) {
        String query = "SELECT * FROM `employees` WHERE id=?";
        try {
            Connection con = dataSource.getConnection();
            con.createStatement()
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, employee.getId());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                update(employee);
            } else {
                insert(employee);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Collection<Employee> findByName(String firstName, String secondName) {
        String query = "SELECT id FROM `employees` WHERE employees.first_name LIKE ? AND employees.second_name LIKE ?";
        LinkedList<Employee> found = new LinkedList<>();
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, firstName);
            statement.setString(2, secondName);
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

    @Override
    public Collection<Employee> findByJobtitle(String jobtitle) {
        String query = "SELECT employees.id FROM `employees` " +
                "JOIN jobtitles ON employees.jobtitles_id = jobtitles.id " +
                "WHERE jobtitles.name = ?";
        LinkedList<Employee> found = new LinkedList<>();
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, jobtitle);
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

    @Override
    public Collection<Employee> findByFirstName(String firstName) {
        return findByName(firstName, "%%");
    }

    @Override
    public Collection<Employee> findBySecondName(String secondName) {
        return findByName("%%", secondName);
    }

    @Override
    public Collection<Employee> findByHireDate(LocalDate min, LocalDate max) {
        String query = "SELECT id FROM `employees` WHERE employees.hire_date >= ? AND employees.hire_date <= ?";
        LinkedList<Employee> found = new LinkedList<>();
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement(query);
            statement.setDate(1, Date.valueOf(min));
            statement.setDate(2, Date.valueOf(max));
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

    @Override
    public Collection<Employee> findByKey(String key, String value) {
        switch (key) {
            case "firstName":
                return findByFirstName(value);
            case "secondName":
                return findBySecondName(value);
            case "hireDateMinimum":
                return findByHireDate(LocalDate.parse(value), LocalDate.parse("9999-12-31"));
            case "hireDateMaximum":
                return findByHireDate(LocalDate.parse("0000-01-01"), LocalDate.parse(value));
            case "jobtitle":
                return findByJobtitle(value);
            default:
                throw new IllegalArgumentException("Unknown key");
        }
    }

    @Override
    public Collection<Employee> getAll() {
        String query = "SELECT * FROM employees";
        ResultSet rs;
        LinkedList<Employee> found = new LinkedList<>();
        try {
            Connection con = dataSource.getConnection();
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                found.add(new Employee(rs.getString("first_name"), rs.getString("second_name"),
                        rs.getDate("birth_date").toLocalDate(), rs.getDate("hire_date").toLocalDate(),
                        rs.getString("name"),
                        rs.getDouble("salary"),
                        null));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return found;
    }
}
