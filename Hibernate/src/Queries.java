import entity.Address;
import entity.Department;
import entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Queries {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new
                Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        List<Department> depts = session.createQuery("SELECT d FROM Department d").getResultList();

        for (Department department : depts){
            System.out.println(department.toString());
        }

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Employee> cr = cb.createQuery(Employee.class);
        Root<Employee> root = cr.from(Employee.class);
        cr.select(root).where(cb.between(root.get("salary"), 10000, 20000));
        cr.orderBy(cb.asc(root.get("salary")));
        List<Employee> employees = session.createQuery(cr).getResultList();

        for (Employee employee : employees ){
            System.out.println(employee.toString());
        }

        List<Address> addresses = session.createNamedQuery("selectAll").getResultList();

        for (Address address : addresses){
            System.out.println(address.toString());
        }
    }
}
