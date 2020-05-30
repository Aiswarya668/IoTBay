<%-- 
    Document   : main
    Created on : Apr 22, 2020, 3:57:58 PM
    Author     : aiswaryarajeev
--%>

<%@page import="uts.isd.model.Customer"%>
<%@page import="uts.isd.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/webpage.css">
        <title>MainPage</title>
    </head>
    <body>

        <%
            Customer customer = (Customer) session.getAttribute("customer");
        %>

        <img src="images/Logo.png" alt="LOGO" style="width:20%; height:10%" class="left"/>
        <p class="right"> <a class="button21" href="edit.jsp">Edit Profile</a> </p>
        <p class="right"> <a class="button21" href="userLogs.jsp">User Logs</a> </p>
        <p class="right"> <a  class="button21" href="logout.jsp">Logout</a> </p>

        <div class="maincolumn2">
            <div class="card">

                <h1>User Profile</h1>
                <table id="profiletable">
                    <tr>
                        <th>First Name</th> <td> ${customer.firstName} </td>
                    </tr>
                    <tr>
                        <th>Last Name</th> <td> ${customer.lastName} </td>
                    </tr>
                    <tr>
                        <th>Email</th> <td> ${customer.email} </td>
                    </tr>
                    <tr>
                        <th>Password</th> <td> ${customer.password} </td>
                    </tr>
                    <tr>
                        <th>Gender</th> <td> ${customer.gender} </td>
                    </tr>
                    <tr>
                        <th>Phone Number</th> <td> ${customer.phoneNumber} </td>
                    </tr>
                    <tr>
                        <th>Unit Number</th> <td> ${customer.unitNumber} </td>
                    </tr>
                    <tr>
                        <th>Street Address</th> <td> ${customer.streetAddress} </td>
                    </tr>
                    <tr>
                        <th>City</th> <td> ${customer.city} </td>
                    </tr>
                    <tr>
                        <th>State</th> <td> ${customer.state} </td>
                    </tr>
                    <tr>
                        <th>Postal Code</th> <td> ${customer.postcode} </td>
                    </tr>
                </table>

            </div>
        </div>

    </body>
</html>
