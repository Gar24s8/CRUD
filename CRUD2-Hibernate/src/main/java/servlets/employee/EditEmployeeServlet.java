package servlets.employee;

import models.Employee;
import models.Office;
import services.EmployeeService;
import services.OfficeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

//package servlets.employee;
//
//import models.Employee;
//import org.apache.commons.lang3.StringUtils;
//import services.CRUDService;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//import static servlets.employee.IndexEmployeeServlet.ERROR_PAGE;
//
@WebServlet("/employee/edit")
public class EditEmployeeServlet extends HttpServlet {

    EmployeeService employeeService = new EmployeeService();
    OfficeService officeService = new OfficeService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        try {
//            int id = Integer.parseInt(req.getParameter("id"));
//            Optional<Employee> optionalEmployee = employeeService.getById(id);
//            List<Office> offices = officeService.getAll();
//
//            if (optionalEmployee.isPresent()) {
//                Employee employee = optionalEmployee.get();
//                req.setAttribute("employee", employee);
//            } else {
//                throw new Exception("Employee " + id + " is not found.");
//            }
//
//            req.setAttribute("offices", offices);

            getServletContext().getRequestDispatcher("/employee/edit.jsp").forward(req, resp);
//        } catch (Exception e) {
//            getServletContext().getRequestDispatcher("/office/error.jsp").forward(req, resp);
//        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");

            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String position = req.getParameter("position");
            int salary = Integer.parseInt(req.getParameter("salary"));
            int officeId = Integer.parseInt(req.getParameter("officeId"));
            List<Office> offices = officeService.getAll();
            req.setAttribute("offices", offices);

            Optional<Employee> optionalEmployee = employeeService.getById(id);
            Optional<Office> optionalOffice = officeService.getById(officeId);

            if (optionalEmployee.isPresent() && optionalOffice.isPresent()) {
                Employee employee = optionalEmployee.get();
                Office office = optionalOffice.get();

                employee.setName(name);
                employee.setPosition(position);
                employee.setSalary(salary);
                employee.setOffice(office);

                boolean isRowEdited = employeeService.update(employee);
                if (isRowEdited) {
                    resp.sendRedirect(req.getContextPath() + "/office/ListOffices");
                } else {
                    throw new Exception("Employee " + id + " is not edited.");
                }
            } else {
                throw new Exception("Employee " + id + " or Office " + officeId + " not found");
            }
        } catch (Exception e) {
            getServletContext().getRequestDispatcher("/office/error.jsp").forward(req, resp);
        }
    }

}

//
//    static final String EDIT_EMPLOYEE_PAGE = "/employee/edit.jsp";
//
//    CRUDService service = new CRUDService();






//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        try {
//            int id = Integer.parseInt(req.getParameter("id"));
//            Employee employee = service.findEmployeeById(id);
//            req.setAttribute("employee", employee);
//            if (employee != null) {
//                getServletContext().getRequestDispatcher(EDIT_EMPLOYEE_PAGE).forward(req, resp);
//            } else throw new Exception("Employee " + id + " is not found.");
//        } catch (Exception e) {
//            getServletContext().getRequestDispatcher(ERROR_PAGE).forward(req, resp);
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        try {
//            req.setCharacterEncoding("UTF-8");
//
//            int id = Integer.parseInt(req.getParameter("id"));
//            String name = req.getParameter("name");
//            String position = req.getParameter("position");
//            long salary = Long.parseLong(req.getParameter("salary"));
//            Employee employee = new Employee(id, name, position, salary);
//
//            if (StringUtils.isEmpty(name) || StringUtils.isEmpty(position)) {
//                resp.getWriter().print("All fields must be filled in");
//            } else {
//                boolean isRowEdited = service.updateEmployee(employee);
//                if (isRowEdited) {
//                    resp.sendRedirect(req.getContextPath() + "/employee/index");
//                } else {
//                    throw new Exception("Employee " + id + " is not edited.");
//                }
//            }
//        } catch (Exception e) {
//            getServletContext().getRequestDispatcher(ERROR_PAGE).forward(req, resp);
//        }
//    }
//}
