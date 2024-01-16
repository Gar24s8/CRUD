package servlets.employee;

import models.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import services.EmployeeService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeInOfficeServletTest {
    @Mock
    private EmployeeService employeeService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private ServletContext context;
    @Mock
    private RequestDispatcher dispatcher;
    @Spy
    @InjectMocks
    private EmployeeInOfficeServlet servlet;

    private List<Employee> employees;

    @Before
    public void setUp() {
        doReturn(context).when(servlet).getServletContext();
        when(request.getParameter("officeID")).thenReturn("1");
        when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        employees = Arrays.asList(new Employee("Elena", "Boss", 500000),
                new Employee("Igor", "Engineer", 100000));

        when(employeeService.getEmployeeByOfficeId(1)).thenReturn(employees);
    }

    @Test
    public void doGet_ShouldRedirectToEmployeeInOfficePage_WhenCalled() throws ServletException, IOException {
        servlet.doGet(request, response);

        verify(dispatcher).forward(request, response);
    }

    @Test
    public void doGet_ShouldReturnListOfEmployees_WhenCalled() throws ServletException, IOException {
        servlet.doGet(request, response);

        verify(employeeService).getEmployeeByOfficeId(1);
        verify(request).setAttribute("employees", employees);
    }
}