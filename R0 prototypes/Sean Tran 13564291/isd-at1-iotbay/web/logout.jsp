<%-- 
    Document   : logout
    Created on : 26/04/2020, 6:53:04 PM
    Author     : seant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/iotbay.css">
        <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">

        <title>Logout</title>
    </head>
    <body>
        <div class="header">
            <jsp:include page="header.jsp" flush="true"/>
            <div class="navbar">

            </div>
        </div>
        <div class="body">
            <h2>Logout</h2>
            <div class="card">
                <p>You have been logged out. Click <a href="index.jsp">here</a> to go back to the home page.</p>
            </div>
        </div>
        <footer class="footer">
            Copyright © 2020 IoTBay. All rights reserved.<br><a id="topLink" href="./logout.jsp#top">Back to Top</a>
        </footer>
        <% session.invalidate();%>
    </body>
</html>
