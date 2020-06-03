<%-- 
    Document   : searchResultSupplier
    Created on : 02/06/2020, 3:23:19 PM
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
            Supplier s = (Supplier)request.getAttribute("supplier");
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
                    <h1>Supplier Search Result</h1>
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

            </div>
        </div>
    </body>
</html>
