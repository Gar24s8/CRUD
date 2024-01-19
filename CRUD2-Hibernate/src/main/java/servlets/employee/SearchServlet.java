package servlets.employee;

import models.Employee;
import services.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/employee/search")
public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(SearchServlet.class.getName());

    private EmployeeService employeeService = new EmployeeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Trying to find employees");
        String name = req.getParameter("name");

        List<Employee> searchResult = employeeService.findEmployeeByName(name);

        req.setAttribute("searchResult", searchResult);
        getServletContext().getRequestDispatcher("/employee/searchResult.jsp").forward(req, resp);
    }
}
