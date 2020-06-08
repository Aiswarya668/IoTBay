<%-- 
    Document   : paymentDetails
    Created on : 06/06/2020, 4:32:35 PM
    Author     : James
--%>

<%@page import="uts.isd.model.PaymentDetails"%>
<%@page import="java.sql.Connection"%>
<%@page import="uts.isd.model.Device"%>
<%@page import="java.util.Date"%>
<%@page import="uts.isd.model.iotbay.dao.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/webpage.css">

        <title>Manage Payment Details</title>
    </head>
    <%
        String paymentDetailsEmptyErr = (String) session.getAttribute("paymentDetailsEmptyErr");
        String methodFieldErr = (String) session.getAttribute("methodFieldErr");
        String cardNumberFieldErr = (String) session.getAttribute("cardNumberFieldErr");
        String cardCodeFieldErr = (String) session.getAttribute("cardCodeFieldErr");
        String expiryDateFieldErr = (String) session.getAttribute("expiryDateFieldErr");
        String exceptionErr = (String) session.getAttribute("exceptionErr");
        PaymentDetails paymentDetail = (PaymentDetails) request.getAttribute("paymentDetails");
    %>

    <body>
        <img src="images/Logo.png" alt="LOGO" style="width:20%; height:10%" class="left"/>
        <p class="right"> <a class="button21" href="main.jsp">Back to Main</a> </p>
        <div class="maincolumn2">
            <div class="card">
                <h1>Your Payment Details </h1>
                <%
                    if (paymentDetail == null) {
                %>
                <p>You do not have any saved payment details. You can make new payment details below.</p>
                <form method="post" method="get" action="PaymentDetailsAddServlet">
                    <input type="hidden" name="isOrder" value="false" />
                    <%
                    } else {
                    %>
                    <table id="paymentDetails" align="center">
                        <thead>
                            <tr>
                                <th>Email</th>
                                <th>Method of Payment</th>
                                <th>Credit Card Number</th>
                                <th>Credit Card Security Code</th>
                                <th>Credit Card Expiry Date</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><%= paymentDetail.getCustomerEmail() %></td>
                                <td><%= paymentDetail.getMethodOfPayment() %></td>
                                <td><%= paymentDetail.getHashedCreditedCardNumber() %></td>
                                <td><%= paymentDetail.getCardSecurityCode() %></td>
                                <td><%= paymentDetail.getCardExpiryDate() %></td>
                            </tr>
                        </tbody>
                    </table>
                    <form method="post" method="get" action="PaymentDetailsUpdateServlet">
                        <input type="hidden" name="isOrder" value="false" />
                        <%
                            }
                        %>
                        <h1>Manage Payment Details </h1>
                        <p class ="error"><%=(paymentDetailsEmptyErr != null ? paymentDetailsEmptyErr : "")%></p>



                        <table>
                            <tr><td>Method of Payment:</td><td><input type="text" placeholder="<%=(methodFieldErr != null ? methodFieldErr : "Enter method of payment")%>" name="methodOfPayment"></td></tr  
                            <tr><td>Credit Card Number:</td><td><input type="text" placeholder="<%=(cardNumberFieldErr != null ? cardNumberFieldErr : "Enter credit card number")%>" name="hashedCreditedCardNumber"></td></tr>
                            <tr><td>Credit Card Security Code</td><td><input type="text" placeholder="<%=(cardCodeFieldErr != null ? cardCodeFieldErr : "Enter credit card security code")%>" name="cardSecurityCode"></td></tr>
                            <tr><td>Credit Card Expiry Date</td><td><input type="text" placeholder="<%=(expiryDateFieldErr != null ? expiryDateFieldErr : "Enter credit card expirty date")%>" name="cardExpiryDate"></td></tr>  
                        </table>
                        <br>
                        <div>
                            <%
                                if (paymentDetail == null) {
                            %>
                            <input class ="button4" type="submit" value="Add Payment Details">
                            <%
                            } else {
                            %>
                            <input class ="button4" type="submit" value="Update Payment Details">
                            <% session.setAttribute("paymentDetail",paymentDetail); %>
                            <input class="button3" type="submit" value="Delete Payment Details" formaction="PaymentDetailsDeleteServlet">
                            <%
                                }
                            %>

                            <p class ="error"><%=(exceptionErr != null ? exceptionErr : "")%></p>
                        </div>
                    </form>
                    <form>
                        <a class ="button4" href="main.jsp">Cancel</a>
                    </form>  
            </div>
        </div>
    </body>
</html>