<%-- 
    Document   : deleteDeviceConfirmation
    Created on : 01/06/2020, 11:23:25 AM
    Author     : aiswarya.r
    This page is the confirmation of device deletion
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
        <title>Device Creation Page</title>
    </head>
    
    <body>
         <%
                    Device device = (Device) session.getAttribute("device");
                %>
        <img src="images/Logo.png" alt="LOGO" style="width:20%; height:10%" class="left"/>
        
        <p class="right"> <a  class="button21" href="ViewDeviceServlet">Browse</a> </p>
        <div class="maincolumn2">
            <div class="card">

                <body>     
                    <h1>The device "<% out.println(device.getDeviceName());%>" with type "<% out.println(device.getType());%>" was successfully deleted!</h1>

            </div>
        </div>
    </body>
</html>