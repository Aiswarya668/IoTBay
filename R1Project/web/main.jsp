<%-- 
    Document   : main
    Created on : Apr 22, 2020, 3:57:58 PM
    Author     : aiswaryarajeev
--%>

<%@page import="uts.isd.model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/webpage.css">
        <title>Main Page</title>
    </head>
    <body>

        <%
            Customer customer = (Customer) session.getAttribute("customer");
            Staff staff = (Staff) session.getAttribute("staff");
            // common attributes amongst staff and customer
            String firstName = "";
            String lastName = ""; 
            String email = "";
            String password = "";
            String phoneNumber = "";
            String unitNumber = "";
            String streetAddress = "";
            String city = "";
            String state = "";            
            String postCode = "";
            
            // customer specific
            String gender = "";
            
            // staff specific
            String manager = "";
            String contractType = "";
            String payHr = "";
            
            if (customer != null) {
                firstName = customer.getFirstName();
                lastName = customer.getLastName();
                email = customer.getEmail();
                password = customer.getPassword();
                gender = customer.getGender();
                phoneNumber = customer.getPhoneNumber();
                unitNumber = customer.getUnitNumber();
                streetAddress = customer.getStreetAddress();
                city = customer.getCity();
                state = customer.getState();
                postCode = customer.getPostcode();
            }
            else if (staff != null) {
                firstName = staff.getFirstName();
                lastName = staff.getLastName();
                email = staff.getEmail();
                password = staff.getPassword();
//                gender = staff();
                phoneNumber = staff.getPhoneNumber();
                unitNumber = staff.getUnitNumber();
                streetAddress = staff.getStreetAddress();
                city = staff.getCity();
                state = staff.getState();
                postCode = staff.getPostcode();
                manager = staff.getManager();
                contractType = staff.getContractType();
                payHr = Integer.toString(staff.getHourlyPay());
            }
        %>

        <img src="images/Logo.png" alt="LOGO" style="width:20%; height:10%" class="left"/>
        <p class="right"> <a class="button21" href="edit.jsp">Edit Profile</a> </p>
        <p class="right"> <a class="button21" href="UserLogsServlet">User Logs</a> </p>
        <p class="right"> <a  class="button21" href="logout.jsp">Logout</a> </p>

        <div class="maincolumn2">
            <div class="card">

                <h1>User Profile</h1>
                <table id="profiletable">
                    <tr>
                        <th>First Name</th> <td> <%= firstName %> </td>
                    </tr>
                    <tr>
                        <th>Last Name</th> <td> <%= lastName %> </td>
                    </tr>
                    <tr>
                        <th>Email</th> <td> <%= email %> </td>
                    </tr>
                    <tr>
                        <th>Password</th> <td> <%= password %> </td>
                    </tr>
                    <tr>
                        <th>Phone Number</th> <td> <%= phoneNumber %> </td>
                    </tr>
                    <tr>
                        <th>Unit Number</th> <td> <%= unitNumber %> </td>
                    </tr>
                    <tr>
                        <th>Street Address</th> <td> <%= streetAddress %> </td>
                    </tr>
                    <tr>
                        <th>City</th> <td> <%= city %></td>
                    </tr>
                    <tr>
                        <th>State</th> <td> <%= state %> </td>
                    </tr>
                    <tr>
                        <th>Postal Code</th> <td> <%= postCode %></td>
                    </tr>
                    <% if (customer != null) { %>
                    <tr>
                        <th>Gender</th> <td> <%= gender %></td>
                    </tr>
                    <% } else if (staff != null) { %>
                    <tr>
                        <th>Manager</th> <td> <%= manager %></td>
                    </tr>
                    <tr>
                        <th>Contract Type</th> <td> <%= contractType %> </td>
                    </tr>
                    <tr>
                        <th>Pay Per Hour</th> <td> <%= payHr %> </td>
                    </tr>
                    <% } %>
                </table>

            </div>
        </div>

    </body>
</html>
