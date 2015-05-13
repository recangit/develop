<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Protractor</title>
        <link href="css/main.css" rel="stylesheet" type="text/css" />
    </head>
    <body>

        <jsp:include page="includes/menu.jsp" />

        <h1>Protractor</h1>

        <table class="mainCanvas">
            <tr>
                <td class="anteckning">                
                    <h2>Om...</h2>
Det fina med Angular är att det designades för att vara testbart. Testerna använder Jasmines syntax vilket innebär att unit och end2end tester skrivs på samma sätt. 

Protractor är ett ramverk framtaget av Google för att end2end testa Angular. Utöver Jasmine krävs ett kodlager som interagerar med browsern samt att Selenium Webdriver är installerad.

Angular kompilerar DOM-trädet, initierar och startar applikationer genom en process kallad bootstrap. Bootstrapping kan göras automatiskt genom att sätta direktivet ng-app på ett element som då beskriver inom vilka element applikation befinner sig. Sätts taggen inte kommer body användas som default. Manuell bootstrapping sätts via JavaScript och använder sig inte av ng-app. 

Ur ett utvecklar perspektiv är det gott och väl att använda manuell bootstrapping. Protractor är däremot beroende av att det finns ett (och endast ett) ng-app direktiv. Utan detta kan alltså inga end2end tester med Protractor göras.

Naturligtvis är inte testbarhet det mest primära i en applikation. Rimligt är att göra en avvägning, hur mycket tid förloras nu på att bygga in testbarhet och hur mycket vinner vi framöver.
                </td>
            </tr>
        </table>

    </body>
</html>