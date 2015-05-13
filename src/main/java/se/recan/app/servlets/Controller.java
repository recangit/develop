package se.recan.app.servlets;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import se.recan.utils.EntityUtil;
import se.recan.app.vector.Graphic;

/**
 *
 * @author Anders Recksén (recan)
 */
public class Controller extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger("Logger");

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
LOGGER.debug(new File("/WEB-INF/classes/hack.sql").getCanonicalPath());
LOGGER.debug(new File("/WEB-INF/classes/hack.sql").getAbsolutePath());
        String template = request.getParameter("template");
        
        if("vector".equals(template)) {
            if(request.getParameter("modal").equals("true")) {
                request.setAttribute("modal", true);
            }
            try {
                EntityUtil entity = EntityUtil.getInstance();
                Graphic graphic = (Graphic) entity.process(request, "se.recan.app.vector.Graphic");
                
                LOGGER.debug(entity.feedback());
                LOGGER.debug(entity.getUri());
                
                HttpSession session = request.getSession();
                session.setAttribute("graphic", graphic);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        request.getRequestDispatcher(template + ".jsp").forward(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}