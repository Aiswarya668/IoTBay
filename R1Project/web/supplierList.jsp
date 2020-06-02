<%-- 
    Document   : supplierList
    Created on : 31/05/2020, 1:09:45 PM
    Author     : Anastasia
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="uts.isd.model.Supplier"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.*"%>
<%@page import="java.sql.Connection"%>
<%@page import="uts.isd.model.iotbay.dao.*"%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>  
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/webpage.css">
        <title>Supplier Information Management</title>

    <img src="images/Logo.png" alt="LOGO" style="width:20%; height:10%" class="left"/>
    <p class="right"> <a class="button21" href="addSupplier.jsp">Add Supplier</a> </p>
    <p class="right"> <a class="button21" href="AddSupplierServlet">Search Supplier</a> </p>
    <p class="right"> <a class="button21" href="AddSupplierServlet">Update Supplier</a> </p>
    <p class="right"> <a class="button21" href="removeSupplier.jsp">Remove Supplier</a> </p>
    <div class="maincolumn2">
        <div class="card">

            </head>
            <body>

                <%
                    Supplier supplier = (Supplier) session.getAttribute("show");
                %>

                <h1>Supplier Information Management</h1>
                <table border="1">
                    <thead>
                        <tr>
                            <th>Contact Name</th>
                            <th>Company</th>
                            <th>Email</th>
                            <th>Address</th>
                            <th>Active</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${show}" var="show">
                            <tr>
                                <td>${show.contactName}</td>
                                <td>${show.supplierName}</td>
                                <td>${show.supplierEmail}</td>
                                <td>${show.supplierAddress}</td>
                                <td>${show.active}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </body>
        </div>
    </div>
</html>

