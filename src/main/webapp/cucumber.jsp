<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Cucumber</title>
        <link href="css/main.css" rel="stylesheet" type="text/css" />
    </head>
    <body>

        <jsp:include page="includes/menu.jsp" />

        <h1>Cucumber</h1>

        <table class="mainCanvas">
            <tr>
                <td>   
                    Cucumber är ett testramverk som används vid Behavior Driven Development (BDD).<br>
                    <img src="pictures/bdd.png">
                </td>
            </tr>
            <tr>
                <td class="anteckning">         

                    <h2>Features/Gherkin</h2>
Testerna definieras i språket Gherkin och sparas i en fil med filändelsen feature. 
En featurefil kan innehålla ett eller flera scenarion.
Ett scenariot beskrivs i formen Givet-när-så (Given-When-Then).
Featurefilen fyller både syftet att exekvera kod och att vara projektdokumentation.

Egenskap: En förklarande titel
    (Som)     Vem beskriver detta (Roll)
    (Vill)    Vad skall göras
    (För)     Vad är förväntat resultat

Bakgrund: Fri text

Scenario: Rubrik
    (Givet) Förutsättningar, startpunkt
    (När) Aktivitet, en action
    (Och) [Aktivitet, en action]
    (Men) [Inte detta]
    (Så) Resultat av föregående

Exempel:

Egenskap: Vilka jobb passar in på min profil
    Som arbetssökande
    Vill jag navigera till Min profil
    För att se vilka förfrågningar från arbetsgivare jag fått

Bakgrund: En arbetssökande ska snabbt få en överblick
över det intresse den skapade profilen gett 

Givet att jag navigerat till Min sida
    Och att jag är inloggad
När jag klickat på länken Min profil
    Och jag klickat på länken Förfrågningar
Så ska en lista med förfrågningar visas
    Men listan ska vara tom
                </td>
            </tr>
            <tr>
                <td>
                    <h2>Glue/Steps</h2>
                    <h2>PageObjects/View</h2>
                    
                    <h3>Namnkonvention</h3>

                    <table class="listCanvas">
                        <tr>
                            <th>Element</th>
                            <th>Variabelnamn</th>
                            <th>Metodnamn</th>
                        </tr>

                        <tr>
                            <td>Select</td>
                            <td>[namn]Select</td>
                            <td>select[Namn]</td>
                        </tr>
                        <tr>
                            <td>Button</td>
                            <td>[namn]Button</td>
                            <td>click[Namn]Button</td>
                        </tr><tr>
                            <td>Länkar</td>
                            <td>[namn]Link</td>
                            <td>click[Namn]Link</td>
                        </tr><tr>
                            <td>Inputfält</td>
                            <td>[namn]Input</td>
                            <td>set[Namn]Input</td>
                        </tr><tr>
                            <td>Resultatlistor</td>
                            <td>[namn]List</td>
                            <td>get[Namn]List</td>
                        </tr><tr>
                            <td></td>
                            <td></td>
                            <td>get[Namn]ListSize</td>
                        </tr><tr>
                            <td>Resultat/meddelande</td>
                            <td>searchMessage</td>
                            <td>getSearchMessage</td>
                        </tr>
                    </table>
                    
                    <h3>Hooks</h3>
                    
                    <h3>Gherkin is the format for Cucumber Specifications</h3>
                    
                    <ul>
                        <li><a href="http://www.weblogism.com/item/334/integration-tests-with-cucumber-jvm-selenium-and-maven">1</a></li>
                        <li><a href="http://cukes.info/step-definitions.html">2</a></li>
                        <li><a href="http://c0deattack.wordpress.com/2012/03/28/cucumber-jvm-with-cucumber-java-cucumber-junit-example/">3</a></li>
                    </ul>
                </td>
            </tr>
        </table>

    </body>
</html>