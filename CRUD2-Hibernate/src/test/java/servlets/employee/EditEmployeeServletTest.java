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
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static servlets.employee.EditEmployeeServlet.EDIT_EMPLOYEE_PAGE;

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
    @Mock
    private ServletContext servletContext;

    @Spy
    @InjectMocks
    private EditEmployeeServlet servlet;

    @Before
    public void setUp() {
        //when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        doReturn(servletContext).when(servlet).getServletContext();
        when(servletContext.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
    }

    @Test
    public void doGet_shouldShowEmployeeForUpdate_whenFound() throws ServletException, IOException {
        when(request.getParameter("id")).thenReturn("1");
        when(service.findEmployeeById(1)).thenReturn(new Employee());

        servlet.doGet(request, response);

        verify(request).setAttribute(eq("employee"), any(Employee.class));
        verify(servletContext).getRequestDispatcher(EDIT_EMPLOYEE_PAGE);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void doGet_shouldFail_whenNotFound() throws ServletException, IOException {
        when(request.getParameter("id")).thenReturn("0");
        //when(service.findEmployeeById(0)).thenThrow(new RuntimeException());
        when(service.findEmployeeById(0)).thenReturn(null);

        assertThrows(Exception.class, () -> servlet.doGet(request, response)); // или редирект на error?
    }

    @Test
    public void doPost_shouldUpdateAndShowIndex_whenAllFieldsFilled() throws ServletException, IOException {
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("name")).thenReturn("Igor");
        when(request.getParameter("position")).thenReturn("Engineer");
        when(request.getParameter("salary")).thenReturn("1000");
        when(service.updateEmployee(any(Employee.class))).thenReturn(true);

        servlet.doPost(request, response);

        verify(service).updateEmployee(any(Employee.class));
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
        verifyNoInteractions(requestDispatcher);
    }

    @Test
    public void doPost_shouldShowError_whenNotUpdated() {}

    @Test
    public void doPost_shouldShowError_whenExceptionIsThrown() {}
}
