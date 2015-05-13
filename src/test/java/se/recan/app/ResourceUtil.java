package se.recan.app;

import java.util.ResourceBundle;

/**
 *
 * @date 2014-maj-09
 * @author Anders Recksén (recan)
 */
public class ResourceUtil {

    private static final ResourceBundle RB = ResourceBundle.getBundle("app");

    // Browser att använda anges som en flagga i maven (mvn ... -D browser=<något>).
    // Default anges i propertyfilen.
    public static final String BROWSER = System.getProperty("browser", RB.getString("BROWSER"));

    public static final String URL = RB.getString("URL");
}
