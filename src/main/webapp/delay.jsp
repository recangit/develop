<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delay</title>
        <link href="css/main.css" rel="stylesheet" type="text/css" />
        
        <script type="text/javascript">

    function doGet(url, m, type) {
        // Create instance of XMLHttpRequest
        var request = createRequest();

        if (request==null) {
            alert ("Your browser does not support AJAX!");
            return;
        }

        if(type == "xml") {
            handlerFunction = getReadyStateHandlerXML(request, m);
        }
        else if(type == "txt") {
            handlerFunction = getReadyStateHandlerTEXT(request, m);
        }

        // The method specified in property onreadystatechange will be called by the server
        request.onreadystatechange = handlerFunction;
	
        // Method, address, asynchronous
        request.open("GET", url, true);
	
        request.send(null);
    }

    function createRequest() {
        var request = null;
    
        try {
            // Create XMLHttpRequest in non-Microsoft browsers like Firefox, Opera 8.0, Safari
            request = new XMLHttpRequest();
        }
        catch(e) {
            try {
                // Try to create XMLHttpRequest in later versions of Explorer
                request = new ActiveXObject("Msxml2.XMLHTTP");
            }
            catch(e) {
                // Try version supported by older versions of Explorer
                request = new ActiveXObject("Microsoft.XMLHTTP");
            }
        }
        return request;
    }

    function getReadyStateHandlerTEXT(request, method) {
        return function() {
            if(request.readyState == 4) {
                // Successful server response, method will be returned
                if(request.status == 200) {
                    if(request.responseText != 0) {
                        method(request.responseText);         
                    }
                }
                else {
                    alert("[getReadyStateHandlerTEXT] HTTP error " + request.status); 
                }
            }
        }
    }
    
    function populate(value) {
        document.getElementById('foo').innerHTML = value;    
    }
    </script>
    </head>

    <body>

        <jsp:include page="includes/menu.jsp" />
        
        <h1>Delay</h1>
        <a href="javaScript:doGet('delay', populate, 'txt');">Testa ajax</a>
        
        <div id="foo"></div>
        
    </body>
</html>