package se.recan.app.coach;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import se.recan.utils.EntityUtil;

/**
 * 
 * * @author Anders Recksén (recan)
 */
public class CoachServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(Logger.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("message", "Ett meddelande");
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            EntityUtil entity = EntityUtil.getInstance();
            CoachEntity coach = (CoachEntity) entity.process(request, "se.recan.app.coach.CoachEntity");

            request.setAttribute("coach", coach);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        String view = request.getParameter("view");
        if (view == null) {
            view = "coach";
        }

        request.getRequestDispatcher(view + ".jsp").forward(request, response);
    }
}