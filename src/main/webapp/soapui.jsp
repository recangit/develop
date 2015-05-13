<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>SoapUI</title>
        <link href="css/main.css" rel="stylesheet" type="text/css" />
    </head>
    <body>

        <jsp:include page="includes/menu.jsp" />

        <h1>SoapUI</h1>
        
        <table class="mainCanvas">
            <tr>
                <td class="anteckning">
Java - koppla ihop 2 services. Ta info från en och sätt i annan. Cucumber?
<h2>Krångel</h2>
SoapUI, eviware plugin i pom.xml förstör Selenium. Måste alltså kommenteras ut.

<h3>Skapa en Web Service</h3>
Skapa ett Web Service Endpoint Interface
Skapa en implementation av ovanstående
web.xml
sun-jaxws.xml

<h3>Skapa en Klient</h3>
wsimport -keep http://localhost:8080/SimpleWebApp/car?wsdl -d /home/recan/Dokument/NetBeans/SimpleWebApp/src/test/java/

<h3>Skapa ett soapUI projekt</h3>
                </td>
            </tr>
            
            <tr>   
                <td>
                    <ul>
                        <li><a href="car">Web Service</a></li>
                        <li><a href="car?wsdl">WSDL</a></li>
                        <li><a href="http://examples.javacodegeeks.com/enterprise-java/jws/jax-ws-web-services-on-tomcat/">JAX-WS Web Service</a></li>
                        <li><a href="http://examples.javacodegeeks.com/enterprise-java/jws/jax-ws-hello-world-example-rpc-style/">JAX-WS Web Service Client</a></li>
                        <li><a href="http://thomassundberg.wordpress.com/2011/11/16/testing-a-web-service-with-soapui-junit-maven-and-cucumber/">SoapUi och Cucumber</a></li>
                        <li><a href="http://www.soapui.org/Test-Automation/maven.html#1-maven">SoapUi och Maven</a></li>
                    </ul>
                </td>
            </tr>
        </table>
        
    </body>
</html>