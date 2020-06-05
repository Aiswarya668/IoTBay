<%-- 
    Document   : welcome
    Created on : Apr 14, 2020, 9:52:20 PM
    Author     : aiswaryarajeev
--%>

<%@page import="java.sql.Connection"%>
<%@page import="uts.isd.model.*"%>
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
    <%
        // check whether newly registered or returning
        Boolean newAccount = Boolean.valueOf(request.getParameter("NewAccount"));
        
        Customer customer = (Customer) session.getAttribute("customer");
        Staff staff = (Staff) session.getAttribute("staff");
    %>
    <body>
        <img src="images/Logo.png" alt="LOGO" style="width:20%; height:10%" class="left"/>
        <p class="right"> <a class="button21" href="logout.jsp">Sign out</a> </p>
        <p class="right"> <a  class="button21" href="main.jsp">Main</a> </p>
        <div class="maincolumn2">
            <div class="card">
                <body>
                    <% if (customer != null) { %>
                    <% if (newAccount) {%>
                    <h1>Hello ${customer.firstName} ${customer.lastName} !</h1>
                    <% } else {%>
                    <h1>Welcome back ${customer.firstName} ${customer.lastName} !</h1>
                    <% }%>
                    <p>
                        To visit your profile, click on 'Main'. 
                        To view our catalogue of product devices, click the button below!
                    </p>
                    <a class ="button21" href="ViewDeviceServletUsers">Device Catalogue</a>
                    <% } %>
                    <% if (staff != null) { %>
                    <h1>Welcome back ${staff.firstName} ${staff.lastName} !</h1>
                    <p>
                        To visit your profile, click on 'Main'. 
                        To view the catalogue of product devices, click "Device Catalogue".
                        To add product devices, click "Add Devices"
                    </p>
                    <a class="button21" href="ViewDeviceServletUsers">Device Catalogue</a>
                    <a class="button21" href="addDevice.jsp">Add Devices</a>
                    <% } %>  
            </div>
        </div>
    </body>
</html>
