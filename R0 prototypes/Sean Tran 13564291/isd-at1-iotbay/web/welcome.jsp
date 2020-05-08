<%-- 
    Document   : welcome
    Created on : 26/04/2020, 6:52:45 PM
    Author     : seant
--%>

<%@page import="java.util.Date"%>
<%@page import="isd.iotbay.model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/iotbay.css">
        <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
        <title>Welcome</title>
    </head>
    <body>
        <%
            Customer customer = (Customer)session.getAttribute("customer");
            String fname = "";
            String lname = "";
            String phone = "";
            String email = "";
            String password = "";
            String unitNo = "";
            String address = "";
            String city = "";
            String state = "";
            String postcode = "";
            String gender = "";
            if (customer == null) {
                fname = request.getParameter("fname");
                lname = request.getParameter("lname");
                phone = request.getParameter("phone");
                email = request.getParameter("email");
                password = request.getParameter("password");
                unitNo = request.getParameter("unitNo");
                address = request.getParameter("address");
                city = request.getParameter("city");
                state = request.getParameter("state");
                postcode = request.getParameter("postcode");
                gender = request.getParameter("gender");
                customer = new Customer(
                        "1",
                        fname,
                        lname,
                        phone,
                        email,
                        password,
                        unitNo,
                        address,
                        city,
                        state,
                        postcode,
                        true,
                        new Date(),
                        gender
                );
                session.setAttribute("customer", customer);
            } else {
                fname = customer.getFname();
                lname = customer.getLname();
                phone = customer.getPhone();
                email = customer.getEmail();
                password = customer.getPassword();
                unitNo = customer.getUnitNo();
                address = customer.getAddress();
                city = customer.getCity();
                state = customer.getState();
                postcode = customer.getPostcode();
                gender = customer.getGender();
            }
        %>
        <div class="header">
            <jsp:include page="header.jsp" flush="true"/>
            <div class="navbar">
                <a href="./logout.jsp">Logout</a>
                <a href="./main.jsp">Main</a>
            </div>
        </div>
        <div class="body">
            <h2>Welcome <%= fname%> <%= lname%>!</h2>
            <div class="card">
                <p>Your email is <b><i><%= email%></i></b>.</p>
                <p>Your password is <b><i><%= password%></i></b>.</p>
                <p>Your phone number is <b><i><%= phone%></i></b>.</p>
                <p>Your address is <b><i><%= unitNo%> <%= address%>, <%= city%> <%= state%> <%= postcode%></i></b>.</p>
                <p>Your gender is <b><i><%= gender%></i></b>.</p>
            </div>
        </div>
        <footer class="footer">
            Copyright © 2020 IoTBay. All rights reserved.<br><a id="topLink" href="./welcome.jsp#top">Back to Top</a>
        </footer>
    </body>
</html>
