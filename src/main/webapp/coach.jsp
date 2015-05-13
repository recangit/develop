<%@ page import="se.recan.app.coach.CoachEntity" %>

<%
    CoachEntity coach = (CoachEntity) request.getAttribute("coach");
    int county = coach.getCounty();
    String kommun = coach.getKommun();
    String postort = coach.getPostort();
    int language = coach.getLanguage();
    int profession = coach.getProfession();
    String company = coach.getCompany();
    String message = (String) request.getAttribute("message")==null? "": (String) request.getAttribute("message");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Coachen</title>
        <link href="css/main.css" rel="stylesheet" type="text/css" />
    </head>
    <body>

        <jsp:include page="includes/menu.jsp" />
        
        <h1>Sök...</h1>

        <form method="post" action="coach">
            <table class="mainCanvas">
                <tr>
                    <td>
                        <label>Län</label>
                        <select name="county">
                            <option <%=county==0 ? " selected=\"selected\" " : ""%>value="0">Välj</option>
                            <option <%=county==10 ? "selected=\"selected\" " : ""%>value="10">Blekinge län</option>
                            <option <%=county==20 ? "selected=\"selected\" " : ""%>value="20">Dalarnas län</option>
                            <option <%=county==9 ? "selected=\"selected\" " : ""%>value="9">Gotlands län</option>
                            <option <%=county==21 ? "selected=\"selected\" " : ""%>value="21">Gävleborgs län</option>
                            <option <%=county==13 ? "selected=\"selected\" " : ""%>value="13">Hallands län</option>
                            <option <%=county==23 ? "selected=\"selected\" " : ""%>value="23">Jämtlands län</option>
                            <option <%=county==06 ? "selected=\"selected\" " : ""%>value="06">Jönköpings län</option>
                            <option <%=county==8 ? "selected=\"selected\" " : ""%>value="8">Kalmar län</option>
                            <option <%=county==07 ? "selected=\"selected\" " : ""%>value="07">Kronobergs län</option>
                            <option <%=county==25 ? "selected=\"selected\" " : ""%>value="25">Norrbottens län</option>
                            <option <%=county==12 ? "selected=\"selected\" " : ""%>value="12">Skåne län</option>
                            <option <%=county==01 ? "selected=\"selected\" " : ""%>value="01">Stockholms län</option>
                            <option <%=county==04 ? "selected=\"selected\" " : ""%>value="04">Södermanlands län</option>
                            <option <%=county==03 ? "selected=\"selected\" " : ""%>value="03">Uppsala län</option>
                            <option <%=county==17 ? "selected=\"selected\" " : ""%>value="17">Värmlands län</option>
                            <option <%=county==24 ? "selected=\"selected\" " : ""%>value="24">Västerbottens län</option>
                            <option <%=county==22 ? "selected=\"selected\" " : ""%>value="22">Västernorrlands län</option>
                            <option <%=county==19 ? "selected=\"selected\" " : ""%>value="19">Västmanlands län</option>
                            <option <%=county==14 ? "selected=\"selected\" " : ""%>value="14">Västra Götalands län</option>
                            <option <%=county==18 ? "selected=\"selected\" " : ""%>value="18">Örebro län</option>
                            <option <%=county==05 ? "selected=\"selected\" " : ""%>value="05">Östergötlands län</option>
                        </select>
                    </td>

                    <td>
                        <label>Kommun</label>
                        <input type="text" name="kommun" title="Ditt sökord måste innehålla minst tre tecken" value="<%=kommun%>" />
                    </td>

                    <td>
                        <label>Postort</label>
                        <input type="text" name="postort" title="Ditt sökord måste innehålla minst tre tecken" value="<%=postort%>" />
                    </td>
                </tr>

                <tr>
                    <td>
                        <label>Språk</label>
                        <select name="language">
                            <option <%=language==0 ? "selected=\"selected\" " : ""%>value="0">Välj</option>
                            <option <%=language==1 ? "selected=\"selected\" " : ""%>value="1">Albanska</option>
                            <option <%=language==2 ? "selected=\"selected\" " : ""%>value="2">Arabiska</option>
                            <option <%=language==3 ? "selected=\"selected\" " : ""%>value="3">Dari</option>
                            <option <%=language==4 ? "selected=\"selected\" " : ""%>value="4">Engelska</option>
                            <option <%=language==5 ? "selected=\"selected\" " : ""%>value="5">Franska</option>
                            <option <%=language==6 ? "selected=\"selected\" " : ""%>value="6">Mongoliska</option>
                            <option <%=language==7 ? "selected=\"selected\" " : ""%>value="7">Persiska</option>
                            <option <%=language==8 ? "selected=\"selected\" " : ""%>value="8">Ryska</option>
                            <option <%=language==9 ? "selected=\"selected\" " : ""%>value="9">Somaliska</option>
                            <option <%=language==10 ? "selected=\"selected\" " : ""%>value="10">Sorani</option>
                            <option <%=language==11 ? "selected=\"selected\" " : ""%>value="11">Spanska</option>
                            <option <%=language==12 ? "selected=\"selected\" " : ""%>value="12">Tigrinja</option>
                        </select>
                    </td>

                    <td>
                        <label>Branschkunskap</label>
                        <select name="profession">
                            <option <%=profession==0 ? "selected=\"selected\" " : ""%>value="0">Välj</option>
                            <option <%=profession==1 ? "selected=\"selected\" " : ""%>value="1">Administration, Ekonomi, Juridik</option>
                            <option <%=profession==2 ? "selected=\"selected\" " : ""%>value="2">Bygg och anläggning</option>
                            <option <%=profession==3 ? "selected=\"selected\" " : ""%>value="3">Data/IT</option>
                            <option <%=profession==4 ? "selected=\"selected\" " : ""%>value="4">Försäljning, inköp, marknadsföring</option>
                            <option <%=profession==5 ? "selected=\"selected\" " : ""%>value="5">Hantverksyrken</option>
                            <option <%=profession==6 ? "selected=\"selected\" " : ""%>value="6">Hotell, restaurang, storhushåll</option>
                            <option <%=profession==7 ? "selected=\"selected\" " : ""%>value="7">Hälso- och sjukvård</option>
                            <option <%=profession==8 ? "selected=\"selected\" " : ""%>value="8">Industriell tillverkning</option>
                            <option <%=profession==9 ? "selected=\"selected\" " : ""%>value="9">Installation, drift, underhåll</option>
                            <option <%=profession==10 ? "selected=\"selected\" " : ""%>value="10">Kropps- och skönhetsvård</option>
                            <option <%=profession==11 ? "selected=\"selected\" " : ""%>value="11">Kultur, media, design</option>
                            <option <%=profession==12 ? "selected=\"selected\" " : ""%>value="12">Ledningsarbete</option>
                            <option <%=profession==13 ? "selected=\"selected\" " : ""%>value="13">Naturbruk</option>
                            <option <%=profession==14 ? "selected=\"selected\" " : ""%>value="14">Naturvetenskapligt arbete</option>
                            <option <%=profession==15 ? "selected=\"selected\" " : ""%>value="15">Pedagogiskt arbete</option>
                            <option <%=profession==16 ? "selected=\"selected\" " : ""%>value="16">Sanering och renhållning</option>
                            <option <%=profession==17 ? "selected=\"selected\" " : ""%>value="17">Socialt arbete</option>
                            <option <%=profession==18 ? "selected=\"selected\" " : ""%>value="18">Säkerhetsarbete</option>
                            <option <%=profession==19 ? "selected=\"selected\" " : ""%>value="19">Tekniskt arbete</option>
                            <option <%=profession==20 ? "selected=\"selected\" " : ""%>value="20">Transport</option>
                        </select>
                    </td>

                    <td>
                        <label>Företagsnamn (minst tre tecken)</label>
                        <input type="text" name="company" title="Ditt sökord måste innehålla minst tre tecken" value="<%=company%>" />
                    </td>
                </tr>

                <tr>
                    <td colspan="3">
                        <input type="submit" value="Sök" />
                        <input type="reset" value="Rensa" />
                    </td>
                </tr>

                <tr>
                    <td colspan="3" class="message">
                        <%=message%>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>