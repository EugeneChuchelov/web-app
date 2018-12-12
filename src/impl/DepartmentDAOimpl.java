package impl;

import dao.DepartmentsDAO;
import data.Department;
import data.Employee;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class DepartmentDAOimpl implements DepartmentsDAO {
    private DataSource dataSource;
    private String JNDIName;
    public DepartmentDAOimpl(String JNDIName){
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
    public int insert(Department department) {
        String query = "INSERT INTO `departments` (`id`, `name`, `Description`) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(query);
            statement.setInt(1, department.getId());
            statement.setString(2, department.getName());
            statement.setString(3, department.getDescription());
            statement.executeUpdate();
            EmployeesDAOimpl employeesDAOimpl = new EmployeesDAOimpl(JNDIName);
            for(Employee employee : department.getEmployees()){
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
            PreparedStatement statement = dataSource.getConnection().prepareStatement(query);
            statement.setInt(1, department.getId());
            statement.executeUpdate();
            EmployeesDAOimpl employeesDAOimpl = new EmployeesDAOimpl(JNDIName);
            for(Employee employee : department.getEmployees()){
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
        Department department;
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            department = new Department(rs.getString("name"), rs.getString("description"),
                                        new EmployeesDAOimpl(JNDIName).findByDepID(id));
            for (Employee employee : department.getEmployees()){
                employee.setDepartment(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Department department) {
        String query = "UPDATE `departments` SET `name` = ?, `Description` = ? WHERE `departments`.`id` = ?";
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(query);
            statement.setString(1, department.getName());
            statement.setString(2, department.getDescription());
            statement.setInt(3, department.getId());
            statement.executeUpdate();
            EmployeesDAOimpl employeesDAOimpl = new EmployeesDAOimpl(JNDIName);
            for(Employee employee : department.getEmployees()){
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
            PreparedStatement statement = dataSource.getConnection().prepareStatement(query);
            statement.setInt(1, department.getId());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                update(department);
            } else {
                insert(department);
            }
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
            PreparedStatement statement = dataSource.getConnection().prepareStatement(query);
            statement.setString(1, name);
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
