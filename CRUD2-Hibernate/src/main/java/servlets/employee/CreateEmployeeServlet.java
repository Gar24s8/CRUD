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

@WebServlet("/employee/create")
public class CreateEmployeeServlet extends HttpServlet {
    CRUDService service = new CRUDService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/employee/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        try {
            String name = req.getParameter("name");
            String position = req.getParameter("position");
            long salary = Long.parseLong(req.getParameter("salary"));
            Employee employee = new Employee(name, position, salary);
            if (StringUtils.isEmpty(name) | StringUtils.isEmpty(position)) {
                PrintWriter pw = resp.getWriter().printf("All fields must be filled in");
            } else {
                service.createEmployee(employee);
                resp.sendRedirect(req.getContextPath() + "/employee/index");
            }
        } catch (Exception e) {
            getServletContext().getRequestDispatcher("/employee/error.jsp").forward(req, resp);
        }
    }
}
