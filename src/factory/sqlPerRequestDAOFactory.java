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
        return new EmployeesDAOimpl("jdbc/Res");
    }

    @Override
    public DepartmentsDAO getDepartmentsDAO() {
        return new DepartmentDAOimpl("jdbc/Res");
    }

    @Override
    public EmployeesDAOplus getEmployeesDAOplus(){
        return new EmployeesDAOimpl("jdbc/Res");
    }
}
