package dao;

import data.Employee;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

public interface EmployeesDAOplus extends EmployeesDAO {
    Set<Employee> findByDepID(int id);

    Collection<Employee> findByFirstName(String firstName);

    Collection<Employee> findBySecondName(String secondName);

    Collection<Employee> findByHireDate(LocalDate min, LocalDate max);

    Collection<Employee> findByKey(String key, String value);

    Collection<Employee> getAll();
}
