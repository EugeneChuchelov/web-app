package employee;

import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.RemoveException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Vector;

public class EmployeeBean implements EntityBean {
    private Integer id;
    private String name;
    private String jobtitle;
    private int mgr;
    private LocalDate hireDate;
    private double salary;
    private double comm;
    private String departmentName;

    private EntityContext entityContext;
    private DataSource dataSource;


    @Override
    public void setEntityContext(EntityContext entityContext) throws EJBException {
        this.entityContext = entityContext;
        try {
            dataSource = (DataSource) new InitialContext().lookup("jdbc/orcl");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unsetEntityContext() throws EJBException {
        this.entityContext = null;
    }

    @Override
    public void ejbRemove() throws RemoveException, EJBException {

    }

    @Override
    public void ejbActivate() throws EJBException {

    }

    @Override
    public void ejbPassivate() throws EJBException {

    }

    @Override
    public void ejbLoad() throws EJBException {
        id = (Integer) entityContext.getPrimaryKey();
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "SELECT * " +
                "FROM emp NATURAL JOIN DEPT " +
                "WHERE empno = ?";
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            name = rs.getString("NAME");
            jobtitle = rs.getString("JOBTITLE");
            mgr = rs.getInt("MGR");
            hireDate = rs.getDate("HIREDATE").toLocalDate();
            salary = rs.getDouble("SAL");
            comm = rs.getDouble("COMM");
            departmentName = rs.getString("DNAME");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ejbStore() throws EJBException {

    }

    public Integer ejbFindByPrimaryKey(Integer id) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "SELECT EMPNO " +
                "FROM emp " +
                "WHERE empno = ?";
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getInt("EMPNO");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public Collection ejbFindByName(String name) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "SELECT EMPNO " +
                "FROM emp " +
                "WHERE ENAME = ?";
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            Vector keys = new Vector();
            while (rs.next()) {
                Integer lastName = rs.getInt("EMPNO");
                keys.addElement(lastName);
            }
            return keys;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Collection ejbFindAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "SELECT EMPNO " +
                "FROM emp";
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            Vector keys = new Vector();
            while (rs.next()) {
                Integer lastName = rs.getInt("EMPNO");
                keys.addElement(lastName);
            }
            return keys;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public String getDepartmentName() {
        return departmentName;
    }
}
