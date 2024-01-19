package servlets.employee;

import models.Employee;
import models.Task;
import services.EmployeeService;

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

@WebServlet("/employee/delete")
public class DeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(DeleteServlet.class.getName());

    EmployeeService employeeService = new EmployeeService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Trying to delete employee");
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Optional<Employee> optionalEmployee = employeeService.getById(id);

            if (optionalEmployee.isPresent()) {
                Employee employee = optionalEmployee.get();
                Optional<List<Task>> optionalTasks = employeeService.getTasksByEmployee(employee);

                if (optionalTasks.isPresent() && !optionalTasks.get().isEmpty()) {
                    LOG.warning(() -> format("Can't delete Employee %s with unfinished tasks", id));
                    throw new Exception("Can't delete Employee " + id + " with unfinished tasks");
                }

                boolean isRowDeleted = employeeService.delete(id);
                if (isRowDeleted) {
                    LOG.info(() -> format("Employee %s successfully deleted, redirecting to start page", id));
                    resp.sendRedirect(req.getContextPath() + "/office/ListOffices");
                } else {
                    LOG.warning(() -> format("Employee %s is already deleted", id));
                    throw new Exception("Employee " + id + " is already deleted.");
                }
            } else {
                LOG.warning(() -> format("Employee %s not found", id));
                throw new Exception("Employee " + id + " not found.");
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e, e::getMessage);
            getServletContext().getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
    }
}

