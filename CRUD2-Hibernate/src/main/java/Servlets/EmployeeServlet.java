package Servlets;

import dao.DAOImpl;
import models.Employee;
import services.CRUDService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class EmployeeServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CRUDService service = new CRUDService();

        //index.jsp
        if (request.getParameter("showAll") != null) {
            request.setAttribute("list", service.findAllEmployees());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("list.jsp");
            requestDispatcher.forward(request, response);
        }
        if (request.getParameter("add") != null) {
            Employee employee = new Employee();
            employee.setId(Integer.parseInt(request.getParameter("id")));
            employee.setName(request.getParameter("name"));
            employee.setPosition(request.getParameter("position"));
            employee.setSalary((Long.parseLong(request.getParameter("salary"))));
            service.createEmployee(employee);
            request.setAttribute("list", service.findAllEmployees());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("list.jsp");
            requestDispatcher.forward(request, response);
        }


        //list.jsp
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("update")) {
            request.setAttribute("employee", service.findEmployeeById(Integer.parseInt(request.getParameter("id"))));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("update.jsp");
            requestDispatcher.forward(request, response);
        }
        if (action.equalsIgnoreCase("delete")) {
            Employee employee = new Employee();
            employee.setId(Integer.parseInt(request.getParameter("id")));
            employee.setName(request.getParameter("name"));
            service.deleteEmployee(employee);
            request.setAttribute("list", service.findAllEmployees());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("list.jsp");
            requestDispatcher.forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CRUDService service = new CRUDService();
        //update.jsp
        if (request.getParameter("update") != null) {
            Employee employee = new Employee();
            employee.setId(Integer.parseInt(request.getParameter("id")));
            employee.setName(request.getParameter("name"));
            service.updateEmployee(employee);
            request.setAttribute("list", service.findAllEmployees());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("list.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
