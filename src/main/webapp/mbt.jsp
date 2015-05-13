<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Model Based Test</title>
        <link href="css/main.css" rel="stylesheet" type="text/css" />
    </head>
    <body>

        <jsp:include page="includes/menu.jsp" />

        <h1>Model Based Test</h1>
        
        <table class="mainCanvas">
            <tr>
                <td class="anteckning">
                    <h2>Modellen</h2>
Modellen som beskriver tänkbara beteenden i vår SUT.
Vid första start är inloggningsuppgifter inte skrivna.
Checkbox som markerar att inloggningsuppgifterna ska sparas är inte iklickad.
När korrekta inloggningsuppgifter är angivna startas programmet.
När programmet stoppas så skall det startas på nytt, nu utan att inloggningsuppgifter behöver anges igen eftersom checkboxen markerats.
                </td>
            </tr>

            <tr>
                <td>
                    <img src="pictures/Login.png">
                </td>
            </tr>

            <tr>
                <td>
                    <h2>Grafen beskriver följande flöde:</h2>
                    <ol>
                        <li>Gå till Login med initparametrar valid=false, check=false, iteration=0.</li>
                        <li>Verify sätter parameter iteration till ett.</li>
                        <li>Det finns nu två möjliga steg, Start och Login men eftersom Start kräver att parameter valid är true så kan vi endast gå tillbaka till Login.</li>
                        <li>Login vänder på parameter Valid (valid=!valid), är den false blir den true, och motsatt. I detta fall blir den satt till true.</li>
                        <li>Verify sätter parameter Iteration till två.</li>
                        <li>Vi kan gå in i Start eftersom parameter Valid nu är true.</li>
                        <li>Programmet stoppas och vill startas på nytt. Det finns tre vägar att gå, Start, Exit och Login.<br />
                                Eftersom Start kräver att parameter check är true kan vi inte gå dit.<br />
                                Parameter Iterator är två och Exit kräver att den skall vara minst fyra, vi kan inte gå dit.<br />
                                Återstår Login.</li>
                        <li>Login vänder på parameter Check, är den false blir den true, och motsatt. I detta fall blir den satt till true.</li>
                        <li>Verify sätter parameter Iteration till tre.</li>
                        <li>Vi går nu hela vägen till Stopp. Återigen finns det tre vägar att välja på, Start, Exit och Login.<br />
                                Denna gång kan vi gå till Start med parametern Check satt till true.<br />
                                Räkna upp iteration.</li>
                        <li>Åter tillbaka till Stop. Nu är Exit öppet eftersom iteration har värde fyra.</li>
                        <li>Utöver ovanstående finns också en vertex med nyckelordet BLOCKED. Denna och de edges som leder till den kommer inte att exekvera.</li>
                    </ol>
                </td>
            </tr>

            <tr>
                <td class="kod">
                    <h2>Skapa ett projekt</h2>
MBTtest
| |-pom.xml
| +-src
| | +-test
| | | +-java
| | | | +-se
| | | | | +-af
| | | | | | +-mbt
| | | +-resources
| | | | |-log4j.xml
| | | | |-Login.graphml

Pomfilen skall innehålla följande:
&lt;dependencies&gt;
	&lt;dependency&gt;
        &lt;groupId&gt;org.graphwalker&lt;/groupId&gt;
        &lt;artifactId&gt;graphwalker&lt;/artifactId&gt;
        &lt;version&gt;LATEST&lt;/version&gt;
    &lt;/dependency&gt;
	&lt;dependency&gt;
		&lt;groupId&gt;org.graphwalker&lt;/groupId&gt;
		&lt;artifactId&gt;graphwalker-core&lt;/artifactId&gt;
		&lt;version&gt;3.1.0&lt;/version&gt;
	&lt;/dependency&gt;
	&lt;dependency&gt;
		&lt;groupId&gt;org.graphwalker&lt;/groupId&gt;
		&lt;artifactId&gt;graphwalker-java&lt;/artifactId&gt;
		&lt;version&gt;3.1.0&lt;/version&gt;
	&lt;/dependency&gt;
	&lt;dependency&gt;
		&lt;groupId&gt;org.graphwalker&lt;/groupId&gt;
		&lt;artifactId&gt;graphwalker-maven-plugin&lt;/artifactId&gt;
		&lt;version&gt;3.1.0&lt;/version&gt;
	&lt;/dependency&gt;
&lt;/dependencies&gt;

&lt;build&gt;
	&lt;plugins&gt;
		&lt;plugin&gt;
			&lt;groupId&gt;org.graphwalker&lt;/groupId&gt;
			&lt;artifactId&gt;graphwalker-maven-plugin&lt;/artifactId&gt;
			&lt;version&gt;3.1.0&lt;/version&gt;
			&lt;executions&gt;
				&lt;execution&gt;
					&lt;id&gt;generate-test-sources&lt;/id&gt;
					&lt;phase&gt;generate-test-sources&lt;/phase&gt;
					&lt;goals&gt;
					    &lt;goal&gt;generate-test-sources&lt;/goal&gt;
					&lt;/goals&gt;
				&lt;/execution&gt;
			&lt;/executions&gt;
		&lt;/plugin&gt;
	&lt;/plugins&gt;
&lt;/build&gt;

Nu kan stubbar skapas med: mvn generate-test-sources

import org.graphwalker.java.annotation.Model;
import org.graphwalker.java.annotation.Vertex;
import org.graphwalker.java.annotation.Edge;

@Model(file = "Login.graphml")
public interface Login {

    @Vertex()
    void s_Login() throws Exception ;

    @Edge()
    void a_Login();
    ...
}

public class LoginTest extends ExecutionContext implements Login {

    public final static Path MODEL_PATH = Paths.get("src/test/resources/Login.graphml");
    
    @Override
    public void a_Login() {
        System.out.println("Inloggning, valid [" + this.getAttribute("valid") + "] och check [" + this.getAttribute("check") + "]");
    }
    
    @Override
    public void s_Login() throws Exception {}
    ...  

    /**
     * Smoke test
     * Använder A* algoritmen.
     * Går raka vägen från a_Login till s_Exit.
     */
    @Test
    public void runSmokeTest() {
        try {
            
        new TestBuilder()
            .setModel(MODEL_PATH)
            .setContext(new LoginTest())
            .setPathGenerator(new AStarPath(new ReachedVertex("s_Exit")))
            .setStart("a_Login")
            .execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Funktionell test
     * Startar på a_Login och passerar det antal edges som angets i procent.
     */
    @Test
    public void runFunctionalTest() {
        new TestBuilder()
            .setModel(MODEL_PATH)
            .setContext(new LoginTest())
            .setPathGenerator(new RandomPath(new EdgeCoverage(100)))
            .setStart("a_Login")
            .execute();
    }

    /**
     * Stabilitet
     * Kör modellen så länge som angets i tid.
     */
    @Test
    public void runStabilityTest() {
        new TestBuilder()
            .setModel(MODEL_PATH)
            .setContext(new LoginTest())
            .setPathGenerator(new RandomPath(new TimeDuration(10, TimeUnit.SECONDS)))
            .setStart("a_Login")
            .execute();
    }
}    
                </td>
            </tr>

            <tr>
                <td>
                    <h2>Traversering</h2>
                    Beroende på vilken generator och vilket stoppkriteria som används skapas olika typer av tester.<br />
                    <br />
                    Typer av generatorer:
                    <ul>
                        <li>AStarPath, går den kortaste vägen genom modellen till stoppkriteriet.</li>
                        <li>RandomPath, går en slumpartad väg genom modellen till stoppkriteriet.</li>
                    </ul>

                    Typer av stoppkriteria:
                    <ul>
                        <li>edge_coverage(anges i procent)</li>
                        <li>vertex_coverage(anges i procent)</li>
                        <li>reached_vertex(Namn på vertex)</li>
                        <li>reached_edge(Namn på edge)</li>
                        <li>time_duration(Anges i sekunder)</li>
                        <li>never</li>
                    </ul>
                </td>
            </tr>

            <tr>
                <td>
                    <h2>GraphWalker</h2>
                    Kommandon:
                    <ul>
                        <li>java -jar graphwalker.jar --debug, -d</li>
                        <li>java -jar graphwalker.jar --help, -h</li>
                        <li>java -jar graphwalker.jar --version, v</li>
                    </ul>

                    Testkör, dryrun:
                    <ul>
                        <li>java -jar graphwalker.jar offline -m src/test/resources/Login.graphml "a_star(reached_vertex(s_Exit))"</li>
                        <li>java -jar graphwalker.jar offline -m src/test/resources/Login.graphml "a_star(reached_edge(a_Verify))"</li>
                        <li>java -jar graphwalker.jar offline -m src/test/resources/Login.graphml "random(time_duration(10))"</li>
                        <li>java -jar graphwalker.jar offline -m src/test/resources/Login.graphml "random(edge_coverage(50))"</li>
                        <li>java -jar graphwalker.jar offline -m src/test/resources/Login.graphml "random(vertex_coverage(50))"</li>
                        <li>java -jar graphwalker.jar offline -m src/test/resources/Login.graphml "random(reached_vertex(s_Exit))"</li>
                        <li>java -jar graphwalker.jar offline -m src/test/resources/Login.graphml "random(length(8))"</li>
                    </ul>
                </td>
            </tr>

            <tr>
                <td class="anteckning">
                    <h2>Osorterat junk</h2>
Noder är antingen vertex eller edge.
En vertex, ritas med en box. Dessa motsvarar ett tillstånd och är ett lämpligt ställe att göra våra tester, assertions.
En edge beskriver övergången från en node till en annan. De ritas ut som pilar. Här definieras händelser och villkor.
Namn (label) på vertex bör prefixas med s för att tydliggöra att det är ett state.
Namn (label) på edges prefixas med a för att tydliggöra att det är en action.
Händelser och villkor kan endast anges i edges. Formen är label/action1=parameter;action2=parameter;[villkor].???????????

En Tillståndsmaskin (State Machine) är en modell som används för att beskriva skeenden i formen tillstånd och villkor för övergång från ett tillstånd till ett annat.
FSM: Finite State Machine Model
EFSM: Extended Finite State Machine Model

Verktyg
http://www.yworks.com
Finns för olika plattformar. Lättast är att endast ladda ner jar-filen och starta med java -jar [PATH]yed.jar. Exempel: java -jar /usr/local/yEd/yed.jar

Ladda ner "Standalone CLI" från http://graphwalker.org/download.
                </td>
            </tr>
        </table>
        
    </body>
</html>