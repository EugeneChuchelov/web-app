package servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/getemployees")
public class Servlet extends javax.servlet.http.HttpServlet {
    public Servlet(){}
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        ServletOutputStream out = resp.getOutputStream();
        out.println(req.getQueryString());
        System.out.println(req.getQueryString());
    }
}
