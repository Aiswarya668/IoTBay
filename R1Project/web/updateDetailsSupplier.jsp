<%-- 
    Document   : updateDetailsSupplier
    Created on : 02/06/2020, 6:26:42 PM
    Author     : Anastasia
--%>

<%@page import="java.sql.Connection"%>
<%@page import="uts.isd.model.Supplier"%>
<%@page import="java.util.Date"%>
<%@page import="uts.isd.model.iotbay.dao.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <%
            Supplier s = (Supplier)session.getAttribute("supplier");
             String contactNameErr = (String) session.getAttribute("contactNameErr");
            String supplierNameErr = (String) session.getAttribute("supplierNameErr");
            String supplierEmailErr = (String) session.getAttribute("supplierEmailErr");
            String supplierAddressErr = (String) session.getAttribute("supplierAddressErr");
            String exceptionSupplierErr = (String) session.getAttribute("exceptionSupplierErr");
            String creationConfirmation = (String) session.getAttribute("creationConfirmation");
            String supplierEmptyErr = (String) session.getAttribute("supplierEmptyErr");
            String formatErr = (String) session.getAttribute("formatErr");
        %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/webpage.css">
        <title>Supplier Search Result</title>
    </head>

    <body>
        <img src="images/Logo.png" alt="LOGO" style="width:20%; height:10%" class="left"/>
        
        <p class="right"> <a  class="button21" href="SupplierListServlet">View Suppliers</a> </p>
        <div class="maincolumn2">
            <div class="card">

                <body>     
                    <h1>Supplier to be Updated</h1>
                    
                    
                    <form method="get" action="UpdateDetailsSupplierServlet">
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
                            <tr>
                                <td><% out.println(s.getContactName()); %></td>
                                <td><% out.println(s.getSupplierName()); %></td>
                                <td><% out.println(s.getSupplierEmail()); %></td>
                                <td><% out.println(s.getSupplierAddress()); %></td>
                                <td><% out.println(s.isActive() ? "Activated" : "Deactivated"); %></td>
                            </tr>
                    </tbody>
                </table>
                    <h1>Supplier's New Details</h1>
                    
                <p class ="error"><%= (creationConfirmation != null) ? creationConfirmation : ""%></p>
                <p class ="error"><%= (supplierEmptyErr != null) ? supplierEmptyErr : ""%></p>
                <p class ="error"><%= (formatErr != null) ? formatErr : ""%></p>
                <p class ="error"><%= (exceptionSupplierErr != null) ? exceptionSupplierErr : ""%></p>
                    
                    <table>
                        <tr><td>Contact Name:</td><td><input type="text" placeholder="<%=(contactNameErr != null ? contactNameErr :"Enter contact name")%>" name="contactName"></td></tr>
                        <tr><td>Company:</td><td><input type="text" placeholder="<%=(supplierNameErr != null ? supplierNameErr :"Enter supplier name")%>" name="supplierName"></td></tr>
                        <tr><td>Email:</td><td><input type="text" placeholder="<%=(supplierEmailErr != null ? supplierEmailErr :"Enter supplier email")%>" name="supplierEmail"></td></tr>
                        <tr><td>Address:</td><td><input type="text" placeholder="<%=(supplierAddressErr != null ? supplierAddressErr :"Enter supplier address")%>" name="supplierAddress"></td></tr>
                        <tr><td>Activated:</td><td><input type="checkbox" name="active"></td></tr> 
                    </table>
                    <div>
                        <input class ="button4" type="submit" value="Update">
                        <a class ="button3" href="UpdateSupplierServlet">Cancel</a>
                        
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
