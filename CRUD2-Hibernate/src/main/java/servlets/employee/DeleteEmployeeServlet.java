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
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/employee/delete")
public class DeleteEmployeeServlet extends HttpServlet {
    CRUDService service = new CRUDService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            int id = Integer.parseInt(req.getParameter("id"));
            boolean isRowDeleted = service.deleteEmployeeById(id);
            if (isRowDeleted) {
                resp.sendRedirect(req.getContextPath() + "/employee/index");
            } else {
                throw new Exception("Employee " + id + " is already deleted.");
            }
        } catch (Exception e) {
            getServletContext().getRequestDispatcher("/employee/error.jsp").forward(req, resp);
        }
    }
}
