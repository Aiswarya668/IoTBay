<%-- 
    Document   : browseCatalogueUsers
    Created on : 02/06/2020, 11:11:44 AM
    Author     : aiswarya.r
--%>

<%@page import="uts.isd.model.Staff"%>
<%@page import="uts.isd.model.Customer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.*"%>
<%@page import="java.sql.Connection"%>
<%@page import="uts.isd.model.Device"%>
<%@page import="uts.isd.model.iotbay.dao.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>




<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>  
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/webpage.css">
        <title>Device Catalogue</title>

    <img src="images/Logo.png" alt="LOGO" style="width:20%; height:10%" class="left"/>
    <%
        Device device = (Device) session.getAttribute("display");
        Customer customer = (Customer) session.getAttribute("customer");
        Staff staff = (Staff) session.getAttribute("staff");
    %>
    
    
    <% if (customer != null || staff != null) { %>
        <p class="right"> <a class="button21" href="main.jsp">Main</a> </p>
    <% } else { %>
        <p class="right"> <a class="button21" href="index.jsp">Home</a> </p>
    <% } %>
    
    
    
    <div class="deviceCatcolumn">
        <div class="deviceCatcard">

            </head>
            <body>

                <h1>Device Catalogue</h1>
                <form method="post" method="get">      
                        Search device: <input id="inputDeviceName" onkeyup="myFunction()" placeholder="Device Name" title="Type in a device name"> <input id="inputDeviceType" onkeyup="myFunction()" placeholder="Device Type" title="Type in a device type">
                    
                    <table id="deviceTable" class="device Table">
                        <tr>
                            <td>Device ID</td>
                            <td>Device Name</td>
                            <td>Type</td>
                            <td>Cost</td>
                            <td>Stock</td>
                            <td>Description</td>                          
                            <td> Actions</td>
                        </tr>

                        <c:forEach items="${display}" var="display">
                            <tr class="device tr">
                                <td>${display.deviceID }</td>
                                <td>${display.deviceName }</td>
                                <td>${display.type }</td>
                                <td>${display.cost }</td>
                                <td>${display.stockQuantity }</td>
                                <td>${display.description }</td>
                                <% if (staff != null) { %>
                                <td><p class="right"> <a class="button2" value='Update Device' href="UpdateDeviceServlet?DeviceID=${display.deviceID}&DeviceName=${display.deviceName}&DeviceType=${display.type}&DeviceCost=${display.cost} & DeviceStock=${display.stockQuantity}&DeviceDescription=${display.description}">Update</a> </p></td>
                                <td><p class="right"> <a class="button3" value='Delete Device' href="ViewDeleteDeviceServlet?DeviceID=${display.deviceID}&DeviceName=${display.deviceName}&DeviceType=${display.type}&DeviceCost=${display.cost} & DeviceStock=${display.stockQuantity}&DeviceDescription=${display.description}">Delete</a> </p></td>
                                <% } else if (customer != null || customer == null) { %>
                                <td><p class="right"> <a class="button1" href="createOrder.jsp ">Buy</a> </p></td>
                                <% }%>
                            </tr>
                        </c:forEach>
                    </table>


                    <script>
                        function myFunction() {
                            var input, input2, filter, filter2, table, tr, tdDeviceName, tdType, i, txtValue;
                            input = document.getElementById("inputDeviceName");
                            filter = input.value.toUpperCase();
                            input2 = document.getElementById("inputDeviceType");
                            filter2 = input2.value.toUpperCase();
                            table = document.getElementById("deviceTable");
                            tr = table.getElementsByTagName("tr");
                            for (i = 0; i < tr.length; i++) {
                                tdDeviceName = tr[i].getElementsByTagName("td")[1];
                                tdType = tr[i].getElementsByTagName("td")[2];
                                if (tdDeviceName && tdType) {
                                    txtValue = tdDeviceName.textContent || tdDeviceName.innerText;
                                    txtValue2 = tdType.textContent || tdType.innerText;
                                    if (txtValue.toUpperCase().indexOf(filter) > -1 && txtValue2.toUpperCase().indexOf(filter2) > -1) {
                                        tr[i].style.display = "";
                                    } else {
                                        tr[i].style.display = "none";
                                    }
                                }
                            }
                        }
                    </script>

                </form>
            </body>
        </div>
        <div class="footer">

        </div>
    </div>

</html>
