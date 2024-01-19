package servlets.employee;

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
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static servlets.office.OfficeServlet.ERROR_PAGE;

@RunWith(MockitoJUnitRunner.class)
public class DeleteFromOfficeTest {
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
    private DeleteFromOffice servlet;

    int employeeId = 1;
    int officeId = 1;

    @Before
    public void setUp() {
        doReturn(context).when(servlet).getServletContext();
        when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    }

    @Test
    public void testDoPost_ShouldSuccessfullyDelete_WhenCalled() throws ServletException, IOException {
        Employee employee = new Employee();
        Office office = new Office();
        when(request.getParameter("id")).thenReturn(String.valueOf(employeeId));
        when(request.getParameter("officeId")).thenReturn(String.valueOf(officeId));
        when(employeeService.getById(employeeId)).thenReturn(Optional.of(employee));
        when(officeService.getById(officeId)).thenReturn(Optional.of(office));

        servlet.doPost(request, response);

        verify(employeeService).update(employee);
    }

    @Test
    public void testDoPost_ShouldShowError_WhenEmployeeOrOfficeNotFound() throws ServletException, IOException {
        when(request.getParameter("id")).thenReturn(String.valueOf(employeeId));
        when(request.getParameter("officeId")).thenReturn(String.valueOf(officeId));
        when(employeeService.getById(employeeId)).thenReturn(Optional.empty());
        when(officeService.getById(officeId)).thenReturn(Optional.empty());
        when(request.getRequestDispatcher(ERROR_PAGE)).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(dispatcher).forward(request, response);
    }

}