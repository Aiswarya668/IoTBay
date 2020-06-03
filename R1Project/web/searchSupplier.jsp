<%-- 
    Document   : searchSupplier
    Created on : 31/05/2020, 12:45:42 PM
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
        <title>Search Supplier</title>

    <img src="images/Logo.png" alt="LOGO" style="width:20%; height:10%" class="left"/>
    <div class="maincolumn2">
        <div class="card">

            </head>
            <body>

                <%
                    //DBConnector dbConnector = new DBConnector();
                    //Connection conn = dbConnector.openConnection();
                    //DBSupplierInformationManager dbManager = new DBSupplierInformationManager(conn);
                    //dbManager.fetchSuppliers();

                %>

                <h1>Search Supplier</h1>

                <form method="get" action="SearchSupplierServlet">
                    <table>
                        <tr>
                            <td><input type="text" placeholder="Enter contact name" name="contactName"></td>
                            <td><input type="text" placeholder="Enter company name" name="supplierName"></td>
                        </tr>
                    </table>
                    <div>
                        <input class ="button4" type="submit" value="Search">
                        <a class ="button3" href="SupplierListServlet">Cancel</a>
                    </div>
                </form>
            </body>
        </div>
    </div>
</html>
