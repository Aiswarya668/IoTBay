<%-- 
    Document   : updateSupplier
    Created on : 02/06/2020, 5:15:30 PM
    Author     : Anastasia
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.*"%>
<%@page import="java.sql.Connection"%>
<%@page import="uts.isd.model.Supplier"%>
<%@page import="uts.isd.model.iotbay.dao.*"%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>  
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/webpage.css">
        <title>Update Supplier</title>

    <img src="images/Logo.png" alt="LOGO" style="width:20%; height:10%" class="left"/>
    <p class="right"> <a  class="button21" href="SupplierListServlet">View Suppliers</a> </p>
    
    <div class="maincolumn2">
        <div class="card">

            </head>
            <%
            String supplierEmptyErr = (String) session.getAttribute("supplierEmptyErr");
            String formatErr = (String) session.getAttribute("formatErr");
            String exceptionSupplierErr = (String) session.getAttribute("exceptionSupplierErr");
            String contactNameErr = (String) session.getAttribute("contactNameErr");
            String supplierNameErr = (String) session.getAttribute("supplierNameErr");
        %>
            <body>

                <h1>Supplier to be Updated</h1>
                
                <p class ="error"><%= (supplierEmptyErr != null) ? supplierEmptyErr : ""%></p>
                <p class ="error"><%= (formatErr != null) ? formatErr : ""%></p>
                <p class ="error"><%= (exceptionSupplierErr != null) ? exceptionSupplierErr : ""%></p>

                <form method="get" action="UpdateSupplierServlet">
                    <table>
                        <tr>
                            <th>Contact Name</th>
                            <th>Company</th>
                        </tr>
                        <tr>
                            <td><input type="text" name="contactName" placeholder="<%=(contactNameErr != null ? contactNameErr :"Enter contact name")%>"></td>
                            <td><input type="text" name="supplierName" placeholder="<%=(supplierNameErr != null ? supplierNameErr :"Enter contact name")%>"></td>
                        </tr>
                    </table>
                    <div>
                        <input class ="button4" type="submit" value="Search">
                        <a class ="button3" href="SupplierListServlet">Cancel</a>
                        <%
                                  session.setAttribute("supplierEmptyErr", "");
                                  session.setAttribute("formatErr", "");
                                  session.setAttribute("exceptionSupplierErr", "");
                                %>
                    </div>
                </form>
            </body>
        </div>
    </div>
</html>
