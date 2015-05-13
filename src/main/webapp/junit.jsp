<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Junit-tester</title>
        <link href="css/main.css" rel="stylesheet" type="text/css" />
    </head>
    <body>

        <jsp:include page="includes/menu.jsp" />

        <h1>Junit.4.11</h1>

        <table class="mainCanvas">
            <tr>
                <td class="anteckning">
<h2>Rules, basklasser</h2>
<h3>ErrorCollector &#x2714;</h3>
ErrorCollector exekverar samtliga tester, oavsett de går igenom eller inte, och visar resultatet efteråt.

<h3>ExpectedException &#x2714;</h3>
Assert vilka Exceptions som skall slängas.

<h3>ExternalResource &#x2714;</h3>
Ersätter tidigare before och after metoder.

<h3>TemporaryFolder &#x2714;</h3>
Skapa temporära filer och foldrar som raderas efter testen.

<h3>TestName &#x2714;</h3>
Visar namnet på test, anropande metod.

<h3>TestWatcher</h3>
add logic at events during method execution

<h3>Timeout</h3>
cause test to fail after a set time

<h3>Verifier</h3>
fail test if object state ends up incorrect

<h3>TestRule &#x2714;</h3>
Egen implementation som kan göras utöver basklasserna.
                </td>
            </tr>
            <tr>
                <td class="anteckning">
<h2>Övrigt</h2>
<h3>Categories</h3>
<h3>ExecutionOrder &#x2714;</h3>
<h3>Matchers</h3>
<h3>Mockito &#x2714;</h3>
<h3>Parameters &#x2714;</h3>
                </td>
            </tr>
            <tr>
                <td>
                    <ul>
                        <li><a href="http://coders-kitchen.com/2012/05/06/tutorial-junit-rule/">Tutorial</a></li>
                        <li><a href="http://blog.schauderhaft.de/2011/07/24/rules-in-junit-4-9-beta-3/">Tutorial</a></li>
                        <li><a href="http://junit-team.github.io/junit/javadoc/4.10/index.html?org/junit/Rule.html">API</a></li>
                        <li><a href="http://www.hascode.com/2012/02/ordering-your-junit-rules-using-a-rulechain/">RuleChain (bl.a)</a></li>
                        <li><a href="https://code.google.com/p/hamcrest/wiki/Tutorial">Hamcrest Matchers</a></li>
                        <li><a href="http://www.silverbaytech.com/2013/01/28/junit-tricks-part-2-junit-rules/">Tutorial</a></li>
                    </ul>
                </td>
            </tr>
            <tr>
                <td class="anteckning">
A TestRule is an alteration in how a test method, or set of test methods, is run and reported. 
A TestRule may add additional checks that cause a test that would otherwise fail to pass, 
or it may perform necessary setup or cleanup for tests, or it may observe test execution to report it elsewhere. 
TestRules can do everything that could be done previously with methods annotated with Before, After, 
BeforeClass, or AfterClass, but they are more powerful, and more easily shared between projects and classes. 
The default JUnit test runners for suites and individual test cases recognize TestRules introduced in two 
different ways. Rule annotates method-level TestRules, and ClassRule annotates class-level TestRules. 
See Javadoc for those annotations for more information. Multiple TestRules can be applied to a test or 
suite execution. The Statement that executes the method or suite is passed to each annotated Rule in turn, 
and each may return a substitute or modified Statement, which is passed to the next Rule, if any. 
For examples of how this can be useful, see these provided TestRules, or write your own:

The resource has to implement the MethodRule interface (or extend ExternalResource, which is more convenient). 
Your test case just uses the resource and doesn’t mind about the rest.
                </td>
            </tr>
        </table>
    </body>
</html>