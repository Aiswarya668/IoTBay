<%-- 
    Document   : addDevice
    Created on : 24/05/2020, 5:01:46 PM
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

        <title>Device Creation</title>
    </head>
        <%
            String deviceNameErr = (String) session.getAttribute("deviceNameErr");
            String typeErr = (String) session.getAttribute("typeErr");
            String priceErr = (String) session.getAttribute("priceError");
            String stockErr = (String) session.getAttribute("stockErr");
            String descriptionErr = (String) session.getAttribute("descriptionErr");
        %>
    <body>
        <img src="images/Logo.png" alt="LOGO" style="width:20%; height:10%" class="left"/>
        <div class="maincolumn2">
            <div class="card">
                <h1>Create new device </span> </h1>
                <form method="post" method="get" action="DeviceCreationServlet">
                    <table>
                        <tr><td>Device Name:</td><td><input type="text" placeholder="<%=(deviceNameErr != null ? deviceNameErr :"Enter device name")%>" name="DeviceName"></td></tr  
                        <tr><td>Device Type:</td><td><input type="text" placeholder="<%=(typeErr != null ? typeErr :"Enter device type")%>" name="DeviceType"></td></tr>
                        <tr><td>Price ($):</td><td><input type="text" placeholder="<%=(priceErr != null ? priceErr :"Enter price $0.00")%>" name="DeviceCost"></td></tr>
                        <tr><td>Stock Quantity:</td><td><input type="text" placeholder="<%=(stockErr != null ? stockErr :"Enter stock quantity")%>" name="DeviceStock"></td></tr>
                        <tr><td>Description</td><td><input type="text" placeholder="<%=(descriptionErr != null ? descriptionErr :"Enter breif description")%>" name="DeviceDescription"></td></tr>   
                    </table>
                    <div>
                        <input class ="button4" type="submit" value="Create device">
                        <a class ="button3" href="index.jsp">Cancel</a>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
