<%-- 
    Document   : updateDetailsSupplierConfirmation
    Created on : 02/06/2020, 8:08:11 PM
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
        <title>Updated Supplier</title>
    </head>

    <body>
        <img src="images/Logo.png" alt="LOGO" style="width:20%; height:10%" class="left"/>
        
        <p class="right"> <a  class="button21" href="SupplierListServlet">View Suppliers</a> </p>
        <div class="maincolumn2">
            <div class="card">

                <body>     
                    <h1>Supplier, <% out.println(s.getSupplierName()); %>, with point of contact, <% out.println(s.getContactName()); %>, was added to the database</h1>

            </div>
        </div>
    </body>
</html>