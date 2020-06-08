<%-- 
    Document   : orderPayment
    Created on : 06/06/2020, 4:32:49 PM
    Author     : James
--%>

<%@page import="uts.isd.model.CustomerOrder"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="uts.isd.model.PaymentDetails"%>
<%@page import="java.sql.Connection"%>
<%@page import="uts.isd.model.Device"%>
<%@page import="java.util.Date"%>
<%@page import="uts.isd.model.iotbay.dao.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/webpage.css">

        <title>Manage Payment Details</title>
    </head>
        <%    
            String paymentDetailsEmptyErr = (String) session.getAttribute("paymentDetailsEmptyErr");
            String updateSucess = (String) session.getAttribute("updateSucess");
            String methodFieldErr = (String) session.getAttribute("methodFieldErr");
            String cardNumberFieldErr = (String) session.getAttribute("cardNumberFieldErr");
            String cardCodeFieldErr = (String) session.getAttribute("cardCodeFieldErr");
            String expiryDateFieldErr = (String) session.getAttribute("expiryDateFieldErr");
            String exceptionErr = (String) session.getAttribute("exceptionErr");
            PaymentDetails paymentDetail = (PaymentDetails) request.getAttribute("paymentDetails");
            CustomerOrder order = (CustomerOrder) session.getAttribute("order");
        %>
        
    <body>
        <img src="images/Logo.png" alt="LOGO" style="width:20%; height:10%" class="left"/>
         <p class="right"> <a class="button21" href="index.jsp">Home</a> </p>
         <p class="right"> <a class="button21" href="ViewDeviceServletUsers">Back to Browse</a> </p>
        <div class="maincolumn2">
            <div class="card">
                <h1>Your Order Details </h1>
                
                <table id="orderDetails">
                    <thead>
                        <tr>
                            <th>Your Email</th>
                            <th>Device</th>
                            <th>Quantity</th>
                            <th>Date Ordered</th>
                            <th>Cost of Order</th>
                            <th>Supplier Email</th>
                            <th>Shipping Cost</th>
                            <th>Shipping Type</th>
                            <th>Order Status</th>
                            <th>Street Address</th>
                            <th>Unit Number</th>
                            <th>City</th>
                            <th>State</th>
                            <th>Postcode</th>
                            <th>Phone Number</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>${order.customerEmail}</td>
                            <td>${order.deviceID}</td>
                            <td>${order.quantity}</td>
                            <td>${order.dateOrdered}</td>
                            <td>${order.totalPrice}</td>
                            <td>${order.supplierEmail}</td>
                            <td>${order.shippingCost}</td>
                            <td>${order.shippingType}</td>
                            <td>${order.status}</td>
                            <td>${order.streetAddress}</td>
                            <td>${order.unitNumber}</td>
                            <td>${order.city}</td>
                            <td>${order.state}</td>
                            <td>${order.postalCode}</td>
                            <td>${order.phoneNumber}</td>
                        </tr>
                    </tbody>
                </table>
                        
                <h1>Your Payment Details </span> </h1>
                <%
                    if (paymentDetail == null) {
                %>
                <p>You do not have any saved payment details. You can make, save and use new payment details below.</p>
                <form method="post" method="get" action="PaymentDetailsAddServlet">
                <input type="hidden" name="isOrder" value="true" />
                <%
                    } else {
                %>
                <p class="success"><%=(updateSucess != null ? updateSucess : "")%></p>
                <table id="paymentDetails">
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
                            <td><%=paymentDetail.getCustomerEmail()%></td>
                            <td><%=paymentDetail.getMethodOfPayment()%></td>
                            <td><%=paymentDetail.getHashedCreditedCardNumber()%></td>
                            <td><%=paymentDetail.getCardSecurityCode()%></td>
                            <td><%=paymentDetail.getCardExpiryDate()%></td>
                        </tr>
                    </tbody>
                </table>
                <form method="post" method="get" action="PaymentDetailsUpdateServlet">
                <input type="hidden" name="isOrder" value="true" />
                <%
                    }
                %>
                    <h1>Manage Payment Details </h1>
                    <p class ="error"><%=(paymentDetailsEmptyErr != null ? paymentDetailsEmptyErr : "")%></p>
                    
                    
                    
                    <table>
                        <tr><td>Method of Payment:</td><td><input type="text" placeholder="<%=(methodFieldErr != null ? methodFieldErr :"Enter method of payment")%>" name="methodOfPayment"></td></tr  
                        <tr><td>Credit Card Number:</td><td><input type="text" placeholder="<%=(cardNumberFieldErr != null ? cardNumberFieldErr :"Enter credit card number")%>" name="hashedCreditedCardNumber"></td></tr>
                        <tr><td>Credit Card Security Code</td><td><input type="text" placeholder="<%=(cardCodeFieldErr != null ? cardCodeFieldErr :"Enter credit card security code")%>" name="cardSecurityCode"></td></tr>
                        <tr><td>Credit Card Expiry Date</td><td><input type="text" placeholder="<%=(expiryDateFieldErr != null ? expiryDateFieldErr :"Enter credit card expirty date")%>" name="cardExpiryDate"></td></tr>  
                    </table>
                    <div>
                    <br>
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
                    <a class="button4" href="CreateOrder?id=<%=order.getDeviceID()%>">Cancel</a>
                </form>
                <br>
                <%
                            if (paymentDetail != null) {
                %>
                
                <form method="post" method="get" action="CompletePaymentServlet">
                    <input type="hidden" name="methodOfPayment" value="${paymentDetail.methodOfPayment}" />
                    <input type="hidden" name="hashedCreditedCardNumber" value="${paymentDetail.hashedCreditedCardNumber}" />
                    <input type="hidden" name="cardSecurityCode" value="${paymentDetail.cardSecurityCode}" />
                    <input type="hidden" name="cardExpiryDate" value="${paymentDetail.cardExpiryDate}" />
                    <input class ="button4" type="submit" value="Purchase">
                </form>
                <%}%>  
            </div>
        </div>
    </body>
</html>