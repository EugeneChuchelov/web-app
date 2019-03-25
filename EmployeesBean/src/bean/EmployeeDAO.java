package bean;

import entity.Employee;

import javax.ejb.Remote;
import java.util.Collection;

@Remote
public interface EmployeeDAO {
    Collection<Employee> findByID(int id);

    Collection<Employee> findByName(String name);

    Collection<Employee> findAll();
}
