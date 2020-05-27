<%-- 
    Document   : index
    Created on : Apr 14, 2020, 9:05:52 PM
    Author     : aiswaryarajeev
--%>

<%@page import="uts.isd.controller.ConnServlet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/webpage.css">
        <title>Landing Page</title>
    </head>
    <body>
         <img src="images/Logo.png" alt="LOGO" style="width:20%; height:10%" class="left"/>
         <p class="right"> <a class ="button21" href="browseCatalogue.jsp">Browse as Guest</a> </p>
                <div class="maincolumn1">
                    <div class="card">
        <h1>Welcome</h1>
        <p> New to IoTBay? </p>
        <a class="button1" href="register.jsp">Register</a>
        <p> Returning customer </p>
        <a class ="button2" href="login.jsp">Login</a>

                    </div>
                </div>
        <jsp:include page="./ConnServlet" flush="true" />
    </body>
</html>
