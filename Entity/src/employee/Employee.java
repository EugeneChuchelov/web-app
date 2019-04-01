package employee;

import java.rmi.RemoteException;
import java.time.LocalDate;

public interface Employee extends javax.ejb.EJBObject {
    int getId() throws RemoteException;

    String getName() throws RemoteException;

    String getJobtitle() throws RemoteException;

    LocalDate getHireDate() throws RemoteException;

    String getDepartmentName() throws RemoteException;
}
