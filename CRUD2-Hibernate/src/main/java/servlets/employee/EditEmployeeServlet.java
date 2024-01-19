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

@WebServlet("/employee/edit")
public class EditEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(EditEmployeeServlet.class.getName());
    static final String EDIT_EMPLOYEE_PAGE = "/employee/edit.jsp";

    EmployeeService employeeService = new EmployeeService();
    OfficeService officeService = new OfficeService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Optional<Employee> optionalEmployee = employeeService.getById(id);

            if (optionalEmployee.isPresent()) {
                Employee employee = optionalEmployee.get();
                req.setAttribute("employee", employee);
                List<Office> offices = officeService.getAll();
                req.setAttribute("offices", offices);
                getServletContext().getRequestDispatcher(EDIT_EMPLOYEE_PAGE).forward(req, resp);
            } else throw new Exception("Employee " + id + " is not found.");
        } catch (Exception e) {
            getServletContext().getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        LOG.info("Trying to edit employee");
        try {

            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String position = req.getParameter("position");
            int salary = Integer.parseInt(req.getParameter("salary"));
            String officeName = req.getParameter("officeName");


            if (StringUtils.isEmpty(name) || StringUtils.isEmpty(position)) {
                resp.getWriter().print("All fields must be filled in");
            } else {
                Employee employee = new Employee(id, name, position, salary);
                Optional<Office> optionalOffice = getOfficeByName(officeName);

                if (optionalOffice.isPresent()) {
                    Office office = optionalOffice.get();
                    employee.setOffice(office);
                } else {
                    LOG.warning("Office does not exist");
                }

                boolean isEmployeeUpdated = employeeService.update(employee);
                if (isEmployeeUpdated) {
                    LOG.info(() -> format("Employee %s successfully edited, redirecting to start page", employee.getName()));
                } else {
                    LOG.warning(() -> format("Failed to edit employee %s", employee.getName()));
                    throw new Exception("Failed to edit employee");
                }

                resp.sendRedirect(req.getContextPath() + "/office/ListOffices");
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e, e::getMessage);
            getServletContext().getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
    }

    private Optional<Office> getOfficeByName(String officeName) {
        return officeService.getOfficeByName(officeName);
    }
}
