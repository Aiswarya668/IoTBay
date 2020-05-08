<%-- 
    Document   : main
    Created on : 26/04/2020, 6:52:50 PM
    Author     : seant
--%>

<%@page import="isd.iotbay.model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/iotbay.css">
        <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
        <title>Main</title>
    </head>
    <body>
        <%
            Customer customer = (Customer)session.getAttribute("customer");
        %>
        <div class="header">
            <jsp:include page="header.jsp" flush="true"/>
            <div class="navbar">
                <a href="logout.jsp">Logout</a>
                <a href="welcome.jsp" style="float: left"><b>&lt;</b> Back to welcome</a>
            </div>
        </div>
        <div class="body">
            <h2>Main</h2>
            <div class="card">

                <table border="1">
                    <thead>
                        <tr>
                            <th>Key</th>
                            <th>Value</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>User ID</td>
                            <td>${customer.userId}</td>
                        </tr>
                        <tr>
                            <td>First name</td>
                            <td>${customer.fname}</td>
                        </tr>
                        <tr>
                            <td>Last name</td>
                            <td>${customer.lname}</td>
                        </tr>
                        <tr>
                            <td>Phone number</td>
                            <td>${customer.phone}</td>
                        </tr>
                        <tr>
                            <td>Email</td>
                            <td>${customer.email}</td>
                        </tr>
                        <tr>
                            <td>Password</td>
                            <td>${customer.password}</td>
                        </tr>
                        <tr>
                            <td>Gender</td>
                            <td>${customer.gender}</td>
                        </tr>
                        <tr>
                            <td>House/unit number</td>
                            <td>${customer.unitNo}</td>
                        </tr>
                        <tr>
                            <td>Street Address</td>
                            <td>${customer.address}</td>
                        </tr>
                        <tr>
                            <td>City</td>
                            <td>${customer.city}</td>
                        </tr>
                        <tr>
                            <td>State</td>
                            <td>${customer.state}</td>
                        </tr>
                        <tr>
                            <td>Postcode</td>
                            <td>${customer.postcode}</td>
                        </tr>
                        <tr>
                            <td>Login Status</td>
                            <td>${customer.loginStatus}</td>
                        </tr>
                        <tr>
                            <td>Register Date</td>
                            <td>${customer.registerDate}</td>
                        </tr>
                    </tbody>
                </table>

            </div>
        </div>
        <footer class="footer">
            Copyright © 2020 IoTBay. All rights reserved.<br><a id="topLink" href="./main.jsp#top">Back to Top</a>
        </footer>

    </body>
</html>
