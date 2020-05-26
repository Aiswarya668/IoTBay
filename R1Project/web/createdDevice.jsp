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
         <p class="right"> <a  class="button21" href="main.jsp">Main</a> </p>
                <div class="maincolumn2">
                    <div class="card">
                        
        
        <%
            int deviceID = 0; // Or a different default value
                try {
                    deviceID = Integer.parseInt(request.getParameter("DeviceID"));
                 } catch(NumberFormatException e) {
                    // log the error or ignore it
                 }
            String deviceName = request.getParameter("DeviceName");
            String type = request.getParameter("DeviceType");
            double cost = Double.parseDouble(request.getParameter("DeviceCost"));
            int stockQuantity = Integer.parseInt(request.getParameter("DeviceStock"));
            String description = request.getParameter("DeviceDescription");
            %>
            
    <body>     
            <h1>The following device was successfully created!</h1>
            <p class="p">Device name: <%= deviceName %> </p>
            <p class="p">Device type: <%= type %> </p>
            <p class="p">Device cost: $<%= cost %> </p>
            <p class="p">Device quantity: <%= stockQuantity %> </p>
            <p class="p">Device description: <%= description %> </p>
          
       
            <%
                Device device = new Device(deviceID, deviceName, type, cost, stockQuantity, description);
                DBConnector dbConnector = new DBConnector();
                Connection conn = dbConnector.openConnection();
                DBDeviceManager dbManager = new DBDeviceManager(conn);
                dbManager.addDevice(deviceName, type, cost, stockQuantity, 
                        description);
                session.setAttribute("device", device);
            %>
           
                    </div>
                </div>
                
    </body>
</html>