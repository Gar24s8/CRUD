package servlets.employee;

import models.Employee;
import services.CRUDService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/employee/edit")
public class EditEmployeeServlet extends HttpServlet {
    CRUDService service = new CRUDService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        Employee employee = service.findEmployeeById(id);
        if (employee == null) {
            throw new ServletException("You must type values");
        }
        req.setAttribute("employee", employee);
        getServletContext().getRequestDispatcher("/edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String position = req.getParameter("position");
            Long salary = Long.parseLong(req.getParameter("salary"));
            Employee employee = new Employee(id, name, position, salary);
            service.updateEmployee(employee);
            resp.sendRedirect(req.getContextPath() + "/employee/index");
        } catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
    }
}
