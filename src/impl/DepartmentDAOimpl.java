package impl;

import dao.DepartmentsDAO;
import data.Department;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentDAOimpl implements DepartmentsDAO {
    private static final String JNDI_NAME = "jdbc/Orcl";
    private DataSource dataSource;

    public DepartmentDAOimpl() {
        InitialContext ctx = null;
        try {
            ctx = new InitialContext();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        try {
            dataSource = (DataSource) ctx.lookup(JNDI_NAME);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Department findByID(int id) {
        String query = "SELECT * FROM dept WHERE deptno = ?";
        Department department = null;
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            department = new Department(rs.getInt("DEPTNO"),
                    rs.getString("DNAME"), rs.getString("LOC"));
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return department;
    }
}
