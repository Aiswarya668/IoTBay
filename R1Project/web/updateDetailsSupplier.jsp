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
        %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/webpage.css">
        <title>Supplier Search Result</title>
    </head>

    <body>
        <img src="images/Logo.png" alt="LOGO" style="width:20%; height:10%" class="left"/>
        
        <p class="right"> <a  class="button21" href="SupplierListServlet">Supplier List</a> </p>
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
                                <td><% out.println(s.isActive()); %></td>
                            </tr>
                    </tbody>
                </table>
                    <h1>Supplier's New Details</h1>
                    
                    <table>
                        <tr><td>Contact Name:</td><td><input type="text" placeholder="Enter contact name" name="contactName"></td></tr>
                        <tr><td>Company:</td><td><input type="text" placeholder="Enter supplier name" name="supplierName"></td></tr>
                        <tr><td>Email:</td><td><input type="text" placeholder="Enter supplier email" name="supplierEmail"></td></tr>
                        <tr><td>Address:</td><td><input type="text" placeholder="Enter supplier address" name="supplierAddress"></td></tr>
                        <tr><td>Boolean:</td><td><input type="text" placeholder="Enter active status" name="active"></td></tr> 
                    </table>
                    <div>
                        <input class ="button4" type="submit" value="Update">
                        <a class ="button3" href="SupplierListServlet">Cancel</a>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
