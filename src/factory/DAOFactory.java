package factory;

import dao.DepartmentsDAO;
import dao.EmployeesDAO;
import dao.EmployeesDAOplus;

public abstract class DAOFactory {
    protected DAOFactory(){}

    public static DAOFactory getDAOFactory(){
        return new sqlPerRequestDAOFactory();
    }

    public abstract EmployeesDAO getEmployeesDAO();
    public abstract DepartmentsDAO getDepartmentsDAO();
    public abstract EmployeesDAOplus getEmployeesDAOplus();
}
