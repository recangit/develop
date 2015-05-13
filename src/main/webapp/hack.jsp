<%@ page import="java.util.ArrayList" %>

<%
    ArrayList<String> result = (ArrayList<String>) request.getAttribute("result");

%>    
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>hack</title>
        <link href="css/main.css" rel="stylesheet" type="text/css" />
    </head>
    <body>

        <jsp:include page="includes/menu.jsp" />

        <h1>hack</h1>

        <table class="mainCanvas">
            <tr>
                <td>                
                    <h2>Om...hack</h2>
                </td>
            </tr>

            <tr>
                <td>                

                    <form method="post" action="hack">
                        <textarea rows="10" name="description"></textarea>
                        <br />
                        <input type="submit" value="Spara" />
                    </form>

                </td>
            </tr>
        </table>

        <table class="mainCanvas">
            <% for (String s : result) {%>
            <tr>
                <td><%=s%></td>
            </tr>
            <% }%>
        </table>
    </body>
</html>