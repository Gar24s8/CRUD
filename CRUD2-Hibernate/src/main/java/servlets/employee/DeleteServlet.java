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

import static servlets.office.OfficeServlet.ERROR_PAGE;

@WebServlet("/employee/delete")
public class DeleteServlet extends HttpServlet {

    EmployeeService employeeService = new EmployeeService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Optional<Employee> optionalEmployee = employeeService.getById(id);

            if (optionalEmployee.isPresent()) {
                Employee employee = optionalEmployee.get();
                Optional<List<Task>> optionalTasks = employeeService.getTasksByEmployee(employee);

                if (optionalTasks.isPresent() && !optionalTasks.get().isEmpty()) {
                    throw new Exception("Can't delete Employee " + id + " with unfinished tasks");
                }

                boolean isRowDeleted = employeeService.delete(id);
                if (isRowDeleted) {
                    resp.sendRedirect(req.getContextPath() + "/office/ListOffices");
                } else {
                    throw new Exception("Employee " + id + " is already deleted.");
                }
            } else {
                throw new Exception("Employee " + id + " not found.");
            }
        } catch (Exception e) {
            System.out.println(getServletContext());
            System.out.println(getServletContext().getRequestDispatcher(ERROR_PAGE));
            getServletContext().getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
    }
}

