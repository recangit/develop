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

    <div class="maincanvas">
        
        <div class="header"></div>

        <div class="subcanvas">
            <div class="content">
                <h1 class="banner">Allmänt om utveckling</h1>
                <p>
                    Namnkonventioner<br />
                    Domänen heter webapp.se därför bör både projekt och databas heta webapp.<br />
                    Paket i projektet vänder på domännamnet, se.webapp. Hela paketet blir då exempelvis se.webapp.pub.actions.<br />
                    Mappstrukturen är följande: src/java/se/webapp/pub/actions.<br /><br />
                    Tabeller bör ha namn som börjar med en versal och är ett substantiv. Fälten i tabellen kan vara gemena.
                </p>
                
                <p>
                    META-INF/context.xml
                    Definiera &lt;Context path=""/&gt; för att slippa ha hel sökväg i alla url:er.
                </p>
    
                <p>
                    deploy.properties ska innehålla<br />
                    HOME_DIR = /Users/bravo/NetBeansProjects/Cafestatist/<br />
                    och DOCUMENT_ROOT_DIR = /Users/bravo/NetBeansProjects/Cafestatist/web/<br />
                    Båda dessa filsystem variabler är föränderliga och skall därför inte definieras i klassen Resources.
                </p>
    
                <p>
                    Mappen test som ligger överst i projektet är dedikerad till Junit tester. Övriga tester läggs i någonstans src.
                </p>
    
                <p>Filer som laddas upp skall ligga direkt in i webmappen, alltså så här: /web/upload/pdf.</p>
    
                <p>En klass som inte innehåller instans variabler skall vara static.</p>
    
                <p>
                    Kasta undantag eller hantera dem. Att skriva ut till terminal eller logg är inte att hantera på ett korrekt sätt eftersom det förutsätter att jag ständigt tittar på dessa. Rätt sätt att hantera är att i catchen definiera ett alternativt beteende. Kan inte detta göras kastas undantaget till användaren så att det tydligt skrivs ut på websidan.
                </p>
                
                <p>
                    <h2>Allmänt</h2>
                    <br />
                    Uppgiften skalll bara lösas, bygg aldrig för framtida bruk (generalisering). Samtidigt skall system aldrig byggas så de låses in.<br />
                    Det jag släpper måste vara klart. Det vill säga testat, alla filer ska finnas och vara incheckade, <br />
    
                    <h2>Användargränssnitt</h2>
                    <br />
                    Ett gränssnitt skall alltid uppföra sig som användaren förväntar sig.
                    <ol>
                        <li>Knappar skall se ut som knappar, länkar skall se ut som länkar.</li>
                        <li>Former skall tydligt visa vilka fält som är obligatoriska. Har dessa ej fyllts i bör den information som skrivits finnas kvar. Töm inte formen.</li>
                        <li>Former skall vara uppbyggda på ett förutsägbart sätt. En sida listar alla poster, en sida för editering, en sida för att spara ner ny information.</li>
                    </ol>
                </p>
            </div>
        </div>
        
    

    </div>

    
</body>
</html>