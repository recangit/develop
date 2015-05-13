package se.recan.app.person;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import se.recan.utils.EntityUtil;
import se.recan.utils.XmlUtil;

/**
 *
 * @author Anders Recksén (recan)
 */
public class PersonServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger("Logger");

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
            File xmlFile = new File(new File(getServletContext().getRealPath("/")) + "/person.xml");
            
            if(request.getParameter("delete") != null) {
                XmlUtil util = new XmlUtil(xmlFile);
                boolean deleted = util.deleteXmlFile();
                LOGGER.debug("Delete " + xmlFile + ":" + deleted);
            }
            
            EntityUtil entity = EntityUtil.getInstance();
            Person person = (Person) entity.process(request, "se.recan.app.person.Person");

            if (person.getMessage().equals("Validering OK")) {
                person.save(xmlFile);
                person = new Person();
            }
            
            ArrayList<Person> persons = new ArrayList<>();

            if (xmlFile.exists()) {
                XmlUtil util = new XmlUtil(xmlFile);

                List<String> xmlList = util.getXmlAsList();

                Person p = null;
                for (String s : xmlList) {
                    String key = s.substring(0, s.indexOf("="));
                    String value = s.substring(s.indexOf("=") + 1);
                    
                    if (key.equals("firstName")) {
                        p = new Person(value);
                        persons.add(p);
                    } else if (key.equals("lastName")) {
                        p.setLastName(value);
                    } else if (key.equals("userName")) {
                        p.setUserName(value);
                    } else if (key.equals("password")) {
                        p.setPassword(value);
                    } else if (key.equals("password2")) {
                        p.setPassword2(value);
                    } else if (key.equals("socialNumb")) {
                        p.setSocialNumb(value);
                    } else if (key.equals("gender")) {
                        int gender = Integer.parseInt(value);
                        p.setGender(gender);
                    }
                }
            }

            request.setAttribute("person", person);
            request.setAttribute("persons", persons);
            request.getRequestDispatcher("person.jsp").forward(request, response);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
