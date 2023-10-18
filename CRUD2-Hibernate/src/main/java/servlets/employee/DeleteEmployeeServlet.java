package servlets.employee;

import services.CRUDService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/employee/delete")
public class DeleteEmployeeServlet extends HttpServlet {
    CRUDService service = new CRUDService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            int id = Integer.parseInt(req.getParameter("id"));
            service.deleteEmployeeById(id);
            resp.sendRedirect(req.getContextPath() + "/employee/index");
        } catch (Exception e) {
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
