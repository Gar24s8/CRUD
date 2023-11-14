package servlets.employee;

import models.Employee;
import services.CRUDService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/employee/index")
public class IndexEmployeeServlet extends HttpServlet {
    static final String INDEX_PAGE = "/index.jsp";

    CRUDService service = new CRUDService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Employee> employees = service.findAllEmployees();
        req.setAttribute("employees", employees);

        getServletContext().getRequestDispatcher(INDEX_PAGE).forward(req, resp);
    }
}
