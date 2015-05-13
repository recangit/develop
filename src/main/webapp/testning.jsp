<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Testning</title>
        <link href="css/main.css" rel="stylesheet" type="text/css" />
    </head>
    <body>

        <jsp:include page="includes/menu.jsp" />

        <h1>Testning</h1>

        <table class="mainCanvas">
            <tr>
                <td>                
                    <h2>Terminologi</h2>
                    <ul>
                        <li><a href="http://en.wikipedia.org/wiki/Data-driven_testing">Data-driven_testing</a></li>
                        <li><a href="http://safsdev.sourceforge.net/FRAMESDataDrivenTestAutomationFrameworks.htm">TestAutomationFrameworks</a></li>
                    </ul>

                    <table class="listCanvas">
                        <tr>
                            <td>SUT</td><td>System Under Test</td>
                        </tr>
                        <tr>
                            <td>DDT</td><td>Data Driven Test</td>
                        </tr>
                        <tr>
                            <td>BDD</td><td>Behaviour Driven Development</td>
                        </tr>
                        <tr>
                            <td>MBT</td><td>Model Based Test</td>
                        </tr>
                    </table>
                </td>
            </tr>
            
            <tr>
                <td class="anteckning">
<h3>Acceptanstest</h3>
Utförs i en produktionslik miljö och med egna testdata
Ska validera verksamhetsprocessen (kundens arbetssätt)
Utförs parallellt med projektets sprintar och iterationer
Uppfyller kundens förväntningar

Syfte och mottagare
Användare
    Gör systemen vad som är tänkt
Beställare
    Har vi fått det vi beställt
    Kan vi ta ansvar och förvalta
Produktion
    Kan vi driftsätta och övervaka
Funktionell
    Är det tillräckligt hög kvalitet
                </td>
            </tr>
        </table>

    </body>
</html>