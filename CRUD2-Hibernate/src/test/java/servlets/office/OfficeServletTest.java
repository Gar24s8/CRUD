package servlets.office;


import models.Employee;
import models.Office;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import services.EmployeeService;
import services.OfficeService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OfficeServletTest {

    @Mock
    private EmployeeService employeeService;
    @Mock
    private OfficeService officeService;
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
    private OfficeServlet servlet;

    private List<Employee> employees;
    private List<Office> offices;

    @Before
    public void setUp() {
        doReturn(context).when(servlet).getServletContext();
        when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        employees = Arrays.asList(new Employee("Elena", "Boss", 500000),
                new Employee("Igor", "Engineer", 100000));
        offices = Arrays.asList(new Office("office1", "address1"),
                new Office("office2", "office2"));

        when(employeeService.getAll()).thenReturn(employees);
        when(officeService.getAll()).thenReturn(offices);
    }

    @Test
    public void doGet_ShouldRedirectToStartPage_WhenCalled() throws ServletException, IOException {
        servlet.doGet(request, response);

        verify(dispatcher).forward(request, response);
    }

    @Test
    public void doGet_ShouldReturnListOfEmployeesAndOffices_WhenCalled() throws ServletException, IOException {
        servlet.doGet(request, response);

        verify(employeeService).getAll();
        verify(request).setAttribute("employees", employees);

        verify(officeService).getAll();
        verify(request).setAttribute("offices", offices);
    }
}