package impl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {

    public static void main(String[] args) throws NamingException, SQLException {
        EmployeesDAOimpl d = new EmployeesDAOimpl("jdbc/Res");
        //System.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
        //Context q = new InitialContext();
        //q.lookup("jdbc/Res");
    }
}
