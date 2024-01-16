package servlets.employee;

import models.Employee;
import services.EmployeeService;

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

@WebServlet("/employee/listEmployees")
public class EmployeeInOfficeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(EmployeeInOfficeServlet.class.getName());

    EmployeeService employeeService = new EmployeeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Trying to get all employees from selected office");

        int officeID = Integer.parseInt(req.getParameter("officeID"));
        List<Employee> employees = employeeService.getEmployeeByOfficeId(officeID)
                .stream().sorted(Comparator.comparing(Employee::getPosition, String.CASE_INSENSITIVE_ORDER)
                        .thenComparing(Employee::getName, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());

        req.setAttribute("employees", employees);
        getServletContext().getRequestDispatcher("/employee/listEmployees.jsp").forward(req, resp);
       // req.getRequestDispatcher("/employee/listEmployees.jsp").forward(req, resp);

    }
}
