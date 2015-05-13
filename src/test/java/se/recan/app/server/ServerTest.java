package se.recan.app.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @date 2014-jul-16
 * @author Anders Recksén (recan)
 * 
 * http://stackoverflow.com/questions/2793150/how-to-use-java-net-urlconnection-to-fire-and-handle-http-requests
 * http://stackoverflow.com/questions/5459162/tcp-connection-is-not-reused-for-http-requests-with-httpurlconnection
 */
public class ServerTest {

    private static final Logger LOGGER = Logger.getLogger("Logger");

    private static final String CHARSET = "UTF-8";

    @Test
    public void testResponseCode() throws MalformedURLException, IOException {
        
        HttpURLConnection httpConnection = (HttpURLConnection) new URL("http://localhost:8080/SimpleWebApp").openConnection();
        Assert.assertTrue("HTTP-header 200", httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK);
        
        LOGGER.debug(httpConnection.getResponseCode());
        
        for (Entry<String, List<String>> header : httpConnection.getHeaderFields().entrySet()) {
            LOGGER.debug(header.getKey() + "=" + header.getValue());
        }
    }

//    @Test
    public void testSendGETRequest() throws IOException {
        StringBuilder buffer = new StringBuilder();

        buffer.append("?");
        buffer.append("password=malla&");
        buffer.append("userName=recan&");
        buffer.append("gender=1&");
        buffer.append("firstName=Anders&");
        buffer.append("lastName=Recksén&");
        buffer.append("socialNumb=621002-4318&");
        buffer.append("password2=malla");

        String query = URLEncoder.encode(buffer.toString(), CHARSET);
        URL url = new URL("http://localhost:8080/SimpleWebApp/person" + query);

        LOGGER.debug(query);
        
        URLConnection connection = url.openConnection();
        
        connection.setRequestProperty("Accept-Charset", CHARSET);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + CHARSET);

        // Detta skickar iväg requesten
        connection.getInputStream();
    }

//    @Test
    public void testSendPOSTRequest() throws IOException {
        URL url = new URL("http://localhost:8080/SimpleWebApp/person");

        URLConnection connection = url.openConnection();
        // Denna sätter POST
        connection.setDoOutput(true);
        connection.setRequestProperty("Accept-Charset", CHARSET);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + CHARSET);

        try (OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream())) {
            out.write("password=" + "malla&");
            out.write("userName=" + "recan&");
            out.write("gender=" + "1&");
            out.write("firstName=" + "Anders&");
            out.write("lastName=" + "Recksén&");
            out.write("socialNumb=" + "621002-4318&");
            out.write("password2=" + "malla");
            out.write("&SHOW_HEADER=" + "false");
//            out.write("&SHOW_ATTR=" + "false");
//            out.write("&SHOW_SESS=" + "false");
        }

        connection.getInputStream();
    }

    // Olika sätt att skicka, vet inte vilken som är bäst.
//    @Test
    public void testSendPOSTRequest2() throws IOException {
        StringBuilder buffer = new StringBuilder();

        buffer.append("password=malla&");
        buffer.append("userName=recan&");
        buffer.append("gender=1&");
        buffer.append("firstName=Anders&");
        buffer.append("lastName=Recksén&");
        buffer.append("socialNumb=621002-4318&");
        buffer.append("password2=malla");

        URL url = new URL("http://localhost:8080/SimpleWebApp/person");

        URLConnection connection = url.openConnection();
        // Denna sätter POST
        connection.setDoOutput(true);
        connection.setRequestProperty("Accept-Charset", CHARSET);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + CHARSET);

        try (OutputStream output = connection.getOutputStream()) {
            output.write(buffer.toString().getBytes(CHARSET));
        }

        connection.getInputStream();
    }

//    @Test
    public void testSendDeleteRequest() throws IOException {
        URL url = new URL("http://localhost:8080/SimpleWebApp/person");
        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);

        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());

        out.write("delete=" + "Radera allt");

        out.close();

        connection.getInputStream();
    }

//    @Test
    public void testResponse() throws IOException {
        URL url = new URL("http://localhost:8080/SimpleWebApp/person");

        URLConnection connection = url.openConnection();

        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"))) {
            String decodedString;
            while ((decodedString = in.readLine()) != null) {
                LOGGER.debug(decodedString);
            }
        }
        
//        HttpURLConnection httpConnection = (HttpURLConnection) new URL("http://cpr.arbetsformedlingen.se").openConnection();
//        Assert.assertTrue("HTTP-header 200", httpConnection.getResponseCode() == 200);
    }

//    @Test
    public void testUrl() throws MalformedURLException {
        URL url = new URL("http://localhost:8080/SimpleWebApp/person?ja=true");
        LOGGER.debug("URL is " + url.toString());
        LOGGER.debug("protocol is " + url.getProtocol());
        LOGGER.debug("authority is " + url.getAuthority());
        LOGGER.debug("file name is " + url.getFile());
        LOGGER.debug("host is " + url.getHost());
        LOGGER.debug("path is " + url.getPath());
        LOGGER.debug("port is " + url.getPort());
        LOGGER.debug("default port is " + url.getDefaultPort());
        LOGGER.debug("query is " + url.getQuery());
        LOGGER.debug("ref is " + url.getRef());
    }
    
//    @Test
    public void testSniffer() throws IOException {
        StringBuilder buffer = new StringBuilder();

        buffer.append("?template=delay");
        buffer.append("&SHOW_HEADER=false");
        buffer.append("&SHOW_ATTR=false");
        buffer.append("&SHOW_SESS=false");

        String query = buffer.toString();
        
//        Encoding gör att Sniffer inte fångar upp parametrarna
//        String query = URLEncoder.encode(buffer.toString(), CHARSET);

        URL url = new URL("http://localhost:8080/SimpleWebApp/controller" + query);

        LOGGER.debug(query);
        
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("en-header-param", "Perkele");
        connection.connect();

        HttpURLConnection http = (HttpURLConnection) connection;

//        Någonting krävs för att nå servern?
//        http.getResponseCode();
//        Assert.assertTrue(http.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND);
        Assert.assertTrue(http.getResponseCode() == HttpURLConnection.HTTP_OK);

//      Response Header
        for (Entry<String, List<String>> header : connection.getHeaderFields().entrySet()) {
//            LOGGER.debug(header.getKey() + "=" + header.getValue());
        }
        
        
    }
    
//    @BeforeClass
    public static void setUp() {

    }
    
//    @AfterClass
    public static void tearDownClass() throws Exception {
        URL url = new URL("http://localhost:8080/SimpleWebApp/person");
        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);

        try (OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream())) {
            out.write("delete=Radera allt");
        }

        connection.getInputStream();
    }
}
