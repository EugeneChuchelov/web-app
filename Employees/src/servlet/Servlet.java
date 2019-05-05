package servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Employee;
import service.EmployeeService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.LinkedList;

public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().create();
        Collection<Employee> employees = new LinkedList<Employee>();
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("EmployeeService");
        EntityManager em = emf.createEntityManager();
        EmployeeService service = new EmployeeService(em);

        if(request.getParameter("id") != null){
            int id = Integer.parseInt(request.getParameter("id"));
            employees.add(service.findById(id));
        } else if(request.getParameter("name") != null){
            String name = request.getParameter("name");
            employees = service.findByName(name.toUpperCase());
        } else {
            employees = service.findAll();
        }

        out.print(gson.toJson(employees));

        out.close();
    }
}
