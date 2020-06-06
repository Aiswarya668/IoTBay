<%-- 
    Document   : removeSupplier
    Created on : 31/05/2020, 1:50:10 PM
    Author     : Anastasia
--%>
<%@page import="uts.isd.model.Supplier"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/webpage.css">
        <title>Remove Supplier</title>
    </head>

    <body>
        <%
            String contactNameErr = (String) session.getAttribute("contactNameErr");
            String supplierNameErr = (String) session.getAttribute("supplierNameErr");
            String exceptionSupplierErr = (String) session.getAttribute("exceptionSupplierErr");
            String creationConfirmation = (String) session.getAttribute("creationConfirmation");
            String supplierEmptyErr = (String) session.getAttribute("supplierEmptyErr");
            String formatErr = (String) session.getAttribute("formatErr");
        %>

        
        <img src="images/Logo.png" alt="LOGO" style="width:20%; height:10%" class="left" />

        <div class="maincolumn2">
            <div class="card">
            <h1>Supplier to be Removed </h1>
            
                <p class ="error"><%= (supplierEmptyErr != null) ? supplierEmptyErr : ""%></p>
                <p class ="error"><%= (formatErr != null) ? formatErr : ""%></p>
                <p class ="error"><%= (exceptionSupplierErr != null) ? exceptionSupplierErr : ""%></p>
                <p class ="error"><%= (creationConfirmation != null) ? creationConfirmation : ""%></p>
                <form method="get" action="RemoveSupplierServlet">
                    
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
                        <input class ="button4" type="submit" value="Delete">
                        <a class ="button3" href="SupplierListServlet">Cancel</a>
                        <%
                                  session.setAttribute("supplierEmptyErr", "");
                                  session.setAttribute("formatErr", "");
                                  session.setAttribute("exceptionSupplierErr", "");
                                  session.setAttribute("creationConfirmation", "");
                                %>
                    </div>
                </form>
            </div>
        </div>

    </body>

</html>