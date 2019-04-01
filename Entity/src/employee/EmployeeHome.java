package employee;

import javax.ejb.FinderException;
import java.rmi.RemoteException;

public interface EmployeeHome extends javax.ejb.EJBHome {
    Employee findByPrimaryKey(Integer primaryKey) throws RemoteException, FinderException;

    java.util.Collection findByName(String name) throws RemoteException, FinderException;

    java.util.Collection findAll() throws RemoteException, FinderException;
}
