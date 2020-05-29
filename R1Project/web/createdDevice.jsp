<%-- 
    Document   : createdDevice
    Created on : 26/05/2020, 5:09:08 PM
    Author     : aiswarya.r
--%>

<%@page import="java.sql.Connection"%>
<%@page import="uts.isd.model.Device"%>
<%@page import="java.util.Date"%>
<%@page import="uts.isd.model.iotbay.dao.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/webpage.css">
        <title>Welcome Page</title>
    </head>
    <body>
        <img src="images/Logo.png" alt="LOGO" style="width:20%; height:10%" class="left"/>
        <p class="right"> <a class="button21" href="logout.jsp">Sign out</a> </p>
        <p class="right"> <a  class="button21" href="index.jsp">Index</a> </p>
        <div class="maincolumn2">
            <div class="card">

                <body>     
                    <h1>The following device was successfully created!</h1>

            </div>
        </div>
    </body>
</html>