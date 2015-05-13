package se.recan.app.servlet.mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import se.recan.app.servlets.Controller;


/**
 *
 * 2014-aug-11
 * @author Anders Recksén (recan)
 */
public class ServletTest {

    private static final Logger LOGGER = Logger.getLogger("Logger");
    
//    private CookieServlet servlet;
//    private MockHttpServletRequest request;
//    private MockHttpServletResponse response;
     
     @Test
    public void testServlet() throws Exception {
Controller c = new Controller();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        request.addParameter("template", "vector");
        request.addParameter("password", "tiger");

        c.doGet(request, response);

//        assertEquals("text/html", response.getContentType());
        
        
    request.setRequestURI("/controller");
    request.setMethod("GET");
        
        
//        HttpServletRequest  mockedRequest = Mockito.mock(HttpServletRequest.class);
        
        
//        HttpServletRequest request = mock(HttpServletRequest.class);       
//        HttpServletResponse response = mock(HttpServletResponse.class);    
//
//        when(request.getParameter("username")).thenReturn("me");
//        when(request.getParameter("password")).thenReturn("secret");
//        PrintWriter writer = new PrintWriter("somefile.txt");
//        when(response.getWriter()).thenReturn(writer);

//        new CookieServlet().doPost(request, response);

//        verify(request, atLeast(1)).getParameter("username"); // only if you want to verify username was called...
//        writer.flush(); // it may not have been flushed yet...
//        assertTrue(FileUtils.readFileToString(new File("somefile.txt"), "UTF-8")
//                   .contains("My Expected String"));
    }
    
    @Before
    public void setUp() {
//        servlet = new CookieServlet();
//        request = new MockHttpServletRequest();
//        response = new MockHttpServletResponse();
//        request = new MockHttpServletRequest();
    }

}