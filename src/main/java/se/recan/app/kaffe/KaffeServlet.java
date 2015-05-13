package se.recan.app.kaffe;

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
public class KaffeServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(Logger.class);

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
            KaffeEntity coffee = (KaffeEntity) entity.process(request, "se.recan.app.kaffe.KaffeEntity");

            LOGGER.info(entity.feedback());

            if (coffee.isValidCombination()) {
                coffee.setMessage("Detta är en korrekt beställning");
            } else {
                coffee.setMessage("Detta är INTE en korrekt beställning");
            }
            request.setAttribute("coffee", coffee);

            request.getRequestDispatcher("kaffe.jsp").forward(request, response);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}