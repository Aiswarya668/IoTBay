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
            String exceptionSupplierErr = (String) session.getAttribute("exceptionSupplierErr");
            String creationConfirmation = (String) session.getAttribute("creationConfirmation");
            String supplierEmptyErr = (String) session.getAttribute("supplierEmptyErr");
            String formatErr = (String) session.getAttribute("formatErr");
        %>

            <img src="images/Logo.png" alt="LOGO" style="width:20%; height:10%" class="left"/>

        <div class="maincolumn2">
            <div class="card">
                <h1>Add New Supplier </h1>
                
                <p class ="success"><%= (creationConfirmation != null) ? creationConfirmation : ""%></p>
                <p class ="error"><%= (supplierEmptyErr != null) ? supplierEmptyErr : ""%></p>
                <p class ="error"><%= (formatErr != null) ? formatErr : ""%></p>
                <p class ="error"><%= (exceptionSupplierErr != null) ? exceptionSupplierErr : ""%></p>

                <form method="get" action="AddSupplierServlet">

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
                        
                            <%
                                  session.setAttribute("exceptionSupplierErr", "");
                                  session.setAttribute("creationConfirmation", "");
                                  session.setAttribute("supplierEmptyErr", "");
                                  session.setAttribute("formatErr", "");
                                %>
                    </div>
                    
                </form>
            </div>
        </div>
    </body>
</html>
