<%-- 
    Document   : addedSupplier
    Created on : 01/06/2020, 10:53:40 PM
    Author     : Anastasia
--%>

<%@page import="java.sql.Connection"%>
<%@page import="uts.isd.model.Supplier"%>
<%@page import="java.util.Date"%>
<%@page import="uts.isd.model.iotbay.dao.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/webpage.css">
        <title>Added Supplier</title>
    </head>

    <body>
        <img src="images/Logo.png" alt="LOGO" style="width:20%; height:10%" class="left"/>
        
        <p class="right"> <a  class="button21" href="SupplierListServlet">View Suppliers</a> </p>
        <div class="maincolumn2">
            <div class="card">

                <body>     
                    <h1>The supplier, ${show.supplierName}, with point of contact, ${show.contactName}, was successfully added</h1>

            </div>
        </div>
    </body>
</html>