<%@ page import="java.util.ArrayList" %>

<%@ page import="javax.servlet.http.Cookie" %>
<%
//    Cookie kaka = new Cookie("testJSPigen", "testJSP");
//    kaka.setMaxAge(365*24*60*60);
//    response.addCookie(kaka);
%>

<%
    ArrayList<String> cookies = (ArrayList<String>) request.getAttribute("cookies");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hantera kakor</title>
        <link href="css/main.css" rel="stylesheet" type="text/css" />
    </head>
    
    <body>
        <jsp:include page="includes/menu.jsp" />

        <h1>Hantera kakor</h1>

        <form method="post" action="cookie">

            <table class="mainCanvas">
                <tr>
                    <td>
                        <label>Namn</label>
                        <input type="text" name="cookie_name" />
                    </td>
                    <td>
                        <label>Value</label>
                        <input type="text" name="cookie_value" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Path</label>
                        <input type="text" name="cookie_path" />
                    </td>
                    <td>
                        <label>Domain</label>
                        <input type="text" name="cookie_domain" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Expire</label>
                        <input type="text" name="cookie_expire" />
                    </td>
                    <td>
                        <label>Kommentar</label>
                        <input type="text" name="cookie_comment" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>SSL</label>
                        <input type="radio" name="secure" />
                    </td>
                    <td>
                        <input type="submit" value="Spara" />
                    </td>
                </tr>
            </table>
        </form>
        
        <table class="mainCanvas">
        <% for(String cookie: cookies) {%>
                <tr>
                    <td>
            <%=cookie%> 
                    </td>
                </tr>
        <% }%>        
        </table>

        <table class="mainCanvas">
            <tr>
            <td>
                Hantera kakor i Firefox konsole:
                document.cookie="psB=0";
                console.log(document.cookie);
            </td>
        </tr>
        </table>
    </body>
</html>