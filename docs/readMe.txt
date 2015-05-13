============================================================
DEPLOYA TILL TOMCAT MED MAVEN:

~/.m2/settings.xml SKALL INNEHÅLLA:

<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                              http://maven.apache.org/xsd/settings-1.0.0.xsd">
                              
	<servers>
		<server>
			<id>TomcatServer</id> Detta id är detsamma som används i pom.xml (<server>myserver</server>).
			<username>admin</username>
			<password>admin</password>
		</server>
	</servers>
                              
	<localRepository/>
	<interactiveMode/>
	<usePluginRegistry/>
	<offline/>
	<pluginGroups/>
	<mirrors/>
	<proxies/>
	<profiles/>
	<activeProfiles/>
</settings>

pom.xml:
<plugin>
	<groupId>org.codehaus.mojo</groupId>
	<artifactId>tomcat-maven-plugin</artifactId>
	<version>1.1</version>
	<configuration>
		<server>TomcatServer</server>
		<url>http://localhost:8080/manager/text</url>
		<path>/SimpleWebApp</path>
		<warSourceDirectory>${basedir}/target/${project.artifactId}</warSourceDirectory>
	</configuration>
</plugin>

exekvera med mvn tomcat:deploy
             mvn tomcat:redeploy
             mvn clean -D skipTests tomcat:redeploy
============================================================

SERVER
wsgen -verbose -keep -s src/main/java -d target/classes -cp target/classes se.recan.app.ws.PersonWSImpl
CLIENT
wsimport -verbose -keep -s src/test/java -d target/test-classes -p se.recan.app.ws http://localhost:8080/SimpleWebApp/personws?wsdl
