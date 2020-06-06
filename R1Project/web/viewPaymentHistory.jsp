<%-- 
    Document   : viewPaymentHistory
    Created on : 06/06/2020, 4:32:12 PM
    Author     : James
--%>

<%@page import="uts.isd.model.CustomerOrder"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="uts.isd.model.PaymentSnapshots"%>
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
        <title>Payment History</title>

    <img src="images/Logo.png" alt="LOGO" style="width:20%; height:10%" class="left"/>
    <p class="right"> <a class="button21" href="paymentDetails.jsp">Cancel</a> </p> 
    <p class="right"> <a class="button21" href="paymentDetails.jsp">Manage Payment Details</a> </p>
    
    <div class="maincolumn2">
        <div class="card">

            </head>
            <body>

                <%
                    PaymentSnapshots paymentHistory = (PaymentSnapshots) request.getAttribute("paymentHistory");
                    CustomerOrder order = (CustomerOrder) session.getAttribute("order");
                    String creationConfirmation = (String) request.getParameter("creationConfirmation");
                %>

                <h1>Supplier Information Management</h1>
                <span><%=creationConfirmation != null ? creationConfirmation : ""%></span>
                <form>
                    Search Payment History: <input type="text" id="inputPaymentID" onkeyup="myFunction()" placeholder="E.g. PaymentID: 132" title="Type in a PaymentID">
                    <input type="text" id="inputDate" onkeyup="myFunction()" placeholder="E.g. Date: YYYY-MM-DD" title="Type in a Date">
                </form>
                
                <%
                    if (paymentHistory != null) {
                %>
                <form method="post" method="get">
                    <table border="1" id="paymentsTable">
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
                            <c:forEach items="${paymentHistory}" var="paymentHistory" varStatus="loop">
                                <tr>
                                    <td>${paymentHistory.contactName}</td>
                                    <td>${paymentHistory.supplierName}</td>
                                    <td>${Order.supplierEmail}</td>
                                    <td>${Order[loop.index].paymentID}</td>
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
                </form>
                <%
                    } else {
                %>
                
                <p>
                    You do not have any payments right now.
                </p>
                
                <%
                    }
                %>
            </body>
        </div>
        <div class="footer"></div>
    </div>
</html>
