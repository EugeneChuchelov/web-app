package bean;

import data.Employee;

import javax.ejb.Remote;
import java.util.Collection;

@Remote
public interface Empl {
    Collection<Employee> findByID(int id);

    Collection<Employee> findByName(String name);

    Collection<Employee> findAll();
}
