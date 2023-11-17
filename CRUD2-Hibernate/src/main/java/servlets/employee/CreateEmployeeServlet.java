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

import static servlets.employee.IndexEmployeeServlet.ERROR_PAGE;

@WebServlet("/employee/create")
public class CreateEmployeeServlet extends HttpServlet {
    static final String CREATE_EMPLOYEE_PAGE = "/employee/create.jsp";

    CRUDService service = new CRUDService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(CREATE_EMPLOYEE_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        try {
            String name = req.getParameter("name");
            String position = req.getParameter("position");
            long salary = Long.parseLong(req.getParameter("salary"));
            if (StringUtils.isEmpty(name) || StringUtils.isEmpty(position)) {
                resp.getWriter().print("All fields must be filled in");
            } else {
                Employee employee = new Employee(name, position, salary);
                boolean isRowCreated = service.createEmployee(employee);
                if (isRowCreated) {
                    resp.sendRedirect(req.getContextPath() + "/employee/index");
                } else {
                    throw new Exception("Employee " + employee + " is not created.");
                }
            }
        } catch (Exception e) {
            getServletContext().getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
    }
}
