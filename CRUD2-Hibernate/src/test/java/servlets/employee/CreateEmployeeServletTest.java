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
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.*;
import static servlets.employee.CreateEmployeeServlet.CREATE_EMPLOYEE_PAGE;
import static servlets.office.OfficeServlet.ERROR_PAGE;

@RunWith(MockitoJUnitRunner.class)
public class CreateEmployeeServletTest {

    @Mock
    private EmployeeService employeeService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher dispatcher;
    @Mock
    private ServletContext context;
    @Spy
    @InjectMocks
    private CreateEmployeeServlet servlet;

    @Before
    public void setUp() {
        doReturn(context).when(servlet).getServletContext();
        when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    }

    @Test
    public void doGet_shouldShowCreateEmployeePage_whenGetRequest() throws ServletException, IOException {
        servlet.doGet(request, response);

        verify(context).getRequestDispatcher(CREATE_EMPLOYEE_PAGE);
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void doPost_shouldCreateAndShowMainPage_whenAllFieldsFilled() throws ServletException, IOException {
        when(request.getParameter("name")).thenReturn("Igor");
        when(request.getParameter("position")).thenReturn("Engineer");
        when(request.getParameter("salary")).thenReturn("1000");
        when(request.getParameter("officeName")).thenReturn("office1");
        when(employeeService.insert(any(Employee.class))).thenReturn(true);

        servlet.doPost(request, response);

        verify(employeeService).insert(any(Employee.class));
        verify(response).sendRedirect(request.getContextPath() + "/office/ListOffices");
    }

    @Test
    public void doPost_shouldSendInformMessage_whenFieldsNotFilled() throws ServletException, IOException {
        when(request.getParameter("name")).thenReturn("");
        when(request.getParameter("position")).thenReturn("Engineer");
        when(request.getParameter("salary")).thenReturn("1000");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        servlet.doPost(request, response);

        writer.flush();
        assert (stringWriter.toString().contains("All fields must be filled in"));
        verifyNoInteractions(dispatcher);
    }

    @Test
    public void doPost_shouldShowError_whenNotCreated() throws ServletException, IOException {
        when(request.getParameter("name")).thenReturn("Igor");
        when(request.getParameter("position")).thenReturn("Engineer");
        when(request.getParameter("salary")).thenReturn("1000");
        when(employeeService.insert(any(Employee.class))).thenReturn(false);

        servlet.doPost(request, response);

        verify(context).getRequestDispatcher(ERROR_PAGE);
    }
}