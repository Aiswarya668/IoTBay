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
    <p class="right"> <a class="button21" href="main.jsp">Main</a> </p> 
    
    <div class="deviceCatcolumn">
        <div class="deviceCatcard">

            </head>
            <body>

                <%
                    ArrayList<PaymentSnapshots> paymentHistory = (ArrayList<PaymentSnapshots>) request.getAttribute("paymentHistory");
                    ArrayList<CustomerOrder> order = (ArrayList<CustomerOrder>) request.getAttribute("order");
                %>

                <h1>Payment History</h1>
                <form>
                    Search Payment History: <input id="inputPaymentID" onkeyup="myFunction()" placeholder="E.g. PaymentID: 132" title="Type in a PaymentID"> <input id="inputDate" onkeyup="myFunction()" placeholder="E.g. Date: YYYY-MM-DD" title="Type in a Date">
                </form>
                
                <%
                    if (paymentHistory != null) {
                %>
                <form method="post" method="get">
                    <table id="paymentsTable">
                        <thead>
                            <tr>
                                <th>Payment ID</th>
                                <th>Order ID</th>
                                <th>Device ID</th>
                                <th>Quantity</th>
                                <th>Method of Payment</th>
                                <th>Card Number</th>
                                <th>Card Security Code</th>
                                <th>Card Expiry Date</th>
                                <th>Date Paid</th>
                                <th>Amount Paid</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (int i = 0; i < paymentHistory.size();i++ ) {%>
                                <tr>
                                    <td><%= paymentHistory.get(i).getPaymentID()%></td>
                                    <td><%= order.get(i).getOrderID() %></td>
                                    <td><%= order.get(i).getDeviceID() %></td>
                                    <td><%= order.get(i).getQuantity() %></td>
                                    <td><%= paymentHistory.get(i).getMethodOfPayment() %></td>
                                    <td><%= paymentHistory.get(i).getHashedCreditedCardNumber() %></td>
                                    <td><%= paymentHistory.get(i).getCardSecurityCode() %></td>
                                    <td><%= paymentHistory.get(i).getCardExpiryDate() %></td>
                                    <td><%= paymentHistory.get(i).getPayDate() %></td>
                                    <td><%= paymentHistory.get(i).getAmountPaid() %></td>
                                </tr>
                            <%}%>
                        </tbody>
                    </table>
                    <script>
                        function myFunction() {
                            var input, input2, filter, filter2, table, tr, tdPaymentID, tdPayDate, i, txtValue;
                            input = document.getElementById("inputPaymentID");
                            filter = input.value.toUpperCase();
                            input2 = document.getElementById("inputDate");
                            filter2 = input2.value.toUpperCase();
                            table = document.getElementById("paymentsTable");
                            tr = table.getElementsByTagName("tr");
                            for (i = 0; i < tr.length; i++) {
                                tdPaymentID = tr[i].getElementsByTagName("td")[0];
                                tdPayDate = tr[i].getElementsByTagName("td")[8];
                                if (tdPaymentID && tdPayDate) {
                                    txtValue = tdPaymentID.textContent || tdPaymentID.innerText;
                                    txtValue2 = tdPayDate.textContent || tdPayDate.innerText;
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
