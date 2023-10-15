package Servlets;

import services.CRUDService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CRUDService service = new CRUDService();

        try {
            int id = Integer.parseInt(req.getParameter("id"));
            service.deleteEmployee(id);
            resp.sendRedirect(req.getContextPath() + "/index");
        } catch (Exception e) {
            getServletContext().getRequestDispatcher("/notFound.jsp").forward(req, resp);
        }
    }
}
