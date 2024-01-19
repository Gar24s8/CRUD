package servlets.employee;

import models.Employee;
import models.Office;
import org.apache.commons.lang3.StringUtils;
import services.EmployeeService;
import services.OfficeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.String.format;
import static servlets.office.OfficeServlet.ERROR_PAGE;

@WebServlet("/employee/create")
public class CreateEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(CreateEmployeeServlet.class.getName());
    static final String CREATE_EMPLOYEE_PAGE = "/employee/create.jsp";

    EmployeeService employeeService = new EmployeeService();
    OfficeService officeService = new OfficeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Office> offices = officeService.getAll();
        req.setAttribute("offices", offices);
        getServletContext().getRequestDispatcher(CREATE_EMPLOYEE_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        LOG.info("Trying to create employee");

        try {
            String name = req.getParameter("name");
            String position = req.getParameter("position");
            int salary = Integer.parseInt(req.getParameter("salary"));
            String officeName = req.getParameter("officeName");

            if (StringUtils.isEmpty(name) || StringUtils.isEmpty(position)) {
                resp.getWriter().print("All fields must be filled in");
            } else {
                Employee employee = createEmployee(name, position, salary);
                Optional<Office> optionalOffice = getOfficeByName(officeName);

                if (optionalOffice.isPresent()) {
                    Office office = optionalOffice.get();
                    employee.setOffice(office);
                } else {
                    LOG.warning("Office does not exist");
                }

                boolean isEmployeeCreated = insertEmployee(employee);
                if (isEmployeeCreated) {
                    LOG.info(() -> format("Employee %s successfully created, redirecting to start page", employee.getName()));
                } else {
                    LOG.warning(() -> format("Failed to create employee %s", employee.getName()));
                    throw new Exception("Failed to create employee");
                }

                resp.sendRedirect(req.getContextPath() + "/office/ListOffices");
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e, e::getMessage);
            getServletContext().getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
    }

    private Employee createEmployee(String name, String position, int salary) {
        return new Employee(name, position, salary);
    }

    private Optional<Office> getOfficeByName(String officeName) {
        return officeService.getOfficeByName(officeName);
    }

    private boolean insertEmployee(Employee employee) {
        return employeeService.insert(employee);
    }
}
