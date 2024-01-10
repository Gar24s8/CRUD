//package servlets.employee;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Spy;
//import org.mockito.junit.MockitoJUnitRunner;
//import services.CRUDService;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//import static org.mockito.Mockito.*;
//import static servlets.employee.IndexEmployeeServlet.ERROR_PAGE;
//
//@RunWith(MockitoJUnitRunner.class)
//public class DeleteEmployeeServletTest {
//
//    @Mock
//    private CRUDService service;
//    @Mock
//    private HttpServletRequest request;
//    @Mock
//    private HttpServletResponse response;
//    @Mock
//    private RequestDispatcher requestDispatcher;
//    @Mock
//    private ServletContext servletContext;
//    @Spy
//    @InjectMocks
//    private DeleteEmployeeServlet servlet;
//
//    @Before
//    public void setUp() {
//        doReturn(servletContext).when(servlet).getServletContext();
//        when(servletContext.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
//    }
//
//    @Test
//    public void doPost_shouldDeleteAndShowIndex_whenEmployeeFound() throws ServletException, IOException {
//        when(request.getParameter("id")).thenReturn("1");
//        when(service.deleteEmployeeById(1)).thenReturn(true);
//
//        servlet.doPost(request, response);
//
//        verify(service).deleteEmployeeById(1);
//        verify(response).sendRedirect(request.getContextPath() + "/employee/index");
//    }
//
//    @Test
//    public void doPost_shouldShowError_whenEmployeeNotDeleted() throws ServletException, IOException {
//        when(request.getParameter("id")).thenReturn("1");
//        when(service.deleteEmployeeById(1)).thenReturn(false);
//
//        servlet.doPost(request, response);
//
//        verify(request, times(1)).getParameter("id");
//        verify(service, times(1)).deleteEmployeeById(1);
//        verify(servletContext).getRequestDispatcher(ERROR_PAGE);
//    }
//}