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
            String contactNameErr = (String) session.getAttribute("contactNameErr");
            String supplierNameErr = (String) session.getAttribute("supplierNameErr");
            String supplierEmailErr = (String) session.getAttribute("supplierEmailErr");
            String supplierAddressErr = (String) session.getAttribute("supplierAddressErr");
            String deleteContactNameErr = (String) session.getAttribute("deleteContactNameErr");
            String deleteSupplierNameErr = (String) session.getAttribute("deleteSupplierNameErr");
            String deleteSupplierEmailErr = (String) session.getAttribute("deleteSupplierEmailErr");
            String deleteSupplierAddressErr = (String) session.getAttribute("deleteSupplierAddressErr");
            String exceptionErr = (String) session.getAttribute("exceptionErr");
        %>

            <img src="images/Logo.png" alt="LOGO" style="width:20%; height:10%" class="left"/>
        <div class="maincolumn2">
            <div class="card">
                <h1>Add New Supplier </span> </h1>
                <form method="get" action="AddSupplierServlet">
                    <p class ="error"><%=(deleteContactNameErr != null ? deleteContactNameErr : "")%></p>
                    <p class ="error"><%=(deleteSupplierNameErr != null ? deleteSupplierNameErr : "")%></p>
                    <p class ="error"><%=(deleteSupplierEmailErr != null ? deleteSupplierEmailErr : "")%></p>
                    <p class ="error"><%=(deleteSupplierAddressErr != null ? deleteSupplierAddressErr : "")%></p>

                    <table>
                        <tr><td>Contact Name:</td><td><input type="text" placeholder="<%=(contactNameErr != null ? contactNameErr :"Enter contact name")%>" name="contactName"></td></tr>
                        <tr><td>Company:</td><td><input type="text" placeholder="<%=(supplierNameErr != null ? supplierNameErr :"Enter supplier name")%>" name="supplierName"></td></tr>
                        <tr><td>Email:</td><td><input type="text" placeholder="<%=(supplierEmailErr != null ? supplierEmailErr :"Enter supplier email")%>" name="supplierEmail"></td></tr>
                        <tr><td>Address:</td><td><input type="text" placeholder="<%=(supplierAddressErr != null ? supplierAddressErr :"Enter supplier address")%>" name="supplierAddress"></td></tr>
                        <tr><td>Activated:</td><td><input type="checkbox" name="active"></td></tr> 
                    </table>
                    <div>
                        <input class ="button4" type="submit" value="Add">
                        <a class ="button3" href="SupplierListServlet">Cancel</a>
                        <p class ="error"><%=(exceptionErr != null ? exceptionErr : "")%>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
