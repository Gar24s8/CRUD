package servlets.employee;

import models.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import services.CRUDService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EditEmployeeServletTest {

    @Mock
    private CRUDService service;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher requestDispatcher;
    @InjectMocks
    private EditEmployeeServlet servlet;

    @Before
    public void setUp() {
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
    }

    @Test
    public void doGet_shouldSuccess_whenValidId() throws ServletException, IOException {
        when(request.getParameter("id")).thenReturn("1");
        when(service.findEmployeeById(1)).thenReturn(new Employee());

        servlet.doGet(request, response);

        verify(request).setAttribute(eq("employee"), any(Employee.class));
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void doGet_shouldFail_whenInvalidId() throws ServletException, IOException {
        when(request.getParameter("id")).thenReturn("1");
        when(service.findEmployeeById(1)).thenThrow(new RuntimeException());

        servlet.doGet(request, response);

        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void doPost_shouldEdited_whenAllFieldsFilled() throws ServletException, IOException {
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("name")).thenReturn("Igor");
        when(request.getParameter("position")).thenReturn("Engineer");
        when(request.getParameter("salary")).thenReturn("1000");
        when(service.updateEmployee(any(Employee.class))).thenReturn(true);

        servlet.doPost(request, response);

        verify(response).sendRedirect(request.getContextPath() + "/employee/index");
    }

    @Test
    public void doPost_shouldSendInformMessage_whenFieldsNotFilled() throws ServletException, IOException {
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("name")).thenReturn("");
        when(request.getParameter("position")).thenReturn("");
        when(request.getParameter("salary")).thenReturn("1000");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        servlet.doPost(request, response);

        writer.flush();
        assert (stringWriter.toString().contains("All fields must be filled in"));
    }
}