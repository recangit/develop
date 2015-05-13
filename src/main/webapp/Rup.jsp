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
                <h1 class="banner">Rational Unified Process (RUP)</h1>
                <a href="http://sv.wikipedia.org/wiki/Rational_Unified_Process">http://sv.wikipedia.org/wiki/Rational_Unified_Process</a>
                <p>RUP-projekt delas in i fyra faser som ett projekt går igenom efter hand. Varje fas avslutas med en väldefinierad milstolpe där specifika delmål måste ha uppfyllts. De fyra faserna är (engelsk benämning inom parentes):</p>
                <ul>
                    <li>Förberedelse (Inception)</li>
                    <li>Etablering (Elaboration)</li>
                    <li>Konstruktion (Construction)</li>
                    <li>Överlämning (Transition)</li>
                </ul>
    
                <p>Varje fas består av en eller flera iterationer. Antal iterationer och deras längd beror på själva projektet; ett stort projekt behöver ofta fler och längre iterationer. Under varje iteration utförs aktiviteter för att skapa en eller flera artefakter (dokument, program, eller liknande).</p>
                
                <h2>Förberedelse</h2>
                <p>Under förberedelsefasen fastställs den grundläggande idén om systemet genom att samla så mycket systemkrav som möjligt. En kandidatsystemarkitektur identifieras och designas samt även systemets nyckelfunktioner. Total kostnad och risk med utveckling av systemet identifieras och beslut om vidareutveckling ska fortlöpa görs. Förberedelsefasens mål är:</p>
                <ul>
                    <li>Förstå vad som ska byggas</li>
                    <li>Identifiera nyckelfunktioner i systemet</li>
                    <li>Designa en prototyp av systemets arkitektur</li>
                    <li>Identifiera och förstå projektets kostnad, plan och risk</li>
                    <li>Förbereda den stöttande omgivningen på projektet</li>
                </ul>
    
                <p>Normalt har ett projekt ca fem medlemmar under förberedelsefasen. De är oftast projektledaren, en eller två kravanalytiker, en arkitekt, en systemutvecklare och en kravställare (som finns på plats). Om projektet inte avslutar förberedelsefasen på ett fullgott sätt bör projektet avbrytas eller åtminstone tänkas igenom igen.</p>
                
                <h2>Etablering</h2>
                <p>Under etableringsfasen analyseras problemdomänen; en grundläggande arkitektur fastställs; en första projektplan framställs och de största riskerna med projektet elimineras. Hela systemet måste vara förstått och greppbart för att kunna göra beslut angående systemets arkitektur. Målen med etableringsfasen är:</p>
                <ul>
                    <li>Designa användningsfallsmodell</li>
                    <li>Konstruera en arkitekturprototyp</li>
                    <li>Granska och revidera risklista</li>
                    <li>Ta fram projektplan</li>
                </ul>
    
                <p>IBM Rational Software menar att etableringsfasen är den viktigaste av de fyra faserna. Vid fasens avslutande är analys och design av systemet färdigställt. Val görs om det är möjligt och rimligt att gå vidare med konstruktions- och överlämningsfaserna. Precis som i förberedelsefasen bör projektet avbrytas eller tänkas igenom igen om inte fasen avslutas på ett fullgott sätt.</p>
                
                <h2>Konstruktion</h2>
                <p>Under konstruktionsfasen utvecklas och testas systems funktioner. Målet med fasen är att utveckla produkter som har värde för kunden och systemets slutanvändare. Tillsammans med mjukvaran skrivs även manualer och dylikt under fasens gång.</p>
    
                <p>När konstruktionsfasen är slut tas beslut huruvida systemet kan släppas till slutanvändarna för att kunna användas på ett funktionsdugligt sätt i verksamheten. Kravet är ofta att en betaversion av systemet inte bör utsätta projektet för några större risker.</p>
    
                <h2>Överlämning</h2>
                <p>Meningen med överlämningsfasen är att leverera systemet till slutanvändaren. Problem med det levererade systemet tas även omhand i denna fas.</p>
            </div>
        </div>

        <div class="subcanvas">
            <div class="content">
                <h2>Usecase</h2>
                <ol>
                    <li>De olika perspektiven. Användaren, annan kod.</li>
                </ol>
            </div>
        </div>

        <div class="subcanvas">
            <div class="content">
                <h2>Iterationer</h2>
                <ol>
                    <li>Hur lång är en iteration?</li>
                    <li>Vad ska den innehålla?</li>
                    <li></li>
                </ol>
            </div>
        </div>

        <div class="subcanvas">
            <div class="content">
                <h2>Tester</h2>
                <ol>
                <li>Tester är processer som fortgår genom hela projektet.</li>
                <li>De ska enkelt kunna exekveras via terminalen.</li>
                <li>Ingen kod ska skrivas innan jag vet hur koden ska testas.</li>
                <li>Tester ska kunna kontrollera sina egna resultat. Det är meningslöst att låta ett test generera resultat som jag i efterhand inte förstår.</li>
                <li>Tester delas upp i Unittester och funktionstester.</li>
                <li></li>
                <li></li>
                <li></li>
                </ol>
            </div>
        </div>
        

    </div>

</body>
</html>