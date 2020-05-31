<%-- 
    Document   : removeSupplier
    Created on : 31/05/2020, 1:50:10 PM
    Author     : Anastasia
--%>
<%@page import="uts.isd.model.Device"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/webpage.css">
        <title>Delete Page</title>
    </head>

    <body>
        <%
            String contactName = (String) session.getAttribute("contactName");
            String supplierName = (String) session.getAttribute("typeErr");

            Supplier supplier = (Supplier) session.getAttribute("supplier");
        %>
        
        <img src="images/Logo.png" alt="LOGO" style="width:20%; height:10%" class="left" />
        <p class="right"> <a class="button21" href="main.jsp">Main</a> </p>
        <p class="right"> <a class="button21" href="logout.jsp">Logout</a> </p>
        <div class="maincolumn2">
            <div class="card">
                <h1>Delete device <span> <%= (updatedMsg != null) ? updatedMsg : ""%> </span></h1>
                <form method="post" method="get" action="RemoveSupplierServlet">
                    
                  <table>
                        
                         <tr>
                            <td>Contact Name</td>
                            <td><input type="text" value="${supplier.contactName}" name="contactName"></td>
                        </tr>
                        <tr>
                            <td>Company</td>
                            <td><input type="text" value="${supplier.supplierName}" name="supplierName"></td>
                        </tr>
                         
                    </table>
                       
                    <div>
                        <input class ="button3" type="submit" value="Delete"</a>
                    </div>
                </form>
            </div>
        </div>

    </body>

</html>