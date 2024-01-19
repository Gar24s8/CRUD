package servlets.office;

import models.Employee;
import models.Office;
import services.EmployeeService;
import services.OfficeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@WebServlet("/office/ListOffices")
public class OfficeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(OfficeServlet.class.getName());
    public static final String ERROR_PAGE = "/office/error.jsp";

    OfficeService officeService = new OfficeService();
    EmployeeService employeeService = new EmployeeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("App started");

        List<Office> offices = officeService.getAll();
        List<Employee> employees = employeeService.getAll().stream()
                .sorted(Comparator.comparing(Employee::getName, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());

        req.setAttribute("offices", offices);
        req.setAttribute("employees", employees);
        LOG.info("Redirecting to start page");
        getServletContext().getRequestDispatcher("/office/listOffices.jsp").forward(req, resp);
    }
}
