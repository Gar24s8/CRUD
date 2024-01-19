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

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SearchServletTest {
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
    private SearchServlet servlet;

    private List<Employee> searchResult;

    @Before
    public void setUp() {
        doReturn(context).when(servlet).getServletContext();
        when(request.getParameter("name")).thenReturn("Igor");
        when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        searchResult = Arrays.asList(new Employee("Elena", "Boss", 500000),
                new Employee("Igor", "Engineer", 100000));

        when(employeeService.findEmployeeByName("Igor")).thenReturn(searchResult);
    }

    @Test
    public void doGet_ShouldRedirectToSearchResultPage_WhenCalled() throws ServletException, IOException {
        servlet.doGet(request, response);

        verify(dispatcher).forward(request, response);
    }

    @Test
    public void doGet_ShouldReturnSearchResultList_WhenCalled() throws ServletException, IOException {
        servlet.doGet(request, response);

        verify(employeeService).findEmployeeByName("Igor");
        verify(request).setAttribute("searchResult", searchResult);
    }
}