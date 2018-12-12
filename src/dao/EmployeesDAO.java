package dao;

import data.Employee;

import java.util.Collection;

public interface EmployeesDAO {
    int insert(Employee employee);

    boolean delete(Employee employee);

    Employee findByID(int id);

    boolean update(Employee employee);

    boolean saveOrUpdate(Employee employee);

    Collection<Employee> findByName(String firstName,
                                    String secondName);

    Collection<Employee> findByJobtitle(String jobtitle);

}
