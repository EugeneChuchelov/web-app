package service;

import entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.LinkedList;

public class EmployeeService {
    private EntityManager em;

    public EmployeeService(EntityManager em) {
        this.em = em;
    }

    public Employee findById(int id){
        return em.find(Employee.class, id);
    }

    public Collection<Employee> findByName(String name){
        String q = "SELECT e FROM Employee e WHERE e.name LIKE ?1";
        return (Collection<Employee>) em.createQuery(q)
        .setParameter(1, "%" + name + "%")
                .getResultList();
    }

    public Collection<Employee> findAll(){
        String q = "SELECT e FROM Employee e";
        return (Collection<Employee>) em.createQuery(q)
                .getResultList();
    }
}
