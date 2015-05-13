package se.recan.app.simple;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import se.recan.utils.EntityUtil;

/**
 * 
 * @author Anders Recksén (recan)
 */
public class Controller extends HttpServlet {

    protected static final Logger logger = Logger.getLogger(Logger.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            EntityUtil entity = EntityUtil.getInstance();
            SearchResult search = (SearchResult) entity.process(request, "se.recan.app.simple.SearchResult");

            request.setAttribute("search", search);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        String view = request.getParameter("view");
        if (view == null) {
            view = "start";
        }

        request.getRequestDispatcher(view + ".jsp").forward(request, response);
    }
}