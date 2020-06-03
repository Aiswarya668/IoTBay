<%-- 
    Document   : supplierList
    Created on : 31/05/2020, 1:09:45 PM
    Author     : Anastasia
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="uts.isd.model.Supplier"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.*"%>
<%@page import="java.sql.Connection"%>
<%@page import="uts.isd.model.iotbay.dao.*"%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>  
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/webpage.css">
        <title>Supplier Information Management</title>

    <img src="images/Logo.png" alt="LOGO" style="width:20%; height:10%" class="left"/>
    <p class="right"> <a class="button21" href="addSupplier.jsp">Add Supplier</a> </p>
    <p class="right"> <a class="button21" href="updateSupplier.jsp">Update Supplier</a> </p>
    <p class="right"> <a class="button21" href="removeSupplier.jsp">Remove Supplier</a> </p>
    <div class="maincolumn2">
        <div class="card">

            </head>
            <body>

                <%
                    Supplier supplier = (Supplier) session.getAttribute("show");
                %>

                <h1>Supplier Information Management</h1>
                <form method="post" method="get">

                    <form>
                        Search supplier: <input type="text" id="inputContactName" onkeyup="myFunction()" placeholder="Contact Name" title="Type in a contact name">
                    <input type="text" id="inputCompany" onkeyup="myFunction()" placeholder="Company" title="Type in a company">
                    </form>
                <table border="1" id="supplierTable">
                    <thead>
                        <tr>
                            <th>Contact Name</th>
                            <th>Company</th>
                            <th>Email</th>
                            <th>Address</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${show}" var="show">
                            <tr>
                                <td>${show.contactName}</td>
                                <td>${show.supplierName}</td>
                                <td>${show.supplierEmail}</td>
                                <td>${show.supplierAddress}</td>
                                <td>${show.active ? "Activated" : "Deactivated"}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <script>
                        function myFunction() {
                            var input, input2, filter, filter2, table, tr, tdContactName, tdCompany, i, txtValue;
                            input = document.getElementById("inputContactName");
                            filter = input.value.toUpperCase();
                            input2 = document.getElementById("inputCompany");
                            filter2 = input2.value.toUpperCase();
                            table = document.getElementById("supplierTable");
                            tr = table.getElementsByTagName("tr");
                            for (i = 0; i < tr.length; i++) {
                                tdContactName = tr[i].getElementsByTagName("td")[0];
                                tdCompany = tr[i].getElementsByTagName("td")[1];
                                if (tdContactName && tdCompany) {
                                    txtValue = tdContactName.textContent || tdCompany.innerText;
                                    txtValue2 = tdCompany.textContent || tdType.innerText;
                                    if (txtValue.toUpperCase().indexOf(filter) > -1 && txtValue2.toUpperCase().indexOf(filter2) > -1) {
                                        tr[i].style.display = "";
                                    } else {
                                        tr[i].style.display = "none";
                                    }
                                }
                            }
                        }
                    </script>
            </body>
        </div>
    </div>
</html>

