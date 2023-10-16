package Servlets;

import models.Employee;
import services.CRUDService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CRUDService service = new CRUDService();

        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Employee employee = service.findEmployeeById(id);
            if (employee != null) {
                req.setAttribute("employee", employee);
                getServletContext().getRequestDispatcher("/edit.jsp").forward(req, resp);
            } else {
                getServletContext().getRequestDispatcher("/notFound.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            getServletContext().getRequestDispatcher("/notFound.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        CRUDService service = new CRUDService();

        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String position = req.getParameter("position");
            Long salary = Long.parseLong(req.getParameter("salary"));
            Employee employee = new Employee(id, name, position, salary);
            service.updateEmployee(employee);
            resp.sendRedirect(req.getContextPath() + "/index");
        } catch (Exception e) {
            getServletContext().getRequestDispatcher("/notFound.jsp").forward(req, resp);
        }
    }
}
