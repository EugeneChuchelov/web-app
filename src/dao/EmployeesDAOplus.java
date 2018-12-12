package dao;

import data.Employee;

import java.util.Set;

public interface EmployeesDAOplus extends EmployeesDAO{
    Set<Employee> findByDepID(int id);
}
