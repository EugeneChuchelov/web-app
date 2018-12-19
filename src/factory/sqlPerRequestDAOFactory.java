package factory;

import dao.DepartmentsDAO;
import dao.EmployeesDAO;
import dao.EmployeesDAOplus;
import impl.DepartmentDAOimpl;
import impl.EmployeesDAOimpl;

public class sqlPerRequestDAOFactory extends DAOFactory {
    protected sqlPerRequestDAOFactory() {
        super();
    }

    @Override
    public EmployeesDAO getEmployeesDAO() {
        return new EmployeesDAOimpl();
    }

    @Override
    public DepartmentsDAO getDepartmentsDAO() {
        return new DepartmentDAOimpl();
    }

    @Override
    public EmployeesDAOplus getEmployeesDAOplus(){
        return new EmployeesDAOimpl();
    }
}
