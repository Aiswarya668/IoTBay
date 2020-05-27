<%-- 
    Document   : logout
    Created on : Apr 22, 2020, 6:22:45 PM
    Author     : aiswaryarajeev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/webpage.css">
        <title>Logout</title>
    </head>
    <body>
        <img src="images/Logo.png" alt="LOGO" style="width:20%; height:10%" class="center"/>
        <div class="maincolumn1">
            <div class="card">

                <p>You have logged out! Click <a href="index.jsp">here</a> to go back to the home page!</p>
                <% session.invalidate();%>

            </div>
        </div>     
    </body>
</html>
