package servlets.employee;

import models.Employee;
import models.Task;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static servlets.office.OfficeServlet.ERROR_PAGE;

@RunWith(MockitoJUnitRunner.class)
public class DeleteServletTest {

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
    private DeleteServlet servlet;

    int employeeId = 1;
    Employee employee = new Employee();
    List<Task> tasks = new ArrayList<>();

    @Before
    public void setUp() {
        doReturn(context).when(servlet).getServletContext();
        when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    }

    @Test
    public void doPost_shouldSuccessfullyDelete_whenEmployeeFound() throws ServletException, IOException {
        when(request.getParameter("id")).thenReturn(String.valueOf(employeeId));
        when(employeeService.getById(employeeId)).thenReturn(Optional.of(employee));
        when(employeeService.getTasksByEmployee(employee)).thenReturn(Optional.of(tasks));
        when(employeeService.delete(employeeId)).thenReturn(true);

        servlet.doPost(request, response);

        verify(employeeService).delete(employeeId);
    }

    @Test
    public void doPost_shouldShowError_whenFoundUnfinishedTasks() throws ServletException, IOException {
        tasks.add(new Task());
        when(request.getParameter("id")).thenReturn(String.valueOf(employeeId));
        when(employeeService.getById(employeeId)).thenReturn(Optional.of(employee));
        when(employeeService.getTasksByEmployee(employee)).thenReturn(Optional.of(tasks));
        when(request.getRequestDispatcher(ERROR_PAGE)).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(dispatcher).forward(request, response);
    }

    @Test
    public void doPost_shouldShowError_whenEmployeeNotFound() throws ServletException, IOException {
        when(request.getParameter("id")).thenReturn(String.valueOf(employeeId));
        when(employeeService.getById(employeeId)).thenReturn(Optional.empty());
        when(request.getRequestDispatcher(ERROR_PAGE)).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(dispatcher).forward(request, response);
    }
}