<%-- 
    Document   : listDevices
    Created on : 31/05/2020, 1:07:28 PM
    Author     : sanya dua 

    TO BE REMOVED 
    CREATED FOR DUMMY PURPOSE

--%>

<%@page import="java.util.ArrayList"%>
<%@page import="uts.isd.model.iotbay.dao.DBDeviceManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="uts.isd.model.iotbay.dao.DBConnector"%>
<%@page import="uts.isd.model.Device"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    //User user = (User)session.getAttribute("user");
    DBConnector dbConnector = new DBConnector();
    Connection conn = dbConnector.openConnection();
    DBDeviceManager dbManager = new DBDeviceManager(conn);
    ArrayList<Device> deviceList = dbManager.fetchDevice();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IOTBay | List of Devices</title>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <style>
            .iot-center {
                display:flex; 
                align-items: center; 
                justify-content: center;
            }
        </style>
    </head>
    <body
        <div class="">

        <a href="/">
            <img src="images/Logo.png" alt="LOGO" style="width:15%; height:10%" class="left"/>
        </a>

        <hr/>
        <h3 class="iot-center">List of all Devices</h3>

        <br />
        <!-- Search Form-->
        <form>
            <div class="iot-center">
                <div class="col-sm-3">
                    <input class="form-control" type="search" name="id" placeholder="Enter Name or ID">        
                </div>

                <div class="col-sm-2">
                    <input type="submit" class="btn btn-secondary btn-block" value="Search">        
                </div>
            </div>
        </form>

        <div class="iot-center" style="margin:40px;">
            <table class="table table-hover"> 
                <thead>
                    <tr>
                        <th>Device ID</th>
                        <th>Device Name</th>
                        <th>Type</th>
                        <th>Cost</th>
                        <th>In Stock</th>
                        <th>Description</th>
                        <th></th>
                    </tr>
                </thead>

                <tbody>
                    <% for (Device dv : deviceList) {%>

                    <tr>
                        <td><%= dv.getDeviceID() %></td>
                        <td><%= dv.getDeviceName()%></td>
                        <td><%= dv.getType()%></td>
                        <td><%= dv.getCost()%></td>
                        <td><%= dv.getStockQuantity() %></td>
                        <td><%= dv.getDescription()%></td>
                        <td>
                            <% if(dv.getStockQuantity() > 0){ %>
                            <a href="/CreateOrder?id=<%= dv.getDeviceID() %>" class="btn btn-success iot-center">
                                Order
                            </a>
                            <%} else {%>
                             <p style="color: red;">
                                Out of stock
                            </p>
                            <% } %>
                        </td>
                    </tr>
                    <%}%>
                </tbody>
            </table> 

        </div>

    </div>


</body>
</html>
