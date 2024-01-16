package servlets.employee;

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
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.String.format;
import static servlets.office.OfficeServlet.ERROR_PAGE;

@WebServlet("/employee/deleteFromOffice")
public class DeleteFromOffice extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(DeleteFromOffice.class.getName());

    EmployeeService employeeService = new EmployeeService();
    OfficeService officeService = new OfficeService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Trying to delete employee from office");

        try {
            int employeeId = Integer.parseInt(req.getParameter("id"));
            int officeId = Integer.parseInt(req.getParameter("officeId"));

            Optional<Employee> optionalEmployee = getEmployeeById(employeeId);
            Optional<Office> optionalOffice = getOfficeById(officeId);

            if (optionalEmployee.isPresent() && optionalOffice.isPresent()) {
                Employee employee = optionalEmployee.get();
                Office office = optionalOffice.get();

                employee.setOffice(null);
                employeeService.update(employee);

                resp.sendRedirect(req.getContextPath() + "/employee/listEmployees?officeID=" + officeId);
                LOG.info(() -> format("Employee %s successfully removed from office %s", employeeId, officeId));
            } else {
                LOG.warning(() -> format("Employee %s or Office %s not found", employeeId, officeId));
                throw new Exception("Employee " + employeeId + " or Office " + officeId + " not found");
            }

        } catch (Exception e) {
            LOG.log(Level.SEVERE, e, e::getMessage);
            getServletContext().getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
    }

    private Optional<Employee> getEmployeeById(int id) {
        return employeeService.getById(id);
    }

    private Optional<Office> getOfficeById(int id) {
        return officeService.getById(id);
    }
}
