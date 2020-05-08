<%-- 
    Document   : login
    Created on : 26/04/2020, 6:52:19 PM
    Author     : seant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/iotbay.css">
        <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
        <title>Login</title>
    </head>
    <body>
        <div class="header">
            <jsp:include page="header.jsp" flush="true"/>
            <div class="navbar">
                <a href="index.jsp" style="float: left"><b>&lt;</b> Back to home</a>
            </div>
        </div>
        <div class="body">
            <h2>Login</h2>
            <div class="card">
                <form method="post" action="welcome.jsp">
                    <table>
                        <tr><td>Email</td><td><input type="text" placeholder="Email" name="email"></td></tr>
                        <tr><td>Password</td><td><input type="password" placeholder="Password" name="password"></td></tr>
                    </table>
                    <div style="float: right">
                        <button type="button" onclick="location.href = 'index.jsp'">Cancel</button> <input type="submit" value="Login">

                    </div>

                </form>
            </div>
        </div>
        <footer class="footer">
            Copyright © 2020 IoTBay. All rights reserved.<br><a id="topLink" href="./login.jsp#top">Back to Top</a>
        </footer>
    </body>
</html>
