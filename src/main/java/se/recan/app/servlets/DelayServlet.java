package se.recan.app.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 * 
 * @author Anders Recksén (recan)
 */
public class DelayServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(Logger.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        sleep(2);
        
        StringBuilder builder = new StringBuilder();
        builder.append("<p class=\"message\">");
        builder.append("Detta ajax meddelande visas med 2 sekunders fördröjning");
        builder.append("</p>");

        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("iso-8859-1");

        PrintWriter out = response.getWriter();
        out.println(builder.toString());
        out.flush();
        out.close();
    }
    
    public void sleep(long l) {
        try {
            Thread.sleep(l * 1000);
        } catch (InterruptedException ie) {
            System.err.println(ie.getMessage());
        }
    }
}