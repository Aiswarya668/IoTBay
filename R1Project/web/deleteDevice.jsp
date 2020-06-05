<%-- 
    Document   : delete
    Created on : Apr 30, 2020, 12:01:31 AM
    Author     : aiswaryarajeev
    This page takes in the parametrs of ViewDeleteDeviceServlet to then prompt device deletion 
--%>

<%@page import="uts.isd.model.Device"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/webpage.css">
        <title>Delete Page</title>
    </head>

    <body>
        <%
            String deletedeviceNameErr = (String) session.getAttribute("deletedeviceNameErr");
            String deletetypeErr = (String) session.getAttribute("deletetypeErr");
            String deletepriceErr = (String) session.getAttribute("deletepriceErr");
            String deletestockErr = (String) session.getAttribute("deletestockErr");
            String deletedescriptionErr = (String) session.getAttribute("deletedescriptionErr");
            String exceptionErr = (String) session.getAttribute("exceptionErr");
            String deleteddMsg = request.getParameter("deleteddMsg");
            String deviceEmptyErr = (String) session.getAttribute("deviceEmptyErr");
            
            Device device = (Device) session.getAttribute("device");
        %>

        <img src="images/Logo.png" alt="LOGO" style="width:20%; height:10%" class="left" />
        <p class="right"> <a class="button21" href="main.jsp">Main</a> </p>
        <p class="right"> <a class="button21" href="logout.jsp">Logout</a> </p>
        <div class="maincolumn2">
            <div class="card">
                <h1>Delete device</h1>
                <form method="get" action="DeleteDeviceServlet">
                    <p class ="error"><%=(deviceEmptyErr != null ? deviceEmptyErr : "")%></p>
                    <p class ="error"><%=(deletedeviceNameErr != null ? deletedeviceNameErr : "")%></p>
                    <p class ="error"><%=(deletetypeErr != null ? deletetypeErr : "")%></p>
                    <p class ="error"><%=(deletepriceErr != null ? deletepriceErr : "")%></p>
                    <p class ="error"><%=(deletestockErr != null ? deletestockErr : "")%></p>
                    <p class ="error"><%=(deletedescriptionErr != null ? deletedescriptionErr : "")%></p>
   
                    <table>

                        <tr>
                            <td>Device ID</td>
                            <td><input type="text" value="${device.deviceID}" name="DeviceID"></td>
                        </tr>
                        <tr>
                            <td>Device Name</td>
                            <td><input type="text" value="${device.deviceName}" name="DeviceName"></td>
                        </tr>
                        <tr>
                            <td>Device Type</td>
                            <td><input type="text" value="${device.type}" name="DeviceType">
                            </td>
                        </tr>
                        <tr>
                            <td>Price ($):</td>
                            <td><input type="text" value="${device.cost}" name="DeviceCost">
                            </td>
                        </tr>
                        <tr>
                            <td>Stock Quantity:	</td>
                            <td><input type="text" value="${device.stockQuantity}" name="DeviceStock">
                            </td>
                        </tr>
                        <tr>
                            <td>Description</td>
                            <td><input type="text" value="${device.description}" name="DeviceDescription">
                        </tr>

                    </table>

                    <div>
                        <input class ="button3" type="submit" value="Delete"</a>
                    </div>
                </form>
            </div>
        </div>

    </body>

</html>