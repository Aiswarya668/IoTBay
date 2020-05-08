<%-- 
    Document   : index
    Created on : 26/04/2020, 6:46:30 PM
    Author     : seant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/iotbay.css">
        <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
        <title>Interface</title>
    </head>
    <body>
        <div class="header">
            <jsp:include page="header.jsp" flush="true"/>
            <div class="navbar">
                <a href="./login.jsp">Login</a>
                <a href="./register.jsp">Register</a>
            </div>
        </div>
        <div class="body">
            <h2>Home</h2>
        </div>
        <footer class="footer">
            Copyright © 2020 IoTBay. All rights reserved.<br><a id="topLink" href="./index.jsp#top">Back to Top</a>
        </footer>
    </body>
</html>
