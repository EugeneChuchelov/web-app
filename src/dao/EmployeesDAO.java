package dao;

import data.Employee;

import java.util.Collection;

public interface EmployeesDAO {
    Collection<Employee> findByID(int id);

    Collection<Employee> findByName(String name);

    Collection<Employee> findAll();
}
