<%-- 
    Document   : supplierInformationManagement
    Created on : 31/05/2020, 12:29:35 PM
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
        <title>Add Supplier</title>
    </head>
    <%
            String contactName = (String) session.getAttribute("contactName");
            String supplierName = (String) session.getAttribute("supplierName");
            String supplierEmail = (String) session.getAttribute("supplierEmail");
            String supplierAddress = (String) session.getAttribute("supplierAddress");
            String active = (String) session.getAttribute("active");
        %>
            <img src="images/Logo.png" alt="LOGO" style="width:20%; height:10%" class="left"/>
        <div class="maincolumn2">
            <div class="card">
                <h1>Add New Supplier </span> </h1>
                <form method="get" action="AddSupplierServlet">
                    <table>
                        <tr><td>Contact Name:</td><td><input type="text" placeholder="Enter contact name" name="contactName"></td></tr>
                        <tr><td>Company:</td><td><input type="text" placeholder="Enter supplier name" name="supplierName"></td></tr>
                        <tr><td>Email:</td><td><input type="text" placeholder="Enter supplier email" name="supplierEmail"></td></tr>
                        <tr><td>Address:</td><td><input type="text" placeholder="Enter supplier address" name="supplierAddress"></td></tr>
                        <tr><td>Boolean:</td><td><input type="text" placeholder="Enter active status" name="active"></td></tr> 
                    </table>
                    <div>
                        <input class ="button4" type="submit" value="Add Supplier">
                        <a class ="button3" href="SupplierListServlet">Cancel</a>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
