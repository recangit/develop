<%@ page import="se.recan.app.kaffe.KaffeEntity" %>
<%
	KaffeEntity coffee = (KaffeEntity) request.getAttribute("coffee");
	boolean kaffe = coffee.isKaffe();
	boolean the = coffee.isThe();
	boolean svart = coffee.isSvart();
	boolean mjolk = coffee.isMjolk();
	boolean socker = coffee.isSocker();
	boolean indiskt = coffee.isIndiskt();
	boolean kinesiskt = coffee.isKinesiskt();
    int summa = coffee.getSumma();
    String message = coffee.getMessage();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kaffekoppen</title>
        <link href="css/main.css" rel="stylesheet" type="text/css" />
    </head>
    <body>

        <jsp:include page="includes/menu.jsp" />

        <h1>Kaffe</h1>

        <form method="post" action="kaffe">
        	<input type="hidden" name="summa" value="<%=summa%>" />

            <table class="mainCanvas">
                <tr>
                    <td>
                        Kaffe <input type="checkbox" name="kaffe" value="true" <%=kaffe ? "checked=\"checked\" " : ""%> /> 5:-
                    </td>
                    <td>
                        The <input type="checkbox" name="the" value="true" <%=the ? "checked=\"checked\" " : ""%> /> 4:-
                    </td>
                </tr>
                
                <tr>
                    <td>
                        Svart <input type="checkbox" name="svart" value="true" <%=svart ? "checked=\"checked\" " : ""%> />
                        Mjölk <input type="checkbox" name="mjolk" value="true" <%=mjolk ? "checked=\"checked\" " : ""%> /> + 1:-
                    </td>
                    <td>
                    	Indiskt <input type="checkbox" name="indiskt" value="true" <%=indiskt ? "checked=\"checked\" " : ""%> />
                    	Kinesiskt <input type="checkbox" name="kinesiskt" value="true" <%=kinesiskt ? "checked=\"checked\" " : ""%> /> + 2:-
                    </td>
                </tr>
                
                <tr>
                    <td colspan="2">
                        Socker <input type="checkbox" name="socker" value="true" <%=socker ? "checked=\"checked\" " : ""%> /> + 1:-
                    </td>
                </tr>

                <tr>
                    <td colspan="2">
                        <input type="submit" name="betalning_10" value="10" />
                        <input type="submit" name="betalning_5" value="5" />
                        <input type="submit" name="betalning_1" value="1" />
                    </td>
                </tr>

                <tr>
                    <td colspan="2">
                        <input type="submit" name="bestall" value="Beställ" />
                        <input type="reset" value="Rensa" />
                    </td>
                </tr>

                <tr>
                    <td colspan="2" class="message">
                        <%=message%>
                    </td>
                </tr>
            </table>
        </form>
                    
                    <img src="graphic" />
    </body>
</html>