<%-- 
    Document   : browseCatalogue
    Created on : 21/05/2020, 8:30:58 PM
    Author     : aiswarya.r
--%>

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
    <p class="right"> <a class="button21" href="index.jsp">Home</a> </p>
    <div class="maincolumn3">
        <div class="card1">

            </head>
            <body>
                <h1>Device Catalogue</h1>
                <form method="post" method="get">

                    <form>
                        Search device: <input type="text" name="Name" placeholder="Name, Type">
                        <input type ="submit" value="Search">
                    </form>

                    <table class="device Table">
                        <tr>
                            <td>Device ID</td>
                            <td>Device Name</td>
                            <td>Type</td>
                            <td>Cost</td>
                            <td>Stock</td>
                            <td>Description</td>
                            <td></td>
                        </tr>

                        <c:forEach items="${display}" var="display">
                            <tr class="device tr">
                                <td>${display.deviceID }</td>
                                <td>${display.deviceName }</td>
                                <td>${display.type }</td>
                                <td>${display.cost }</td>
                                <td>${display.stockQuantity }</td>
                                <td>${display.description }</td>
                                <td><p class="right"> <a class="button1" href="main.jsp ">Buy</a> </p></td>
                            </tr>
                        </c:forEach>
                    </table>

                </form>
            </body>
        </div>
        <div class="footer">

        </div>
    </div>

</html>
