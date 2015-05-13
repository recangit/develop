package se.recan.app;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import se.recan.app.db.HackDAO;
import se.recan.utils.CacheUtil;

/**
 *
 * @author Anders Recksén (recan)
 */
public class StartupServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        try {
            // Load language textfiles to a HashMap
            CacheUtil.initCache("SE");
            
            String path = getServletContext().getRealPath("/") + "/WEB-INF/classes/hack.sql";
            
            HackDAO.createSchema(path);
            HackDAO.insert("Bara en text");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
