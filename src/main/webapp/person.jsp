<%@ page import="se.recan.app.person.Person" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="se.recan.utils.CacheUtil" %>

<%
    ArrayList<Person> persons = (ArrayList<Person>) request.getAttribute("persons");
    Person person = (Person) request.getAttribute("person");
    String firstName = person.getFirstName();
    String lastName = person.getLastName();
    String userName = person.getUserName();
    String password = person.getPassword();
    String password2 = person.getPassword2();
    String socialNumb = person.getSocialNumb();
    String message = person.getMessage();
    int gender = person.getGender();
%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CacheUtil.getProperty("se.recan.app.person.title")%></title>
        <link href="css/main.css" rel="stylesheet" type="text/css" />
    </head>
    <body>

        <jsp:include page="includes/menu.jsp" />

        <h1><%=CacheUtil.getProperty("se.recan.app.person.headline")%></h1>

        <form id="person" method="post" action="person">
            <table class="mainCanvas">
                <tr>
                    <td>
                        <label><%=CacheUtil.getProperty("se.recan.app.person.firstname")%> *</label>
                        <input type="text" name="firstName" value="<%=firstName%>" />
                    </td>

                    <td>
                        <label><%=CacheUtil.getProperty("se.recan.app.person.lastname")%> *</label>
                        <input type="text" name="lastName" value="<%=lastName%>" />
                    </td>
                </tr>

                <tr>
                    <td>
                        <label><%=CacheUtil.getProperty("se.recan.app.person.socialnumb")%> *</label>
                        <input type="text" name="socialNumb" value="<%=socialNumb%>" />
                    </td>

                    <td>
                        <label><%=CacheUtil.getProperty("se.recan.app.person.gender")%></label>
                        <%=CacheUtil.getProperty("se.recan.app.person.female")%> <input type="radio" name="gender" value="0" <%=gender == 0 ? "checked=\"checked\" " : ""%> />
                        <%=CacheUtil.getProperty("se.recan.app.person.male")%> <input type="radio" name="gender" value="1" <%=gender == 1 ? "checked=\"checked\" " : ""%> />
                    </td>
                </tr>

                <tr>
                    <td colspan="2">
                        <label><%=CacheUtil.getProperty("se.recan.app.person.username")%> *</label>
                        <input type="text" name="userName" value="<%=userName%>" />
                    </td>
                </tr>

                <tr>
                    <td>
                        <label><%=CacheUtil.getProperty("se.recan.app.person.password")%> *</label>
                        <input type="text" name="password" value="<%=password%>" />
                    </td>

                    <td>
                        <label><%=CacheUtil.getProperty("se.recan.app.person.password2")%></label>
                        <input type="text" name="password2" value="<%=password2%>" />
                    </td>
                </tr>

                <tr>
                    <td colspan="2">
                        <input id="save" type="submit" value="<%=CacheUtil.getProperty("se.recan.app.global.save")%>" />
                        <input type="reset" value="<%=CacheUtil.getProperty("se.recan.app.global.clean")%>" />
                        <input id="delete" type="submit" name="delete" value="<%=CacheUtil.getProperty("se.recan.app.global.delete")%>" />
                    </td>
                </tr>

                <tr>
                    <td colspan="2" id=message class="message">
                        <%=message%>
                    </td>
                </tr>
            </table>
        </form>
                    
        <table id="personlista" class="mainCanvas">
            <% for (Person p : persons) {%>
            <tr id="<%=p.getId()%>">
                <td><%=p.getFirstName()%></td>
                <td><%=p.getLastName()%></td>
                <td><%=p.getSocialNumb()%></td>
                <td><%=p.getUserName()%></td>
                <td><%=p.getPassword()%></td>
                <td><%=p.getPassword2()%></td>
                <td><%=p.getGender() == 1 ? CacheUtil.getProperty("se.recan.app.person.male") : CacheUtil.getProperty("se.recan.app.person.female")%></td>
            </tr>
            <% }%>
        </table>
    </body>
</html>