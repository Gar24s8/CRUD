package Servlets;

import models.Employee;
import services.CRUDService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CRUDService service = new CRUDService();
        try {
            String name = req.getParameter("name");
            String position = req.getParameter("position");
            Long salary = Long.parseLong(req.getParameter("salary"));
            Employee employee = new Employee();
            service.createEmployee(employee);
            resp.sendRedirect(req.getContextPath() + "/index");

        } catch (Exception e) {
            getServletContext().getRequestDispatcher("/create.jsp").forward(req, resp);
        }
    }
}
