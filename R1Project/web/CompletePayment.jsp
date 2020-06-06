<%-- 
    Document   : CompletePayment
    Created on : 07/06/2020, 3:02:09 AM
    Author     : James
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/webpage.css">
        <title>Payment Completed</title>
    </head>
    <%
        int paymentID = (int) request.getAttribute("paymentID");
    %>
    <body>

        <img src="images/Logo.png" alt="LOGO" style="width:20%; height:10%" class="left"/>
        <div class="maincolumn2">
            <div class="card">
     
                <h1>Payment has been successfully completed.</h1>
                <form method="get" action="">
                    <input type="hidden" name="paymentID" value="${paymentID}" />
                    <input class ="button4" type="submit" value="Finish Order">
                </form>
            </div>
        </div>
    </body>
</html>
