package Servlets;

import models.Employee;
import services.CRUDService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CRUDService service = new CRUDService();

        List<Employee> employees = service.findAllEmployees();
        req.setAttribute("employees", employees);

        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
