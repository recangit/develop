<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Maven</title>
        <link href="css/main.css" rel="stylesheet" type="text/css" />
    </head>
    <body>

        <jsp:include page="includes/menu.jsp" />

        <h1>Maven</h1>
        
        <table class="mainCanvas">
            <tr>
                <td class="anteckning">
<h3>Skapa ett nytt jobb</h3>
mvn archetype:generate -DgroupId=se.recan.app.maven -DartifactId=MavenTest -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
Den skapade pomfilen är i det närmaste tom men den effektiva pomfilen innehåller allt som behövs för att kunna köra alla default kommandon.

<h3>Men hur funkar detta då?</h3>
maven-site-plugin
http://whatiscomingtomyhead.wordpress.com/2011/06/05/maven-3-site-plugin-how-to/
http://maven.apache.org/plugins/maven-site-plugin/maven-3.html

<h3>maven-compiler-plugin</h3>
Finns i effektiva pomfilen men kan överlagras för att lägga till funktionalitet, exempelvis öka minneskapacitet för kompilering.

<h3>Maven Surefire är ett paket med plugins</h3>
I den effektiva pomen finns maven-surefire-plugin vars ända goal är test.
Den tar argument som: mvn -D test=DennaKlass#DennaMetod test

<h3>maven-surefire-report-plugin</h3>
Har två goals:
surefire-report:report och surefire-report:report-only
Denna finns ej med i den effektiva pomfilen men kan ändå köras vilket är obegripligt. Dock måste den överlagras för att bli en del av site dokumentationen.

<h3>mvn site</h3>
Genererar target/site/index.html (och andra htmlfiler). Här finns information om projektet men inget om de körda testerna.

<h3>mvn test</h3>
Genererar target/surefire-reports/filer i txt och xml-format.

<h3>surefire-report:report</h3>
Kör testerna, genererar ovanstående rapporter parsar dem till html och lägger imappen site.
Ingen övrig projekt information skapas.
Bilder och stylesheets följer inte med.

<h3>surefire-report:report-only</h3>
Skapar samma rapport som ovan från tidigare kompilerad kod. Lägg till clean före och detta blir ett mycket meningslöst dokument.

<h3>maven-surefire-report-plugin</h3>
För att få in rapporter om körda tester skall maven-surefire-report-plugin överlagras.<br>
                    <div class="kod">
&lt;reporting&gt;
    &lt;plugins&gt;
	&lt;plugin&gt;
            &lt;groupId&gt;org.apache.maven.plugins&lt;/groupId&gt;
            &lt;artifactId&gt;maven-surefire-report-plugin&lt;/artifactId&gt;
            &lt;version&gt;2.9&lt;/version&gt;
	&lt;/plugin&gt;
</div>
Ytterligare pluginer kan läggas in.
<div class="kod">
	&lt;plugin&gt;
            &lt;groupId&gt;org.codehaus.mojo&lt;/groupId&gt;
            &lt;artifactId&gt;findbugs-maven-plugin&lt;/artifactId&gt;
            &lt;version&gt;2.3.1&lt;/version&gt;
	&lt;/plugin&gt;
    &lt;/plugins&gt;
&lt;/reporting&gt;
                    </div>
                </td>
            </tr>

            <tr>
                <td>

<h3>Clean lifecycle phases</h3>
<table class="listCanvas">
<tr><th>Phases</th><th>Beskrivning</th></tr>
<tr><td>pre-clean</td><td></td></tr>
<tr><td>clean</td><td></td></tr>
<tr><td>post-clean</td><td></td></tr>
</table>

</td>
            </tr>

            <tr>
                <td>

                    <h3>Default lifecycle phases</h3>
                    Detta är bara några av phaserna som ingår i denna lifecykle.
                    <table class="listCanvas">
                        <tr><th>Phases</th><th>Beskrivning</th></tr>
                        <tr><td>validate</td><td>Validera att projektet har allt som krävs för att utföra byggprocessen</td></tr>
                        <tr><td>initialize</td><td>Exempelvis sätta properties</td></tr>
                        <tr><td>compile</td><td>Kompilera projektet</td></tr>
                        <tr><td>test-compile</td><td>Kompilera testkoden</td></tr>
                        <tr><td>test</td><td>Kör projektets tester</td></tr>
                        <tr><td>package</td><td>Paketera projektet till exempelvis .jar</td></tr>
                        <tr><td>integration-test</td><td>Deploya paketet till miljö där integrationstester kan köras</td></tr>
                        <tr><td>verify</td><td>Verifiera projektet</td></tr>
                        <tr><td>install</td><td>Installera projektet i ett lokalt repository</td></tr>
                        <tr><td>deploy</td><td>Installera i remote repository</td></tr>
                        <!--tr><td>clean</td><td>Radera Target Directoryt</td></tr>
                        <tr><td>site</td><td>Generera dokumentation</td></tr-->
                    </table>


</td>
            </tr>

            <tr>
                <td>

                    <h3>Site lifecycle phases</h3>
<table class="listCanvas">
<tr><th>Phases</th><th>Beskrivning</th></tr>
<tr><td>pre-site</td><td></td></tr>
<tr><td>site</td><td>Generera dokumentation</td></tr>
<tr><td>post-site</td><td></td></tr>
<tr><td>site-deploy</td><td></td></tr>
</table>

                </td>
            </tr>
        </table>
        
    </body>
</html>