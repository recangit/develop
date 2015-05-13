package se.recan.app.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 * http://www.tutorialspoint.com/servlets/servlets-cookies-handling.htm
 *
 * @date 2014-maj-13
 * @author Anders Recksén (recan)
 */
public class CookieServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger("Logger");

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String template = request.getParameter("template");
        request.getRequestDispatcher(template + ".jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        LOGGER.debug("");
        
        List<String> list = new ArrayList<String>();

        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie c : cookies) {
                StringBuilder builder = new StringBuilder();

                builder.append("Cookie: ");
                builder.append(c.getName());
                builder.append(", Value: ");
                builder.append(c.getValue());
                builder.append(", Max age: ");
                builder.append(c.getMaxAge());
                builder.append(", Comment: ");
                builder.append(c.getComment());
                builder.append(", Path: ");
                builder.append(c.getPath());
                builder.append(", Domain: ");
                builder.append(c.getDomain());
                list.add(builder.toString());
            }
        }

        request.setAttribute("cookies", list);
        request.getRequestDispatcher("cookie.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.debug("");
        String cookie_name = request.getParameter("cookie_name");
        String cookie_value = request.getParameter("cookie_value");
        String cookie_path = request.getParameter("cookie_path");
        String cookie_domain = request.getParameter("cookie_domain");
        String cookie_comment = request.getParameter("cookie_comment");
//        int cookie_expire = request.getParameter("cookie_expire").isEmpty() ? -1 : Integer.parseInt(request.getParameter("cookie_expire"));

        Cookie cookie = new Cookie(cookie_name, cookie_value);
cookie.setPath("/");
//        cookie.setMaxAge(60 * 60 * 24);
//        if (!cookie_path.isEmpty()) {
//            cookie.setPath(cookie_path);
//        }
//
//        if (!cookie_domain.isEmpty()) {
//            cookie.setDomain(cookie_domain);
//        }
//
//        cookie.setComment(cookie_comment);

        // Add the cookie in the response header.
        response.addCookie(cookie);

        StringBuilder builder = new StringBuilder();

        builder.append(cookie.getName());
        builder.append(", Value: ");
        builder.append(cookie.getValue());
        builder.append(", Max age: ");
        builder.append(cookie.getMaxAge());
        builder.append(", Comment: ");
        builder.append(cookie.getComment());
        builder.append(", Path: ");
        builder.append(cookie.getPath());
        builder.append(", Domain: ");
        builder.append(cookie.getDomain());

        LOGGER.debug("Sparade kakan " + builder.toString());

        doGet(request, response);
    }
}
