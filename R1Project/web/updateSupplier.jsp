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
    <div class="maincolumn2">
        <div class="card">

            </head>
            <body>

                <h1>Search Supplier to be Updated</h1>

                <form method="get" action="UpdateSupplierServlet">
                    <table>
                        <tr>
                            <td><input type="text" placeholder="Enter contact name" name="contactName"></td>
                            <td><input type="text" placeholder="Enter company name" name="supplierName"></td>
                        </tr>
                    </table>
                    <div>
                        <input class ="button4" type="submit" value="Search">
                        <a class ="button3" href="UpdateSupplierServlet">Cancel</a>
                    </div>
                </form>
            </body>
        </div>
    </div>
</html>
