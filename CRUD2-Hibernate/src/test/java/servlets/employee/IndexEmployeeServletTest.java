package servlets.employee;

import models.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import services.CRUDService;

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
public class IndexEmployeeServletTest {

    @Mock
    private CRUDService service;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private ServletContext servletContext;
    @Mock
    private RequestDispatcher dispatcher;
    @Spy
    @InjectMocks
    private IndexEmployeeServlet servlet;

    private List<Employee> employees;

    @Before
    public void setUp() {
        doReturn(servletContext).when(servlet).getServletContext();
        when(servletContext.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        employees = Arrays.asList(new Employee("Elena", "Boss", 500000),
                new Employee("Igor", "Manager", 100000));

        when(service.findAllEmployees()).thenReturn(employees);
    }

    @Test
    public void doGet_shouldRedirectToIndex_whenCalled() throws ServletException, IOException {
        servlet.doGet(request, response);

        verify(dispatcher).forward(request, response);
    }


    @Test
    public void doGet_shouldReturnListOfEmployees_whenFound() throws ServletException, IOException {
        servlet.doGet(request, response);

        verify(service).findAllEmployees();
        verify(request).setAttribute("employees", employees);
    }
}