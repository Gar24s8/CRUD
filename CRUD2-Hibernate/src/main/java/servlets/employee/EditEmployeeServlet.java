package servlets.employee;

import models.Employee;
import org.apache.commons.lang3.StringUtils;
import services.CRUDService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/employee/edit")
public class EditEmployeeServlet extends HttpServlet {
    CRUDService service = new CRUDService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Employee employee = service.findEmployeeById(id);
            req.setAttribute("employee", employee);
            getServletContext().getRequestDispatcher("/employee/edit.jsp").forward(req, resp);
        } catch (Throwable t) {
            throw new ServletException("Error: " + t.getMessage(), t);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String position = req.getParameter("position");
            long salary = Long.parseLong(req.getParameter("salary"));
            Employee employee = new Employee(id, name, position, salary);
            if (StringUtils.isEmpty(name) | StringUtils.isEmpty(position)) {
                PrintWriter pw = resp.getWriter().printf("All fields must be filled in");
            } else {
                boolean isRowEdited = service.updateEmployee(employee);
                if (isRowEdited) {
                    resp.sendRedirect(req.getContextPath() + "/employee/index");
                } else {
                    throw new Exception("Employee " + id + " is not edited.");
                }
            }
        } catch (Exception e) {
            getServletContext().getRequestDispatcher("/employee/error.jsp").forward(req, resp);
        }
    }
}
