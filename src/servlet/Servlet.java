package servlet;

import dao.EmployeesDAOplus;
import data.Employee;
import factory.DAOFactory;
import factory.sqlPerRequestDAOFactory;
import json.WriteToJson;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

@WebServlet("/getemployees")
public class Servlet extends javax.servlet.http.HttpServlet {
    private static DAOFactory factory = sqlPerRequestDAOFactory.getDAOFactory();

    public Servlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        EmployeesDAOplus ds = factory.getEmployeesDAOplus();
        LinkedList<Employee> found = new LinkedList<>();
        Map<String, String[]> map = req.getParameterMap();
        String[] keys = new String[]{"firstName", "secondName", "hireDateMinimum", "hireDateMaximum", "jobtitle"};
        if (map.isEmpty()) {
            found.addAll(ds.getAll());
        } else if (map.containsKey("id")) {
            found.add(ds.findByID(Integer.parseInt(map.get("id")[0])));
        } else {
            for (String key : keys) {
                if (map.containsKey(key)) {
                    if (found.isEmpty()) {
                        found.addAll(ds.findByKey(key, map.get(key)[0]));
                    } else {
                        found.retainAll(ds.findByKey(key, map.get(key)[0]));
                    }
                }
            }
        }
        out.println(WriteToJson.write(found));
    }
}
